package finalproject.sorters;

import finalproject.database.*;

import java.util.*;

/**
 * Class: AllergyNameComparator
 *
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Alows for sorting of Allergies by substance name
 */
public class AllergyNameComparator implements Comparator<Allergy> {

	@Override
	public int compare(Allergy o1, Allergy o2) {
		return o1.getSubstance().compareTo(o2.getSubstance());
	}
}
