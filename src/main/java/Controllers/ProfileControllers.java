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
import DAO.ChatDAO;
import DAO.LoginDAO;
import DAO.MatchDAO;
import DAO.ProfileDAO;
import DAO.RegisterDAO;
import Models.Account;
import Models.Hobby;
import Models.Match;
import Models.Message;
import Models.Profile;
import Models.UserHobby;
import Handle.FarseToJSON;
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
	private MatchDAO matchDAO;
	private ChatDAO chatDAO;

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
		matchDAO=new MatchDAO();
		chatDAO=new ChatDAO();
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
			 case "/listMatch":
                ListProfileMatch(request, response);
                break;
			 case "/deleteMatch":
				 DeleteProfileMatch(request, response);
				 break;
			 case "/sayHello":
				 SayHello(request, response);
				 break; 
				 
            case "/showCard":
                ListProfile(request, response);
                	break;
               case "/message":
   				HandleMessage(request, response);
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
		 String requestType = request.getHeader("X-Request-Type");
		
	    if ("HandleMatch".equals(requestType)) {
	    	try {
				HandleMatch(request, response);
			} catch (ClassNotFoundException | SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    } else {
	       doGet(request, response);
	    }
		

		
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
		if (filePart != null && filePart.getSize() > 0)
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pro/showCard");
		dispatcher.forward(request, response);
	}
	
	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ClassNotFoundException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Account account = new Account();
        account = loginDao.validate(email, password);
        System.out.print(account);
        String url = "";
        if (account != null) {
			HttpSession session = request.getSession();
			session.setAttribute("account", account);
			url = "/pro/showCard";
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
	  //phương
    private void ListProfile(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException, ClassNotFoundException {
		HttpSession session = request.getSession();
		System.out.print("hahaa");
    	Account account = (Account) session.getAttribute("account");	
		
		  ProfileDAO profileDAO = new ProfileDAO();
		  
		  Profile profile = profileDAO.GetProfile(account); if(profile!= null) {
		  request.setAttribute("MyOwnProfile", profile); }
		 
		String userID = account.getUserID();
    	List < Profile > ListProfile = profileDAO.GeListProfile(userID);
//        request.setAttribute("listImage", listImage);
        request.setAttribute("ListProfile", ListProfile);
        
    	String userid = account.getUserID();
    	List < Profile > ListProfileMatch = profileDAO.GeListProfileMatch(userid);
//        request.setAttribute("listImage", listImage);
    	request.setAttribute("ListProfileMatch", ListProfileMatch);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/Match.jsp");
        dispatcher.forward(request, response);
    }
    
	private void ListProfileMatch(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String userID = account.getUserID();
		List<Profile> ListProfileMatch = profileDAO.GeListProfileMatch(userID);
//        request.setAttribute("listImage", listImage);
		request.setAttribute("ListProfileMatch", ListProfileMatch);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/ListMatch.jsp");
		dispatcher.forward(request, response);
	}
	private void DeleteProfileMatch(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String userID1 = account.getUserID();
		String userID2 = request.getParameter("deleteMatch");
		boolean isDelete= matchDAO.deleteMatch(userID1, userID2);
		if(isDelete)
		{	
			System.out.print(isDelete);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pro/listMatch");
			dispatcher.forward(request, response);
		
	}
   private void SayHello(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String userID1 = account.getUserID();
		String userID2 = request.getParameter("sayHello");
		String content = request.getParameter("content");
		System.out.print(content);
		if (content != null && !content.trim().isEmpty())
		{
			chatDAO.insertMessage(userID2, userID1, content);
			content=null;
		}
				
		response.sendRedirect(request.getContextPath() + "/pro/message");
		
	}
    //phương
       
	     private void HandleMatch(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException, ClassNotFoundException {
   		HttpSession session = request.getSession();
    	Account account = (Account) session.getAttribute("account");	
		String userID1 = account.getUserID();
		String userID2 = request.getParameter("userID");
//		System.out.print(userID2);
		Match match=new Match(LocalDate.now(),userID1,userID2);
        // Call DAO to save the user ID to the database		
		try {
			matchDAO.insertMatch(match);
			matchDAO.updateMatchAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	     private void HandleMessage(HttpServletRequest request, HttpServletResponse response)
	 			throws Exception {
	 		System.out.print("aaaaaaa");
	 		HttpSession session = request.getSession();
	 		Account acc = new Account();		
	 		acc = (Account) session.getAttribute("account");
	 		List<Profile> list_profile = chatDAO.select_other_user_message(acc.getUserID());
	 		request.setAttribute("list_other_user", list_profile);		
	 		String listProfileJSON = FarseToJSON.listProfileToJSON(list_profile);
	 		request.setAttribute("listProfileJSON", listProfileJSON);
	 		
	 		List<Message> lastmessage = chatDAO.select_message_last(acc.getUserID());
	 		request.setAttribute("last_Message", lastmessage);

	 		List<Message> listMessage = chatDAO.select_message_by_UserID(acc.getUserID());
	 		request.setAttribute("list_Message", listMessage);
	 		String listMessJSON = FarseToJSON.listMessageToJSON(listMessage);
	 		request.setAttribute("listMessJSON", listMessJSON);
	 		
	 		Profile profile = new Profile();
	 		profile = profileDAO.GetProfile(acc);
	 		String image = ImageHandle.byteArrayToImage(profile.getImageData());
	 		request.setAttribute("image", image);
	 		request.setAttribute("profile", profile);
	 		
	 		RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/chat.jsp");
	 		dispatcher.forward(request, response);
	 	}
	 	

}