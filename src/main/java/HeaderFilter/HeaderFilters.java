package HeaderFilter;

import java.io.IOException;

import javax.servlet.Filter;//...
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
@WebFilter("/*")
public class HeaderFilters implements Filter {//...
	
	private String mode = "DENY";

	// Add X-FRAME-OPTIONS response header to tell any other browsers who   not to display this //content in a frame.
	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
	        httpServletResponse.addHeader("X-Content-Type-Options", "nosniff"); 
//	        httpServletResponse.setHeader("Content-Security-Policy", "default-src 'self' ; \r\n"
//	        		+ "	                script-src  'self' 'unsafe-inline' https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js https://use.fontawesome.com/releases/v5.15.4/js/all.js; \r\n"
//	        		+ "	                style-src 'self' 'unsafe-inline' https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css;\r\n"
//	        		+ "	                font-src 'self' https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css;\r\n"
//	        		+ "	                connect-src 'self';\r\n"
//	        		+ "	                img-src 'self' data: https://media.istockphoto.com/id/936681148/vi/vec-to/bi%E1%BB%83u-t%C6%B0%E1%BB%A3ng-kh%C3%B3a.jpg?s=612x612&w=0&k=20&c=U6Hw5e1K70NUaQz2MjOeal_FjERS9swHClnFI6MMVEY= https://i.postimg.cc https://play-lh.googleusercontent.com;");
//	       
	        chain.doFilter(request, response);
	    }
	    @Override
	    public void destroy() {
	    }

	    @Override
	    public void init(FilterConfig filterConfig) {
	        String configMode = filterConfig.getInitParameter("mode");
	        if ( configMode != null ) {
	            mode = configMode;
	        }
	    }
}
