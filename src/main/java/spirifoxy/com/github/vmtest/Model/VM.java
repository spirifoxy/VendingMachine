package spirifoxy.com.github.vmtest.Model;

import java.util.ArrayList;
import java.util.List;

public class VM {
	
	private Wallet wallet;
	private List<Product> products;
	private int currentPaidAmount;
	
	VM() {
		wallet = new Wallet();
		products = new ArrayList<Product>();
		
		initializeProducts();
		initializeWallet();
	}
	
	private void initializeProducts() {
		products.add(new Product("Чай", 13, 10));
		products.add(new Product("Кофе", 18, 20));
		products.add(new Product("Кофе с молоком", 21, 20));
		products.add(new Product("Сок", 35, 15));
	}
	
	private void initializeWallet() {
		wallet.getCoins().put(Wallet.Coin.ONE, 100);
		wallet.getCoins().put(Wallet.Coin.TWO, 100);
		wallet.getCoins().put(Wallet.Coin.FIVE, 100);
		wallet.getCoins().put(Wallet.Coin.TEN, 100);
	}
	
	public Wallet getWallet() {
		return wallet;
	}
	
	public List getProducts() {
		return products;
	}
}
