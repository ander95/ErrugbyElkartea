package servletak;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Data;

public class LogInServlet extends HttpServlet {

	private Data database;

	public void init (ServletConfig config) {
		System.out.println("---> Entering init() LoginServlet");

		database = new Data();

		System.out.println("<--- Exiting init() LoginServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---> Entering doGet() LoginServlet");

		response.setHeader("Cache-Control", "no-cache");
		request.setAttribute("login_error", false);

		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		System.out.println("     Extracting request parameters: " + email + " " + password);

		String username = database.getUsername(email, password);
		if(username == null) {					//erabiltzailea ez dago datubasean edo pasahitza oker dago
			request.setAttribute("login_error", true);
			
			System.out.println("     Login error: Wrong email or password --> Redirecting the user to loginForm");
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/loginForm.jsp");
			rd.forward(request, response);
		}else{
			HttpSession session = request.getSession(true);			//saioa eman eta ez badu sortu
			session.setAttribute("username",  username);			//saioan erabiltzaile atributua eman
			String sessionID = session.getId();						//erabiltzaileari ID bat esleitu
			session.setAttribute("email", email);
			System.out.println("     User session for " + username + ": " + sessionID);
			
			System.out.println("     Redirecting the user to mainPage.html");
			RequestDispatcher rd = request.getRequestDispatcher("/html/mainPage.html");
			rd.forward(request, response);
		}
		System.out.println("<--- Exiting doGet() LoginServlet");
	}

}
