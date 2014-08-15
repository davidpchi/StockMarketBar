
public class DrinkOrder {

	public String name;
	public double curPrice;
	public double minPrice;
	public double maxPrice;
	public int status;
	
	public DrinkOrder(String name, double curPrice, double minPrice, double maxPrice) {
		this.name = name;
		this.curPrice = curPrice;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.status = 0;
	}
	
	public DrinkOrder(String name) {
		this(name, 0, 0, 0);
	}
	
	public void crashPrice() {
		curPrice = minPrice;
		this.status = -1;
	}
	
	public void increasePrice(int percent) {
		if (curPrice < maxPrice) {
			curPrice = curPrice + (curPrice * percent / 100);
			this.status = 1;
		}
		
		if (curPrice > maxPrice) {
			curPrice = maxPrice;
		}
	}
	
	public void decreasePrice(int percent) {
		if (curPrice > minPrice) {
			curPrice = curPrice - (curPrice * percent / 100);
			this.status = -1;
		}
		
		if (curPrice < minPrice) {
			curPrice = minPrice;
		}
	}
}
