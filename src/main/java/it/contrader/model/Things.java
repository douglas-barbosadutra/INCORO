package it.contrader.model;

public class Things {
	private int idthing;
	private String Nome;
	private int iduser;
	private int idlabel;

	public Things() {
	}

	public Things(String thingsname) {
		this.Nome = thingsname;
	}

	public int getIdthing() {
		return idthing;
	}

	public void setIdthing(int idthing) {
		this.idthing = idthing;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
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

	@Override
	public String toString() {
		return this.getIdthing() + "\t" + this.getNome() + "\t" + this.getIdlabel() + "\t" + this.getIduser();
	}

	public boolean equals(Things ThingsCompare) {
		if (!this.getNome().equals(ThingsCompare.getNome())) {
			return false;
		}
		return true;

	}

}