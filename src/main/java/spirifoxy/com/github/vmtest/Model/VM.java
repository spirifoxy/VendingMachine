package spirifoxy.com.github.vmtest.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spirifoxy.com.github.vmtest.Model.interfaces.HasMoney;

public class VM implements HasMoney {
	
	private Wallet wallet;
	private Wallet coinAcceptorWallet;
	private List<Product> products;
	
	public VM() {
		wallet = new Wallet();
		coinAcceptorWallet = new Wallet();
		
		products = new ArrayList<Product>();
		
		initializeProducts();
		initializeWallets();
	}
	
	private void initializeProducts() {
		products.add(new Product("Чай", 13, 10));
		products.add(new Product("Кофе", 18, 20));
		products.add(new Product("Кофе с молоком", 21, 20));
		products.add(new Product("Сок", 35, 15));
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
	
	public List<Product> getProducts() {
		return products;
	}
	
	public int getCurrentPaidSum() {
		int sum = 0;
		for(Map.Entry<Wallet.Coin, Integer> coin : coinAcceptorWallet.getCoins().entrySet()) {
		    Wallet.Coin c = coin.getKey();
		    int amount = coin.getValue();
		    
		    sum += c.getValue() * amount;
		}
		return sum;
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
	}
	
	@Override
	public void addCoin(int denom) {
		coinAcceptorWallet.addCoin(denom);
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
