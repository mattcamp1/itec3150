package finalproject.database;


/**
 * Class: Allergy
 *
 * @author Matthew Camp Version 1.0 Course: Advanced Programming Fall 2017
 * Written: , 2017 Contains information about patient allergies
 */
public class Allergy {

	private String substance;
	private String effects;
	private int severity;
	private int id;
	private int patiendId;

	public Allergy(int patiendId, String substance, String effects, int severity) {
		this.substance = substance;
		this.effects = effects;
		this.severity = severity;
		this.patiendId = patiendId;
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

	// change for list view
	@Override
	public String toString() {
		return this.substance;
	}


//    public static Allergy getAllergyFromString(String allergy) {
//        String[] array = allergy.split(" ");
//        String substance = array[0];
//        String effects = array[1];
//        int severity = Integer.valueOf(array[2]);
//        return new Allergy(substance, effects, severity);
//    }
//
//    public static List<Allergy> getAllergyList(String allergies) {
//        String[] arrayAllergy = allergies.split("END");
//        List<Allergy> list = new LinkedList<Allergy>();
//        for (int index = 0; index < arrayAllergy.length; index++) {
//            list.add(getAllergyFromString(arrayAllergy[index]));
//        }
//        return list;
//    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatiendId() {
		return patiendId;
	}

	public void setPatiendId(int patiendId) {
		this.patiendId = patiendId;
	}
}
