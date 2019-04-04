package it.contrader.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.contrader.converter.UsersConverter;
import it.contrader.dto.UsersDTO;
import it.contrader.service.UsersServiceDTO;

/**
 * La servlet si occupa di parlare con la JSP e utilizza i servizi opportuni.
 * Per chi farà User dovrà anche occuparsi del Login che abbiamo lasciato come struttura e va modificata in modo opportuno
 *
 */
public class UsersServlet extends HttpServlet {

	private final UsersServiceDTO usersServiceDTO = new UsersServiceDTO();
	private List<UsersDTO> allUsers= new ArrayList<>();
	private int idUser;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String scelta = request.getParameter("action");
		final HttpSession session = request.getSession(true); //sto impostando una serie di pulsanti

		switch (scelta) {

		case "UsersManager":
			showAllUsers(request,response);
			break;		
			
		case "openInsert":{
			response.sendRedirect("insertUser.jsp");
		} break;
		
		case "openUpdate":{
			idUser = Integer.parseInt(request.getParameter("id"));
			//int id = Integer.parseInt(request.getParameter("id"));
			response.sendRedirect("updateUser.jsp");
		} break;
		

		case "insert":
			final String username = request.getParameter("username");
			final String password = request.getParameter("password");
			final Integer ruolo = Integer.parseInt(request.getParameter("type"));
			final UsersDTO users = new UsersDTO(0,username, password, ruolo);
			usersServiceDTO.insertUsers(users);
			showAllUsers(request, response);
			break;
					
		case "update":
			final String usernameUpdate = request.getParameter("username");
			final String passwordUpdate = request.getParameter("password");
			final Integer tipoUpdate = Integer.parseInt(request.getParameter("type"));
			final UsersDTO user = new UsersDTO(idUser, usernameUpdate,passwordUpdate, tipoUpdate);
			System.out.println(user);
			usersServiceDTO.updateUsers(user);
			showAllUsers(request, response);
			break;

		case "delete":
			final Integer idDelete = Integer.parseInt(request.getParameter("id"));
			final UsersDTO use = new UsersDTO(idDelete,"" ,"", 1);
			usersServiceDTO.deleteUsers(use);
			showAllUsers(request, response);
			break;
			
		case "update2":
			final Integer idUpdate2 = Integer.parseInt(request.getParameter("id"));			
			final UsersDTO userUpdate2 = new UsersDTO(idUpdate2,"" ,"", 1);
			usersServiceDTO.updateUsers(userUpdate2);
			showAllUsers(request, response);
			break;
			
		case "Indietro":
			response.sendRedirect("homeAdmin.jsp");
			break;

		case "LogsMenu":
			response.sendRedirect("index.jsp");
			break;

		}
	}
	
private void showAllUsers(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		allUsers = this.usersServiceDTO.getAllUsers();
		request.getSession().setAttribute("users_list", allUsers);
		getServletContext().getRequestDispatcher("/users.jsp").forward(request, response);
	}
}