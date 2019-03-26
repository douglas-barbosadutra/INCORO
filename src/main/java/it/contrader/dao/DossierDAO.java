package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.contrader.controller.GestoreEccezioni;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Dossier;
import it.contrader.model.User;

public class DossierDAO {
	
	private final String QUERY_ALL= "select * from tab_dossier";
	private final String QUERY_INSERT= "insert into tab_dossier (periodo_di_imposta, costo_dipendenti_periodo_imposta, fatturato_periodo_di_imposta, numero_totale_dipendenti, costo_complessivo_attivita, costo_personale,pi_utente_finale, id_progetto ) values (?,?,?,?,?,?,?,?)";
	private final String QUERY_READ= "select * from tab_dossier where id=?";
	 private final String QUERY_UPDATE = "UPDATE tab_dossier SET periodo_di_imposta=?, costo_dipendenti_periodo_imposta=?, fatturato_periodo_di_imposta=?, numero_totale_dipendenti=?, costo_complessivo_attivita=?, costi_personale=?, pi_utente_finale=?, id_progetto=? WHERE pi_utente_finale=?";
	 private final String QUERY_DELETE = "DELETE FROM tab_dossier WHERE pi_utente_finale=?";
	 
	 
	 public DossierDAO()  {
		 
	 }
			 
	
	 public List<Dossier> getAllDossier() {
			List<Dossier> dossierList = new ArrayList<>();
			Connection connection = ConnectionSingleton.getInstance();
			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(QUERY_ALL);
				Dossier dossier;
				while (resultSet.next()) {
					int costoDipendenti = resultSet.getInt("costo_dipendenti_periodo_imposta");
					double fatturatoPeriodoDiImposta = resultSet.getDouble("fatturato_periodo_di_imposta");
					int numeroDipendenti = resultSet.getInt("numero_totale_dipendenti");
					double costiAttivitaRd = resultSet.getDouble("costo_complessivo_attivita");
					double costiPersonaleRd = resultSet.getDouble("costo_personale");
					String partitaIva = resultSet.getString("pi_utente_finale");
					int idProgetto = resultSet.getInt("id_progetto");
					int idDossier = resultSet.getInt("id");
					String periodoDiImposta = resultSet.getString("perido_di_imposta");
					dossier = new Dossier(periodoDiImposta, costoDipendenti, fatturatoPeriodoDiImposta, numeroDipendenti, costiAttivitaRd, costiPersonaleRd, partitaIva, idProgetto, idDossier);
					dossier.setPartitaIva(partitaIva);
					dossierList.add(dossier);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dossierList;
		}
	
	 public boolean insertDossier(Dossier dossier) {
			Connection connection = ConnectionSingleton.getInstance();
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
				preparedStatement.setString(1, dossier.getPeriodoDiImposta());
				preparedStatement.setDouble(2, dossier.getCostoDipendenti());
				preparedStatement.setDouble(3, dossier.getFatturatoPeriodoDiImposta());
				preparedStatement.setInt(4, dossier.getNumeroDipendenti());
				preparedStatement.setDouble(5, dossier.getCostiAttivitaRd());
				preparedStatement.setDouble(6, dossier.getCostiPersonaleRd());
				preparedStatement.setString(7, dossier.getPartitaIva());
				preparedStatement.setInt(8, dossier.getIdProgetto());
				
				preparedStatement.execute();
				return true;
			} catch (SQLException e) {
				GestoreEccezioni.getInstance().gestisciEccezione(e);
				return false;
			}

		}
	 

