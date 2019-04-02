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

			final String scelta = request.getParameter("action");
			final HttpSession session = request.getSession(true); //sto impostando una serie di pulsanti

			switch (scelta) {
			case "openInsert":
				response.sendRedirect("insertThings.jsp");
				break;
			case "openDelete":
				response.sendRedirect("deleteThings.jsp");
				break;
			case "openUpdate":
				response.sendRedirect("updateThings.jsp");
				break;
			case "openList":
				showAllThings(request,response);
				break;
			}
		}
		
	private void showAllThings(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		allThings = this.thingsServiceDTO.getAllThings();
		request.setAttribute("allThings", allThings);
		getServletContext().getRequestDispatcher("/showThings.jsp").forward(request, response);
	}

}
