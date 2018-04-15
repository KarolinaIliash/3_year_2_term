package e_shopping.admin_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import e_shopping.beans.Product;
import e_shopping.utils.DBUtils;
import e_shopping.utils.StoreUtils;
import e_shopping.utils.UsefulFunctions;
import javafx.util.Pair;
 
@WebServlet(urlPatterns = { "/admin/createProduct" })
public class CreateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateProductServlet() {
        super();
    }
    
    // Show page for creating a product.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
        dispatcher.forward(request, response);
    }
 
    // After click on submit button. Save product.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = StoreUtils.getStoredConnection(request);
 
        String name = (String) request.getParameter("name");
        String nameUa = (String) request.getParameter("nameUA");
        String priceStr = (String) request.getParameter("price");
        
        String[] prices = priceStr.split("\\.");
        
        Pair<Integer, Integer> splitPair = UsefulFunctions.splitString(priceStr);
        
        Product product = new Product(name, nameUa, splitPair.getKey(), splitPair.getValue());
 
        String errorString = null;
 
        if (errorString == null) {
            try {
                DBUtils.addProduct(conn, product);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
 
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);
 
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
            dispatcher.forward(request, response);
        }
        
        else {
            response.sendRedirect(request.getContextPath() + "/admin/products");
        }
    }
 
}