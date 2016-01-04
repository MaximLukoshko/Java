package food;

public class IceCream extends Food {

	private String Param;

	public IceCream(String sir) {
		super("IceCream");
		Param = sir;
		// TODO Auto-generated constructor stub
	}

	protected String getParam() {
		return Param;
	}

	@Override
	public int calculateCalories() {
		// TODO Auto-generated method stub
		int Cal = 50;
		if (Param.equals("карамель")) {
			Cal += 100;
		} else if (Param.equals("шоколадное")) {
			Cal += 200;
		}
		return Cal;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (super.equals(obj)) {
			if (this.Param == ((IceCream) obj).Param) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + "/" + Param;
	}

}
