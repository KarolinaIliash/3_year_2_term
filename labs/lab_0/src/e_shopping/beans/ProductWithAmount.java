package e_shopping.beans;

public class ProductWithAmount {
	private Product product;
	private int amount;
	
	public ProductWithAmount() {}
	
	public ProductWithAmount(Product product, int amount) {
		this.product = product;
		this.amount = amount;
	}
	
	
	@Override
	public boolean equals(Object object) {
		
		if(object.getClass().equals(this.getClass())) {
			Product objProd = ((ProductWithAmount)object).product;
			
			return product.getId() == objProd.getId();
		}
		
		return false;
	}
	
	@Override 
	public int hashCode() {
		return product.getId();
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
