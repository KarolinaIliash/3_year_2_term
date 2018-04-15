package e_shopping.beans;

import java.sql.Date;
import java.util.List;

public class Order {
	private int id;
	private Date date;
	private String userPhone;
	private int priceBeforePoint;
	private int priceAfterPoint;
	private boolean isPayed;
	
	private double curPrice = 0;
	
	private List<ProductWithAmount> products;
	
	public Order() {}
	
	public Order(int id, Date date, String userPhone, int priceBeforePoint,
			     int priceAfterPoint, boolean isPayed, List<ProductWithAmount> products) {
		this.id = id;
		this.date = date;
		this.userPhone = userPhone;
		this.priceBeforePoint = priceBeforePoint;
		this.priceAfterPoint = priceAfterPoint;
		this.isPayed = isPayed;
		this.products = products;
		
		curPrice = calculateSum();
	}
	
	public Order(Date date, String userPhone, int priceBeforePoint,
		     int priceAfterPoint, boolean isPayed, List<ProductWithAmount> products) {
	this.date = date;
	this.userPhone = userPhone;
	this.priceBeforePoint = priceBeforePoint;
	this.priceAfterPoint = priceAfterPoint;
	this.isPayed = isPayed;
	this.products = products;
	
	curPrice = calculateSum();
}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public int getPriceBeforePoint() {
		return priceBeforePoint;
	}
	public void setPriceBeforePoint(int price) {
		priceBeforePoint = price;
	}
	public int getPriceAfterPoint() {
		return priceAfterPoint;
	}
	public void setPriceAfterPoint(int price) {
		priceAfterPoint = price;
	}
	public boolean getIsPayed() {
		return isPayed;
	}
	public void setIsPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}
	public List<ProductWithAmount> getProducts(){
		return products;
	}
	public void setProducts(List<ProductWithAmount> products) {
		this.products = products;
		
		curPrice = calculateSum();
	}
	public double getCurPrice(){
		return curPrice;
	}
	public void setCurPrice(double curPrice) {
		this.curPrice = curPrice;
	}
	
	public double calculateSum() {
		double sum = 0;
		for(ProductWithAmount prAmount : products) {
			sum += prAmount.getAmount() * prAmount.getProduct().getPrice();
		}
		return sum;
	}
}
