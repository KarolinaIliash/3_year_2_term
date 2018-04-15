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
import javax.servlet.http.HttpSession;

import e_shopping.conn.ConnectionUtils;
import e_shopping.utils.StoreUtils;
 
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {
 
    public JDBCFilter() {}
 
    @Override
    public void init(FilterConfig fConfig) throws ServletException {}
 
    @Override
    public void destroy() {}
 
    //Check whether purpose of request is a servlet
    private boolean needJDBC(HttpServletRequest request) {
        System.out.println("JDBC Filter");
        // 
        // Servlet Url-pattern: /spath/*
        // 
        // => /spath
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
 
        String urlPattern = servletPath;
 
        if (pathInfo != null) {
            // => /spath/*
            urlPattern = servletPath + "/*";
        }
 
        // Key: servletName.
        // Value: ServletRegistration
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();
        
        //All projects servlets
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
 
        HttpSession session = req.getSession();
        // Set locale and language for multi language support
        if(StoreUtils.getBundle(session) == null) {
        	Locale locale = new Locale("en", "US");
        	StoreUtils.storeBundle(session, locale);
        	StoreUtils.storeLang(session, "en");
        }
        
        if (this.needJDBC(req)) {
 
            System.out.println("Open Connection for: " + req.getServletPath());
 
            Connection conn = null;
            try {
                conn = ConnectionUtils.getConnection();
                conn.setAutoCommit(false);
 
                StoreUtils.storeConnection(request, conn);

                chain.doFilter(request, response);
 
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                try {
					ConnectionUtils.rollbackQuietly(conn);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
                throw new ServletException();
            } finally {
                try {
					ConnectionUtils.closeQuietly(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        }
        // For html, css etc
        else {
            chain.doFilter(request, response);
        }
 
    }
 
}
