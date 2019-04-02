package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import it.contrader.model.Users;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.utils.GestoreEccezioni;

public class UsersDAO {

	/**
	 * Qui possiamo se vogliamo dichiarare delle stringhe rappresentanti le query
	 * che verranno utilizzate dai service, Non è obbligatorio ma è consigliato
	 * usare un ordine e dei nomi significativi per tutte le query. Con GET_ALL
	 * intendiamo recuperare tutte le tuple dal db. Se volessimo creare una query
	 * per l'inserimento, un nome identificativo potrebbe essere INSERT_ESEMPIO
	 */
	private final String GET_ALL = "select * from user";
	private final String QUERY_INSERT = "INSERT INTO user (username, password, type) values (?,?,?)";
	private final String QUERY_DELETE = "DELETE FROM user WHERE idUser = (?)";
	private final String QUERY_UPDATE = "UPDATE user SET username=?, password=?, type=? WHERE idUser=?";
	private final String QUERY_LOGIN = "select * from user where username=(?) and password=(?)";

	/**
	 * Il suddetto metodo si occupa interagire con il database e restituire tutte le
	 * tuple al servizio che ha chiamato questo metodo
	 */

	public Users login(String username, String password) {

		Connection connection = ConnectionSingleton.getInstance();
		Users utente = null;
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_LOGIN);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.execute();
			ResultSet resultSet = statement.getResultSet();

			while (resultSet.next()) {
				String name = resultSet.getString("username");
				String pass = resultSet.getString("password");
				Integer idUser = resultSet.getInt("idUser");
				Integer type = resultSet.getInt("type");
				utente = new Users(idUser, name, pass, type);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utente;
	}

	public List<Users> getAllUsers() {

		final List<Users> users = new ArrayList<>();
		final Connection connection = ConnectionSingleton.getInstance();

		try {
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery(GET_ALL);
			while (resultSet.next()) {
				final Integer id = resultSet.getInt("idUser");
				final String username = resultSet.getString("username");
				final String password = resultSet.getString("password");
				final Integer type = resultSet.getInt("type");

				users.add(new Users(id, username, password, type));
			}
			return users;
		} catch (final SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	// Inserimento

	public boolean insertUsers(Users users) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			preparedStatement.setString(1, users.getUsername());
			preparedStatement.setString(2, users.getPassword());
			preparedStatement.setInt(3, users.getType());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}

	// cancella una chat
	public boolean deleteUsers(Users users) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, users.getId());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}
	}

	// Modifica Chat

	public boolean updateUsers(Users users) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
			preparedStatement.setString(1, users.getUsername());
			preparedStatement.setString(2, users.getPassword());
			preparedStatement.setInt(3, users.getType());
			preparedStatement.setInt(4, users.getId());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {

			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}

	}
}
