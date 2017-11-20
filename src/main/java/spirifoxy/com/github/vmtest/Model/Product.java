package spirifoxy.com.github.vmtest.Model;

public class Product {
	
	private String name;
	private int price;
	
	public Product(String name) {
		this(name, 0);
	}
	
	Product(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if (obj == this) { 
			return true; 
		} 
		if (obj == null || obj.getClass() != this.getClass()) { 
			return false; 
		} 
		
		Product product = (Product) obj; 
		return this.name.equals(product.name); 
	} 
	
	@Override 
	public int hashCode() {
		final int prime = 31; 
		int result = 1; 
		result = prime * result + name.hashCode();
		return result; 
	}
}
