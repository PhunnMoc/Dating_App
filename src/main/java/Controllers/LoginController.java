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
 * @email Ramesh Fadatare
 */

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1 ;
    private LoginDAO loginDao;

    public void init() {
        loginDao = new LoginDAO();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
    	//B1: lấy dâta từ form người dùng 
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        //B2: Khởi tạo model
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);

        //B3: Kết nối csdl -> Trả kết quả 
        try {
			
			  Account acc = new Account(); 
			  acc= loginDao.onLogin(account);
			 
            if (acc != null) {           	
            		HttpSession session = request.getSession();
                    session.setAttribute("Acc", acc);
                    session.setAttribute("nameAcc", username);
                    response.sendRedirect("loginsuccess.jsp");           	
            	
            } else {
                //HttpSession session = request.getSession();
                //session.setAttribute("user", username);
                //response.sendRedirect("login.jsp");
            	request.setAttribute("errMsg", "Thong tin dang nhap bi sai");
            	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            	dispatcher.forward(request, response);
            	
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        response.sendRedirect("login.jsp");
    	    }
}