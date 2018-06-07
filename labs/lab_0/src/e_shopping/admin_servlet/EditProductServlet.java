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

@WebServlet(urlPatterns = { "/admin/editProduct" })
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditProductServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = StoreUtils.getStoredConnection(request);

		String idStr = (String) request.getParameter("id");
		int id = Integer.parseInt(idStr);
		Product product = null;

		String errorString = null;

		try {
			product = DBUtils.getProduct(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		if (errorString != null && product == null) {
			response.sendRedirect(request.getServletPath() + "/admin/products");
			return;
		}

		request.setAttribute("errorString", errorString);
		request.setAttribute("product", product);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = StoreUtils.getStoredConnection(request);

		String idStr = (String) request.getParameter("id");
		int id = Integer.parseInt(idStr);
		String name = (String) request.getParameter("name");
		String nameUa = (String) request.getParameter("nameUA");
		String priceStr = (String) request.getParameter("price");

		Pair<Integer, Integer> splitPair = UsefulFunctions.splitString(priceStr);

		Product product = new Product(id, name, nameUa, splitPair.getKey(), splitPair.getValue());

		String errorString = null;

		try {
			DBUtils.updateProduct(conn, product);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		request.setAttribute("errorString", errorString);
		request.setAttribute("product", product);

		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			response.sendRedirect(request.getContextPath() + "/admin/products");
		}
	}

}