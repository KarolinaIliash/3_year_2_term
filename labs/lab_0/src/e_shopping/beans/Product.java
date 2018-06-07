package e_shopping.beans;

public class Product {
	private int id;
	private String name;
	private String nameUA;
	private int priceBeforePoint;
	private int priceAfterPoint;
	//private int categoryId;
	
	public Product() {}
	
	public Product(int id, String name, String nameUA, int priceBeforePoint, int priceAfterPoint) {
		this.id = id;
		this.name = name;
		this.nameUA = nameUA;
		this.priceBeforePoint = priceBeforePoint;
		this.priceAfterPoint = priceAfterPoint;
	}
	
	public Product(String name, String nameUA, int priceBeforePoint, int priceAfterPoint) {
		this.name = name;
		this.nameUA = nameUA;
		this.priceBeforePoint = priceBeforePoint;
		this.priceAfterPoint = priceAfterPoint;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameUA() {
		return nameUA;
	}
	public void setNameUA(String name) {
		nameUA = name;
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
	
	public double getPrice() {
		String priceStr = priceBeforePoint + "." + priceAfterPoint;
		double price = Double.parseDouble(priceStr);
		return price;
	}
	/*public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}*/
}
