package it.contrader.model;

public class Label {
	private int LabelId;
	private String Labelname;

	public Label() {
	}

	public Label(String Labelname, String password, int Labeltype) {
		this.Labelname = Labelname;
	}

	public int getLabelId() {
		return LabelId;
	}
	public void setLabelId(int LabelId) {
		this.LabelId = LabelId;
	}

	public void setLabelname(String Labelname) {
		this.Labelname = Labelname;
	}

	public String getLabelname() {
		return Labelname;
	}

	@Override
	public String toString() {
		return this.getLabelId() + "\t" + this.getLabelname();
	}

	public boolean equals(Label LabelCompare) {
		if (!this.getLabelname().equals(LabelCompare.getLabelname())) {
			return false;
		}
		return true;
	}
}
