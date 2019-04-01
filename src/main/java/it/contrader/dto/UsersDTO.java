package it.contrader.dto;

/**
 * Il DTO (Data transfer object) è un ponte che ci permette di nascondere le
 * informazioni principali del nostro model
 * 
 */
public class UsersDTO {

	private int idUser;
	private String username;
	private String password;
	private int type;
	
	public UsersDTO(int id, String username, String password, int type) {
		super();
		this.idUser = id;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public int getId() {
		return idUser;
	}

	public void setId(int id) {
		this.idUser = id;
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
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}