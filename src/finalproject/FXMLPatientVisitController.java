package finalproject;

import finalproject.database.Patient;
import finalproject.database.PatientVisit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

	private Patient patient;
    @FXML
    private DatePicker txtDate;
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
    private Button btnSubmit;
    @FXML
    private Button btnCancel;
    @FXML
    private TextArea txtDoctorNotes;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO
	}

	@Override
	public void initData(Patient patient, PatientVisit target) {
		this.patient = patient;
	}

	@Override
	public PatientVisit validateForm() {
		return null;
	}
}
