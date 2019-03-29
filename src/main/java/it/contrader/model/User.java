package it.contrader.model;

public class User {
	private int idUsers;
	private String username;
	private String password;
	private Integer tipo;

	public User() {
	}

	public User(String username, String password, Integer tipo) {
		this.username = username;
		this.password = password;
		this.tipo = tipo;
	}

	public int getidUsers() {
		return idUsers;
	}
	public void setidUsers(int idUsers) {
		this.idUsers = idUsers;
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

	public Integer gettipo() {
		return tipo;
	}

	public void settipo(Integer tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return this.getidUsers() + "\t" + this.getUsername() + "\t" + this.getPassword() + "\t" + this.gettipo().toString();
	}

	public boolean equals(User userCompare) {
		if (!this.getUsername().equals(userCompare.getUsername())) {
			return false;
		}

		if (!this.getPassword().equals(userCompare.getPassword())) {
			return false;
		}
		if (!this.gettipo().equals(userCompare.gettipo())) {
			return false;
		}
		return true;

	}
}