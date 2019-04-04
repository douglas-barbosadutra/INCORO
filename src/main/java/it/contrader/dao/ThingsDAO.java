package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import it.contrader.model.Things;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.utils.GestoreEccezioni;

public class ThingsDAO {

	/*
	 * Qui possiamo se vogliamo dichiarare delle stringhe rappresentanti le query
	 * che verranno utilizzate dai service, Non � obbligatorio ma � consigliato
	 * usare un ordine e dei nomi significativi per tutte le query. Con GET_ALL
	 * intendiamo recuperare tutte le tuple dal db. Se volessimo creare una query
	 * per l'inserimento, un nome identificativo potrebbe essere INSERT_ESEMPIO
	 */
	
	private final String GET_ALL = "select * from things";
	private final String QUERY_INSERT = "INSERT INTO things (name, fktouser, fktolabel) values (?,?,?)";
	private final String QUERY_DELETE = "DELETE FROM things WHERE idThing = (?)";
	private final String QUERY_UPDATE = "UPDATE things SET name WHERE idThings = (?)";
	private final String QUERY_INSERT_CODE = "UPDATE  things set code = ? where idThing = ?";
	//private final String QUERY_LOGIN = "select * from things where username=(?) and password=(?)";

	/*
	 * Il suddetto metodo si occupa interagire con il database e restituire tutte le
	 * tuple al servizio che ha chiamato questo metodo
	 */

	public List<Things> getAllThings() {
		final List<Things> things = new ArrayList<>();
		final Connection connection = ConnectionSingleton.getInstance();

		try {
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery(GET_ALL);
			while (resultSet.next()) {
				final Integer id = resultSet.getInt("idThing");
				final String name = resultSet.getString("name");
				final int fktouser = resultSet.getInt("fktouser");
				final int fktolabel = resultSet.getInt("fktolabel");
				things.add(new Things(id, name, fktouser, fktolabel));
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return things;
	}
	// Inserimento

	public boolean insertThings(Things things) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			//preparedStatement.setInt(1, things.getId());
			preparedStatement.setString(1, things.getName());
			preparedStatement.setInt(2, things.getFktouser());
			preparedStatement.setInt(3, things.getFktolabel());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}

	// cancella una chat
	public boolean deleteThings(Things things) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, things.getId());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}

	public boolean updateThings(Things things) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
			preparedStatement.setString(1, things.getName());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}
	
	public boolean insertCode(Things things) {
		//System.out.println("DAO: "+things.getId()+" "+things.getCode());
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT_CODE);
			preparedStatement.setString(1, things.getCode());
			preparedStatement.setInt(2,things.getId());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}

	public boolean deleteThingsById(Integer idLabel) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, idLabel);
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {

			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}

	public Things getThing() {
		// TODO Auto-generated method stub
		return null;
	}
}