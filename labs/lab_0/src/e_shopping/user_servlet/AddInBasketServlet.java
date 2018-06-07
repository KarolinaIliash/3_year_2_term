package e_shopping.user_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

// Add product into basket
@WebServlet(urlPatterns = { "/addInBasket" })
public class AddInBasketServlet extends HttpServlet {
	
	public AddInBasketServlet() {
		super();
	}
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpSession session = req.getSession();
        List<ProductWithAmount> products = StoreUtils.getBasket(session);
        
        if(products == null) {
        	products = new ArrayList<>();
        }
        
        String productIdS = (String)request.getParameter("prod");
        int productId = Integer.parseInt(productIdS);
        
        Connection conn = StoreUtils.getStoredConnection(request);
        
        Product product;
		try {
			product = DBUtils.getProduct(conn, productId);
	        String amountS = (String)request.getParameter("amount");
	        int amount = Integer.parseInt(amountS);
	        
	        ProductWithAmount pr = new ProductWithAmount(product, amount);
	        
	        int index = products.indexOf(pr);
	        if(index != -1) {
	        	ProductWithAmount lastProd = products.get(index);
	        	lastProd.setAmount(lastProd.getAmount() + amount);
	        }
	        else {
	        	products.add(pr);
	        }
	        
	        StoreUtils.storeBasket(session, products);
	        
	        response.sendRedirect(request.getContextPath() + "/products");
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}         
    }
	
}
