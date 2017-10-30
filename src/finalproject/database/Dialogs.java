package finalproject.database;


public enum Dialogs {
	Summary ("FXMLPatientSummary.fxml"),
	Visit ("FXMLPatientVisit.fxml"),
	Allergies ("FXMLEditAllergies.fxml"),
	Meds ("FXMLEditMeds.fxml");

	private final String name;

	Dialogs(String s) {
		name = s;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
