package finalproject;

import finalproject.database.Allergy;
import finalproject.database.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**Class: FXMLEditAllergiesController
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Controller class for the JavaFX GUI handling allergies
 *
 */
public class FXMLEditAllergiesController extends BaseController<Allergy> {

	private Allergy currentAllergy;

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

	/**
	 * Populates the fields in the form with existing data if it exists, otherwise
	 * the fields are cleared out to blank value.
	 */
	private void repopulateFields() {
		if (currentAllergy == null) {
			sldrAllergySeverity.adjustValue(0);
			txtAllergyEffect.setText(null);
			txtAllergySubstance.setText(null);
			txtAllergySeverity.setText("0");
		} else {
			sldrAllergySeverity.adjustValue(currentAllergy.getSeverity());
			txtAllergyEffect.setText(currentAllergy.getEffects());
			txtAllergySubstance.setText(currentAllergy.getSubstance());
			txtAllergySeverity.setText(String.valueOf(currentAllergy.getSeverity()));
		}
	}

	@FXML
	void handleReset(ActionEvent event) {
		currentAllergy = null;
		repopulateFields();
	}

	@FXML
	void handleSaveAllergy(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// NO-OP
	}

	@Override
	public void initData(Patient patient, Allergy target) {
		currentAllergy = target;
		repopulateFields();
	}

	@Override
	public Allergy validateForm() {
		return null;
	}
}
