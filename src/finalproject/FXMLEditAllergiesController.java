package finalproject;

import finalproject.database.Allergy;
import finalproject.database.Patient;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
public class FXMLEditAllergiesController extends BaseController<Allergy> {

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

	@Override
	public void populateData() {
		if (target == null) {
			sldrAllergySeverity.adjustValue(0);
			txtAllergyEffect.setText(null);
			txtAllergySubstance.setText(null);
			txtAllergySeverity.setText("0");
		} else {
			sldrAllergySeverity.adjustValue(target.getSeverity());
			txtAllergyEffect.setText(target.getEffects());
			txtAllergySubstance.setText(target.getSubstance());
			txtAllergySeverity.setText(String.valueOf(target.getSeverity()));
		}
	}

	@FXML
	void handleReset(ActionEvent event) {
		target = null;
		populateData();
	}

	@FXML
	void handleSaveAllergy(ActionEvent event) {
		// TODO: Add validation
		// TODO: Figure out why I get "true" as a result, but the allergy is not added to the Allergy database
		target = new Allergy(patient.getId(), txtAllergySubstance.getText(), txtAllergyEffect.getText(), Integer.parseInt(txtAllergySeverity.getText()));
		boolean result;
		if (target.getId() == -1) {
			result = dbManager.insert(target);
		} else {
			result = dbManager.update(target);
		}

		System.out.println(result);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// NO-OP
	}

	@Override
	public void initData(BaseController parent, Patient patient, Allergy target) {
		super.initData(parent, patient, target);

		populateData();
	}

	@Override
	public Allergy validateForm() {
		return null;
	}
}
