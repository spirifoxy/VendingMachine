package spirifoxy.com.github.vmtest.Model.interfaces;

public interface HasMoney {
	
	void addCoin(int denom);
	
	void spendCoin(int denom);
	
	int getCoinsAmount(int denom);
}
