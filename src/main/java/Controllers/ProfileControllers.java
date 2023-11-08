package Controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProfileDAO;
import Models.Account;
import Models.Profile;
import Models.UserHobby;
import Models.Image;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * Servlet implementation class ProfileControllers
 */
@WebServlet("/Profile")
public class ProfileControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfileDAO profileDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileControllers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		profileDAO = new ProfileDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

        try {
            switch (action) {
                case "/Profile/insert":
                    insertUser(request, response);
                    break;
                case "/Profile/update":
                    updateProfile(request, response);
                    break;
                default:
                	ShowProfile(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
//		HttpSession session = request.getSession();
//		Account account = (Account) session.getAttribute("acc");
//		Profile profile = new Profile();
//		Image image = new Image();
//		Hobby hobby = new Hobby();
//        try {
//        	profile = profileDAO.GetProfile(account);
//        	image = profileDAO.GetImage(account);
//        	
//            if (profile != null) {
//                session.setAttribute("profile", profile);
//                response.sendRedirect("Match.jsp");
//            } else {
//            	request.setAttribute("errMsg", "Have to Login to view your Profile");
//                RequestDispatcher dispartcher = request.getRequestDispatcher("Login.jsp");
//                dispartcher.forward(request, response);
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
	}
	
    private void ShowProfile(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException, ClassNotFoundException {
    	HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("acc");
    	Profile profile = new Profile();
		profile = profileDAO.GetProfile(account);	
        List < Image > listImage = profileDAO.GetImage(account);
        List < UserHobby > listHobby = profileDAO.GetHobby(account);
        request.setAttribute("profile", profile);
        request.setAttribute("listImage", listImage);
        request.setAttribute("listHobby", listHobby);
        RequestDispatcher dispatcher = request.getRequestDispatcher("InforLogin.jsp");
        dispatcher.forward(request, response);
    }
	
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String country = request.getParameter("country");
//        User newUser = new User(name, email, country);
//        userDAO.insertUser(newUser);
//        response.sendRedirect("list");
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ParseException {
    	HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("acc");
        String userID = account.getUserid();
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        
        String birthday = request.getParameter("birthday");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay = (Date) dateFormat.parse(birthday);
        
        String relationship = request.getParameter("relationship");
        int height = Integer.parseInt(request.getParameter("height"));
        String zodiac = request.getParameter("zodiac");
        String address = request.getParameter("address");
        String introduce = request.getParameter("introduce");

        Profile profile = new Profile(userID, name, age, gender, birthDay, relationship, height,zodiac, address, introduce);
        profileDAO.updateProfile(profile);
        response.sendRedirect("list");
    }

}
