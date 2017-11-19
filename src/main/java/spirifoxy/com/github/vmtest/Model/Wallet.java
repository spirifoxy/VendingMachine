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
		
		public int value() {
			return value;
		}
		
	}
	
	private Map<Integer, Integer> coins;
	
	public Wallet() {
		this.coins = new LinkedHashMap<Integer, Integer>();
	}
	
	public Map<Integer, Integer> getCoins() {
		return coins;
	}

	public void setCoins(Coin coin, int amount) {
		coins.put(coin.value(), amount);
	}
	
	public void addCoin(Coin coin) {
		coins.put(coin.value(), coins.get(coin.value()) + 1);
	}
	
	
}
