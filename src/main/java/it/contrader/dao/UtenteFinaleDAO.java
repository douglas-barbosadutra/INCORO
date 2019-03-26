package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import it.contrader.controller.GestoreEccezioni;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.UtenteFinale;

public class UtenteFinaleDAO {

	private final String QUERY_ALL = "SELECT * FROM tab_utente_finale";
	private final String QUERY_INSERT = "denominazione_societa, forma_giuridica, sede_legale, partita_iva, telefono, e_mail, indirizzo_unita_locale, attivita_azienda, legale_rappresentante, nato_a, nato_il, id_utente) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM tab_utente_finale WHERE partita_iva = ?";

	private final String QUERY_UPDATE = "UPDATE tab_utente_finale SET denominazione_societa = ?, forma_giuridica = ?, sede_legale = ?, partita_iva = ?, telefono = ?, e_mail = ?, indirizzo_unita_locale = ?, attivita_azienda = ?, legale_rappresentante = ?, nato_a = ?, nato_il = ?, id_utente = ? WHERE partita_iva = ?";
	private final String QUERY_DELETE = "DELETE FROM tab_utente_finale WHERE partita_iva = ?";

	public UtenteFinaleDAO() {

	}

	public List<UtenteFinale> getAllUtenteFinale() {
		List<UtenteFinale> utenteFinaleList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			UtenteFinale utenteFinale;
			while (resultSet.next()) {
				String denominazioneSocieta= resultSet.getString("denominazione_societa");
				String formaGiuridica = resultSet.getString("forma_giuridica");
				String sedeLegale = resultSet.getString("sede_legale");
				String partitaIva = resultSet.getString("partita_iva");
				String telefono = resultSet.getString("telefono");
				String email = resultSet.getString("e_mail");
				String indirizzoUnitaLocale = resultSet.getString("indirizzo_unita_locale");
				String attivitaAzienda = resultSet.getString("attivita_azienda");
				String legaleRappresentante = resultSet.getString("legale_rappresentante");
				String natoA = resultSet.getString("nato_a");
				Date natoIl = resultSet.getDate("nato_il");
				int idUtente = resultSet.getInt("id_utente");
				utenteFinale = new UtenteFinale(denominazioneSocieta, formaGiuridica, sedeLegale,
						partitaIva, telefono, email, indirizzoUnitaLocale, attivitaAzienda,
						legaleRappresentante, natoA, natoIl, idUtente);
				utenteFinaleList.add(utenteFinale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utenteFinaleList;
	}

	public boolean insertUtenteFinale(UtenteFinale utenteFinale) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			preparedStatement.setString(1, utenteFinale.getDenominazioneSocieta());
			preparedStatement.setString(2, utenteFinale.getFormaGiuridica());
			preparedStatement.setString(3, utenteFinale.getSedeLegale());
			preparedStatement.setString(4, utenteFinale.getPartitaIva());
			preparedStatement.setString(5, utenteFinale.getTelefono());
			preparedStatement.setString(6, utenteFinale.getEmail());
			preparedStatement.setString(7, utenteFinale.getIndirizzoUnitaLocale());
			preparedStatement.setString(8, utenteFinale.getAttivitaAzienda());
			preparedStatement.setString(9, utenteFinale.getLegaleRappresentante());
			preparedStatement.setString(10, utenteFinale.getNatoA());
			preparedStatement.setDate(11, utenteFinale.getNatoIl());
			preparedStatement.setInt(12, utenteFinale.getIdUtente());
			
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}

	}

	public UtenteFinale readUtenteFinale(String partitaIva) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setString(1, partitaIva);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();

			String denominazioneSocieta= resultSet.getString("denominazione_societa");
			String formaGiuridica = resultSet.getString("forma_giuridica");
			String sedeLegale = resultSet.getString("sede_legale");
			String telefono = resultSet.getString("telefono");
			String email = resultSet.getString("e_mail");
			String indirizzoUnitaLocale = resultSet.getString("indirizzo_unita_locale");
			String attivitaAzienda = resultSet.getString("attivita_azienda");
			String legaleRappresentante = resultSet.getString("legale_rappresentante");
			String natoA = resultSet.getString("nato_a");
			Date natoIl = resultSet.getDate("nato_il");
			int idUtente = resultSet.getInt("id_utente");
			UtenteFinale utenteFinale = new UtenteFinale(denominazioneSocieta, formaGiuridica, sedeLegale,
					partitaIva, telefono, email, indirizzoUnitaLocale, attivitaAzienda, legaleRappresentante, natoA, natoIl, idUtente);

