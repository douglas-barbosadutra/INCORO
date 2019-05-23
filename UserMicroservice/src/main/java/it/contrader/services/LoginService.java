package it.contrader.services;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.contrader.converter.ConverterUser;
import it.contrader.dao.UserRepository;
import it.contrader.dto.LoginDTO;
import it.contrader.dto.UserDTO;
import it.contrader.utils.JwtUtils;

@Service
public class LoginService {
    private UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO login(LoginDTO loginDTO) {
        return ConverterUser.toDTO(this.userRepository.findUserByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()));
    }
    
    public String createJwt(String subject, String name, String permission, Date dateNow) throws UnsupportedEncodingException {
		
		 Date expDate = dateNow;
       expDate.setTime(dateNow.getTime() + (7200*1000));
      
       String token = JwtUtils.generateJwt(subject, expDate, name, permission);
       return token;
	}
	
	public Map<String,Object> verifyJwtAndGetData(HttpServletRequest request) throws Exception{
		
		String jwt = JwtUtils.getJwtFromHttpRequest(request);
       if(jwt == null){
           throw new Exception("Authentication token not found in the request");
       }
       Map<String, Object> userData = JwtUtils.jwt2Map(jwt);
       return userData;
	}
}