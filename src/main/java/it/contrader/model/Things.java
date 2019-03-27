package it.contrader.model;

public class Things {
	private int thingsId;
	private String thingsname;

	public Things() {
	}

	public Things(String thingsname) {
		this.thingsname = thingsname;
	}

	public int getThingsId() {
		return thingsId;
	}
	public void setThingsId(int thingsId) {
		this.thingsId = thingsId;
	}

	public void setThingsname(String thingsname) {
		this.thingsname = thingsname;
	}

	public String getThingsname() {
		return thingsname;
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