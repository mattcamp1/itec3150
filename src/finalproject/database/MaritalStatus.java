package finalproject.database;


public enum MaritalStatus {
	Single ("Single"),
	Married ("Married"),
	Divorced ("Divorced"),
	Widowed ("Widowed"),
	Other ("Other");

	private final String name;

	MaritalStatus(String s) {
		name = s;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
