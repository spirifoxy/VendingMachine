package spirifoxy.com.github.vmtest.Model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Wallet {

	public enum Coin {
		ONE(1), TWO(2), FIVE(5), TEN(10);
		
		private final int value;
		
		Coin(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
		public static Coin fromValue(int value) {
		    for (Coin c : Coin.values()) {
		      if (c.value == value) {
		        return c;
		      }
		    }
		    throw new IllegalArgumentException("There is no coin of denomination " + value);
		  }
		
	}
	
	private Map<Coin, Integer> coins;
	
	public void addCoin(Coin coin) {
		addCoins(coin, 1);
	}
	
	public void addCoins(Coin coin, int amount) {
		Integer coinsInWallet = this.getCoinsAmount(coin);
		if (coinsInWallet == null) {
			this.setCoins(coin, amount);
		} else {
			coins.put(coin, coinsInWallet.intValue() + amount);
		}
	}
	
	public void spendCoin(Coin coin) {
		Integer coinsInWallet = this.getCoinsAmount(coin);
		if (coinsInWallet.intValue() == 0 || coinsInWallet == null) {
			throw new IllegalArgumentException("There are no coins of denomination " + coin.getValue() + " in the wallet");
		}
		coins.put(coin, coinsInWallet.intValue() - 1);
	}
	
	public Integer getCoinsAmount(Coin coin) {
		return coins.get(coin);
	}
	
	public Wallet() {
		this.coins = new LinkedHashMap<Coin, Integer>();
	}
	
	public Wallet(Wallet wallet) {
		this.coins = new LinkedHashMap<Coin, Integer>(wallet.coins);
	}
	
	public Map<Coin, Integer> getCoins() {
		return coins;
	}

	public void setCoins(Coin coin, int amount) {
		coins.put(coin, amount);
	}
	
	public void addCoin(int denom) {
		Coin coin = Coin.fromValue(denom);
		addCoin(coin);
	}
	
	public void spendCoin(int denom) {
		Coin coin = Coin.fromValue(denom);
		spendCoin(coin);
	}
	
	public Integer getCoinsAmount(int denom) {
		Coin coin = Coin.fromValue(denom);
		return getCoinsAmount(coin);
	}
	
}
