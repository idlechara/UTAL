package Proyecto;

public class User {
	private UserType type;
	String password;
	String username;
	
	public User(UserType type, String password, String username) {
		this.type = type;
		this.password = password;
		this.username = username;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
