package finalproject;

import finalproject.database.Medication;
import finalproject.database.Patient;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**Class: FXMLEditMedsController
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Controller class for the JavaFX GUI handling medications
 *
 */
public class FXMLEditMedsController extends BaseController<Medication> {

	@FXML
	private TextField txtMedName;

	@FXML
	private Button btnSaveMedication;

	@FXML
	private Button btnResetMedication;

	@FXML
	private TextField txtMedDose;

	@FXML
	private TextField txtMedDoseCount;

	@FXML
	private TextField txtMedReason;

	private boolean isEdit;

	@FXML
	void handleReset(ActionEvent event) {

	}

	@FXML
	void handleSaveMedication(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// NO-OP
	}

	@Override
	public void populateData() {
		// TODO: Fill in data
	}

	@Override
	public void initData(BaseController parent, Patient patient,  Medication target) {
		super.initData(parent, patient, target);
	}

	@Override
	public Medication validateForm() {
		return null;
	}
}
