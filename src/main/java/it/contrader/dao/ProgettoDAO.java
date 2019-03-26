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
import it.contrader.model.Progetto;


public class ProgettoDAO {

	private final String QUERY_ALL = "select * from tab_progetto";
	private final String QUERY_INSERT = "insert into tab_progetto (nomeProgetto) values (?)";
	private final String QUERY_READ = "select * from tab_progetto where nomeprogetto=?";

	private final String QUERY_UPDATE = "UPDATE tab_progetto SET idprogetto=?,nomeprogetto=? WHERE idprogetto=?";
	private final String QUERY_DELETE = "delete from tab_progetto where idProgetto=?";

	public ProgettoDAO() {

	}

	public List<Progetto> getAllProgetto() {
		List<Progetto> progettoList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Progetto progetto;
			while (resultSet.next()) {
				int idProgetto = resultSet.getInt("idProgetto");
				String nomeProgetto = resultSet.getString("nomeProgetto");
				progetto = new Progetto(idProgetto, nomeProgetto);
				progetto.setIdProgetto(idProgetto);
				progettoList.add(progetto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return progettoList;
	}

	public boolean insertProgetto(Progetto progetto) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			preparedStatement.setString(1, progetto.getNomeProgetto());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}

	}

	public Progetto readProgetto(int idProgetto) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, idProgetto);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int idprogetto;
			String nomeprogetto;
			

			nomeprogetto = resultSet.getString("nomeprogetto");
			idprogetto = resultSet.getInt("idprogetto");
			
			Progetto progetto = new Progetto(idprogetto, nomeprogetto);
			progetto.setIdProgetto(resultSet.getInt("idprogetto"));

			return progetto;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return null;
		}

	}

	public boolean updateProgetto(Progetto progettoToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (progettoToUpdate.getIdProgetto() == 0)
			return false;

		
		Progetto progettoRead = readProgetto(progettoToUpdate.getIdProgetto());
		if (!progettoRead.equals(progettoToUpdate)) {
			try {
				// Fill the progettoToUpdate object
				if (progettoToUpdate.getNomeProgetto() == null || progettoToUpdate.getNomeProgetto().equals("")) {
					progettoToUpdate.setNomeProgetto(progettoRead.getNomeProgetto());
				
				}
				
				// Update the progetto
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setInt(1, progettoToUpdate.getIdProgetto());
				preparedStatement.setString(2, progettoToUpdate.getNomeProgetto());
			    preparedStatement.setInt(3, progettoToUpdate.getIdProgetto());
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

	public boolean deleteProgetto(Integer id) {
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
