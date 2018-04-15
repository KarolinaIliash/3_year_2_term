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

import e_shopping.beans.Product;
import e_shopping.beans.ProductWithAmount;
import e_shopping.utils.DBUtils;
import e_shopping.utils.StoreUtils;

@WebServlet(urlPatterns = { "/basket" })
public class BasketServlet extends HttpServlet{
	
	public BasketServlet() {
		super();
	}
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = StoreUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        List<ProductWithAmount> products = StoreUtils.getBasket(session);
        
        double sum = 0;
        
        if(products != null) {
        	for(ProductWithAmount product : products){
        		double priceToAdd = product.getProduct().getPrice() * product.getAmount();
        		sum += priceToAdd;
        	}
        }
        
        String errorString = null;
        
        StoreUtils.storeSum(session, String.valueOf(sum));
        
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/basketView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
