package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import it.contrader.controller.GestoreEccezioni;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Things;
import it.contrader.model.User;

public class ThingsDAO {

	private final String QUERY_ALL = "SELECT * FROM things";
	private final String QUERY_INSERT = "INSERT INTO things (name) VALUES (?)";
	private final String QUERY_READ = "SELECT * FROM things WHERE idthing=?";

	private final String QUERY_UPDATE = "UPDATE things SET name=?, WHERE idthing=?";
	private final String QUERY_DELETE = "DELETE FROM things WHERE idthing=?";

	public ThingsDAO() {

	}

	public List<Things> getAllUser() {
		List<Things> thingList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Things thing;
			while (resultSet.next()) {
				int idthing = resultSet.getInt("idthing");
				String thingname = resultSet.getString("name");
				thing = new Things(thingname);
				thing.setThingsId(idthing);
				thingList.add(thing);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thingList;
	}

	public boolean insertThing(Things thing) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			preparedStatement.setString(1, thing.getThingsname());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}

	}

	public Things readThings(int idThings) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, idThings);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String name;
			name = resultSet.getString("name");
			Things thing = new Things(name);
			thing.setThingsId(resultSet.getInt("idThings"));

			return thing;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return null;
		}

	}

	public boolean updateThings(Things thingToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (thingToUpdate.getThingsId() == 0)
			return false;

		Things thingRead = readThings(thingToUpdate.getThingsId());
		if (!thingRead.equals(thingToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (thingToUpdate.getThingsname() == null || thingToUpdate.getThingsname().equals("")) {
					thingToUpdate.setThingsname(thingRead.getThingsname());
				}
					
				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, thingToUpdate.getThingsname());
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

	public boolean deleteThings(Integer id) {
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
