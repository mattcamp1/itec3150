package finalproject.database;

/**Class: Allergy
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Contains information about patient allergies
 *
 */
public class Allergy {

	private String substance;
	private String effects;
	private int severity;

	public Allergy(String substance, String effects, int severity) {
		this.substance = substance;
		this.effects = effects;
		this.severity = severity;
	}

	public String getSubstance() {
		return substance;
	}

	public void setSubstance(String substance) {
		this.substance = substance;
	}

	public String getEffects() {
		return effects;
	}

	public void setEffects(String effects) {
		this.effects = effects;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}
}
