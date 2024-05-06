package Controllers;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProfileDAO;
import Util.EmailUtility;

@WebServlet("/send-email")
public class EmailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProfileDAO profileDAO;

    public void init() {
        profileDAO = new ProfileDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        request.setCharacterEncoding("UTF-8");
        try {
            switch (action) {
                case "/send":
                    sendEmail(request, response);
                    break;
                case "/createCSRF":
                    getCSRFtoken(request, response);
                    break;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void getCSRFtoken(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String csrfToken = (String) session.getAttribute("csrf_token");

        if (csrfToken == null) {
            System.out.print("csrfToken: ");

            csrfToken = UUID.randomUUID().toString();
            System.out.print(csrfToken);
            session.setAttribute("csrf_token", csrfToken);
        }
        request.setAttribute("csrf_token", csrfToken);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/FogetPass.jsp");
        dispatcher.forward(request, response);
    }

    private void sendEmail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String csrfToken = (String) session.getAttribute("csrf_token");
        // Láº¥y token CSRF tá»« form
        String csrfTokenFromForm = request.getParameter("csrf_token");
        // Kiá»ƒm tra tÃ­nh há»£p lá»‡ cá»§a token CSRF
        if (csrfTokenFromForm == null || !csrfTokenFromForm.equals(csrfToken)) {
            // Náº¿u token CSRF khÃ´ng há»£p lá»‡, xá»­ lÃ½ lá»—i
            String errorMessage = "CSRF Token khÃ´ng há»£p lá»‡.";
            session.invalidate();
            request.setAttribute("Message", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/FogetPass.jsp");
            dispatcher.forward(request, response);
            return; // Dá»«ng xá»­ lÃ½ Ä‘á»ƒ khÃ´ng gá»­i email náº¿u token CSRF khÃ´ng há»£p lá»‡
        }

        // Tiáº¿p tá»¥c xá»­ lÃ½ khi token CSRF há»£p lá»‡
        String recipient = request.getParameter("recipient");
        String content = profileDAO.FogerPass(recipient);
        String resultMessage = "";

        try {
            // Gá»­i email
            String host = getServletContext().getInitParameter("host");
            String port = getServletContext().getInitParameter("port");
            String user = getServletContext().getInitParameter("user");
            String pass = getServletContext().getInitParameter("pass");
            EmailUtility.sendEmail(host, port, user, pass, recipient, content);
            resultMessage = "Email Ä‘Ã£ Ä‘Æ°á»£c gá»­i thÃ nh cÃ´ng";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "CÃ³ lá»—i xáº£y ra khi gá»­i email: " + ex.getMessage();
        }

        // Gá»­i káº¿t quáº£ thÃ´ng bÃ¡o vá»� cho trang FogetPass.jsp
        request.setAttribute("Message", resultMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Pages/FogetPass.jsp");
        dispatcher.forward(request, response);
    }
}
