package spirifoxy.com.github.vmtest.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import spirifoxy.com.github.vmtest.Model.interfaces.HasMoney;

public class VM implements HasMoney {
	
	private Wallet wallet;
	private Wallet coinAcceptorWallet;
	private Map<Product, Integer> products;
	
	private int currentPaidSum;
	
	public VM() {
		wallet = new Wallet();
		coinAcceptorWallet = new Wallet();
		
		products = new HashMap<Product, Integer>();
		
		initializeProducts();
		initializeWallets();
	}
	
	private void initializeProducts() {
		products.put(new Product("Чай", 13), 10);
		products.put(new Product("Кофе", 18), 20);
		products.put(new Product("Кофе с молоком", 21), 20);
		products.put(new Product("Сок", 35), 15);
	}
	
	private void initializeWallets() {
		wallet.setCoins(Wallet.Coin.ONE, 100);
		wallet.setCoins(Wallet.Coin.TWO, 100);
		wallet.setCoins(Wallet.Coin.FIVE, 100);
		wallet.setCoins(Wallet.Coin.TEN, 100);
		
		coinAcceptorWallet.setCoins(Wallet.Coin.ONE, 0);
		coinAcceptorWallet.setCoins(Wallet.Coin.TWO, 0);
		coinAcceptorWallet.setCoins(Wallet.Coin.FIVE, 0);
		coinAcceptorWallet.setCoins(Wallet.Coin.TEN, 0);
	}
	
	public Wallet getWallet() {
		return wallet;
	}
	
	public Map<Product, Integer> getProducts() {
		return products;
	}
	
	public Product getProduct(String name) {
		Set<Product> keys = getProducts().keySet();
		for (Product key : keys) {
			if (name.equals(key.getName())) {
				return key;
			}
		}
		return null;
	}
	
	public int getCurrentPaidSum() {
		return currentPaidSum;
	}

	public Wallet giveChange(int sum) {
		
		if (sum == 0) {
			throw new IllegalArgumentException("There is nothing to return");
		}
		
		int currentPaidSumCopy = getCurrentPaidSum();
		this.clearCoinAcceptor();
		
		Wallet change = this.giveChange(sum, 0, new Wallet(wallet));
		
		if (change == null) {
			currentPaidSum = currentPaidSumCopy;
			throw new IllegalStateException(
					"VM can't give you all the money at that momemnt for some reason.\nCall the administrator.");
		}
		
		for (Map.Entry<Wallet.Coin, Integer> coin : change.getCoins().entrySet()) {
			wallet.setCoins(coin.getKey(), wallet.getCoinsAmount(coin.getKey()) - coin.getValue());
		}
		
		return change;
	}

	public Wallet giveChange(int sum, int offset, Wallet vmWallet) {
		
		Wallet.Coin[] values = Wallet.Coin.values();
		
		for (int i = values.length - 1 - offset; i >= 0; i--) { //reverse order
		
			Wallet.Coin coin = values[i];
			if (coin.getValue() > sum) {
				continue;
			} 
			
			Integer coinsAmount = vmWallet.getCoinsAmount(coin);
			if (coinsAmount != null && coinsAmount > 0) {
				
				Wallet coinsToReturn = new Wallet();
				
				Wallet vmWalletCopy = new Wallet(vmWallet); //we have to create copy of the wallet, so we can return 
				vmWalletCopy.spendCoin(coin);				//to this state in case of failure with some combination
				
				coinsToReturn.addCoin(coin);
				
				int sumLeftToReturn = sum - coin.getValue();
				if (sumLeftToReturn == 0) {
					return coinsToReturn;
				}
				
				Wallet extraCoinsToReturn = giveChange(sumLeftToReturn, values.length - 1 - i, vmWalletCopy);
				if (extraCoinsToReturn != null) {
					for (Map.Entry<Wallet.Coin, Integer> c : extraCoinsToReturn.getCoins().entrySet()) {
						coinsToReturn.addCoins(c.getKey(), c.getValue());
					}
					
					return coinsToReturn;
				}
			}
		}
		return null;
	}
	
	private void clearCoinAcceptor() {

		for (Map.Entry<Wallet.Coin, Integer> coin : coinAcceptorWallet.getCoins().entrySet()) {

			wallet.addCoins(coin.getKey(), coin.getValue());
			coinAcceptorWallet.setCoins(coin.getKey(), 0);
		}
		currentPaidSum = 0;
	}
	
	public void sellProduct(String productName) {
		
		Product product = getProduct(productName);
		Integer amount = getProducts().get(product);
		
		if (amount == null || amount == 0) {
			throw new IllegalArgumentException("Product is not available");
		}
		
		if (getCurrentPaidSum() < product.getPrice()) {
			throw new IllegalArgumentException("Insufficient funds");
		}
		
		int sum = 0;
		
		Wallet.Coin[] values = Wallet.Coin.values();
		
		for (int i = values.length - 1; i >= 0; i--) { //reverse order
			
			Wallet.Coin coin = values[i];
			Integer coinAcceptorCoinsAmount = coinAcceptorWallet.getCoinsAmount(coin);
			if (!(coinAcceptorCoinsAmount > 0)) { // can be zero or null
				continue;
			}
			
			for (int j = 0; j < coinAcceptorCoinsAmount; j++) {
				if (sum >= product.getPrice()) {
					break;
				}
				sum += coin.getValue();
				coinAcceptorWallet.spendCoin(coin.getValue());
				wallet.addCoin(coin.getValue());
			}
			
			if (sum >= product.getPrice()) {
				break;
			}
		}
		
		products.put(product, products.get(product) - 1);
		currentPaidSum -= product.getPrice();
	}
	
	@Override
	public void addCoin(int denom) {
		coinAcceptorWallet.addCoin(denom);
		currentPaidSum += denom;
	}

	@Override
	public void spendCoin(int denom) {
		coinAcceptorWallet.spendCoin(denom);
	}
	
	@Override
	public int getCoinsAmount(int denom) {
		return coinAcceptorWallet.getCoinsAmount(denom);
	}
	
}
