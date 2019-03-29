package it.contrader.dto;

public class thingsDTO {
	private int idthing;
	private int iduser;
	private int idlabel;
	private String Nome;
	
	public thingsDTO(String name) {
		this.Nome = name;
	}
	
	public thingsDTO(String name, int iduser, int idlabel) {
		this.Nome = name;
		this.iduser = iduser;
		this.idlabel = idlabel;
	}

	public thingsDTO() {}

	public int getIdthing() {
		return idthing;
	}

	public void setIdthing(int idthing) {
		this.idthing = idthing;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public int getIdlabel() {
		return idlabel;
	}

	public void setIdlabel(int idlabel) {
		this.idlabel = idlabel;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	} 
}