package food;

// Params: "IceCream/шоколадное" "IceCream/карамель" "IceCream/шоколадное" "IceCream/шоколадное" "Cake/шоколадная" "Cake/шоколадная" "Cake/сливочная" "Cake/карамель" "-sort" "-calories" "House/stone" "The Queen"

public class MainApplication {

	static boolean ParamCalories;
	static boolean ParamSort;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		CheckParams(args);
		Meal Breakfast = new Meal(args);

		if (ParamSort) {
			Breakfast.SortByLength();
		}

		System.out.print(Breakfast.toString());

		if (ParamCalories) {
			System.out.print("\nThe Nutritious of your Meal is "
					+ Breakfast.calculateCalories() + " callories\n");
		}

		System.out.println("The Number of IceCream/шоколадное is "
				+ Breakfast.CountTheSameProducts("IceCream/шоколадное"));
	}

	public static void CheckParams(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-calories")) {
				ParamCalories = true;
			}
			if (args[i].equals("-sort")) {
				ParamSort = true;
			}
		}
	}
}
