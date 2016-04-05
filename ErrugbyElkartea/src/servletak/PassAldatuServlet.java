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

public class PassAldatuServlet extends HttpServlet {

	private Data db;

	public void init(ServletConfig config){
		System.out.println("---> Entering init() MainServlet");

		db = new Data();

		System.out.println("<--- Exiting init() MainServlet");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		doPost(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---> Entering doPost() PassAldatuServlet");

		response.setHeader("Cache-Control", "no-cache");
		request.setAttribute("old_password_error", false);
		request.setAttribute("password_error", false);

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
				HttpSession session = request.getSession();
				String Email = (String)session.getAttribute("email");
				String PasahitzZaharra = request.getParameter("OldPass");
				String PasahitzBerria = request.getParameter("NewPass");
				String PasahitzBerria2 = request.getParameter("NewPass2");
				if (!db.getUserPass(Email).equals(PasahitzZaharra)) {
					request.setAttribute("old_password_error", true);
					System.out.println("    Old password error: wrong password -> redirecting to changePassword.html");

					RequestDispatcher rd = request.getRequestDispatcher("/jsp/changePassword.jsp");
					rd.forward(request, response);
				}else if (PasahitzBerria!=PasahitzBerria2) {
					request.setAttribute("password_error", true);
					System.out.println("    Password error: write the new password correctly -> redirecting to changePassword.html");

					RequestDispatcher rd = request.getRequestDispatcher("/jsp/changePassword.jsp");
					rd.forward(request, response);
				}else{
					db.updateUserPassword(Email, PasahitzBerria);

					System.out.println("    Updating user pass");
					System.out.println("    Redirecting user to mainPage.html");

					RequestDispatcher rd= request.getRequestDispatcher("/html/mainPage.html");
					rd.forward(request, response);
				}
			}
		}

		System.out.println("<--- Exiting doPost() PassAldatuServlet");

	}

}
