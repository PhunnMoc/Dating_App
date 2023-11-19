package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ChatDAO;
import DAO.LoginDAO;
import DAO.MatchDAO;
import DAO.ProfileDAO;
import Handle.ImageHandle;
import Models.Account;
import Models.Hobby;
import Models.Profile;
import Models.UserHobby;

/**
 * Servlet implementation class AdminRole
 */
@WebServlet("/AdminRole")
public class AdminRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfileDAO profileDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRole() {
        super();
        // TODO Auto-generated constructor stub
    }
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		profileDAO = new ProfileDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		
		request.setCharacterEncoding("UTF-8");
		try {
			switch (action) {
			case "/listHobby":
				ListHobby(request, response);
				break;
			case "/edit":
				EditHobby(request, response);
				break;
			case "/add":
				AddHobby(request, response);
				break;
			case "/delete":
				DeleteHobby(request, response);
				break;
			default:
				System.out.println("df" );
				break;
			}
		  } catch (SQLException ex) {
	            throw new ServletException(ex);
		  }  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void ListHobby(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		List<Hobby> listHobby = profileDAO.GetAllHobbies();
		request.setAttribute("listHobby", listHobby);		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/Admin.jsp");
		dispatcher.forward(request, response);
	}
	private void EditHobby(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		List<Hobby> listHobby = profileDAO.GetAllHobbies();
		request.setAttribute("listHobby", listHobby);		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/Admin.jsp");
		dispatcher.forward(request, response);
	}

	private void AddHobby(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		List<Hobby> listHobby = profileDAO.GetAllHobbies();
		request.setAttribute("listHobby", listHobby);		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/Admin.jsp");
		dispatcher.forward(request, response);
	}
		private void DeleteHobby(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		String id=request.getParameter("id");
		profileDAO.DeleteUserHobby_IDhobby(id);
		profileDAO.DeleteHobby_IDhobby(id);
		response.sendRedirect("/AdminRole/listHobby");
	}

}
