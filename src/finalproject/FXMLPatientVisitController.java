package finalproject;

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
		// NO-OP
	}

//	@Override
//	public void initData(BaseController parent, Patient patient, PatientVisit target) {
//		super.initData(parent, patient, target);
//	}

	@Override
	public void populateData() {
		// TODO: Fill in data
	}

	@Override
	public void reset() {

	}

	@Override
	public ValidationStatus validateForm() {
		return null;
	}

	@Override
	public void saveToDatabase() {

	}
}
