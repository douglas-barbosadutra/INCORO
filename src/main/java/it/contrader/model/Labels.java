package it.contrader.model;

public class Labels {
	
	
	
	/**
	 * I campi che sono attributi di una certa tabella che vogliamo rappresentare
	 * <br>
	 * Possiamo avere n colonne
	 */
	private Integer idLabel;
	private String name;
	private int fktouser;

	/**
	 * Costruttore con parametri
	 */
	public Labels(Integer id, String name,  int fktouser) {
		super();
		this.idLabel = id;
		this.name = name;
		this.fktouser = fktouser;
		

		
	}

	/**
	 * Metodi setter e getter che ci permettono di recuperare le informazioni del
	 * model o di settarle
	 */
	public Integer getId() {
		return idLabel;
	}

	public void setId(Integer id) {
		this.idLabel = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getFktouser() {
		return fktouser;
	}

	public void setFktouser(int fktouser) {
		this.fktouser = fktouser;
	}
	
	
	
	

}
