package it.contrader.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.contrader.dto.ThingsDTO;
import it.contrader.service.ThingsServiceDTO;


	public class ThingsServlet extends HttpServlet {

		private final ThingsServiceDTO thingsServiceDTO = new ThingsServiceDTO();
		private List<ThingsDTO> allThings= new ArrayList<>();

		@Override
		public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			final String scelta = request.getParameter("richiesta");
			final HttpSession session = request.getSession(true); //sto impostando una serie di pulsanti

			switch (scelta) {

			case "UsersManager":
				allThings = this.thingsServiceDTO.getAllThings();
				request.setAttribute("allThings", allThings);
				getServletContext().getRequestDispatcher("/things.jsp").forward(request, response);
				break;			

			case "insert":
				final Integer id = Integer.parseInt(request.getParameter("idThing"));
				final String name = request.getParameter("name");
				final Integer fktouser = Integer.parseInt(request.getParameter("fktouser"));
				final Integer fktolabel = Integer.parseInt(request.getParameter("fktolabel"));
				final ThingsDTO thing = new ThingsDTO(id,name, fktouser, fktolabel);
				thingsServiceDTO.insertThings(thing);
				showAllThings(request, response);
				break;
						
			case "update":
				System.out.println("id: "+Integer.parseInt(request.getParameter("id")));
				System.out.println("name: "+request.getParameter("name"));
				System.out.println("fktouser: "+Integer.parseInt(request.getParameter("fktouser")));
				System.out.println("fktolabel: "+Integer.parseInt(request.getParameter("fktolabel")));
			     	
				final Integer idUpdate = Integer.parseInt(request.getParameter("id"));
				final String nameUpdate = request.getParameter("name");
				final Integer fktouserUpdate = Integer.parseInt(request.getParameter("fktouser"));
				final Integer fktolabelUpdate = Integer.parseInt(request.getParameter("fktolabel"));
				final ThingsDTO things1 = new ThingsDTO(idUpdate,nameUpdate,fktouserUpdate, fktolabelUpdate);
											
				thingsServiceDTO.updateThings(things1);
				showAllThings(request, response);
				break;

			case "delete":
				final Integer idUpdat = Integer.parseInt(request.getParameter("id"));
				final Integer idBO = Integer.parseInt(request.getParameter("fktouser"));
				final Integer idLab = Integer.parseInt(request.getParameter("fktolabel"));
				final ThingsDTO things2 = new ThingsDTO(idUpdat,"", idBO, idLab);
				thingsServiceDTO.deleteThings(things2);
				showAllThings(request, response);
				break;

			case "Indietro":
				response.sendRedirect("homeAdmin.jsp");
				break;

			case "LogsMenu":
				response.sendRedirect("homeLogs.jsp");
				break;

					}
				}
		
	private void showAllThings(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		allThings = this.thingsServiceDTO.getAllThings();
		request.setAttribute("allThings", allThings);
		getServletContext().getRequestDispatcher("/things.jsp").forward(request, response);
	}

}
