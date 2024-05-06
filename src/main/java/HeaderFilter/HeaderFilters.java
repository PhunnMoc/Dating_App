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
