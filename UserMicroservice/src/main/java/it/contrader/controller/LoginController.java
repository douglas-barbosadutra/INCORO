package it.contrader.controller;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.LoginDTO;
import it.contrader.dto.UserDTO;
import  it.contrader.dto.UserLoggedDTO;
import  it.contrader.services.LoginService;
import  it.contrader.utils.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(value="*")
@RestController
@RequestMapping("/Login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	public LoginController() {
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoggedDTO> login(@RequestBody LoginDTO loginDTO) {
		
		UserDTO user = loginService.login(loginDTO);
		
		if(user != null) {
			
			try {
				String jwt = loginService.createJwt(""+user.getIdUser(), user.getUsername()+" "+user.getPassword(), ""+user.getType(), new Date());
				UserLoggedDTO userLogged = new UserLoggedDTO(jwt,user.getType());
				return ResponseEntity.status(HttpStatus.OK).header("jwt", jwt).body(userLogged);
				
			} catch (UnsupportedEncodingException e) {
				
				 return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
		}
		
		else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		
	}

}
