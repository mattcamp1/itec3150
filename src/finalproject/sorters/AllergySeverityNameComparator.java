package finalproject.sorters;

import finalproject.database.*;

import java.util.*;

/**
 * Class: AllergySeveritynameComparator
 *
 * @author Matthew Camp
 * Version 1.0
 * Course: Advanced Programming Fall 2017
 * Written: , 2017
 * Allows for sorting alleries by severity and then by name
 */
public class AllergySeverityNameComparator implements Comparator<Allergy> {

	@Override
	public int compare(Allergy o1, Allergy o2) {
		int severityCompare = Integer.compare(o1.getSeverity(), o2.getSeverity());
		return severityCompare == 0 ? new AllergyNameComparator().compare(o1, o2) : severityCompare;
	}
}
