package servletak;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import database.Data;
import userinfo.UserInfo;

public class PDFBihurtuServlet extends HttpServlet {

	private Data db;

	public void init(ServletConfig config){
		System.out.println("---> Entering init() PDFBihurtuServlet");

		db = new Data();

		System.out.println("<--- Exiting init() PDFBihurtuServlet");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println(request.getParameter("action"));

		if(request.getSession(false) == null) {
			System.out.println("     User is not logged in");
			System.out.println("     Redirecting the user to loginForm");
			
			RequestDispatcher rd = request.getRequestDispatcher("/html/loginForm.html");
			rd.forward(request, response);
		}else{
			System.out.println("     User is logged in");
			String action = request.getParameter("action");
			if (action != null && action.equals("logout")) {
				HttpSession session = request.getSession();
				session.invalidate();
				System.out.println("     Login error: redirecting the user to loginForm");
				
				RequestDispatcher rd = request.getRequestDispatcher("/html/loginForm.html");
				rd.forward(request, response);
			}else if (action != null && action.equals("ziurtagiria_lortu")) {
				HttpSession session = request.getSession();
				String Email = (String)session.getAttribute("email");
				UserInfo userInfo = db.getUserInfo(Email);

				ServletOutputStream out = response.getOutputStream();
				response.setContentType("application/pdf");
				try {
					Document document = new Document();
					PdfWriter.getInstance(document, out);
					document.open();
					document.add(new Paragraph("LIZENTZIAREN ZIURTAGIRIA"));
					document.add(new Paragraph("Izena: " + userInfo.getIzena()));
					document.add(new Paragraph("Abizena: " + userInfo.getAbizena()));
					document.add(new Paragraph("Lizentzia: " + userInfo.getLizentziaMota()));
					document.add(new Paragraph("Kalea: " + userInfo.getKalea()));
					document.add(new Paragraph("Herria: " + userInfo.getHerria()));
					document.add(new Paragraph("Posta Kodea: " + userInfo.getPostaKodea()));
					document.add(new Paragraph("Email: " + userInfo.getEmail()));
					document.add(new Paragraph("Telefonoa: " + userInfo.getTelefonoa()));
					document.close();
				} catch (Exception e) {
					System.out.println(e);
				}
				out.flush();
				out.close();
			}
		}
	}
}
