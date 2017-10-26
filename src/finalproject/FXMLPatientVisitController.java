package finalproject;

import finalproject.database.Patient;

/**Class: FXMLPatientSummaryController
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Controller class for the main JavaFX GUI handling patient visits to doctor
 *
 */
public class FXMLPatientVisitController {

	private Patient patient;

	public FXMLPatientVisitController(Patient patient) {
		this.patient = patient;
	}
}
