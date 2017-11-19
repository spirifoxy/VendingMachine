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
	
	private void addCoin(Coin coin) {
		coins.put(coin, coins.get(coin) + 1);
	}
	
	private void spendCoin(Coin coin) {
		if (getCoinsAmount(coin) == 0) {
			throw new IllegalArgumentException("There are no coins of denomination " + coin.getValue() + " in the wallet");
		}
		coins.put(coin, coins.get(coin) - 1);
	}
	
	private int getCoinsAmount(Coin coin) {
		return coins.get(coin);
	}
	
	public Wallet() {
		this.coins = new LinkedHashMap<Coin, Integer>();
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
	
	public int getCoinsAmount(int denom) {
		Coin coin = Coin.fromValue(denom);
		return getCoinsAmount(coin);
	}
}
