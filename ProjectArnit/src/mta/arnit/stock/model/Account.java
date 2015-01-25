package mta.arnit.stock.model;

public class Account {

	public static final float DEFAULT_BALANCE = 10000;
	
	private String username;
	private String password;
	private float balance;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public float getBalance() {
		return balance;
	}
	
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
}
