package finalproject;

import finalproject.database.*;
import finalproject.helpers.AlertHelper;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

import finalproject.helpers.Reference;
import finalproject.sorters.AllergySeverityNameComparator;
import finalproject.sorters.MedicationNameComparator;
import finalproject.sorters.PatientNameComparator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Class: FXMLPatientSummaryController
 *
 * @author Matthew Camp Version 1.0 Course: Advanced Programming Fall 2017
 * Written: , 2017 Controller class for the main JavaFX GUI handling patient
 * summary information
 */
public class FXMLPatientSummaryController extends BaseController<Patient> implements Observer {

    private AllergyDbManager allergyManager = new AllergyDbManager();
    private MedicationDbManager medicationManager = new MedicationDbManager();
    private VisitDbManager visitManager = new VisitDbManager();
    private PatientDbManager patientManager = new PatientDbManager();

    @FXML
    private ListView<Patient> listviewPatients;

    @FXML
    private Label lblPatientId;

    @FXML
    private TextField txtPatientName;

    @FXML
    private TextField txtPatientAddress;

    @FXML
    private TextField txtPatientEmail;

    @FXML
    private ComboBox<MaritalStatus> cboxPatientMaritalStatus;

    @FXML
    private TextField txtPatientPhoneAreaCode;

    @FXML
    private TextField txtPatientPhonePrefix;

    @FXML
    private TextField txtPatientPhoneLineNumber;

    @FXML
    private ListView<Allergy> listviewPatientAllergies;

    @FXML
    private ListView<Medication> listviewPatientMeds;

    @FXML
    private TextField txtPatientInsurance;

    @FXML
    private DatePicker dpickDateOfBirth;

