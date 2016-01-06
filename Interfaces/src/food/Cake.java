package food;

public class Cake extends Food {

	private String Param;

	protected String getParam() {
		return Param;
	}

	public Cake(String Param) {
		// TODO Auto-generated constructor stub
		super("Cake");
		this.Param = Param;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (super.equals(obj)) {
			if (this.Param == ((Cake) obj).Param) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + "/" + Param + "_глазурь";
	}

	@Override
	public int calculateCalories() {
		int Cal = 300;
		if (Param.equals("шоколадная")) {
			Cal += 150;
		} else if (Param.equals("сливочная")) {
			Cal += 100;
		} else if (Param.equals("карамель")) {
			Cal += 50;
		}

		// TODO Auto-generated method stub
		return Cal;
	}
}
