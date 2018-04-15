package e_shopping.user_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import e_shopping.beans.User;
import e_shopping.utils.DBUtils;
import e_shopping.utils.StoreUtils;
 
@WebServlet(urlPatterns = { "/signUp" })
public class SignUpServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
    public SignUpServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher 
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signUpView.jsp");
 
        dispatcher.forward(request, response);
 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPhone = request.getParameter("userPhone");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
 
        String errorString = "";

        HttpSession session = request.getSession();
        Connection conn = StoreUtils.getStoredConnection(request);
        
        User user = null;
		try {
			user = DBUtils.findUser(conn, userPhone);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
        if(user == null) {
        user = new User(firstName, lastName, userPhone, password, false);
        
        	try {
        		// Add new user to database
        		DBUtils.addUser(conn, user);
        	} catch (SQLException e) {
        		errorString = e.getMessage();
        		e.printStackTrace();
        	}
        }
        else {
        	errorString = StoreUtils.getBundle(session).getString("existedUser");
        }
        
        if(!errorString.isEmpty()) {
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signUpView.jsp");
 
            dispatcher.forward(request, response);
        }
        
        else {
            StoreUtils.storeLoginedUser(session, user);
            StoreUtils.storeLoginedAdmin(session, null);
            
            response.sendRedirect(request.getContextPath() + "/");
        }
        
    }
}
