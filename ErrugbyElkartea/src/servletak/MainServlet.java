package servletak;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Data;

public class MainServlet extends HttpServlet {

	private Data database;

	public void init(ServletConfig config){
		System.out.println("---> Entering init() MainServlet");

		database = new Data();

		System.out.println("<--- Exiting init() MainServlet");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("---> Entering doPost() MainServlet");

		response.setHeader("Cache-Control", "no-cache");

		if(request.getSession(false) == null) {				//logeatu gabe
			System.out.println("     User is not logged in");
			System.out.println("     Redirecting the user to loginForm");
			
			RequestDispatcher rd = request.getRequestDispatcher("/html/loginForm.html");
			rd.forward(request, response);
		}else{
			System.out.println("     User is logged in");
			String action = request.getParameter("action");
			if (action != null) { 							//erabiltzaileak Logout botoia sakatu du
				HttpSession session = request.getSession();
    		    session.invalidate();
    		    System.out.println("     Login error: redirecting the user to loginForm");
    		    
    		    RequestDispatcher rd = request.getRequestDispatcher("/html/loginForm.html");    
    			rd.forward(request, response);
			}
		}
		System.out.println("<--- Exiting doPost() MainServlet");
	}

}
