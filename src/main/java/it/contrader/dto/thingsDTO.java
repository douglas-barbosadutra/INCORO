
package it.contrader.dto;

public class thingsDTO {
	private int userId;
	private String name;
	
	public thingsDTO(int userId, String name) {
		this.userId = userId;
		this.name = name;
	}
	
	public thingsDTO() {} 
	
	public int getThingsId() {
		return userId;
	}

	public void setThingsId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}