package finalproject.sorters;

import finalproject.database.Medication;

import java.util.Comparator;

/**
 * Class: MedicationNameComparator
 *
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Allows for sorting medications by name
 */
public class MedicationNameComparator implements Comparator<Medication> {

	@Override
	public int compare(Medication o1, Medication o2) {
		return o1.getName().compareTo(o2.getName());
	}
}
