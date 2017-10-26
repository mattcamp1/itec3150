/*
 */
package finalproject;

import finalproject.database.Allergy;
import finalproject.database.Medication;
import finalproject.database.Patient;
import finalproject.database.PatientVisit;
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
import java.util.ResourceBundle;

/**Class: FXMLPatientSummaryController
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Controller class for the main JavaFX GUI handling patient summary information
 *
 */
public class FXMLPatientSummaryController extends BaseController<Patient> {

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
    private ComboBox<?> cboxPatientMaritalStatus;

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
    void handleAddPatient(ActionEvent event) {

    }

    void handleAddVisit(ActionEvent event) {
    	Patient patient = listviewPatients.getSelectionModel().getSelectedItem();
    	if (patient == null) patient = new Patient();
    	showDialog(Dialogs.Visit, patient, new PatientVisit());
    }

    @FXML
    void handleConfirmPatient(ActionEvent event) {

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

			controller.initData(patient, target);
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
    void handleRemovePatient(ActionEvent event) {

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

	@Override
	public void initData(Patient patient, Patient target) {
		// NO-OP?
	}

    @FXML
    private void viewVisits(ActionEvent event) {
    }

    @FXML
    private void addAllergy(ActionEvent event) {
    }

    @FXML
    private void addMedication(ActionEvent event) {
    }
}
