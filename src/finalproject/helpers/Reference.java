package finalproject.helpers;

import finalproject.database.Allergy;
import finalproject.database.Medication;
import finalproject.database.PatientVisit;

import java.time.format.DateTimeFormatter;

/**
 * Class: Reference
 *
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Reference class to hold final variables
 */
public class Reference {

	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	public static final String[] PHONE_REGEX = {"[0-9]{3}", "[0-9]{4}"};
	public static final Medication DEFAULT_MEDICATION = new Medication(0, "", "", 0, 0);
	public static final Allergy DEFAULT_ALLERGY = new Allergy(0, "", "", 0);
	public static final PatientVisit DEFAULT_VISIT = new PatientVisit(0, "", "", "", 0, 0, "", "");

	public static class AlertText {

		// Titles
		public static final String INVALID_SELECTION = "Invalid Selection";
		public static final String DIALOG_ERROR = "Dialog Error";
		public static final String DATABASE_ERROR = "Database Access Error";
		public static final String FORM_LOAD_ERROR = "FXML Loading Error";

		// Content - Invalid
		public static final String INVALID_SELECTION_VISIT = "Please select a visit to view the details of.";
		public static final String INVALID_SELECTION_PATIENT = "Please select a patient and try again.";

		// Content - Dialogs
		public static final String PATIENTVISIT_DIALOG_ERROR = "Error opening the PatientVisit dialog";

		// Content - Database
		public static final String DATABASE_SAVE_ERROR = "There was an error submitting your information to the database.";
	}
}