		public Dossier readDossier(int id) {
			Connection connection = ConnectionSingleton.getInstance();
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
				preparedStatement.setInt(1, id);
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.next();
				int costoDipendenti;
				double fatturatoPeriodoDiImposta;
				int numeroDipendente;
				double costiAttiviRd;
				double costiPersonaleRd;
				String periodoDiImposta;
				int idProgetto;
				String partitaIva = resultSet.getString("pi_utente_finale");
				costoDipendenti = resultSet.getInt("costo_dipendenti_periodo_di_imposta");
				fatturatoPeriodoDiImposta = resultSet.getDouble("fatturato_perido_di_imposta");
				numeroDipendente = resultSet.getInt("numeor_totale_dipendenti");
				costiAttiviRd = resultSet.getDouble("costo_complessivo_attivita");
				costiPersonaleRd = resultSet.getDouble("costo_personale");
				periodoDiImposta = resultSet.getString("periodo_di_imposta");
				partitaIva = resultSet.getString("pi_utente_finale"); 
				idProgetto = resultSet.getInt("id_progetto");
				Dossier dossier = new Dossier(periodoDiImposta, costoDipendenti, fatturatoPeriodoDiImposta, numeroDipendente, costiAttiviRd, costiPersonaleRd, partitaIva, idProgetto, id);

				return dossier;
			} catch (SQLException e) {
				GestoreEccezioni.getInstance().gestisciEccezione(e);
				return null;
			}

		}
		
		public boolean updateD(Dossier dossierToUpdate) {
			Connection connection = ConnectionSingleton.getInstance();

			// Check if id is present
			if (dossierToUpdate.getIdDossier() == 0)
				return false;

			Dossier dossierRead = readDossier(dossierToUpdate.getIdDossier());
			if (!dossierRead.equals(dossierToUpdate)) {
				try {
					// Fill the userToUpdate object
					if (dossierToUpdate.getCostoDipendenti() == 0) {
						dossierToUpdate.setCostoDipendenti(dossierRead.getCostoDipendenti());
					}
					
					if (dossierToUpdate.getFatturatoPeriodoDiImposta() == 0) {
						dossierToUpdate.setFatturatoPeriodoDiImposta(dossierRead.getFatturatoPeriodoDiImposta());
					}
					
					if (dossierToUpdate.getNumeroDipendenti() == 0) {
						dossierToUpdate.setNumeroDipendenti(dossierRead.getNumeroDipendenti());
					}
					
					if (dossierToUpdate.getCostiAttivitaRd() == 0) {
						dossierToUpdate.setCostiAttivitaRd(dossierRead.getCostiAttivitaRd());
					}
					
					if (dossierToUpdate.getCostiPersonaleRd() == 0) {
						dossierToUpdate.setCostiPersonaleRd(dossierRead.getCostiPersonaleRd());
					}
					
					if (dossierToUpdate.getPartitaIva() == null || dossierToUpdate.getPartitaIva().equals("")) {
						dossierToUpdate.setPartitaIva(dossierRead.getPartitaIva());
						}
					
					if (dossierToUpdate.getIdProgetto() == 0) {
						dossierToUpdate.setIdDossier(dossierRead.getIdDossier());
					}
					
					if (dossierToUpdate.getIdDossier() == 0) {
						dossierToUpdate.setIdDossier(dossierRead.getIdDossier());
					}
					
					
					if (dossierToUpdate.getPeriodoDiImposta() == null || dossierToUpdate.getPeriodoDiImposta().equals("")) {
						dossierToUpdate.setPeriodoDiImposta(dossierRead.getPeriodoDiImposta());
					}
					
					// Update the user
					PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
					preparedStatement.setString(1, dossierToUpdate.getPeriodoDiImposta());
					preparedStatement.setDouble(2, dossierToUpdate.getCostoDipendenti());
					preparedStatement.setDouble(3, dossierToUpdate.getFatturatoPeriodoDiImposta());
					preparedStatement.setInt(4, dossierToUpdate.getNumeroDipendenti());
					preparedStatement.setDouble(5, dossierToUpdate.getCostiAttivitaRd());
					preparedStatement.setDouble(6, dossierToUpdate.getCostiPersonaleRd());
					preparedStatement.setString(7, dossierToUpdate.getPartitaIva());
					preparedStatement.setInt(8, dossierToUpdate.getIdProgetto());
					
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

		public boolean deleteDossier(Integer id) {
			Connection connection = ConnectionSingleton.getInstance();
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
				preparedStatement.setInt(1, id);
				int n = preparedStatement.executeUpdate();
				if (n != 0)
					return true;
			} catch (SQLException e) {
			}
			return false;
		}
	}

		
		
	 
	 

