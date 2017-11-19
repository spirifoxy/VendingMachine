package spirifoxy.com.github.vmtest.Model;

public class Product {
	
	private String name;
	private int price;
	private int amount;
	
	Product(String name, int price, int amount) {
		this.name = name;
		this.price = price;
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getAmount() {
		return amount;
	}

}
