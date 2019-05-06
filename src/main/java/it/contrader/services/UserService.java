package it.contrader.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.ConverterUser;
import it.contrader.dao.UserRepository;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserDTO> getListaUserDTO() {
		return ConverterUser.toListDTO((List<User>) userRepository.findAll());
	}

	public UserDTO getUserDTOById(Integer id) {
		return ConverterUser.toDTO(userRepository.findById(id).get());
	}

	public UserDTO getByUsernameAndPassword(String username, String password) {
		final User user = userRepository.findUserByUsernameAndPassword(username, password);
		return ConverterUser.toDTO(user);
	}
	
	public UserDTO getByUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		return ConverterUser.toDTO(user);
	}

	/*
	public boolean insertUser(UserDTO userDTO) {
		return userRepository.save(ConverterUser.toEntity(userDTO)) != null;
	}*/

	// metodo di rest controller 
	public UserDTO insertUser(UserDTO userDTO) {
		User user = ConverterUser.toEntity(userDTO);
		// userDAO.saveAndFlush(user);
		userRepository.save(user); 
		return ConverterUser.toDTO(user);
	}
	
	public boolean updateUser(UserDTO userDTO) {
		return userRepository.save(ConverterUser.toEntity(userDTO)) != null;
	}
	
	// METODO USATO PER REST CONTROLLER
	public UserDTO login(String username, String password) {
        return ConverterUser.toDTO(this.userRepository.findUserByUsernameAndPassword(username, password));
    }
	
	// metodo di controller
	/*
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}*/
	
	// METODO DI REST CONTROLLER
	public void deleteUser(int id) {
		this.userRepository.deleteById(id);
		
	}
	
	// METODO DI REST CONTROLLER
	public List<UserDTO> getAllUsers(){
		return ConverterUser.toListDTO((List<User>) userRepository.findAll());
	}
	
	// METODO DI REST CONTROLLER
	public UserDTO findUserById(int id) {
		return ConverterUser.toDTO(userRepository.findUserByIdUser(id));
	}
	
	public List<UserDTO> findUserDTOByUsername(String username) {
		final List<User> list = userRepository.findAllByUsername(username);
		final List<UserDTO> userDTOs = new ArrayList<>();
		list.forEach(i -> userDTOs.add(ConverterUser.toDTO(i)));
		return userDTOs;	
	}
	
	public UserDTO findUserDTOById(int id) {
		final UserDTO u = ConverterUser.toDTO(userRepository.findUserByIdUser(id));
		return u;
	}
}