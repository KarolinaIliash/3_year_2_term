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

//Find users in blacklist
@WebServlet("/admin/blacklist")
public class BlackListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public BlackListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = StoreUtils.getStoredConnection(request);
		List<User> users = null;
		List<UserInfoUnpayedOrders> listInfo = new ArrayList<>();
		try {
			users = DBUtils.findBlackListUsers(conn);
			// Get info about unpayed orders.
			for(User user : users) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("userList", listInfo);
		RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/blackListView.jsp");
			dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
