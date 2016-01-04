package food;

import java.util.Arrays;

public class Meal implements Nutritious {

	private Food[] YourMeal;
	private int NumberOfItems;

	public Meal(String[] arg) {
		// TODO Auto-generated constructor stub

		NumberOfItems = arg.length;

		// Count the number of possible products by excluding args, that conain
		// parametres
		for (int i = 0; i < arg.length; i++) {
			if (arg[i].charAt(0) == '-') {
				NumberOfItems--;
			}
		}

		YourMeal = new Food[NumberOfItems];

		// Fill Array with food
		for (int i = 0; i < NumberOfItems; i++) {
			if (arg[i].charAt(0) != '-') {
				String Name = new String("");
				StringBuffer help = new StringBuffer(arg[i] + "/");
				String Param = new String("");

				Name = help.substring(0, arg[i].indexOf('/'));
				help.delete(0, arg[i].indexOf('/') + 1);
				if (!help.equals("")) {
					help.delete(help.length() - 1, help.length());
					Param = help.substring(0,
							(help.substring(0) + " ").indexOf(' '));
				}

				if (Name.equals("IceCream")) {
					YourMeal[i] = new IceCream(Param);
				} else if (Name.equals("Cake")) {
					YourMeal[i] = new Cake(Param);
				} else {
					YourMeal[i] = null;
				}
			}
		}

	}

	@Override
	public int calculateCalories() {
		// TODO Auto-generated method stub
		int Cal = 0;

		for (int i = 0; i < NumberOfItems; i++) {
			if (YourMeal[i] != null) {
				Cal += YourMeal[i].calculateCalories();
			}
		}
		return Cal;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String Meal = new String("Your Meal:\n");

		for (int i = 0; i < NumberOfItems; i++) {
			if (YourMeal[i] != null) {
				Meal += YourMeal[i].toString() + "\n";
			}
		}
		return Meal;
	}

	public void SortByLength() {
		Arrays.sort(YourMeal, new FoodComparator());
	}

	public int CountTheSameProducts(String Product) {

		int Number = 0;

		String Name = new String("");
		StringBuffer help = new StringBuffer(Product + "/");
		String Param = new String("");

		Name = help.substring(0, Product.indexOf('/'));
		help.delete(0, Product.indexOf('/') + 1);
		if (!help.equals("")) {
			help.delete(help.length() - 1, help.length());
			Param = help.substring(0, (help.substring(0) + " ").indexOf(' '));
		}

		for (int j = 0; j < YourMeal.length; j++) {
			if (YourMeal[j] != null) {
				if (((YourMeal[j].getName()).equals(Name))
						&& ((YourMeal[j].getParam()).equals(Param))) {
					Number++;
				}
			}
		}
		return Number;
	}
}
