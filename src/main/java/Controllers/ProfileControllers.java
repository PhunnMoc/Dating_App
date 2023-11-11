package Controllers;

import java.io.IOException;
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
import Models.Hobby;
import Models.Profile;
import Models.UserHobby;
import Models.Image;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Servlet implementation class ProfileControllers
 */
@WebServlet("/")
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
		request.setCharacterEncoding("UTF-8");
        try {
            switch (action) {
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/update":
                    updateProfile(request, response);
                    break;
                case "/showhobby":
                    showHobby(request, response);
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
		doGet(request, response);
		
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
		//Account account = (Account) session.getAttribute("acc");
    	Account account = new Account("user2", "password2", "user2_id");
    	Profile profile = new Profile();
		profile = profileDAO.GetProfile(account);	
        List < Image > listImage = profileDAO.GetImage(account);
        List < UserHobby > listHobby = profileDAO.GetHobby(account);
        request.setAttribute("profile", profile);
        request.setAttribute("listImage", listImage);
        request.setAttribute("listHobby", listHobby);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./Pages/InforLogin.jsp");
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
		//Account account = (Account) session.getAttribute("acc");
    	Account account = new Account("user2", "password2", "user2_id");
        String userID = account.getUserid();
        
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        System.out.println("Giá trị của textarea là: " + birthday);
        int year = getYearFromDate(birthday);
        System.out.println("Giá trị của tea là: " + year);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date birthDay = new java.sql.Date(dateFormat.parse(birthday).getTime());

        
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - year;
        
        String relationship = request.getParameter("relationship");
        int height = Integer.parseInt(request.getParameter("height"));
        String zodiac = request.getParameter("cunghoangdao");
        String address = request.getParameter("address");
        String introduce = request.getParameter("introduce");
        System.out.println("Giá trị của tea là: " + introduce);
        Profile profile = new Profile(userID, name, age, gender, birthDay, relationship, height,zodiac, address, introduce);
        profileDAO.updateProfile(profile);
		/*
		 * List < UserHobby > listHobby = profileDAO.GetHobby(account);
		 * request.setAttribute("listHobby", listHobby);
		 */
        response.sendRedirect("showhobby");
    }
    private void showHobby(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ParseException, ServletException {
    	        List < Hobby > listAllHobby = profileDAO.GetAllHobbies();
    	        request.setAttribute("listAllHobby", listAllHobby);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("./Pages/SoThich.jsp");
    	        dispatcher.forward(request, response);
    	        
    	    }
    private int getYearFromDate(String date) {
    	 String[] parts = date.split("-");
         String firstPart = parts[0];
         return Integer.parseInt(firstPart);
    }

}
