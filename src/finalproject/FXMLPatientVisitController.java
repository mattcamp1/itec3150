package finalproject;

import finalproject.database.PatientVisit;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import finalproject.helpers.AlertHelper;
import finalproject.helpers.Reference;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


/**
 * Class: FXMLPatientSummaryController
 *
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Controller class for the main JavaFX GUI handling patient visits to doctor
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
		if (target == null) return;

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
		txtDoctorName.setText(null);
		txtReason.setText(null);
		txtVitalPulse.setText(null);
		txtVitalBPTop.setText(null);
		txtVitalBPBottom.setText(null);
		txtVitalTemp.setText(null);
		txtDoctorNotes.setText(null);
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
			} catch (NumberFormatException e) {
				status.addFieldError("Temperature");
			}
		}

		// Validation not needed for notes -- not a required field
		notes = txtDoctorNotes.getText();

		if (target.getVisitId() == 0) {
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
			System.out.println("inserting into " + patient.getId()); // testing
			result = dbManager.insert(target);
		} else {
			System.out.println("updating"); // testing
			result = dbManager.update(target);
		}

		if (!result) {
			AlertHelper.ShowWarning("Database Error", "Visit Table", "There was an error submitting your information to the database.");
		}
	}
}
