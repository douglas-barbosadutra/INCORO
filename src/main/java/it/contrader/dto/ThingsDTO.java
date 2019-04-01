package it.contrader.dto;

public class ThingsDTO {
	private int idThing;
	private String name;
	private int fktouser;
	private int fktolabel;
	
	public ThingsDTO(int id, String name, int fktouser, int fktolabel) {
		super();
		this.idThing = id;
		this.name = name;
		this.fktouser = fktouser;
		this.fktolabel = fktolabel;
	}

	public int getId() {
		return idThing;
	}

	public void setId(int id) {
		this.idThing = id;
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
	public int getFktolabel() {
		return fktolabel;
	}

	public void setFktolabel(int fktolabel) {
		this.fktolabel = fktolabel;
	}

}


