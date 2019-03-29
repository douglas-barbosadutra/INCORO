package it.contrader.model;

public class Things {
	private int idthing;
	private String name;
	private int iduser;
	private int idlabel;

	public Things() {
	}

	public Things(String thingsname) {
		this.name = thingsname;
	}

	public Things(String thingsname, int idUser, int idLabel) {
		this.name = thingsname;
		this.iduser = idUser;
		this.idlabel = idLabel;
	}
	
	public int getIdthing() {
		return idthing;
	}

	public void setIdthing(int idthing) {
		this.idthing = idthing;
	}

	public String getName() {
		return name;
	}

	public void setNome(String name) {
		this.name = name;
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
		return this.getIdthing() + "\t" + this.getName() + "\t" + this.getIdlabel() + "\t" + this.getIduser();
	}

	public boolean equals(Things ThingsCompare) {
		if (!this.getName().equals(ThingsCompare.getName())) {
			return false;
		}
		return true;

	}

}