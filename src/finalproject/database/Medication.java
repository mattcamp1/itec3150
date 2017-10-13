package finalproject.database;

/**Class: Medication
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Contains information about patient medications
 *
 */
public class Medication {

	private String name;
	private String reason;
	private int doseMilligrams;
	private int doseCount;

	public Medication(String name, String reason, int doseMilligrams, int doseCount) {
		this.name = name;
		this.reason = reason;
		this.doseMilligrams = doseMilligrams;
		this.doseCount = doseCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getDoseMilligrams() {
		return doseMilligrams;
	}

	public void setDoseMilligrams(int doseMilligrams) {
		this.doseMilligrams = doseMilligrams;
	}

	public int getDoseCount() {
		return doseCount;
	}

	public void setDoseCount(int doseCount) {
		this.doseCount = doseCount;
	}
}
