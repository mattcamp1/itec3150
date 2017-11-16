package finalproject.sorters;

import finalproject.database.*;

import java.util.*;

/**
 * Class: PatientNameComparator
 *
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Allows for sorting patients by name
 */
public class PatientNameComparator implements Comparator<Patient> {

	@Override
	public int compare(Patient o1, Patient o2) {
		return o1.getName().compareTo(o2.getName());
	}
}
