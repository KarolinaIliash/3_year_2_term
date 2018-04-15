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

import e_shopping.beans.Product;
import e_shopping.utils.DBUtils;
import e_shopping.utils.StoreUtils;

//Find product list for admin
@WebServlet("/admin/products")
public class AdminProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public AdminProductsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = StoreUtils.getStoredConnection(request);
		 
        String errorString = null;
        List<Product> list = null;
        try {
        	// Get all products from db
            list = DBUtils.getAllProducts(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        request.setAttribute("errorString", errorString);
        request.setAttribute("productList", list);
         
        // Forward to jsp view
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/adminProductsView.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