	private <T> Stage showDialog(Dialogs dialog, Patient patient, T target) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource(dialog.toString())
			);

			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setScene(new Scene((AnchorPane) loader.load()));
			DatabaseManager<?> manager;
			BaseController controller;
			switch (dialog) {
				case Allergies:
					controller = loader.<FXMLEditAllergiesController>getController();
					manager = allergyManager;
					break;

				case Meds:
					controller = loader.<FXMLEditMedsController>getController();
					manager = medicationManager;
					break;

				case VisitList:
					controller = loader.<FXMLVisitListController>getController();
					manager = visitManager;
					break;

				case VisitModify:
					controller = loader.<FXMLPatientVisitController>getController();
					manager = visitManager;
					break;

				default:
					throw new IllegalArgumentException();
			}

			controller.initData(this, patient, target, manager);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			return stage;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

    private void populateCombos() {
        cboxPatientMaritalStatus.getItems().clear();
        cboxPatientMaritalStatus.getItems().addAll(MaritalStatus.values());
    }

    // TODO
    @FXML
    void btnViewVisits_OnAction(ActionEvent event) {
        showDialog(Dialogs.VisitList, patient, visitManager.getList(patient.getId()));
    }

    public ValidationStatus validateForm() {
        ValidationStatus status = new ValidationStatus();
        String name = "";
        String address = "";
        String phone = "";
        String email = "";
        String dob = "";
        String marriage = "";
        String insurance = "";

        if (txtPatientName.getText().isEmpty()) {
            status.addFieldError("Name");
        } else {
            name = txtPatientName.getText();
        }

        if (txtPatientAddress.getText().isEmpty()) {
            status.addFieldError("Address");
        } else {
            address = txtPatientAddress.getText();
        }

        if (txtPatientPhoneAreaCode.getText().isEmpty() || txtPatientPhonePrefix.getText().isEmpty() || txtPatientPhoneLineNumber.getText().isEmpty()) {
            status.addFieldError("Phone Number");
        } else {
            phone = "";
            if (Pattern.matches(Reference.PHONE_REGEX[0], txtPatientPhoneAreaCode.getText())) {
                phone = txtPatientPhoneAreaCode.getText();

                if (Pattern.matches(Reference.PHONE_REGEX[0], txtPatientPhonePrefix.getText())) {
                    phone += txtPatientPhonePrefix.getText();

                    if (Pattern.matches(Reference.PHONE_REGEX[1], txtPatientPhoneLineNumber.getText())) {
                        phone += txtPatientPhoneLineNumber.getText();
                    } else {
                        status.addFieldError("Phone Number");
                    }
                } else {
                    status.addFieldError("Phone Number");
                }
            } else {
                status.addFieldError("Phone Number");
            }
        }

        if (txtPatientEmail.getText().isEmpty()) {
            status.addFieldError("Email");
        } else {
            if (Pattern.matches(Reference.EMAIL_REGEX, txtPatientEmail.getText())) {
                email = txtPatientEmail.getText();
            } else {
                status.addFieldError("Email");
            }
        }

        if (dpickDateOfBirth.getValue() == null) {
            status.addFieldError("Date of Birth");
        } else {
            dob = dpickDateOfBirth.getValue().format(Reference.DATE_FORMAT);
        }

        if (cboxPatientMaritalStatus.getSelectionModel().isEmpty() || cboxPatientMaritalStatus.getSelectionModel().getSelectedItem() == null) {
            status.addFieldError("Marital Status");
        } else {
            marriage = cboxPatientMaritalStatus.getSelectionModel().getSelectedItem().toString();
        }

        if (txtPatientInsurance.getText().isEmpty()) {
            status.addFieldError("Insurance");
        } else {
            insurance = txtPatientInsurance.getText();
        }

        if (patient == null || patient.getId() == 0) {
            patient = new Patient(name, address, phone, email, dob, marriage, insurance);
        } else {
            patient.setName(name);
            patient.setAddress(address);
            patient.setPhoneNumber(phone);
            patient.setEmail(email);
            patient.setDob(dob);
            patient.setMaritalStatus(marriage);
            patient.setInsurance(insurance);
        }
        return status;
    }

    @Override
    public void saveToDatabase() {
        boolean result;
        if (patient.getId() == 0) {
            System.out.println("inserting into " + patient.getId()); // testing
            result = patientManager.insert(patient); // dbManager for this controller is null, if you wish to use it 
        } else {                                     // call the initData method in finalproject.java and addObserver to it in initialize
            System.out.println("updating"); // testing
            result = patientManager.update(patient);
        }

        if (!result) {
            AlertHelper.ShowWarning("Database Error", "Patient Table", "There was an error submitting your information to the database.");
        }
    }

    // comfirm patient
    @FXML
    protected void btnSave_OnAction(ActionEvent event) {
        ValidationStatus status = validateForm();
        if (status.getIsValid()) {
            saveToDatabase();
        } else {
            AlertHelper.ShowWarning("Patient Error", null, status.getErrors());
        }
    }

    @FXML
    void btnEditAllergy_OnAction(ActionEvent event) {
        Allergy allergy = listviewPatientAllergies.getSelectionModel().getSelectedItem();
        System.out.println(allergy); // testing
        if (patient == null || allergy == null) {
            AlertHelper.ShowWarning("Editing Error", null, "Please select a patient and allergy to edit.");
        } else {
            showDialog(Dialogs.Allergies, patient, allergy);
        }
    }

    @FXML
    void btnEditMedication_OnAction(ActionEvent event) {
        if (patient == null || patient.getId() == 0) {
            return; // alert dialog?
        }
        Patient patient = listviewPatients.getSelectionModel().getSelectedItem();
        Medication med = listviewPatientMeds.getSelectionModel().getSelectedItem();
        showDialog(Dialogs.Meds, patient, med);
    }

    @FXML
    void btnNewPatient_OnAction(ActionEvent event) {
        patient = null;
        listviewPatientAllergies.getItems().clear();
        listviewPatientMeds.getItems().clear();
        // clear everything
        lblPatientId.setText("");
        txtPatientName.clear();
        txtPatientAddress.clear();
        txtPatientPhoneAreaCode.clear();
        txtPatientPhonePrefix.clear();
        txtPatientPhoneLineNumber.clear();
        txtPatientEmail.clear();
        txtPatientInsurance.clear();
        cboxPatientMaritalStatus.getSelectionModel().clearSelection();
        dpickDateOfBirth.setValue(null);
    }

    @FXML
    void btnRemovePatient_OnAction(ActionEvent event) {
        if (patient == null) {
            return; // need logic for removing patient when none is selected.
        }
        patientManager.delete(patient.getId());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateData();

        patientManager.addObserver(this);
        allergyManager.addObserver(this);
        medicationManager.addObserver(this);
        visitManager.addObserver(this);
    }

    @Override
    public void populateData() {
        populateCombos();
        populatePatientList();
        populatePatientInfo(patient);
    }

    @Override
    public void reset() {

    }

    private void populatePatientList() {
        try {
            listviewPatients.getItems().clear();
            List<Patient> patients = patientManager.getList(-1);
            patients.sort(new PatientNameComparator());
            listviewPatients.getItems().addAll(patients);
        } catch (NullPointerException e) {
            AlertHelper.ShowError("An error occurred", "Error accessing PATIENT table", e);
        }
    }

    private void populatePatientInfo(Patient patient) {
        if (patient == null) {
            return;
        }

        txtPatientInsurance.setText(patient.getInsurance());
        txtPatientEmail.setText(patient.getEmail());
        txtPatientPhoneLineNumber.setText(patient.getPhoneNumber().substring(6));
        txtPatientPhonePrefix.setText(patient.getPhoneNumber().substring(3, 6));
        txtPatientPhoneAreaCode.setText(patient.getPhoneNumber().substring(0, 3));
        txtPatientAddress.setText(patient.getAddress());
        txtPatientName.setText(patient.getName());
        lblPatientId.setText(String.valueOf(patient.getId()));
        if (patient.getDob() != null || !patient.getDob().equals("")) {
            dpickDateOfBirth.setValue(LocalDate.parse(patient.getDob(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        }
        cboxPatientMaritalStatus.setValue(Enum.valueOf(MaritalStatus.class, patient.getMaritalStatus()));

        //MedicationDbManager medDatabase = new MedicationDbManager();
        //Collection<Medication> medList = medDatabase.getList(patient.getId());
        fillMeds(patient.getId());
        fillAllergies(patient.getId());
    }

    @FXML
    private void btnAddAllergy_OnAction(ActionEvent event) {
        if (patient == null || patient.getId() == 0) {
            return; // alert dialog?
        }
        showDialog(Dialogs.Allergies, patient, Reference.DEFAULT_ALLERGY);
    }

    @FXML
    private void btnAddMedication_OnAction(ActionEvent event) {
        if (patient == null || patient.getId() == 0) {
            return; // alert dialog?
        }
        showDialog(Dialogs.Meds, patient, Reference.DEFAULT_MEDICATION);
    }

    @FXML
    private void listviewPatients_OnMouseClicked(MouseEvent event) {
        patient = listviewPatients.getSelectionModel().getSelectedItem();
        populateData();
    }

    private void fillMeds(int patientId) {
        listviewPatientMeds.getItems().clear();
        List<Medication> list = (new MedicationDbManager()).getList(patientId);
        list.sort(new MedicationNameComparator());
        listviewPatientMeds.getItems().addAll(list);
    }

    private void fillAllergies(int patientId) {
        listviewPatientAllergies.getItems().clear();
        List<Allergy> list = (new AllergyDbManager()).getList(patientId);
        list.sort(new AllergySeverityNameComparator());
        listviewPatientAllergies.getItems().addAll(list);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof PatientDbManager) {
            populatePatientList();
        } else if (o instanceof AllergyDbManager) {
            fillAllergies(patient.getId());
        } else if (o instanceof MedicationDbManager) {
            fillMeds(patient.getId());
        } else {
            // is visit db manager ... no need for this yet
        }
    }
}
