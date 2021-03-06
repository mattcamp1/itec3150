package finalproject;

import finalproject.database.*;
import finalproject.helpers.*;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.net.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

/**
 * Class: FXMLPatientSummaryController
 *
 * @author Matthew Camp Version 1.0 Course: Advanced Programming Fall 2017
 * Written: , 2017 Controller class for the main JavaFX GUI handling patient
 * visits to doctor
 */
public class FXMLPatientVisitController extends BaseController<PatientVisit> {

    @FXML
    private Label lblPatientName;

    @FXML
    private TextField txtDoctorName;

    @FXML
    private DatePicker dpickDateOfVisit;

    @FXML
    private TextArea txtReason;

    @FXML
    private TextField txtVitalPulse;

    @FXML
    private TextField txtVitalBPTop;

    @FXML
    private TextField txtVitalBPBottom;

    @FXML
    private TextField txtVitalTemp;

    @FXML
    private TextArea txtDoctorNotes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // NO-OP
    }

    @Override
    public void populateData() {
        lblPatientName.setText(patient.getName());
        if (target == null) {
            return;
        }

        txtDoctorName.setText(target.getDoctorName());
        txtReason.setText(target.getReason());
        txtVitalPulse.setText(String.valueOf(target.getPulse()));
        try {
            txtVitalBPTop.setText(target.getBloodPressure().split("/")[0]);
            txtVitalBPBottom.setText(target.getBloodPressure().split("/")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            txtVitalBPTop.setText("");
            txtVitalBPBottom.setText("");
        }
        txtVitalTemp.setText(String.valueOf(target.getTemperature()));
        try {
            dpickDateOfVisit.setValue(LocalDate.parse(target.getDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        } catch (Exception e) {
            dpickDateOfVisit.setValue(LocalDate.now());
        }
        txtDoctorNotes.setText(target.getDoctorNotes());
    }

    @Override
    public void reset() {
        txtDoctorName.setText("");
        txtReason.setText("");
        txtVitalPulse.setText("");
        txtVitalBPTop.setText("");
        txtVitalBPBottom.setText("");
        txtVitalTemp.setText("");
        txtDoctorNotes.setText("");
        dpickDateOfVisit.setValue(null);
    }

    @Override
    public ValidationStatus validateForm() {
        ValidationStatus status = new ValidationStatus();
        String docName = "";
        String date = "";
        String reason = "";
        int pulse = 0;
        String pressure = "";
        int temp = 0;
        String notes = "";

        // Validate doctor's name
        if (txtDoctorName.getText().isEmpty()) {
            status.addFieldError("Doctor Name");
        } else {
            docName = txtDoctorName.getText();
        }

        // Validate date of visit
        if (dpickDateOfVisit.getValue() == null) {
            status.addFieldError("Date of Visit");
        } else {
            date = dpickDateOfVisit.getValue().format(Reference.DATE_FORMAT);
        }

        // Validate reason
        if (txtReason.getText().isEmpty()) {
            status.addFieldError("Reason");
        } else {
            reason = txtReason.getText();
        }

        // Validate pulse
        if (txtVitalPulse.getText().isEmpty()) {
            status.addFieldError("Pulse");
        } else {
            try {
                pulse = Integer.parseInt(txtVitalPulse.getText());
                if (pulse < 0 || pulse > 300) {
                    status.addFieldError("Pulse");
                }
            } catch (NumberFormatException e) {
                status.addFieldError("Pulse");
            }
        }

        // Validate blood pressure
        if (txtVitalBPTop.getText().isEmpty() || txtVitalBPBottom.getText().isEmpty()) {
            status.addFieldError("Blood Pressure");
        } else {
            try {
                int bp[] = new int[2];
                bp[0] = Integer.parseInt(txtVitalBPTop.getText());
                bp[1] = Integer.parseInt(txtVitalBPBottom.getText());
                if (bp[0] < 0 || bp[1] < 0 || bp[0] > 300 || bp[1] > 300){
                    status.addFieldError("Blood Pressure");
                }
                pressure = String.valueOf(bp[0] + "/" + String.valueOf(bp[1]));
            } catch (NumberFormatException e) {
                status.addFieldError("Blood Pressure");
            }
        }

        // Validate temperature
        if (txtVitalTemp.getText().isEmpty()) {
            status.addFieldError("Temperature");
        } else {
            try {
                temp = Integer.parseInt(txtVitalTemp.getText());
                if (temp < 30 || temp > 200){
                    status.addFieldError("Temperature");
                }
            } catch (NumberFormatException e) {
                status.addFieldError("Temperature");
            }
        }

        // Validation not needed for notes -- not a required field
        notes = txtDoctorNotes.getText();

        if (target == null || target.getVisitId() == 0) {
            target = new PatientVisit(patient.getId(), date, docName, pressure, pulse, temp, reason, notes);
        } else {
            target.setDate(date);
            target.setDoctorName(docName);
            target.setBloodPressure(pressure);
            target.setPulse(pulse);
            target.setTempereature(temp);
            target.setReason(reason);
            target.setDoctorNotes(notes);
        }

        return status;
    }

    @Override
    public void saveToDatabase() {
        boolean result;
        if (target.getVisitId() == 0) {
            result = dbManager.insert(target);
        } else {
            result = true; // no editing visits
        }
        if (!result) {
            AlertHelper.ShowWarning("Database Error", "Visit Table", "There was an error submitting your information to the database.");
        }
    }
}
