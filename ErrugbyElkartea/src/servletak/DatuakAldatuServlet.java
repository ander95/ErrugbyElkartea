package servletak;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Data;


public class DatuakAldatuServlet extends HttpServlet {

	private Data database;

	public void init(ServletConfig config){
		System.out.println("---> Entering init() MainServlet");

		database = new Data();

		System.out.println("---> Exiting init() MainServlet");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("--->Etering doPost() SignUpServlet");

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
				String ErabiltzaileIzena = request.getParameter("ErabiltzaileIzena");
				String Izena = request.getParameter("Izena");
				String Abizena = request.getParameter("Abizena");
				String Kalea = request.getParameter("Kalea");
				String Herria = request.getParameter("Herria");
				String PostaKodea = request.getParameter("PostaKodea");
				String JaiotzeData = request.getParameter("JaiotzeData");
				String Telefonoa = request.getParameter("Telefonoa");
				
				request.setAttribute("empty_value_error", false);

				if (ErabiltzaileIzena.isEmpty()||Izena.isEmpty()||Abizena.isEmpty()||Kalea.isEmpty()||
						Herria.isEmpty()||PostaKodea.isEmpty()||JaiotzeData.isEmpty()||Telefonoa.isEmpty()) {
					request.setAttribute("empty_value_error", true);
					System.out.println("    Empty value error: No null values allowed --> redirecting to changeAccountInfo.html");

					RequestDispatcher rd = request.getRequestDispatcher("/jsp/changeAccountInfo.jsp");
					rd.forward(request, response);
				}else{
					HttpSession session = request.getSession();
					String Email = (String)session.getAttribute("email");
					
					System.out.println("    Extracting request parameters: " + ErabiltzaileIzena + ", " + Izena + ", " + Abizena + ", " 
							+ Kalea + ", " + Herria + ", " + PostaKodea + ", " + JaiotzeData + ", " + Telefonoa);

					database.updateUserInfo(ErabiltzaileIzena, Izena, Abizena, Email, Kalea, Herria, PostaKodea, JaiotzeData, Telefonoa);

					System.out.println("    Updating user info");
					System.out.println("    Redirecting user to mainPage.html");

					RequestDispatcher rd = request.getRequestDispatcher("/html/mainPage.html");
					rd.forward(request, response);
				}
			}
		}

		System.out.println("<--- Exiting doPost() DatuakAldatuServlet");
	}

}
