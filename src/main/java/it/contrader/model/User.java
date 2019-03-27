package it.contrader.model;

public class User {
	private int userId;
	private String username;
	private String password;
	private Integer usertype;

	public User() {
	}

	public User(String username, String password, Integer usertype) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	@Override
	public String toString() {
		return this.getUserId() + "\t" + this.getUsername() + "\t" + this.getPassword() + "\t" + this.getUsertype().toString();
	}

	public boolean equals(User userCompare) {
		if (!this.getUsername().equals(userCompare.getUsername())) {
			return false;
		}

		if (!this.getPassword().equals(userCompare.getPassword())) {
			return false;
		}
		if (!this.getUsertype().equals(userCompare.getUsertype())) {
			return false;
		}
		return true;

	}

}
