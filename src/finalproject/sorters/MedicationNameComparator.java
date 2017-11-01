package finalproject.sorters;

import finalproject.database.Medication;

import java.util.Comparator;

public class MedicationNameComparator implements Comparator<Medication> {

	@Override
	public int compare(Medication o1, Medication o2) {
		return o1.getName().compareTo(o2.getName());
	}
}
