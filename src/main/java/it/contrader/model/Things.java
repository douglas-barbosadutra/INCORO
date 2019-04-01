package it.contrader.model;

public class Things {

	/**
	 * I campi che sono attributi di una certa tabella che vogliamo rappresentare
	 * <br>
	 * Possiamo avere n colonne
	 */
	private Integer idThing;
	private String name;
	private int fktouser;
	private int fktolabel;

	/**
	 * Costruttore con parametri
	 */
	public Things(Integer id, String name, int fktouser, int fktolabel) {
		super();
		this.idThing = id;
		this.name = name;
		this.fktouser = fktouser;
		this.fktolabel = fktolabel;

		
	}

	/**
	 * Metodi setter e getter che ci permettono di recuperare le informazioni del
	 * model o di settarle
	 */
	public Integer getId() {
		return idThing;
	}

	public void setId(Integer id) {
		this.idThing = id;
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
	public int getFktolabel() {
		return fktolabel;
	}

	public void setFktolabel(int fktolabel) {
		this.fktolabel = fktolabel;
	}	
}
