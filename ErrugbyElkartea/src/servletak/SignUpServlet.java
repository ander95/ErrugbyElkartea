package servletak;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;
import database.*;
import sun.tools.jar.resources.jar;

public class SignUpServlet extends HttpServlet {

	private Data db;

	public void init(){
		System.out.println("---> Entering init() SignupServlet");

		db = new Data();

		System.out.println("<--- Exiting init() SignupServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("--->Etering doPost() SignUpServlet");

		String ErabiltzaileIzena = request.getParameter("ErabiltzaileIzena");
		String Pasahitza = request.getParameter("Pasahitza");
		String Izena = request.getParameter("Izena");
		String Abizena = request.getParameter("Abizena");
		String Email = request.getParameter("Email");
		String Kalea = request.getParameter("Kalea");
		String Herria = request.getParameter("Herria");
		String PostaKodea = request.getParameter("PostaKodea");
		String JaiotzeData = request.getParameter("JaiotzeData");
		String Telefonoa = request.getParameter("Telefonoa");
		String Lizentzia = "Lizentziarik ez";
		
		request.setAttribute("signup_error", false);
		request.setAttribute("empty_value_error", false);
		
		if (ErabiltzaileIzena.isEmpty()||Pasahitza.isEmpty()||Izena.isEmpty()||Abizena.isEmpty()||Email.isEmpty()||
				Kalea.isEmpty()||Herria.isEmpty()||PostaKodea.isEmpty()||JaiotzeData.isEmpty()||Telefonoa.isEmpty()) {
			request.setAttribute("empty_value_error", true);
			System.out.println("    Empty value error: No null values allowed --> redirecting to signUpForm");
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/signupForm.jsp");
			rd.forward(request, response);
		}else{
			System.out.println("    Extracting request parameters: " + ErabiltzaileIzena + ", " + Pasahitza + ", " + Izena + ", " + Abizena + ", " 
					+ Email + ", " + Kalea + ", " + Herria + ", " + PostaKodea + ", " + JaiotzeData + ", " + Telefonoa + ", " + Lizentzia);

			try {
				db.setUserInfo(ErabiltzaileIzena, Pasahitza, Izena, Abizena, Email, Kalea, Herria, PostaKodea, JaiotzeData, Telefonoa, Lizentzia);
				
				System.out.println("    Inserting user info to database");
				System.out.println("    Redirecting to login page 'loginForm'");

				RequestDispatcher rd = request.getRequestDispatcher("/html/loginForm.html");
				rd.forward(request, response);
				
			} catch (SQLException e) {
				request.setAttribute("signup_error", true);
				System.out.println("    Signup error: Email is used in other account --> redirecting to signupForm");
				
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/signupForm.jsp");
				rd.forward(request, response);
			}
		}
		System.out.println("<--- Exiting doPost() SignupServlet");
	}

}
