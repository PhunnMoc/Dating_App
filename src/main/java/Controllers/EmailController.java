package Controllers;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProfileDAO;
import Util.EmailUtility;

/**
 * Servlet implementation class EmailController
 */
@WebServlet("/send-email")
public class EmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String host;
    private String port;
    private String user;
    private String pass;
    private ProfileDAO profileDAO;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
        profileDAO = new ProfileDAO();
    }
 


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String recipient = request.getParameter("recipient");
	       String content = profileDAO.FogerPass(recipient);
	 
	        String resultMessage = "";
	        try {
	            EmailUtility.sendEmail(host, port, user, pass, recipient,content);
	            resultMessage = "The e-mail was sent successfully";
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            resultMessage = "There were an error: " + ex.getMessage();
	        } finally {
	            request.setAttribute("Message", resultMessage);
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/FogetPass.jsp");
		 		dispatcher.forward(request, response);

	    
	        }
	}

}
