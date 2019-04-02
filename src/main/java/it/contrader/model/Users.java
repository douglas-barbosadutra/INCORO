package it.contrader.model;

/**
 * Classe Model di esempio
 *
 */
public class Users {

	/**
	 * I campi che sono attributi di una certa tabella che vogliamo rappresentare
	 * <br>
	 * Possiamo avere n colonne
	 */
	private int idUser;
	private String username;
	private String password;
	private int type;

	/**
	 * Costruttore con parametri
	 */
	public Users(int id, String username, String password, int ruolo) {
		super();
		this.idUser = id;
		this.username = username;
		this.password = password;
		this.type = ruolo;	
	}

	/**
	 * Metodi setter e getter che ci permettono di recuperare le informazioni del
	 * model o di settarle
	 */
	public Integer getId() {
		return idUser;
	}

	public void setId(int id) {
		this.idUser = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}

	public void setType(int ruolo) {
		this.type = ruolo;
	}
}