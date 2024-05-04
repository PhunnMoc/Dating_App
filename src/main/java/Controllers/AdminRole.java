package Controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.ChatDAO;
import DAO.LoginDAO;
import DAO.MatchDAO;
import DAO.ProfileDAO;
import DAO.RegisterDAO;
import Handle.ImageHandle;
import Models.Account;
import Models.Hobby;
import Models.Profile;
import Models.UserHobby;

/**
 * Servlet implementation class AdminRole
 */
@WebServlet("/AdminRole")
@MultipartConfig
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();

		request.setCharacterEncoding("UTF-8");
		try {
			switch (action) {
			case "/list":
				ListHobby(request, response);
				break;
			case "/listUser":
				ListProfileMatch(request, response);
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
				System.out.println("df");
				break;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	private void ListHobby(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		List<Hobby> listHobby = profileDAO.GetAllHobbies();
		request.setAttribute("list", listHobby);
		String name = request.getParameter("namehobby");
		System.out.print(name);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/Admin.jsp");
		dispatcher.forward(request, response);
	}

	private void EditHobby(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		String id = request.getParameter("idhobby");
		String name = request.getParameter("namehobby");
		Part filePart = request.getPart("image");

		if (filePart != null && filePart.getSize() > 0) {
			InputStream fileContent = filePart.getInputStream();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = fileContent.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, bytesRead);
			}
			byte[] imageData = byteArrayOutputStream.toByteArray();
			System.out.print(id);
			System.out.print(name);
			System.out.print(imageData);
			profileDAO.UpdateHobby(id, name, imageData);
		} else {
			profileDAO.UpdateHobby_noImg(id, name);
		}
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole/list");
		dispatcher.forward(request, response);
	}

	private void AddHobby(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		RegisterDAO registerAccount = new RegisterDAO();
		String id = registerAccount.generateUserID();
		String name = request.getParameter("namehobby");
		String name1 = request.getParameter("huhu");
		System.out.print(name);
		System.out.print(name1);
		Part filePart = request.getPart("image");

		if (filePart != null && filePart.getSize() > 0) {
			InputStream fileContent = filePart.getInputStream();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = fileContent.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, bytesRead);
			}
			byte[] imageData = byteArrayOutputStream.toByteArray();
			System.out.print(id);
			System.out.print(name);
			System.out.print(imageData);
			profileDAO.InsertHobby(id, name, imageData);
		} else {
			profileDAO.InsertHobby(id, name, null);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole/list");
		dispatcher.forward(request, response);
	}

	private void DeleteHobby(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		String id = request.getParameter("id");
		profileDAO.DeleteUserHobby_IDhobby(id);
		profileDAO.DeleteHobby_IDhobby(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminRole/list");
		dispatcher.forward(request, response);
	}
	private void ListProfileMatch(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		
		List<Profile> ListProfileMatch = profileDAO.GetAllProfile();
//        request.setAttribute("listImage", listImage);
		request.setAttribute("ListProfileMatch", ListProfileMatch);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/AdminMangerUser.jsp");
		dispatcher.forward(request, response);
	}

}
