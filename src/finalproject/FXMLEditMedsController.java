package finalproject;

import finalproject.database.*;
import finalproject.helpers.*;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.net.*;
import java.util.*;

/**
 * Class: FXMLEditMedsController
 *
 * @author Matthew Camp Version 1.0 Course: Advanced Programming Fall 2017
 * Written: , 2017 Controller class for the JavaFX GUI handling medications
 */
public class FXMLEditMedsController extends BaseController<Medication> {

    @FXML
    private TextField txtMedName;

    @FXML
    private TextField txtMedDose;

    @FXML
    private TextField txtMedDoseCount;

    @FXML
    private TextField txtMedReason;

    private boolean isEdit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // NO-OP
    }

    @Override
    public void populateData() {
        txtMedName.setText(target.getName());
        txtMedDose.setText(String.valueOf(target.getDoseMilligrams()));
        txtMedDoseCount.setText(String.valueOf(target.getDoseCount()));
        txtMedReason.setText(target.getReason());
    }

    @Override
    public void reset() {
        txtMedName.setText("");
        txtMedDose.setText("");
        txtMedDoseCount.setText("");
        txtMedReason.setText("");
    }

    @Override
    public void initData(Patient patient, Medication target, DatabaseManager<Medication> manager) {
        super.initData(patient, target, manager);
    }

    @Override
    public ValidationStatus validateForm() {
        ValidationStatus status = new ValidationStatus();
        String med = "";
        int doseMillis = 0;
        int doseCount = 0;
        String reason = "";

        // Validate Medication Name
        if (txtMedName.getText().isEmpty()) {
            status.addFieldError("Medication Name");
        } else {
            med = txtMedName.getText();
        }

        // Validate dose in mg
        if (txtMedDose.getText().isEmpty()) {
            status.addFieldError("Dose in Milligrams");
        } else {
            try {
                doseMillis = Integer.parseInt(txtMedDose.getText());
                if (doseMillis < 1 || doseMillis > 1000){
                    status.addFieldError("Dose in Milligrams");
                }
            } catch (NumberFormatException e) {
                status.addFieldError("Dose in Milligrams");
            }
        }

        // Validate dose count
        if (txtMedDoseCount.getText().isEmpty()) {
            status.addFieldError("Dose Count");
        } else {
            try {
                doseCount = Integer.parseInt(txtMedDoseCount.getText());
                if (doseCount < 1 || doseCount > 300){
                    status.addFieldError("Dose Count");
                }
            } catch (NumberFormatException e) {
                status.addFieldError("Dose Count");
            }
        }

        if (txtMedReason.getText().isEmpty()) {
            status.addFieldError("Reason");
        } else {
            reason = txtMedReason.getText();
        }

        if (target.getId() == 0) {
            target = new Medication(patient.getId(), med, reason, doseMillis, doseCount);  // I had to force initialization to 0...weird
        } else {
            target.setName(med);
            target.setReason(reason);
            target.setDoseMilligrams(doseMillis);
            target.setDoseCount(doseCount);
        }

        return status;
    }

    @Override
    public void saveToDatabase() {
        boolean result;
        if (target.getId() == 0) {
            System.out.println("inserting into " + patient.getId()); // testing
            result = dbManager.insert(target);
        } else {
            System.out.println("updating"); // testing
            result = dbManager.update(target);
        }

        if (!result) {
            AlertHelper.ShowWarning("Database Error", "Medication Table", "There was an error submitting your information to the database.");
        }
    }
}
