package Controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
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

import DAO.AccountDAO;
import DAO.LoginDAO;
import DAO.ProfileDAO;
import DAO.RegisterDAO;
import Models.Account;
import Models.Hobby;
import Models.Profile;
import Models.UserHobby;
import Handle.ImageHandle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Servlet implementation class ProfileControllers
 */
@WebServlet("/pro")
@MultipartConfig
public class ProfileControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfileDAO profileDAO;
	private LoginDAO loginDao;

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
		loginDao = new LoginDAO();
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
			case "/update":
				updateProfile(request, response);
				break;
			case "/updateHobby":
				updateHobby(request, response);
				break;
			case "/list":
				System.out.println("hehe" );
				ShowProfile(request, response);
				break;
			case "/Login":
				System.out.println("hehe" );
				authenticate(request, response);
				break;
			case "/Logout":
				System.out.println("hehe" );
				HandleLogout(request, response);
				break;
			case "/Register":
				System.out.println("hehe" );
				HandleRegister(request, response);
				break;
			default:
				System.out.println("df" );
				break;
			}
		  } catch (SQLException ex) {
	            throw new ServletException(ex);
		  } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
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
		doGet(request, response);
	}

	private void ShowProfile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		Profile profile = new Profile();
		profile = profileDAO.GetProfile(account);
		List<UserHobby> listHobby = profileDAO.GetHobby(account);
		request.setAttribute("profile", profile);
		request.setAttribute("listHobby", listHobby);		
		String image = ImageHandle.byteArrayToImage(profile.getImageData());
		request.setAttribute("image", image);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/InforLogin.jsp");
		dispatcher.forward(request, response);
	}


	private void updateProfile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException, ServletException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");	
		String userID = account.getUserID();
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		int year = getYearFromDate(birthday);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date birthDay = new java.sql.Date(dateFormat.parse(birthday).getTime());

		int currentYear = LocalDate.now().getYear();
		int age;
		if (year == 1)
			age = 0;
		else
			age = currentYear - year;
		String relationship = request.getParameter("relationship");
		int height = Integer.parseInt(request.getParameter("height"));
		String zodiac = request.getParameter("cunghoangdao");
		String address = request.getParameter("address");
		String introduce = request.getParameter("introduce");
		
		Part filePart = request.getPart("image");
		if (filePart != null)
		{
			InputStream fileContent = filePart.getInputStream();
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int bytesRead;
	        while ((bytesRead = fileContent.read(buffer)) != -1) {
	            byteArrayOutputStream.write(buffer, 0, bytesRead);
	        }
	        byte[] imageData = byteArrayOutputStream.toByteArray();
	        System.out.println("ImageData= " + imageData);
	        Profile profile = new Profile(userID, name, age, gender, birthDay, relationship, height, zodiac, address,
					introduce, imageData);
	        profileDAO.updateProfileImage(profile);	        
		}
		else
		{
			Profile profile = new Profile(userID, name, age, gender, birthDay, relationship, height, zodiac, address,
					introduce);
	        profileDAO.updateProfile(profile);
		}
		List < UserHobby > listHobby = profileDAO.GetHobby(account);
		request.setAttribute("listHobby", listHobby);
		List<Hobby> listAllHobby = profileDAO.GetAllHobbies();
		request.setAttribute("listAllHobby", listAllHobby);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/SoThich.jsp");
		dispatcher.forward(request, response);

	}

	private void updateHobby(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ParseException, ServletException, ClassNotFoundException {
		String[] selectedHobbies = request.getParameterValues("listHobby");
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		profileDAO.DeleteUserHobby(account);
        if (selectedHobbies != null && selectedHobbies.length > 0) {
            for (String hobby : selectedHobbies) {
            	profileDAO.UpdateUserHobby(hobby, account);
            }
        } else {
            System.out.println("Không có hobby nào được chọn");
        }
        Profile profile = new Profile();
		profile = profileDAO.GetProfile(account);
		List<UserHobby> listHobby = profileDAO.GetHobby(account);
		request.setAttribute("profile", profile);
		request.setAttribute("listHobby", listHobby);		
		String image = ImageHandle.byteArrayToImage(profile.getImageData());
		request.setAttribute("image", image);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/Match.jsp");
		dispatcher.forward(request, response);
	}
	
	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Account account = new Account();
        account = loginDao.validate(email, password);
        String url = "";
        if (account != null) {
			HttpSession session = request.getSession();
			session.setAttribute("account", account);
			url = "/Pages/Match.jsp";
			Profile profile = new Profile();
			profile = profileDAO.GetProfile(account);
			List<UserHobby> listHobby = profileDAO.GetHobby(account);
			request.setAttribute("profile", profile);
			request.setAttribute("listHobby", listHobby);		
			String image = ImageHandle.byteArrayToImage(profile.getImageData());
			request.setAttribute("image", image);
		    //RequestDispatcher dispatcher = request.getRequestDispatcher("Match.jsp");
		    
		} else {
			request.setAttribute("error_login", "Email hoặc password không chính xác");
			url = "/Pages/Login.jsp";
		    //RequestDispatcher dispatcher = request.getRequestDispatcher("Pages/Login.jsp");
		    //dispatcher.forward(request, response);
		}
        
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	protected void HandleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			String url ="/pro/list";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
		}		
	}
	protected void HandleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/Login.jsp");
		dispatcher.forward(request, response);
	}
	private int getYearFromDate(String date) {
		if (date == null)
			return 1;
		String[] parts = date.split("-");
		String firstPart = parts[0];
		return Integer.parseInt(firstPart);
	}
	

}
