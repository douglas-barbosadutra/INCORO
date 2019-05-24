package it.contrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.ExpiredJwtException;

//import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import it.contrader.dto.LoginDTO;
import it.contrader.dto.ParamDTO;
import it.contrader.dto.UserDTO;
import it.contrader.services.UserService;
import it.contrader.utils.JwtUtils;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/User")
@CrossOrigin
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// METODI DI REST CONTROLLER
	@RequestMapping(value="/insertUser", method= RequestMethod.POST)
	public ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO userDTO) throws UnsupportedEncodingException {
		
		
		try {
			
			
			
				return ResponseEntity.status(HttpStatus.OK).body(userService.insertUser(userDTO));
			
			
		} catch (ExpiredJwtException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}

	@RequestMapping(value="/deleteUser" , method= RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteUsershowUser(@RequestParam(value="id") int idUser) throws UnsupportedEncodingException {		
		
		try {
			
			
				return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(idUser));
			
			
		} catch (ExpiredJwtException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
	}
	
	@RequestMapping(value="/showUser" , method= RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> showUser(@RequestParam(value="jwt") String jwt) {		
		try {
			int type = this.getTypeFromJwt(jwt);
			
			
			if(type == 0)
				return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
			else
				return ResponseEntity.status(HttpStatus.OK).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserDTO login( @RequestBody LoginDTO user) {
		return(userService.login(user.getUsername() , user.getPassword()));
	}
	
	@RequestMapping(value="/updateUser" , method= RequestMethod.PUT)
	public ResponseEntity<UserDTO> showUser(@RequestBody UserDTO userDTO) throws UnsupportedEncodingException {		
		try {
			
			

				return ResponseEntity.status(HttpStatus.OK).body(userService.insertUser(userDTO));
			
			
		} catch (ExpiredJwtException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	@RequestMapping(value="/findUser" , method= RequestMethod.GET)
	public ResponseEntity<UserDTO> findUser(@RequestParam(value="jwt") String jwt) {		
		try {
			
			Map<String, Object> data = JwtUtils.jwt2Map(jwt);
			int idUser = Integer.parseInt(data.get("subject").toString());
			int rank = Integer.parseInt(data.get("scope").toString());
		
			
			if(rank != 1)
				return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(idUser));
			else
				return ResponseEntity.status(HttpStatus.OK).body(null);
			
		} catch (ExpiredJwtException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	@RequestMapping(value="/loginGoogle", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> glgLogin(@RequestBody ParamDTO paramDTO) throws ExpiredJwtException, UnsupportedEncodingException {
		Map<String, Object> data = JwtUtils.jwt2Map(paramDTO.getJwt());
		int idUser = Integer.parseInt(data.get("subject").toString());
		int type = Integer.parseInt(data.get("scope").toString());
		LinkedHashMap login = (LinkedHashMap) paramDTO.getParam();
		ResponseEntity glg = ResponseEntity.status(HttpStatus.OK).body(userService.getByUsernameAndPassword(login.get("username").toString(), login.get("password").toString()));
		if(glg != null) {
			return glg;
		} else {
			//UserDTO insGlg = new UserDTO();
			//insGlg.setUsername(login.get("username").toString());
			//insGlg.setPassword(login.get("password").toString());
			//insGlg.setType(1);
			//this.userService.insertUser(insGlg);
			//return insGlg;
			
			return ResponseEntity.status(HttpStatus.OK).body(userService.insertUser(new UserDTO(0, login.get("username").toString(), login.get("password").toString(), 1)));
		}
	}
	
	private int getIdUserFromJwt(String jwt) throws ExpiredJwtException, UnsupportedEncodingException {
		Map<String, Object> data = JwtUtils.jwt2Map(jwt);
		int idUser = Integer.parseInt(data.get("subject").toString());
		return idUser;
	}

	
private int getTypeFromJwt(String jwt) throws ExpiredJwtException, UnsupportedEncodingException {
		
		Map<String, Object> data = JwtUtils.jwt2Map(jwt);
		int type = Integer.parseInt(data.get("scope").toString());
		
		return type;
	}
	
}



// METODI UTILIZZATO CON CONTROLLER
/*
@RequestMapping(value = "/creaUser", method = RequestMethod.POST)
public String insertUser(HttpServletRequest request) {
	String username = request.getParameter("username").toString();
	if (userService.getByUsername(username) == null) {
		String password = request.getParameter("password").toString();
		int type = Integer.parseInt(request.getParameter("role"));
		UserDTO userObj = new UserDTO(0, username, password, type);
		userService.insertUser(userObj);
		visualUser(request);
		return "homeAdmin";
	} else {
		return "userError";
	}
}

@RequestMapping(value = "/delete", method = RequestMethod.GET)
public String delete(HttpServletRequest request) {
	int id = Integer.parseInt(request.getParameter("id"));
	this.userService.deleteUserById(id);
	visualUser(request);
	return "homeAdmin";
}

private void visualUser(HttpServletRequest request) {
	List<UserDTO> allUser = this.userService.getListaUserDTO();
	request.getSession().setAttribute("allUserDTO", allUser);
}

@RequestMapping(value = "/userManagement", method = RequestMethod.GET)
public String userManagement(HttpServletRequest request) {
	visualUser(request);
	return "homeAdmin";
}


@RequestMapping(value = "/reindirizzaCrea", method = RequestMethod.GET)
public String reindirizzaCrea(HttpServletRequest request) {
	visualUser(request);
	return "creaUser";
}

@RequestMapping(value = "/reindirizzaModifica", method = RequestMethod.GET)
public String reindirizzaModifica(HttpServletRequest request) {
	idUser = Integer.parseInt(request.getParameter("id"));
	visualUser(request);
	return "modificaUser";
}

@RequestMapping(value = "/modifica", method = RequestMethod.POST)
public String modifica(HttpServletRequest request) {
	UserDTO userOldDTO = new UserDTO();
	userOldDTO = userService.getUserDTOById(idUser);
	String username = request.getParameter("username").toString();
	if (userService.getByUsername(username) == null || userService.getByUsername(username).equals(userOldDTO)) {
		String password = request.getParameter("password").toString();
	int type = Integer.parseInt(request.getParameter("role"));
	if ((username == null || username == "" ) || (password == null || password == "")) {
		userService.updateUser(userOldDTO);
	}
	else {
		UserDTO userObj = new UserDTO(idUser, username, password, type);
		userService.updateUser(userObj);
	}
	visualUser(request);
	return "homeAdmin";
}
	else {
		return "userErrorModifica";
	}
}

@RequestMapping(value = "/crea", method = RequestMethod.GET)
public String insert(HttpServletRequest request) {
	visualUser(request);
	request.setAttribute("option", "insert");
	return "creaUser";
}

@RequestMapping(value = "/cercaUser", method = RequestMethod.GET)
public String cercaUser(HttpServletRequest request) {
	final String content = request.getParameter("search");
	List<UserDTO> allUser = this.userService.findUserDTOByUsername(content);
	request.setAttribute("allUserDTO", allUser);
	return "homeUser";
}

@RequestMapping(value = "/erroreUser", method = RequestMethod.GET)
public String logout(HttpServletRequest request) {
	return "creaUser";
}

@RequestMapping(value = "/erroreUserModifica", method = RequestMethod.GET)
public String logoutUpdate(HttpServletRequest request) {
	return "modificaUser";
}
	
@RequestMapping(value = "/login", method = RequestMethod.POST)
public String loginControl(HttpServletRequest request) {
	session = request.getSession();
	final String username = request.getParameter("username");
	final String password = request.getParameter("password");
	try {
		final UserDTO userDTO = userService.getByUsernameAndPassword(username, password);
		final int type = userDTO.getType();
		if (userDTO != null) {
			session.setAttribute("utenteCollegato", userDTO);
		}
		if (type == 0) {
			visualUser(request);
			return "homeAdmin";
		} else if (type == 1) {
			return "homeBO";
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return "loginError";
}*/