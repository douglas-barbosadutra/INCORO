package it.contrader.servlets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.contrader.dto.LabelsDTO;
import it.contrader.dto.UsersDTO;
import it.contrader.service.LabelsServiceDTO;

public class LabelsServlet extends HttpServlet {

	private final LabelsServiceDTO labelsServiceDTO = new LabelsServiceDTO();
	private List<LabelsDTO> allLabels = new ArrayList<>();
	private int idLabel;
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String scelta = request.getParameter("action");
		final HttpSession session = request.getSession(true); //sto impostando una serie di pulsanti

		switch (scelta) {
		case "openInsert":
			response.sendRedirect("insertLabel.jsp");
			break;
		
		case "insert":
			//final Integer id = Integer.parseInt(request.getParameter("idLabel"));
			final String name = request.getParameter("name");
			final Integer fktouser = Integer.parseInt(request.getParameter("fktouser"));
			final LabelsDTO label = new LabelsDTO(name, fktouser);
			labelsServiceDTO.insertLabels(label);
			showAllLabels(request, response);
			break;
					
		case "openDelete":
			response.sendRedirect("deleteLabels.jsp");
			break;
			
		case "delete":
			final Integer idDelete = Integer.parseInt(request.getParameter("id"));
			final LabelsDTO use = new LabelsDTO(idDelete,"", 1);
			labelsServiceDTO.deleteLabels(use);
			showAllLabels(request, response);
			break;
		
		case "openUpdate":{
			idLabel = Integer.parseInt(request.getParameter("id"));
			//int id = Integer.parseInt(request.getParameter("id"));
			response.sendRedirect("updateLabels.jsp");
		} break;
		
		case "update":
			/*
			System.out.println("id: "+Integer.parseInt(request.getParameter("idLabel")));
			System.out.println("name: "+request.getParameter("name"));
			System.out.println("fktouser: "+Integer.parseInt(request.getParameter("fktouser")));*/
			//final Integer idUpdate = Integer.parseInt(request.getParameter("id"));
			final String nameUpdate = request.getParameter("name");
			final Integer fktouserUpdate = Integer.parseInt(request.getParameter("fktouser"));
			final LabelsDTO label2 = new LabelsDTO(idLabel,nameUpdate,fktouserUpdate);					
			labelsServiceDTO.updateLabels(label2);
			showAllLabels(request, response);
			break;		
		
		case "openList":
			showAllLabels(request,response);
			break;
		/*
		case "UsersManager":
			allLabels = this.labelsServiceDTO.getAllLabels();
			request.setAttribute("allLabels", allLabels);
			getServletContext().getRequestDispatcher("/Labels.jsp").forward(request, response);
			break;			

		case "insert":
			final Integer id = Integer.parseInt(request.getParameter("idThing"));
			final String name = request.getParameter("name");
			final Integer fktouser = Integer.parseInt(request.getParameter("fktouser"));
			
			final LabelsDTO thing = new LabelsDTO(id,name, fktouser);
			labelsServiceDTO.insertLabels(thing);
			showAllLabels(request, response);
			break;
		
		case "update":
			System.out.println("id: "+Integer.parseInt(request.getParameter("id")));
			System.out.println("name: "+request.getParameter("name"));
			System.out.println("fktouser: "+Integer.parseInt(request.getParameter("fktouser")));
			final Integer idUpdate = Integer.parseInt(request.getParameter("id"));
			final String nameUpdate = request.getParameter("name");
			final Integer fktouserUpdate = Integer.parseInt(request.getParameter("fktouser"));
			final LabelsDTO label2 = new LabelsDTO(idUpdate,nameUpdate,fktouserUpdate);					
			labelsServiceDTO.updateLabels(label2);
			showAllLabels(request, response);
			break;		
				
		case "delete":
			final Integer idUpdat = Integer.parseInt(request.getParameter("id"));
			final LabelsDTO use = new LabelsDTO(idUpdat,"" ,new Integer(0));
			labelsServiceDTO.deleteLabels(use);
			showAllLabels(request, response);
			break;

		case "Indietro":
			response.sendRedirect("homeAdmin.jsp");
			break;

		case "LogsMenu":
			response.sendRedirect("homeLogs.jsp");
			break;
			*/
				}
			}
	
private void showAllLabels(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		allLabels = this.labelsServiceDTO.getAllLabels();
		request.getSession().setAttribute("allLabels", allLabels);
		getServletContext().getRequestDispatcher("/showLabels.jsp").forward(request, response);
	}

private void showAllLabels2(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		allLabels = this.labelsServiceDTO.getAllLabels();
		request.getSession().setAttribute("allLabels", allLabels);
		getServletContext().getRequestDispatcher("/updateLabels.jsp").forward(request, response);
	}
}
