package it.contrader.servlets;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.contrader.dto.LabelsDTO;
import it.contrader.dto.ThingsDTO;
import it.contrader.dto.UsersDTO;
import it.contrader.service.ThingsServiceDTO;

	public class ThingsServlet extends HttpServlet {

		private final ThingsServiceDTO thingsServiceDTO = new ThingsServiceDTO();
		private List<ThingsDTO> allThings= new ArrayList<>();
		private StringBuffer textArea;
		private int idThingCode;
		
		@Override
		public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			final String scelta = request.getParameter("action");
			final HttpSession session = request.getSession(true); //sto impostando una serie di pulsanti
			
			switch (scelta) {

			case "update":
				final Integer idthing = Integer.parseInt(request.getParameter("idThing"));
				final String uname = request.getParameter("name");
				final Integer fklabel = Integer.parseInt(request.getParameter("idLabel"));
				break;
			
			case "delete":
				final Integer idLabel = Integer.parseInt(request.getParameter("id"));
				thingsServiceDTO.deleteThingsById(idLabel);
				showAllThings(request, response);

				break;

			case "insert":
				final String name = request.getParameter("name");
				final Integer fkUser = Integer.parseInt(request.getParameter("fkUser"));
				final Integer fkLabel = Integer.parseInt(request.getParameter("fkLabel"));
				final ThingsDTO thing = new ThingsDTO(0,name,fkUser,fkLabel);
				//request.getSession().setAttribute("tTest", thing);
				thingsServiceDTO.insertThings(thing);
				showAllThings(request, response);
				break;
	
			case "openInsert":
				response.sendRedirect("insertThings.jsp");
				break;
			
			case "openDelete":
				response.sendRedirect("deleteThings.jsp");
				break;
			
			case "openUpdate":
				response.sendRedirect("updateThings.jsp");
				break;
			
			case "openInsertCode":
				idThingCode = Integer.parseInt(request.getParameter("id"));
				response.sendRedirect("insertCode.jsp");
				break;
				
			case "openList":
				showAllThings(request,response);
				break;
				
			case "insertFile":
				String text = request.getParameter("textarea1");
				//System.out.println("text: "+text);
				
				/*
				int loc = (new String(text)).indexOf('\n');
				while(loc > 0) {
		    			text.replace(loc, loc+1, "<BR>");
		    			loc = (new String(text)).indexOf('\n');
					}
					//System.out.println(text);
					textArea = text;  
				//final String code = request.getParameter("textrea1");
				*/
				
				final ThingsDTO label2 = new ThingsDTO(idThingCode, text);					
				thingsServiceDTO.insertCode(label2);
				break;
			}
		}

	private void showAllThings(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			allThings = this.thingsServiceDTO.getAllThings();
			request.getSession().setAttribute("allThings", allThings);
			getServletContext().getRequestDispatcher("/showThings.jsp").forward(request, response);
		}
}