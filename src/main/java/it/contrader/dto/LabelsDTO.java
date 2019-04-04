package it.contrader.dto;

public class LabelsDTO {
	private int idLabel;
	private String name;
	private int fktouser;
	
	
	public LabelsDTO(int id, String name, int fktouser) {
		super();
		this.idLabel = id;
		this.name = name;	
		this.fktouser = fktouser;
	}

	public LabelsDTO(String name, Integer fktouser) {	
		this.name = name;
		this.fktouser = fktouser;
	}

	public int getId() {
		return idLabel;
	}

	public void setId(int id) {
		this.idLabel = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getFktouser() {
		return fktouser;
	}

	public void setFktouser(int fktouser) {
		this.fktouser = fktouser;
	}
}