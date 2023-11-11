package Controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.LoginDAO;
import Models.Account;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDAO loginDAO;

	public LoginController() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {
		loginDAO = new LoginDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);

		try {
			Account acc =new Account();
			acc =loginDAO.onLogin(account);
			 HttpSession session = request.getSession();
			if (acc!=null) {

				 session.setAttribute("user",acc);
				response.sendRedirect("loginsuccess.jsp");
			} else {
				 session.setAttribute("errMsg", "Thông tin đăng nhập không chính xác");
				 RequestDispatcher dispatcher= request.getRequestDispatcher("login.jsp");
//				 response.sendRedirect("login.jsp");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
