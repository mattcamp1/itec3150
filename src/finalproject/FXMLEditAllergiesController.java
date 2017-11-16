package finalproject;

import finalproject.database.Allergy;
import finalproject.database.DatabaseManager;
import finalproject.database.Patient;

import java.net.URL;
import java.util.ResourceBundle;

import finalproject.helpers.AlertHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Class: FXMLEditAllergiesController
 *
 * @author Matthew Camp Version 1.0 Course: Advanced Programming Fall 2017
 * Written: , 2017 Controller class for the JavaFX GUI handling allergies
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

    @Override
    public void populateData() {
        sldrAllergySeverity.adjustValue(target.getSeverity());
        txtAllergyEffect.setText(target.getEffects());
        txtAllergySubstance.setText(target.getSubstance());
        txtAllergySeverity.setText(String.valueOf(target.getSeverity()));
    }

    @Override
    public void reset() {
        txtAllergySubstance.setText("");
        txtAllergyEffect.setText("");
        txtAllergySeverity.setText("");
        sldrAllergySeverity.adjustValue(0);
    }

    @FXML
    void sldrAllergySeverity_OnMouseClicked(MouseEvent event) {
        txtAllergySeverity.setText(String.valueOf((int) sldrAllergySeverity.getValue()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // NO-OP
    }

    @Override
    public void initData(Patient patient, Allergy target, DatabaseManager<Allergy> manager) {
        super.initData(patient, target, manager);
        populateData();
    }

    @Override
    public ValidationStatus validateForm() {
        ValidationStatus status = new ValidationStatus();
        String substance = "";
        String effect = "";
        int severity = 0;

        // Validate Substance
        if (txtAllergySubstance.getText().isEmpty()) {
            status.addFieldError("Substance");
        } else {
            substance = txtAllergySubstance.getText();
        }

        // Validate Allergy Effect
        if (txtAllergyEffect.getText().isEmpty()) {
            status.addFieldError("Allergy Effect");
        } else {
            effect = txtAllergyEffect.getText();
        }

        // Validate severity
        if (txtAllergySeverity.getText().isEmpty()) {
            status.addFieldError("Severity");
        } else {
            severity = (int) sldrAllergySeverity.getValue();
            if (severity < 0 || severity > 10){
                status.addFieldError("Severity");
            }
        }

        if (target.getId() == 0) {
            target = new Allergy(patient.getId(), substance, effect, severity);
        } else {
            target.setSubstance(substance);
            target.setEffects(effect);
            target.setSeverity(severity);
        }

        // Return validation status with updated target information
        return status;
    }

    @Override
    public void saveToDatabase() {
        boolean result;
        if (target.getId() == 0) {
            result = dbManager.insert(target);
        } else {
            result = dbManager.update(target);
        }
        if (!result) {
            AlertHelper.ShowWarning("Database Error", "Allergy Table", "There was an error submitting your information to the database.");
        }
    }
}
