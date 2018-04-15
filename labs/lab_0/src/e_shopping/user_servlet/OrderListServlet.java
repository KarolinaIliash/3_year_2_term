package e_shopping.user_servlet;

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
import e_shopping.beans.Product;
import e_shopping.beans.User;
import e_shopping.utils.DBUtils;
import e_shopping.utils.StoreUtils;


//Show list of ordes
@WebServlet("/orders")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = StoreUtils.getStoredConnection(request);
        
        String errorString = null;
        List<Order> list = null;
        
        HttpSession session = request.getSession();
        
        User user = StoreUtils.getLoginedUser(session);
        
        if(user == null)
        {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("login");
	    	
	    	dispatcher.forward(request, response);
        }
        else {
        	try {
                list = DBUtils.getOrdersByUser(conn, user.getPhone());
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
            request.setAttribute("errorString", errorString);
            request.setAttribute("orderList", list);
            
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/orderListView.jsp");
            dispatcher.forward(request, response);
        }
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
