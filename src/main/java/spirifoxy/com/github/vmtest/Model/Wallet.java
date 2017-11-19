package spirifoxy.com.github.vmtest.Model;

import java.util.HashMap;
import java.util.Map;

public class Wallet {

	enum Coin {
		ONE(1), TWO(2), FIVE(5), TEN(10);
		
		private final int value;
		
		Coin(int value) {
			this.value = value;
		}
		
		int value() {
			return value;
		}
		
	}
	
	private Map<Coin, Integer> coins;
	
	Wallet() {
		setCoins(new HashMap<Coin, Integer>());
	}

	private void setCoins(Map<Coin, Integer> coins) {
		this.coins = coins;
	}
	
	public Map<Coin, Integer> getCoins() {
		return coins;
	}

	public void addCoin(Coin coin) {
		coins.put(coin, coins.get(coin) + 1);
	}
	
	
}
