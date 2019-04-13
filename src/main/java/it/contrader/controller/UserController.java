package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.contrader.dto.UserDTO;
import it.contrader.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/User")
public class UserController {

	private final UserService userService;
	private HttpSession session;
	private int idUser;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
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

	@RequestMapping(value = "/modifica", method = RequestMethod.GET)
	public String modifica(HttpServletRequest request) {
		// int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username").toString();
		String password = request.getParameter("password").toString();
		int type = Integer.parseInt(request.getParameter("type"));
		UserDTO userObj = new UserDTO(idUser, username, password, type);
		// UserDTO userObj = new UserDTO(0, username, password, type,"");
		userService.updateUser(userObj);
		visualUser(request);
		return "homeAdmin";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		// request.setAttribute("id", id);
		this.userService.deleteUserById(id);
		visualUser(request);
		return "homeAdmin";
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

	@RequestMapping(value = "/creaUser", method = RequestMethod.POST)
	public String insertUser(HttpServletRequest request) {

		String username = request.getParameter("username").toString();
		if (userService.getByUsername(username) == null) {

			String password = request.getParameter("password").toString();
			int type = Integer.parseInt(request.getParameter("type"));
			UserDTO userObj = new UserDTO(0, username, password, type);
			// UserDTO userObj = new UserDTO(0, username, password, type,"");
			userService.insertUser(userObj);
			visualUser(request);
			return "homeAdmin";
		} else {
			return "userError";
		}
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
	}
}
