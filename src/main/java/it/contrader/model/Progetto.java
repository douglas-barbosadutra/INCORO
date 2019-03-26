package it.contrader.model;

public class Progetto {
	
	private int idProgetto;
	private String nomeProgetto;
	
	public Progetto() {
	}

	public Progetto(int idProgetto, String nomeProgetto) {
		this.idProgetto = idProgetto;
		this.nomeProgetto = nomeProgetto;
	}

	public int getIdProgetto() {
		return idProgetto;
	}

	public void setIdProgetto(int idProgetto) {
		this.idProgetto = idProgetto;
	}

	public String getNomeProgetto() {
		return nomeProgetto;
	}

	public void setNomeProgetto(String nomeProgetto) {
		this.nomeProgetto = nomeProgetto;
	}


}



