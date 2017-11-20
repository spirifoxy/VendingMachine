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
        for(Product key: keys){
            if (name.equals(key.getName())) {
            	return key;
            }
        }
        return null;
	}
	
	public int getCurrentPaidSum() {
		return currentPaidSum;
	}
	
	public Map<Wallet.Coin, Integer> giveChange(int sum) {
		
		Map<Wallet.Coin, Integer> coinsToReturn = new HashMap<>();
		Wallet.Coin[] values = Wallet.Coin.values();
		
		for (int i = values.length - 1; i >= 0; i--) { //reverse order
		    
			Wallet.Coin coin = values[i];
		    int amount = sum / coin.getValue();
			if (amount != 0) {
				sum -= amount * coin.getValue();
				
				coinsToReturn.put(coin, amount);
				
				int coinsLeftToReturn = amount;
				int acceptorCoinsAmount = coinAcceptorWallet.getCoinsAmount(coin);
				
				if (coinsLeftToReturn - acceptorCoinsAmount > 0) { //amount of coins of this denomination in acceptor is not enough
					
					wallet.setCoins(coin, wallet.getCoinsAmount(coin) - coinsLeftToReturn);
					
				} else {
					coinAcceptorWallet.setCoins(coin, acceptorCoinsAmount - coinsLeftToReturn);
				}
			}
		}
		
		clearCoinAcceptor();
		
		return coinsToReturn;
	}

	private void clearCoinAcceptor() {
		
		for(Map.Entry<Wallet.Coin, Integer> coin : coinAcceptorWallet.getCoins().entrySet()) {
		    
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
		
		for(Map.Entry<Wallet.Coin, Integer> coin : coinAcceptorWallet.getCoins().entrySet()) { //normal order - take coins with less denomination first
		    
			int denom = coin.getKey().getValue();
			int coinsAmount = coin.getValue();
			
			if (coinsAmount == 0) {
				continue;
			}
			
			for (int i = 0; i < coinsAmount; i++ ) {
				if (sum >= product.getPrice()) {
					break;
				}
				sum += denom;
				spendCoin(denom);
				wallet.addCoin(denom);
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
