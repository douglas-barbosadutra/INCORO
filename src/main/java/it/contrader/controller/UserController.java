package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import it.contrader.dto.LoginDTO;
import it.contrader.dto.UserDTO;
import it.contrader.services.UserService;

import java.util.List;


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
	public UserDTO insertUser(@RequestBody UserDTO user) {
		return userService.insertUser(user);
	}

	@RequestMapping(value="/deleteUser" , method= RequestMethod.DELETE)
	public boolean deleteUser(@RequestParam(value="id") Integer id) {		
		userService.deleteUser(id);
		return true;
	}
	
	@RequestMapping(value="/showUser" , method= RequestMethod.GET)
	public List<UserDTO> showUser() {		
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserDTO login( @RequestBody LoginDTO user) {
		return(userService.login(user.getUsername() , user.getPassword()));
	}
	
	@RequestMapping(value="/updateUser" , method= RequestMethod.PUT)
	public UserDTO showUser(@RequestBody UserDTO user) {		
		return userService.insertUser(user);
	}
	
	@RequestMapping(value="/findUser" , method= RequestMethod.GET)
	public UserDTO findUser( @RequestBody UserDTO user) {		
		return userService.findUserById(user.getIdUser());
	}
	
	@RequestMapping(value="/loginGoogle", method = RequestMethod.POST)
	public UserDTO glgLogin(@RequestBody LoginDTO user) {
		UserDTO glg = userService.getByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(glg != null) {
			return glg;
		} else {
			UserDTO insGlg = new UserDTO();
			insGlg.setUsername(user.getUsername());
			insGlg.setPassword(user.getPassword());
			insGlg.setType(1);
			this.userService.insertUser(insGlg);
			return insGlg;
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
}