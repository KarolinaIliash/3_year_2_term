package e_shopping.beans;

public class User {
	private String firstName;
	private String lastName;
	private String phone;
	private String password;
	private boolean inBlackList;
	
	
	public User() {}
	
	public User(String firstName, String lastName, String phone, String password,
			    boolean inBlackList) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.password = password;
		this.inBlackList = inBlackList;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isInBlackList() {
		return inBlackList;
	}
	public void setInBlackList(boolean inBlackList) {
		this.inBlackList = inBlackList;
	}
}
