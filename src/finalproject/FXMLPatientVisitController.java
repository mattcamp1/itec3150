package finalproject;

import finalproject.database.Patient;
import finalproject.database.PatientVisit;

import java.net.URL;
import java.util.ResourceBundle;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO
	}

	@Override
	public void initData(Patient patient, PatientVisit target) {
		this.patient = patient;
	}
}
