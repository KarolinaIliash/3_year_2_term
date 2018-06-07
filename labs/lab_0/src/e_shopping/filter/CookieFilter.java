package e_shopping.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import e_shopping.beans.User;
import e_shopping.utils.DBUtils;
import e_shopping.utils.StoreUtils;
 
// Check cookie
@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {
 
    public CookieFilter() {}
 
    @Override
    public void init(FilterConfig fConfig) throws ServletException {}
 
    @Override
    public void destroy() {}
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
 
        User userInSession = StoreUtils.getLoginedUser(session);
        
        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            return;
        }
 
        Connection conn = StoreUtils.getStoredConnection(request);
 
        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null && conn != null) {
            String userPhone = StoreUtils.getUserPhoneInCookie(req);
            if(userPhone != null) {
            	try {
            		User user = DBUtils.findUser(conn, userPhone);
            		StoreUtils.storeLoginedUser(session, user);
            	} catch (SQLException e) {
            		e.printStackTrace();
            	}
            }
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }
 
        chain.doFilter(request, response);
    }
 
}
