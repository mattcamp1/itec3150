package finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**Class: FXMLEditMedsController
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Controller class for the JavaFX GUI handling medications
 *
 */
public class FXMLEditMedsController {

	@FXML
	private ListView<?> listviewMedications;

	@FXML
	private Button btnAddMedication;

	@FXML
	private Button btnRemoveMedication;

	@FXML
	private Button btnConfirmChanges;

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

	@FXML
	void handleAddMedication(ActionEvent event) {

	}

	@FXML
	void handleConfirmChanges(ActionEvent event) {

	}

	@FXML
	void handleRemoveMedication(ActionEvent event) {

	}

	@FXML
	void handleReset(ActionEvent event) {

	}

	@FXML
	void handleSaveMedication(ActionEvent event) {

	}

}
