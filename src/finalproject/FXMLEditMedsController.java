package finalproject;

import finalproject.database.Medication;
import finalproject.database.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
        
        private Patient patient;
        private Medication medication;
        private boolean isEdit;

	@FXML
	void handleReset(ActionEvent event) {

	}

	@FXML
	void handleSaveMedication(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO
	}

	@Override
	public void initData(Patient patient,  Medication target) {
		// TODO
                
	}
        
}
