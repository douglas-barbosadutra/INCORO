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
import it.contrader.model.Label;

public class LabelDAO {

	private final String QUERY_ALL = "SELECT * FROM label";
	private final String QUERY_INSERT = "INSERT INTO label (nomeLabel) VALUES (?)";
	private final String QUERY_READ = "SELECT * FROM label WHERE idLabel=?";

	private final String QUERY_UPDATE = "UPDATE label SET nomeLabel=? WHERE idLabel=?";
	private final String QUERY_DELETE = "DELETE FROM label WHERE idLabel=?";

	public LabelDAO() {

	}

	public List<Label> getAllLabel() {
		List<Label> LabelList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Label Label;
			while (resultSet.next()) {
				int idLabel = resultSet.getInt("idLabel");
				String nomeLabel = resultSet.getString("nomeLabel");
				Label = new Label(nomeLabel);
				Label.setIdLabel(idLabel);
				LabelList.add(Label);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return LabelList;
	}

	public boolean insertLabel(Label Label) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
			preparedStatement.setString(1, Label.getNomeLabel());
			
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return false;
		}

	}

	public Label readLabel(int idLabel) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, idLabel);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String nomeLabel;
			nomeLabel = resultSet.getString("nomeLabel");
			Label Label = new Label(nomeLabel);
			Label.setIdLabel(resultSet.getInt("idLabel"));

			return Label;
		} catch (SQLException e) {
			GestoreEccezioni.getInstance().gestisciEccezione(e);
			return null;
		}

	}

	public boolean updatelabel(Label labelToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (labelToUpdate.getIdLabel() == 0)
			return false;

		Label labelRead = readLabel(labelToUpdate.getIdLabel());
		if (!labelRead.equals(labelToUpdate)) {
			try {
				// Fill the labelToUpdate object
				if (labelToUpdate.getNomeLabel() == null || labelToUpdate.getNomeLabel().equals("")) {
					labelToUpdate.setNomeLabel(labelRead.getNomeLabel());
				}
				
				// Update the label
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, labelToUpdate.getNomeLabel());
				
				preparedStatement.setInt(2, labelToUpdate.getIdLabel());
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

	public boolean deletelabel(Integer id) {
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
