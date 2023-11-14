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

import DAO.AccountDAO;
import DAO.ProfileDAO;
import DAO.RegisterDAO;
import Models.Account;
import Models.Profile;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		request.setCharacterEncoding("UTF-8");

		RegisterDAO registerAccount = new RegisterDAO();
		if (registerAccount.checkEmailExists(email)) {
			request.setAttribute("error_register", "Email đã được sử dụng. Vui lòng chọn email khác.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/Login.jsp");
            dispatcher.forward(request, response);
		} else {
			String UserID = registerAccount.generateUserID();
			System.out.print("ID moi duoc tao");
			Profile profile = new Profile(UserID, fullname);
			ProfileDAO profileDAO = new ProfileDAO();
			profileDAO.insertProfile(profile);
			
			Account account = new Account(email, password, UserID);
			AccountDAO accountDAO = new AccountDAO();
			accountDAO.insertAccount(account);
			HttpSession session = request.getSession();
        	session.setAttribute("account", account);
			String url ="/Pages/InforLogin.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
