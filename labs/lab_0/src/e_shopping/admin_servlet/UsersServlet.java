package e_shopping.admin_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import e_shopping.beans.Order;
import e_shopping.beans.User;
import e_shopping.beans.UserInfoUnpayedOrders;
import e_shopping.utils.DBUtils;
import e_shopping.utils.StoreUtils;

// Show all users or users with unpayed orders
@WebServlet("/admin/users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UsersServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = StoreUtils.getStoredConnection(request);
		String all = request.getParameter("all");
		
		List<User> list;

		try {
			if(all.equals("true")) {
				list = DBUtils.findAllUsers(conn);
			}
			else {
				list = DBUtils.getUsersWithUnpayedOrders(conn);
			}
			
			List<UserInfoUnpayedOrders> listInfo = new ArrayList<>();
			
			for(User user : list) {
				List<Order> orders = DBUtils.getOrdersUnpayedByUser(conn, user.getPhone());
				int amount = 0; 
				double sum = 0;
				for(Order order : orders) {
					amount++;
					String priceStr = order.getPriceBeforePoint() + "." + order.getPriceAfterPoint();
					double price = Double.parseDouble(priceStr);
					sum += price;
				}
				UserInfoUnpayedOrders info = new UserInfoUnpayedOrders(user, amount, sum);
				listInfo.add(info);
			}
			
			request.setAttribute("userList", listInfo);
			
			if(all.equals("true")) {
				RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/allUsersView.jsp");
					dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/badUsersView.jsp");
					dispatcher.forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
