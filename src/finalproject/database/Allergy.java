package finalproject.database;

import java.util.LinkedList;
import java.util.List;

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
        
                @Override
        public String toString(){
            return this.substance + " " + this.effects + " " + this.severity + "END";
        }
        
        public static Allergy getAllergyFromString(String allergy){
            String[] array = allergy.split(" ");
            String substance = array[0];
            String effects = array[1];
            int severity = Integer.valueOf(array[2]);
            return new Allergy(substance, effects, severity);
        }
        
        public static List<Allergy> getAllergyList(String allergies){
            String[] arrayAllergy = allergies.split("END");
            List<Allergy> list = new LinkedList<>();
            for (int index = 0; index < arrayAllergy.length; index++){
                list.add(getAllergyFromString(arrayAllergy[index]));
            }
            return list;
        }
}
