package spirifoxy.com.github.vmtest.Model;

import java.util.ArrayList;
import java.util.List;

import spirifoxy.com.github.vmtest.Model.interfaces.HasMoney;

public class VM implements HasMoney {
	
	private Wallet wallet;
	private List<Product> products;
	private int currentPaidAmount;
	
	public VM() {
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
		wallet.setCoins(Wallet.Coin.ONE, 100);
		wallet.setCoins(Wallet.Coin.TWO, 100);
		wallet.setCoins(Wallet.Coin.FIVE, 100);
		wallet.setCoins(Wallet.Coin.TEN, 100);
	}
	
	public Wallet getWallet() {
		return wallet;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public int getCurrentPaidAmount() {
		return currentPaidAmount;
	}

	@Override
	public void addCoin(int denom) {
		wallet.addCoin(denom);
	}

	@Override
	public void spendCoin(int denom) {
		wallet.spendCoin(denom);
	}
	
	@Override
	public int getCoinsAmount(int denom) {
		return wallet.getCoinsAmount(denom);
	}
	
}
