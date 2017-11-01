package finalproject;

import finalproject.database.PatientVisit;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


/**Class: FXMLPatientSummaryController
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Controller class for the main JavaFX GUI handling patient visits to doctor
 *
 */
public class FXMLPatientVisitController extends BaseController<PatientVisit> {

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
	}

	@Override
	public void reset() {
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
		// TODO: Add validation
		return null;
	}

	@Override
	public void saveToDatabase() {
		// TODO: Add code to save to database
	}
}
