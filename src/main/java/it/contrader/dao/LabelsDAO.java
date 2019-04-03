package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import it.contrader.model.Labels;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.utils.GestoreEccezioni;

public class LabelsDAO {

	/**
	 * Qui possiamo se vogliamo dichiarare delle stringhe rappresentanti le query
	 * che verranno utilizzate dai service, Non è obbligatorio ma è consigliato
	 * usare un ordine e dei nomi significativi per tutte le query. Con GET_ALL
	 * intendiamo recuperare tutte le tuple dal db. Se volessimo creare una query
	 * per l'inserimento, un nome identificativo potrebbe essere INSERT_ESEMPIO
	 */
	private final String GET_ALL = "select * from label";
	//private final String QUERY_INSERT = "INSERT INTO label (idLabel, name, fktouser) values (?,?,?)";
	private final String QUERY_INSERT = "INSERT INTO label (name, fktouser) values (?,?)";
	private final String QUERY_DELETE = "DELETE FROM label WHERE idLabel = (?)";
	//private final String QUERY_UPDATE = "UPDATE label SET name WHERE idLabel = (?)";
	private final String QUERY_UPDATE = "UPDATE label SET name=?, fktouser=? WHERE idLabel=?";
	//private final String QUERY_LOGIN = "select * from things where username=(?) and password=(?)";

	/**
	 * Il suddetto metodo si occupa interagire con il database e restituire tutte le
	 * tuple al servizio che ha chiamato questo metodo
	 */

	public List<Labels> getAllLabels() {

		final List<Labels> label = new ArrayList<>();
		final Connection connection = ConnectionSingleton.getInstance();

		try {
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery(GET_ALL);
			while (resultSet.next()) {
				final Integer id = resultSet.getInt("idLabel");
				final String name = resultSet.getString("name");
				final int fktouser = resultSet.getInt("fktouser");
				label.add(new Labels(id, name, fktouser));
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return label;
	}
	// Inserimento

	public boolean insertLabel(Labels labels) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			//preparedStatement.setInt(1, labels.getId());
			preparedStatement.setString(1, labels.getName());
			preparedStatement.setInt(2, labels.getFktouser());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}

	// cancella una chat
	public boolean deleteLabel(Labels labels) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, labels.getId());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}

	public boolean updateLabels(Labels labels) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
			preparedStatement.setString(1, labels.getName());			
			preparedStatement.setInt(2, labels.getFktouser());		
			preparedStatement.setInt(3, labels.getId());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {

			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}

	}
}