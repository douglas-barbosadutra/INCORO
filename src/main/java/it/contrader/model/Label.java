package it.contrader.model;

public class Label {
	private int idlabel;
	private int idusers;
	private String nameLabel;

	public Label() {
	}

	public Label(String Labelname) {
		this.nameLabel = Labelname;
	}
	
	public int getIdlabel() {
		return idlabel;
	}

	public void setIdlabel(int idLabel) {
		this.idlabel = idLabel;
	}

	public int getIdusers() {
		return idusers;
	}

	public void setIdusers(int idusers) {
		this.idusers = idusers;
	}

	public String getName() {
		return nameLabel;
	}

	public void setName(String nomeLabel) {
		this.nameLabel = nomeLabel;
	}

	@Override
	public String toString() {
		return this.getIdlabel() + "\t" + this.getName() + "\t" + this.getIdusers();
	}

	public boolean equals(Label LabelCompare) {
		if (!this.getName().equals(LabelCompare.getName())) {
			return false;
		}
		return true;
	}
}