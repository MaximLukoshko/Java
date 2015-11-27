package food;

import java.util.Comparator;

public class FoodComparator implements Comparator<Food> {

	@Override
	public int compare(Food obj1, Food obj2) {
		// TODO Auto-generated method stub
		if (obj1 == null) {
			return 1;
		}
		if (obj2 == null) {
			return -1;
		}

		if ((obj1.toString()).length() > (obj2.toString()).length()) {
			return 1;
		} else if ((obj1.toString()).length() == (obj2.toString()).length()) {
			return 0;
		}

		return -1;
	}

}
