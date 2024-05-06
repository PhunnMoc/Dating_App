package Controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieSameSite
 */
@WebServlet("/CookieSameSite")
public class CookieSameSite implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code can be added here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Continue the chain to generate the response
        chain.doFilter(request, response);

        // Modify the session cookie after the response has been generated
        // But before it is sent out
        if (req.getSession(false) != null) {  // Check if session exists
            res.addHeader("Set-Cookie", "JSESSIONID=" + req.getSession().getId() + "; Path=" + req.getContextPath() 
            + "; HttpOnly; Secure; SameSite=Strict");
        }

    }

    @Override
    public void destroy() {
        // Clean-up code can be added here
    }
}
