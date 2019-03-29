package it.contrader.dto;

public class UserDTO {
	private int userId;
	private String username;
	private String password;
	private Integer usertype;

	public UserDTO(String username, String password, Integer usertype) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}
	
	public UserDTO() {} 
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
}