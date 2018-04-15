package e_shopping.beans;

public class UserInfoUnpayedOrders {
	private User user;
	private int amount;
	private double sum;
	
	public UserInfoUnpayedOrders() {}
	
	public UserInfoUnpayedOrders(User user, int amount, double sum) {
		this.user = user;
		this.amount = amount;
		this.sum = sum;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAmount() {
		return amount;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public double getSum() {
		return sum;
	}
}
