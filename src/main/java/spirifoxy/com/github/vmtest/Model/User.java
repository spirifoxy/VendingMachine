package spirifoxy.com.github.vmtest.Model;

public class User {
	private Wallet wallet;
	
	User() {
		wallet = new Wallet();
		
		initializeWallet();
	}
	
	private void initializeWallet() {
		wallet.getCoins().put(Wallet.Coin.ONE, 10);
		wallet.getCoins().put(Wallet.Coin.TWO, 30);
		wallet.getCoins().put(Wallet.Coin.FIVE, 20);
		wallet.getCoins().put(Wallet.Coin.TEN, 15);
	}

	public Wallet getWallet() {
		return wallet;
	}
	
	
}
