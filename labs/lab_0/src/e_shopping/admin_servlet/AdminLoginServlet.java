package e_shopping.admin_servlet;

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

import e_shopping.beans.Admin;
import e_shopping.beans.User;
import e_shopping.utils.DBUtils;
import e_shopping.utils.StoreUtils;

//Servlet for admin authentafication
@WebServlet("/admin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public AdminLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/adminLoginView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
        String password = request.getParameter("password");
 
        Admin admin = null;
        boolean hasError = false;
        String errorString = null;
 
        HttpSession session = request.getSession();
        
        Connection conn = StoreUtils.getStoredConnection(request);
        try {
        	admin = DBUtils.getAdmin(conn, login, password);
            if (admin == null) {
            	hasError = true;
            	if(StoreUtils.getLang(session).equals("en")) {
            		errorString = "Login or password invalid";
            	}
            	else {
            		errorString = "Логін чи пароль неправильний";
            	}
            }
         } catch (SQLException e) {
        	 e.printStackTrace();
             hasError = true;
             errorString = e.getMessage();
         }     
        
        // If sth wrong, go back to loginView.jsp and give user info about error
        if (hasError) {
            admin = new Admin();
            admin.setLogin(login);
            admin.setPassword(password);
 
            request.setAttribute("errorString", errorString);
            request.setAttribute("admin", admin);
 
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/adminLoginView.jsp");
 
            dispatcher.forward(request, response);
        }
        
        //Everything is ok. We should save loginedUser and discard loginedAdmin.
        else {
            StoreUtils.storeLoginedUser(session, null);
            StoreUtils.storeLoginedAdmin(session, admin);

            response.sendRedirect(request.getContextPath() + "/admin/products");
        }
	}

}
