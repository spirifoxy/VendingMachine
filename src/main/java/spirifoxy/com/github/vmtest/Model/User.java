package spirifoxy.com.github.vmtest.Model;

import spirifoxy.com.github.vmtest.Model.Wallet.Coin;
import spirifoxy.com.github.vmtest.Model.interfaces.HasMoney;

public class User implements HasMoney {
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
