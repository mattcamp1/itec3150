package finalproject.sorters;

import finalproject.database.Allergy;

import java.util.Comparator;

public class AllergyNameComparator implements Comparator<Allergy> {

	@Override
	public int compare(Allergy o1, Allergy o2) {
		return o1.getSubstance().compareTo(o2.getSubstance());
	}
}
