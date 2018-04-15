package e_shopping.admin_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import e_shopping.utils.DBUtils;
import e_shopping.utils.StoreUtils;

// Add or remove user from black list
@WebServlet("/admin/setBlackList")
public class SetBlackListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SetBlackListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = StoreUtils.getStoredConnection(request);
		
		String phone = request.getParameter("phone");
		String addStr = request.getParameter("add");
		boolean add = Boolean.parseBoolean(addStr);
		
		try {
			
			DBUtils.setUserInBlackList(conn, phone, add);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(add) {
			response.sendRedirect(request.getContextPath() + "/admin/users?all=false");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/admin/blacklist");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
