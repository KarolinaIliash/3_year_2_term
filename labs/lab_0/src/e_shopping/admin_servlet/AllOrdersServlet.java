package e_shopping.admin_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import e_shopping.beans.Order;
import e_shopping.utils.DBUtils;
import e_shopping.utils.StoreUtils;

//Find orders and unpayed orders 
@WebServlet(urlPatterns = {"/admin/allorders"})
public class AllOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AllOrdersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = StoreUtils.getStoredConnection(request);
        
		String all = request.getParameter("all");
		
        String errorString = null;
        try {
			List<Order> list;
			if(all.equals("false")) {
				list = DBUtils.getUnPayedOrders(conn);
			}
			else {
				list = DBUtils.getAllOrders(conn);
			}			
			request.setAttribute("orderList", list);
			request.setAttribute("errorString", errorString);
			
			if(all.equals("true")) {
				RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/allOrdersView.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/unpayedOrdersView.jsp");
					dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			errorString = e.getMessage();
			e.printStackTrace();
		}
        
        
        
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