			return utenteFinale;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return null;
		}

	}

	public boolean updateUtenteFinale(UtenteFinale utenteFinaleToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (utenteFinaleToUpdate.getPartitaIva() == null || utenteFinaleToUpdate.getPartitaIva().equals(""))
			return false;

		UtenteFinale utenteFinaleRead = readUtenteFinale(utenteFinaleToUpdate.getPartitaIva());
		if (!utenteFinaleRead.equals(utenteFinaleToUpdate)) {
			try {
				// Fill the utenteFinaleToUpdate object
				if (utenteFinaleToUpdate.getDenominazioneSocieta() == null || utenteFinaleToUpdate.getDenominazioneSocieta().equals("")) {
					utenteFinaleToUpdate.setDenominazioneSocieta(utenteFinaleRead.getDenominazioneSocieta());
				}
				
				if (utenteFinaleToUpdate.getFormaGiuridica() == null || utenteFinaleToUpdate.getFormaGiuridica().equals("")) {
					utenteFinaleToUpdate.setFormaGiuridica(utenteFinaleRead.getFormaGiuridica());
				}
				
				if (utenteFinaleToUpdate.getSedeLegale() == null || utenteFinaleToUpdate.getSedeLegale().equals("")) {
					utenteFinaleToUpdate.setSedeLegale(utenteFinaleRead.getSedeLegale());
				}
				
				if (utenteFinaleToUpdate.getTelefono() == null || utenteFinaleToUpdate.getTelefono().equals("")) {
					utenteFinaleToUpdate.setTelefono(utenteFinaleRead.getTelefono());
				}
				
				if (utenteFinaleToUpdate.getEmail() == null || utenteFinaleToUpdate.getEmail().equals("")) {
					utenteFinaleToUpdate.setEmail(utenteFinaleRead.getEmail());
				}
				
				if (utenteFinaleToUpdate.getIndirizzoUnitaLocale() == null || utenteFinaleToUpdate.getIndirizzoUnitaLocale().equals("")) {
					utenteFinaleToUpdate.setIndirizzoUnitaLocale(utenteFinaleRead.getIndirizzoUnitaLocale());
				}
				
				if (utenteFinaleToUpdate.getAttivitaAzienda() == null || utenteFinaleToUpdate.getAttivitaAzienda().equals("")) {
					utenteFinaleToUpdate.setAttivitaAzienda(utenteFinaleRead.getAttivitaAzienda());
				}
				
				if (utenteFinaleToUpdate.getLegaleRappresentante() == null || utenteFinaleToUpdate.getLegaleRappresentante().equals("")) {
					utenteFinaleToUpdate.setLegaleRappresentante(utenteFinaleRead.getLegaleRappresentante());
				}
				
				if (utenteFinaleToUpdate.getNatoA() == null || utenteFinaleToUpdate.getNatoA().equals("")) {
					utenteFinaleToUpdate.setNatoA(utenteFinaleRead.getNatoA());
				}
				
				if (utenteFinaleToUpdate.getNatoIl() == null || utenteFinaleToUpdate.getNatoIl().equals("")) {
					utenteFinaleToUpdate.setNatoIl(utenteFinaleRead.getNatoIl());
				}
				
				if (utenteFinaleToUpdate.getIdUtente() == 0) {
					utenteFinaleToUpdate.setIdUtente(utenteFinaleRead.getIdUtente());
				}
				
				// Update the utenteFinale
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, utenteFinaleToUpdate.getDenominazioneSocieta());
				preparedStatement.setString(2, utenteFinaleToUpdate.getFormaGiuridica());
				preparedStatement.setString(3, utenteFinaleToUpdate.getSedeLegale());
				preparedStatement.setString(4, utenteFinaleToUpdate.getPartitaIva());
				preparedStatement.setString(5, utenteFinaleToUpdate.getTelefono());
				preparedStatement.setString(6, utenteFinaleToUpdate.getEmail());
				preparedStatement.setString(7, utenteFinaleToUpdate.getIndirizzoUnitaLocale());
				preparedStatement.setString(8, utenteFinaleToUpdate.getAttivitaAzienda());
				preparedStatement.setString(9, utenteFinaleToUpdate.getLegaleRappresentante());
				preparedStatement.setString(10, utenteFinaleToUpdate.getNatoA());
				preparedStatement.setDate(11, utenteFinaleToUpdate.getNatoIl());
				preparedStatement.setInt(12, utenteFinaleToUpdate.getIdUtente());
				int a = preparedStatement.executeUpdate();
				if (a > 0)
					return true;
				else
					return false;

			} catch (SQLException e) {
				return false;
			}
		}

		return false;
		
	}

	public boolean deleteUtenteFinale(String partitaIva) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setString(1, partitaIva);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;
		} catch (SQLException e) {
		}
		return false;
	}
}
