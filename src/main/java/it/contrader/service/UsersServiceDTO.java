package it.contrader.service;

import java.util.ArrayList;
import java.util.List;

import it.contrader.converter.UsersConverter;
import it.contrader.dao.UsersDAO;
import it.contrader.dto.UsersDTO;
import it.contrader.model.Users;

/**
 * Classe che si occupa di interfacciarsi con la persistenza e recuperare
 * attraverso i metodi del Data Access Object le tuple desiderate, Le converte
 * in un oggetto DTO e le restituisce al controller opportuno
 */
public class UsersServiceDTO {

	private final UsersDAO usersDAO;
	private static UsersDTO userLogged;

	public UsersServiceDTO() {
		this.usersDAO = new UsersDAO();
	}

	/**
	 * Come vediamo la lista recuperata è di tipo Esempio ma noi la convertiamo in EsempioDTO
	 * Invito chi fa i converter a fare un metodo per convertire direttamente la lista senza farli uno ad uno perchè è sporco e poco efficiente
	 */
	public List<UsersDTO> getAllUsers() {
		List<Users> list = usersDAO.getAllUsers();
		List<UsersDTO> listDTO = new ArrayList<>();

		for (Users users : list) {
			listDTO.add(UsersConverter.toDTO(users));
		}
		return listDTO;
	}
	
	public static void setUserLogged(UsersDTO user) {
		userLogged = user;
	}
	
	public static UsersDTO getUserLogged() {
		return userLogged;
	}
	
	public UsersDTO getUserByUsernameAndPasword(String username, String password) {
		UsersDTO user = usersDAO.login(username, password);
		return user;
	}

	public boolean updateUsers (UsersDTO usersDTO) {
		return this.usersDAO.updateUsers(UsersConverter.toEntity(usersDTO));		
	}	
	
	public boolean deleteUsers (UsersDTO usersDTO) {
		return this.usersDAO.deleteUsers(UsersConverter.toEntity(usersDTO));
	}
	
	public boolean insertUsers (UsersDTO usersDTO) {
		return this.usersDAO.insertUsers(UsersConverter.toEntity(usersDTO));
	}
}