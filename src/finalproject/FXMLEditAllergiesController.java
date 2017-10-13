package finalproject;

import finalproject.database.Allergy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**Class: FXMLEditAllergiesController
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Controller class for the JavaFX GUI handling allergies
 *
 */
public class FXMLEditAllergiesController {

	private Allergy currentAllergy;

	@FXML
	private ListView<Allergy> listviewAllergies;

	@FXML
	private Button btnAllergyAdd;

	@FXML
	private Button btnAllergyRemove;

	@FXML
	private Button btnConfirmChanges;

	@FXML
	private Slider sldrAllergySeverity;

	@FXML
	private TextField txtAllergySeverity;

	@FXML
	private TextArea txtAllergyEffect;

	@FXML
	private TextField txtAllergySubstance;

	@FXML
	private Button btnSaveAllergy;

	@FXML
	private Button btnResetAllergy;

	@FXML
	void handleAddAllergy(ActionEvent event) {

	}

	@FXML
	void handleConfirmChanges(ActionEvent event) {

	}

	@FXML
	void handleRemoveAllergy(ActionEvent event) {

	}

	@FXML
	void handleReset(ActionEvent event) {

	}

	@FXML
	void handleSaveAllergy(ActionEvent event) {

	}

}
