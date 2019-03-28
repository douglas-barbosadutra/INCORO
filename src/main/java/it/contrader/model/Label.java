package it.contrader.model;

public class Label {
	private int idLabel;
	private int idusers;
	private String nomeLabel;

	public Label() {
	}

	public Label(String Labelname) {
		this.nomeLabel = Labelname;
	}

	

	public int getIdLabel() {
		return idLabel;
	}

	public void setIdLabel(int idLabel) {
		this.idLabel = idLabel;
	}

	public int getIdusers() {
		return idusers;
	}

	public void setIdusers(int idusers) {
		this.idusers = idusers;
	}

	public String getNomeLabel() {
		return nomeLabel;
	}

	public void setNomeLabel(String nomeLabel) {
		this.nomeLabel = nomeLabel;
	}

	@Override
	public String toString() {
		return this.getIdLabel() + "\t" + this.getNomeLabel() + "\t" + this.getIdusers();
	}

	public boolean equals(Label LabelCompare) {
		if (!this.getNomeLabel().equals(LabelCompare.getNomeLabel())) {
			return false;
		}
		return true;
	}
}
