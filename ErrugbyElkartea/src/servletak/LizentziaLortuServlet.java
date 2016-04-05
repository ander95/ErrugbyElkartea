package servletak;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Data;

public class LizentziaLortuServlet extends HttpServlet {

	private Data db;

	public void init(){
		System.out.println("---> Entering init() SignupServlet");

		db = new Data();

		System.out.println("<--- Exiting init() SignupServlet");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("--->Etering doPost() LizentziaLortuServlet");

		response.setHeader("Cache-Control", "no-cache");

		if(request.getSession(false) == null) {
			System.out.println("     User is not logged in");
			System.out.println("     Redirecting the user to loginForm");
			
			RequestDispatcher rd = request.getRequestDispatcher("/html/loginForm.html");
			rd.forward(request, response);
		}else{
			System.out.println("     User is logged in");
			String action = request.getParameter("action");
			if (action != null) {
				HttpSession session = request.getSession();
				session.invalidate();
				System.out.println("     Login error: redirecting the user to loginForm");
				
				RequestDispatcher rd = request.getRequestDispatcher("/html/loginForm.html");
				rd.forward(request, response);
			}else{
				String Lizentzia = request.getParameter("Lizentzia");
				HttpSession session = request.getSession();
				String Email = (String)session.getAttribute("email");
				System.out.println(Lizentzia);

				db.updateUserLicence(Email, Lizentzia);

				RequestDispatcher rd = request.getRequestDispatcher("/html/mainPage.html");
				rd.forward(request, response);
			}
		}

		System.out.println("<---Exiting doPost() LizentziaLortuServlet");

	}

}
