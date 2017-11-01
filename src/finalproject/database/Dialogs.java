package finalproject.database;


/**
 * Enum: Dialogs
 *
 * @author Matthew Camp Version 1.0 Course: Advanced Programming Fall 2017
 * Written: , 2017 Contains enumeration of dialogs and their associated files
 */
public enum Dialogs {
	Summary("FXMLPatientSummary.fxml"),
	VisitModify("FXMLPatientVisit.fxml"),
	VisitList("FXMLVisitList.fxml"),
	Allergies("FXMLEditAllergies.fxml"),
	Meds("FXMLEditMeds.fxml");

	private final String name;

	Dialogs(String s) {
		name = s;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
