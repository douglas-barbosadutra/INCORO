package it.contrader.model;

public class Things {
	private int ThingsId;
	private String Thingsname;

	public Things() {
	}

	public Things(String Thingsname, String password, int Thingstype) {
		this.Thingsname = Thingsname;
	}

	public int getThingsId() {
		return ThingsId;
	}
	public void setThingsId(int ThingsId) {
		this.ThingsId = ThingsId;
	}

	public void setThingsname(String Thingsname) {
		this.Thingsname = Thingsname;
	}

	public String getThingsname() {
		return Thingsname;
	}

	@Override
	public String toString() {
		return this.getThingsId() + "\t" + this.getThingsname();
	}

	public boolean equals(Things ThingsCompare) {
		if (!this.getThingsname().equals(ThingsCompare.getThingsname())) {
			return false;
		}
		return true;

	}

}