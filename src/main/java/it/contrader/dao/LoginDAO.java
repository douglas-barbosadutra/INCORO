package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.contrader.controller.GestoreEccezioni;
import it.contrader.main.ConnectionSingleton;

public class LoginDAO {

    private final String QUERY_LOGIN = "select * from user where username = ? and password = ?";
    
    /**
     * 
     * @param username
     * @param password
     * @return the type of user of null if nothing is found
     */
    public Integer login (String username, String password) {

        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_LOGIN);
            statement.setString(1, username);
            statement.setString(2, password);
            
            Integer userType=null;
            ResultSet rs;
            if(statement.executeQuery().next()) {
            	rs = statement.executeQuery();
            	rs.next();
            	userType = rs.getInt("type");
            }
            
            return userType;
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return null;
        }
    }
    public Integer loginUser (String username, String password) {

        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY_LOGIN);
            statement.setString(1, username);
            statement.setString(2, password);
            
            Integer userId=null;
            ResultSet rs;
            if(statement.executeQuery().next()) {
            	rs = statement.executeQuery();
            	rs.next();
            	userId = rs.getInt("idUser");
            } 
            return userId;
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return null;
        }
    }
}
