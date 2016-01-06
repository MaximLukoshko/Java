package food;

public abstract class Food extends Object implements Nutritious {

	protected String name;
	protected int Calories;
	protected String Param;

	public Food(String arg) {
		// TODO Auto-generated constructor stub
		name = arg;
		Param = "";
	}

	protected String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (!(obj instanceof Food)) {
			return false;
		} else if (this.name == null || ((Food) obj).name == null) {
			return false;
		} else if (this.name == ((Food) obj).name) {
			return true;
		}
		return false;
	}

	@Override
	public abstract String toString();

	protected abstract String getParam();

	@Override
	public abstract int calculateCalories();

}
