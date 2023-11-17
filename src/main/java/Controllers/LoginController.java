package Controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.LoginDAO;
import Models.Account;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDAO loginDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
    	loginDao = new LoginDAO();
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
		authenticate(request, response);
	}
	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        
        String url = "";
        try {
            if (loginDao.validate(account.getEmail(),account.getPassword())!= null) {
            	HttpSession session = request.getSession();
            	session.setAttribute("account", account);
            	url = "/Pages/Match.jsp";
                //RequestDispatcher dispatcher = request.getRequestDispatcher("Match.jsp");
                
            } else {
            	request.setAttribute("error_login", "Email hoặc password không chính xác");
            	url = "/Pages/Login.jsp";
                //RequestDispatcher dispatcher = request.getRequestDispatcher("Pages/Login.jsp");
                //dispatcher.forward(request, response);
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}

}
