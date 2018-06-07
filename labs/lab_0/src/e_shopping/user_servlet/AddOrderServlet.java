package e_shopping.user_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
import e_shopping.beans.ProductWithAmount;
import e_shopping.beans.User;
import e_shopping.utils.DBUtils;
import e_shopping.utils.StoreUtils;
import e_shopping.utils.UsefulFunctions;
import javafx.util.Pair;

// Create order
@WebServlet("/addOrder")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpSession session = req.getSession();
	    
	    User user = StoreUtils.getLoginedUser(session);
	    // User should login firstly
	    if(user == null) {
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("login");
	    	dispatcher.forward(request, response);
	    }
	    
	    // Black list users can't add orders
	    else if(user.isInBlackList()) {
	    	request.setAttribute("message", StoreUtils.getBundle(session).getString("blackListMessage"));
	    	
	    	RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/userMessageView.jsp");
	        dispatcher.forward(request, response);
	    }
	    
	    else {
	    	List<ProductWithAmount> products = StoreUtils.getBasket(session);
        	String sumStr = StoreUtils.getSum(session);
        	
        	Pair<Integer, Integer> splitPair = UsefulFunctions.splitString(sumStr);
        
        	Connection conn = StoreUtils.getStoredConnection(request);
        	//java.util.Date date = new java.util.Date();
        	Date date = new Date(System.currentTimeMillis());
        	Order order = new Order(date, user.getPhone(), splitPair.getKey(), splitPair.getValue(), false, products);
        	try {
				DBUtils.addOrder(conn, order);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	StoreUtils.storeBasket(session, null);
        	response.sendRedirect(request.getContextPath() + "/orders");
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
