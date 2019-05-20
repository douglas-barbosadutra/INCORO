package it.contrader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.contrader.converter.ConverterUser;
import it.contrader.dao.UserRepository;
import it.contrader.dto.UserDTO;

@Service
public class LoginService {
    private UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO login(String username, String password) {
        return ConverterUser.toDTO(this.userRepository.findUserByUsernameAndPassword(username, password));
    }
}