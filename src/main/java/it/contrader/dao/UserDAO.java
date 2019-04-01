package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import it.contrader.controller.GestoreEccezioni;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.User;

public class UserDAO {

	private final String QUERY_ALL = "SELECT * FROM user";
	private final String QUERY_INSERT = "INSERT INTO user (username, password, type) VALUES (?,?,?)";
	private final String QUERY_READ = "SELECT * FROM user WHERE idUser=?";

	private final String QUERY_UPDATE = "UPDATE user SET username=?, password=?, type=? WHERE idUser=?";
	private final String QUERY_DELETE = "DELETE FROM user WHERE idUser=?";

	public UserDAO() {

	}

	public List<User> getAllUser() {
		List<User> usersList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			User user;
			while (resultSet.next()) {
				int idUsers = resultSet.getInt("idUser");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				int tipo = resultSet.getInt("type");
				user = new User(username, password, tipo);
				user.setidUsers(idUsers);
				usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	public boolean insertUser(User user) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setInt(3, user.gettipo());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}

	}

	public User readUser(int idUsers) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, idUsers);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String username, password;
			int tipo;
			username = resultSet.getString("username");
			password = resultSet.getString("password");
			tipo = resultSet.getInt("type");
			User user = new User(username, password, tipo);
			user.setidUsers(resultSet.getInt("idUser"));

			return user;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return null;
		}

	}

	public boolean updateUser(User userToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (userToUpdate.getidUsers() == 0)
			return false;

		User userRead = readUser(userToUpdate.getidUsers());
		if (!userRead.equals(userToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (userToUpdate.getUsername() == null || userToUpdate.getUsername().equals("")) {
					userToUpdate.setUsername(userRead.getUsername());
				}
				
				if (userToUpdate.getPassword() == null || userToUpdate.getPassword().equals("")) {
					userToUpdate.setPassword(userRead.getPassword());
				}
				
				if (userToUpdate.gettipo() == null ) {
					userToUpdate.settipo(userRead.gettipo());
				}
				
				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, userToUpdate.getUsername());
				preparedStatement.setString(2, userToUpdate.getPassword());
				preparedStatement.setInt(3, userToUpdate.gettipo().intValue());
				preparedStatement.setInt(4, userToUpdate.getidUsers());
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

	public boolean deleteUser(Integer id) {
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
