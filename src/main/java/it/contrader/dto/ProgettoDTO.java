package it.contrader.dto;

public class ProgettoDTO {

	private int idprogetto;
	private String nomeprogetto;
	

	public ProgettoDTO(int idprogetto, String nomeprogetto) {
		this.idprogetto = idprogetto;
		this.nomeprogetto = nomeprogetto;
		
	}
	
	public ProgettoDTO() {} 
	
	public int getIdProgetto() {
		return idprogetto;
	}

	public void setIdProgetto(int idprogetto) {
		this.idprogetto = idprogetto;
	}

	public String getNomeProgetto() {
		return nomeprogetto;
	}

	public void setNomeProgetto(String nomeprogetto) {
		this.nomeprogetto = nomeprogetto;
	}

}


