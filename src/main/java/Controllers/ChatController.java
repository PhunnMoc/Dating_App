package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ChatDAO;
import Models.Account;
import Models.Message;
import Models.Profile;

/**
 * Servlet implementation class ChatController
 */
@WebServlet("/chat")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChatDAO chatDAO;

	public void init() {
		chatDAO = new ChatDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChatController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
System.out.print("aaaaaaa");
		try {
			switch (action) {
			case "/last_Message":
				last_message(request, response);
				break;
			case "/list":
				
				list_other_user(request, response);
				break;
			default:
				/*
				 * HttpSession session = request.getSession(); session.invalidate();
				 * RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				 * dispatcher.forward(request, response);
				 */
				list_other_user(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void list_other_user(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		System.out.print("aaaaaaa");
		Account acc = new Account();
		HttpSession session = request.getSession();
		acc = (Account) session.getAttribute("Acc");
		List<Profile> list_profile = chatDAO.select_other_user_message("user1_id");
		request.setAttribute("list_other_user", list_profile);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/chat.jsp");
		dispatcher.forward(request, response);
	}

	private void last_message(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		Account acc = new Account();
		HttpSession session = request.getSession();
		acc = (Account) session.getAttribute("Acc");
		String otherUserID = request.getParameter("otherUserID");

		Message last_Message = chatDAO.select_message_last("user1_id", "otherUserID");
		RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp");
		request.setAttribute("last_Message", last_Message);
		dispatcher.forward(request, response);
	}
}
