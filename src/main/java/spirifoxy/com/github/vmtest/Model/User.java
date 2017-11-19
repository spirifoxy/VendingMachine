package spirifoxy.com.github.vmtest.Model;

import spirifoxy.com.github.vmtest.Model.Wallet.Coin;

public class User {
	private Wallet wallet;
	
	public User() {
		wallet = new Wallet();
		
		initializeWallet();
	}
	
	private void initializeWallet() {
		wallet.setCoins(Coin.ONE, 10);
		wallet.setCoins(Coin.TWO, 30);
		wallet.setCoins(Coin.FIVE, 20);
		wallet.setCoins(Coin.TEN, 15);
	}

	public Wallet getWallet() {
		return wallet;
	}
	
	
}
