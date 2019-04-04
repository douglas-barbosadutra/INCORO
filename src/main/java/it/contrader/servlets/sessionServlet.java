package it.contrader.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.contrader.dto.ThingsDTO;

/**
 * Servlet implementation class sessionServlet
 */
//@WebServlet("/sessionServlet")
public class sessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		final String scelta = request.getParameter("action");
		final HttpSession session = request.getSession(true); //sto impostando una serie di pulsanti
		String step = new String("1");
		switch (scelta) {
		case "s1":
			session.setAttribute("nome", "admin");
			session.setAttribute("step", step);
			response.sendRedirect("provasession.jsp");
			break;
		case "s2":
			step = new String("2");
			session.setAttribute("step", step);
			response.sendRedirect("provasession.jsp");
			break;
		case "s3":
			break;
		case "s4":
			break;
		case "s5":
			break;
		case "s6":
			break;
		case "s7":
			break;
		}
	}


}
