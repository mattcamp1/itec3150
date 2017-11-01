package finalproject.sorters;

import finalproject.database.Allergy;

import java.util.Comparator;

public class AllergySeverityNameComparator implements Comparator<Allergy> {

	@Override
	public int compare(Allergy o1, Allergy o2) {
		int severityCompare = Integer.compare(o1.getSeverity(), o2.getSeverity());
		return severityCompare == 0 ? new AllergyNameComparator().compare(o1, o2) : severityCompare;
	}
}
