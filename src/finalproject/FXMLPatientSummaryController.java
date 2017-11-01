package finalproject;

import finalproject.database.*;
import finalproject.helpers.AlertHelper;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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

	private PatientDbManager patientManager;

	private final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private final String[] PHONE_REGEX = {"[0-9]{3}", "[0-9]{4}"};

	private final Medication DEFAULT_MEDICATION = new Medication(-1, "", "", 0, 0);
	private final Allergy DEFAULT_ALLERGY = new Allergy(-1, "", "", 0);

	private AllergyDbManager allergyManager = new AllergyDbManager();
	private MedicationDbManager medicationManager = new MedicationDbManager();
	private VisitDbManager visitManager = new VisitDbManager();

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

	private void populateCombos() {
		cboxPatientMaritalStatus.getItems().clear();
		cboxPatientMaritalStatus.getItems().addAll(MaritalStatus.values());
	}

	// TODO
	@FXML
	void btnViewVisits_OnAction(ActionEvent event) {
		Patient patient = listviewPatients.getSelectionModel().getSelectedItem();
		if (patient == null) {
			return;
		}
		showDialog(Dialogs.Visit, patient, new PatientVisit());
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

		if (patient.getId() == 0) {
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
			result = dbManager.insert(patient);
		} else {
			System.out.println("updating"); // testing
			result = dbManager.update(patient);
		}

		if (!result) {
			AlertHelper.ShowWarning("Database Error", "Patient Table", "There was an error submitting your information to the database.");
		}
	}

	@FXML
	protected void btnSave_OnAction(ActionEvent event) {
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

		// TODO
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

				case Visit:
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

	@FXML
	void btnEditMedication_OnAction(ActionEvent event) {
		Patient patient = listviewPatients.getSelectionModel().getSelectedItem();
		Medication med = listviewPatientMeds.getSelectionModel().getSelectedItem();
		showDialog(Dialogs.Meds, patient, med);
	}

	@FXML
	void btnNewPatient_OnAction(ActionEvent event) {
		// TODO: Add patient
	}

	@FXML
	void btnRemovePatient_OnAction(ActionEvent event) {
		// TODO: Remove patient
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
			patientManager = new PatientDbManager();
			Collection<Patient> patients = patientManager.getList(-1);
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
		dpickDateOfBirth.setValue(LocalDate.parse(patient.getDob(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		cboxPatientMaritalStatus.setValue(Enum.valueOf(MaritalStatus.class, patient.getMaritalStatus()));

		//MedicationDbManager medDatabase = new MedicationDbManager();
		//Collection<Medication> medList = medDatabase.getList(patient.getId());
		fillMeds(patient.getId());
		fillAllergies(patient.getId());
	}

	@FXML
	private void btnAddAllergy_OnAction(ActionEvent event) {
		showDialog(Dialogs.Allergies, patient, DEFAULT_ALLERGY);
	}

	@FXML
	private void btnAddMedication_OnAction(ActionEvent event) {
		showDialog(Dialogs.Meds, patient, DEFAULT_MEDICATION);
	}

	@FXML
	private void listviewPatients_OnMouseClicked(MouseEvent event) {
		patient = listviewPatients.getSelectionModel().getSelectedItem();
		populateData();
	}

	private void fillMeds(int patientId) {
		listviewPatientMeds.getItems().clear();
		List<Medication> list = (new MedicationDbManager()).getList(patientId);
		listviewPatientMeds.getItems().addAll(list);
	}

	private void fillAllergies(int patientId) {
		listviewPatientAllergies.getItems().clear();
		List<Allergy> list = (new AllergyDbManager()).getList(patientId);
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
