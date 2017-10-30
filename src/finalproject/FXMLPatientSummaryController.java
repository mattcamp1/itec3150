package finalproject;

import finalproject.database.*;
import finalproject.helpers.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**Class: FXMLPatientSummaryController
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Controller class for the main JavaFX GUI handling patient summary information
 *
 */
public class FXMLPatientSummaryController extends BaseController<Patient> {

	private PatientDbManager patientManager;

	private final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private final String[] PHONE_REGEX = {"[0-9]{3}", "[0-9]{4}"};

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label lblTitle;

    @FXML
    private ListView<Patient> listviewPatients;

    @FXML
    private Button btnNewParient;

    @FXML
    private Button btnRemovePatient;

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
    private HBox hboxPhone;

    @FXML
    private TextField txtPatientPhoneAreaCode;

    @FXML
    private TextField txtPatientPhonePrefix;

    @FXML
    private TextField txtPatientPhoneLineNumber;

    @FXML
    private HBox hboxAllergies;

    @FXML
    private ListView<Allergy> listviewPatientAllergies;

    @FXML
    private Button btnPatientEditAllergies;

    @FXML
    private HBox hboxMeds;

    @FXML
    private HBox hboxAllergies1;

    @FXML
    private ListView<Medication> listviewPatientMeds;

    @FXML
    private Button btnPatientEditMeds;

    @FXML
    private TextField txtPatientInsurance;

    @FXML
    private Button btnConfirmPatient;

    @FXML
    private Button viewVisitButton;

    @FXML
    private Button addAllergyButton;

    @FXML
    private Button addMedicationButton;

    @FXML
	private DatePicker dpickDateOfBirth;

    private void populateCombos() {
    	cboxPatientMaritalStatus.getItems().clear();
    	cboxPatientMaritalStatus.getItems().addAll(MaritalStatus.values());
	}

	// TODO
    void handleAddVisit(ActionEvent event) {
    	Patient patient = listviewPatients.getSelectionModel().getSelectedItem();
    	if (patient == null) patient = new Patient();
    	showDialog(Dialogs.Visit, patient, new PatientVisit());
    }

    public Patient validateForm() {
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
    		if (Pattern.matches(PHONE_REGEX[0], txtPatientPhoneAreaCode.getText())) {
    			phone = txtPatientPhoneAreaCode.getText();

				if (Pattern.matches(PHONE_REGEX[0], txtPatientPhonePrefix.getText())) {
					phone += txtPatientPhonePrefix.getText();

					if (Pattern.matches(PHONE_REGEX[1], txtPatientPhoneLineNumber.getText())) {
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
    		if (Pattern.matches(EMAIL_REGEX, txtPatientEmail.getText())) {
    			email = txtPatientEmail.getText();
			} else {
    			status.addFieldError("Email");
			}
		}

		if (dpickDateOfBirth.getValue() == null) {
    		status.addFieldError("Date of Birth");
		} else {
			dob = dpickDateOfBirth.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

		if (status.getIsValid()) {
    		return new Patient(name, address, phone, email, dob, marriage, insurance);
		} else {
			AlertHelper.ShowWarning("Invalid Patient Information", "Please fix the following fields", status.getErrors());
			return null;
		}
	}

	// TODO: Test with working database
    @FXML
    void handleConfirmPatient(ActionEvent event) {
    	// region Testing Database
        // testing db
        
        //Patient patient = new Patient("name", "home", "999", "@gmail", "today", "single", "none");
        PatientDbManager pman = new PatientDbManager();
        //pman.insert(patient); // patient inserted
        //System.out.println(pman.get(1)); // patient printed
        VisitDbManager vman = new VisitDbManager();
        //PatientVisit visit = new PatientVisit(1, "tomorrow", "bob", "1/1", 99, 99, "cancer", "as good as dead");
        //vman.insert(visit); // visit inserted
        //System.out.println(vman.get(1)); // visit printed
        MedicationDbManager mman = new MedicationDbManager();
        //Medication medication = new Medication(1, "viagra", "limp", 500, 60);
        // mman.insert(medication); inserted
        //System.out.println(mman.get(1)); printed
        AllergyDbManager aman = new AllergyDbManager();
//        Allergy allergy = new Allergy(1, "bullshit", "death", 9);
//        aman.insert(allergy);
        //System.out.println(aman.get(1));
		// endregion

		Patient patient;

		if (listviewPatients.getSelectionModel().getSelectedItem() == null) {
			System.out.println("[FXMLPatientSummaryController] Inserting new patient");
			patient = validateForm();
			patientManager.insert(patient);
		} else {
			System.out.println("[FXMLPatientSummaryController] Updating existing patient");
			patient = validateForm();
			patientManager.update(patient);
		}
    }

    @FXML
    void handleEditAllergies(ActionEvent event) {
    	Patient patient = listviewPatients.getSelectionModel().getSelectedItem();
    	Allergy allergy = listviewPatientAllergies.getSelectionModel().getSelectedItem();
    	if (patient == null) patient = new Patient();
    	showDialog(Dialogs.Allergies, patient, allergy);
    }

	private <T> Stage showDialog(Dialogs dialog, Patient patient, T target) {
    	try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource(dialog.toString())
			);

			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setScene(new Scene((AnchorPane) loader.load()));

			BaseController controller;
			switch (dialog) {
				case Allergies:
					controller = loader.<FXMLEditAllergiesController>getController();
					break;

				case Meds:
					controller = loader.<FXMLEditMedsController>getController();
					break;

				case Visit:
					controller = loader.<FXMLPatientVisitController>getController();
					break;

				default:
					throw new IllegalArgumentException();
			}

			controller.initData(this, patient, target);
			stage.show();
			return stage;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
    		return null;
		}
	}

    @FXML
    void handleEditMeds(ActionEvent event) {
    	Patient patient = listviewPatients.getSelectionModel().getSelectedItem();
		Medication med = listviewPatientMeds.getSelectionModel().getSelectedItem();
		showDialog(Dialogs.Meds, patient, med);
    }

	@FXML
	void handleAddPatient(ActionEvent event) {
		// TODO: Add patient
	}

    @FXML
    void handleRemovePatient(ActionEvent event) {
    	// TODO: Remove patient
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void populateData() {
		populateCombos();
		populatePatientList();
	}

	@Override
	public void initData(BaseController parent, Patient patient, Patient target) {
    	super.initData(parent, patient, target);
	}

	private void populatePatientList() {
    	try {
			listviewPatients.getItems().clear();
			patientManager = new PatientDbManager();
			Collection<Patient> patients = patientManager.getList(-1);
			listviewPatients.getItems().addAll(patients);
		} catch (NullPointerException e) {
    		AlertHelper.ShowError("An error occurred", "Error accessing PATIENT table", e);
		}
	}

    @FXML
    private void viewVisits(ActionEvent event) {
    	// TODO: Add visit
    }

    @FXML
    private void addAllergy(ActionEvent event) {
    	// TODO: Add allergy
    }

    @FXML
    private void addMedication(ActionEvent event) {
    	// TODO: Add med
    }
}
