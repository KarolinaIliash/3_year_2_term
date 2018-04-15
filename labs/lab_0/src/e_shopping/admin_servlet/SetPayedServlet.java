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

// Set order as payed
@WebServlet("/admin/setPayed")
public class SetPayedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SetPayedServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = StoreUtils.getStoredConnection(request);
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		
		try {
			DBUtils.setOrderPayed(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/admin/allorders?all=false");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
