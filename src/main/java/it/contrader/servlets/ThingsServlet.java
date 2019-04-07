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
import it.contrader.service.LabelsServiceDTO;
import it.contrader.service.ThingsServiceDTO;
import it.contrader.service.UsersServiceDTO;

	public class ThingsServlet extends HttpServlet {

		private final ThingsServiceDTO thingsServiceDTO = new ThingsServiceDTO();
		private final LabelsServiceDTO labelsServiceDTO = new LabelsServiceDTO();
		private List<ThingsDTO> allThings= new ArrayList<>();
		private StringBuffer textArea;
		private int idThingCode;
		private int idThings;
		
		@Override
		public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			final String scelta = request.getParameter("action");
			final HttpSession session = request.getSession(true); //sto impostando una serie di pulsanti
			
			switch (scelta) {
			case "openInsert":
				List<String> names = labelsServiceDTO.getAllNames();
				session.setAttribute("names", names);
				response.sendRedirect("insertThings.jsp");
				break;
				
			case "insert":
				final String name = request.getParameter("nameThing");
				UsersDTO usersDTO = UsersServiceDTO.getUserLogged();
				final String nameLabel = request.getParameter("fkLabel").toString();
				final int idLabel = labelsServiceDTO.getIdByName(nameLabel);
				final ThingsDTO thing = new ThingsDTO(0,name,usersDTO.getId(),idLabel);
				request.getSession().setAttribute("tTest", thing);
				thingsServiceDTO.insertThings(thing);
				showAllThings(request, response);
				break;
			
			case "openDelete":
				response.sendRedirect("deleteThings.jsp");
				break;
			
			case "delete":
				final Integer idDelete = Integer.parseInt(request.getParameter("id"));
				final ThingsDTO use = new ThingsDTO(idDelete,"",1, 1);
				thingsServiceDTO.deleteThings(use);
				showAllThings(request, response);
				break;
			
			case "openUpdate":
				idThings = Integer.parseInt(request.getParameter("id"));
				response.sendRedirect("updateThings.jsp");
				break;
				
			case "update":
				//final Integer idthing = Integer.parseInt(request.getParameter("idThing"));
				final String nameUpdate = request.getParameter("name");
				final Integer fktouserUpdate = Integer.parseInt(request.getParameter("fktouser"));
				final Integer fklabelUpdate = Integer.parseInt(request.getParameter("fktolabel"));
				final ThingsDTO things2 = new ThingsDTO(idThings,nameUpdate,fktouserUpdate,fklabelUpdate);					
				thingsServiceDTO.updateThings(things2);
				showAllThings(request, response);
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
				final ThingsDTO label2 = new ThingsDTO(idThingCode, text);					
				thingsServiceDTO.insertCode(label2);
				//showAllThings(request, response);
				response.sendRedirect("homeBO.jsp");
				break;
				
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
			}
		}

	private void showAllThings(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				allThings = this.thingsServiceDTO.getAllThings();
				request.getSession().setAttribute("allThings", allThings);
				getServletContext().getRequestDispatcher("/showThings.jsp").forward(request, response);
		}
}