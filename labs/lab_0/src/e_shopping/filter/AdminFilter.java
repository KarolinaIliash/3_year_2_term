package e_shopping.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import e_shopping.utils.StoreUtils;
 
// Every attempt get some page after /admin/ without logined admin will
// return back to admin login page
@WebFilter(filterName = "adminFilter", urlPatterns = { "/admin/*" })
public class AdminFilter implements Filter {
 
    public AdminFilter() {}
 
    @Override
    public void init(FilterConfig fConfig) throws ServletException {}
 
    @Override
    public void destroy() {}
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
 
        HttpSession session = req.getSession();
        
        String uri = req.getRequestURI();

        if(StoreUtils.getLoginedAdmin(session) != null || uri.equals(req.getContextPath() + "/admin")) {
            chain.doFilter(request, response);
        }
        
        else {
        	HttpServletResponse resp = (HttpServletResponse) response;
        	 
            resp.sendRedirect(req.getContextPath() + "/admin");
        }
 
    }
 
}
