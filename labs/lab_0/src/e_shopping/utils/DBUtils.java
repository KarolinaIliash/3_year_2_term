package e_shopping.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import e_shopping.beans.User;
import e_shopping.beans.Product;
import e_shopping.beans.ProductWithAmount;
import e_shopping.beans.Order;
import e_shopping.beans.Admin;

// Incapsulates queries to db
public class DBUtils {
	
	private static final String UserTable = "useracc";
	private static final String ProductTable = "product";
	private static final String OrderTable = "order_";
	private static final String AdminTable = "admin";
	private static final String OrderProductTable = "order_product";
	
	public static User findUser(Connection conn, 
        String userPhone, String password) throws SQLException {
		String sql = "Select * from " + UserTable + " as x where x.phone = ? and x.password = ?";  
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userPhone);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            Boolean inBlackList = rs.getBoolean("in_black_list");
            User user = new User(firstName, lastName, userPhone, password, inBlackList);
            return user;
        }
        return null;
    }
 
    public static User findUser(Connection conn, String userPhone) throws SQLException {
 
    	String sql = "Select * from " + UserTable + " as x where x.phone = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userPhone);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            Boolean inBlackList = rs.getBoolean("in_black_list");
            String password = rs.getString("password");
            User user = new User(firstName, lastName, userPhone, password, inBlackList);
            return user;
        }
        return null;
    }
    
    /*public static User findUser(Connection conn, int id) throws SQLException {
    	 
    	String sql = "Select * from ? as x where x.id = ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, UserTable);
        pstm.setInt(2, id);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            String phone = rs.getString("phone");
            Boolean inBlackList = rs.getBoolean("in_black_list");
            String password = rs.getString("password");
            User user = new User(id, firstName, lastName, phone, password, inBlackList);
            return user;
        }
        return null;
    }*/
    
    public static List<User> findAllUsers(Connection conn) throws SQLException{
    	List<User> users = new ArrayList<>();
    	
    	String sql = "Select * from " + UserTable;
    	
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	
    	ResultSet rs = pstm.executeQuery();
    	while(rs.next()) {
    		String phone = rs.getString("phone");
    		String firstName = rs.getString("firstname");
    		String lastName = rs.getString("lastname");
    		String password = rs.getString("password");
    		Boolean inBlackList = rs.getBoolean("in_black_list");
    		User user = new User(firstName, lastName, phone, password, inBlackList);
    		users.add(user);
    	}
    	return users;
    }
    
    public static List<User> findBlackListUsers(Connection conn) throws SQLException{
    	List<User> users = new ArrayList<>();
    	
    	String sql = "Select * from " + UserTable + " as x where x.in_black_list = true";
    	
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	
    	ResultSet rs = pstm.executeQuery();
    	while(rs.next()) {
    		String phone = rs.getString("phone");
    		String firstName = rs.getString("firstname");
    		String lastName = rs.getString("lastname");
    		String password = rs.getString("password");
    		User user = new User(firstName, lastName, phone, password, true);
    		users.add(user);
    	}
    	return users;
    }
    
    public static List<Product> getAllProducts(Connection conn) throws SQLException{
    	List<Product> products = new ArrayList<>();
    	
    	String sql = "Select * from product";
    	
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	
    	ResultSet rs = pstm.executeQuery();
    	while(rs.next()) {
    		int id = rs.getInt("id");
    		String name = rs.getString("name");
    		String nameUa = rs.getString("nameua");
    		int price_ = rs.getInt("price_");
    		int price__ = rs.getInt("price__");
    		
    		Product product = new Product(id, name, nameUa, price_, price__);
    		
    		products.add(product);
    	}
    	return products;
    }
    
    public static List<ProductWithAmount> getProducts(Connection conn, int order_id) throws SQLException{
    	List<ProductWithAmount> products = new ArrayList<>();
    	
    	String sql = "Select * from " + ProductTable + " as x "
    			+ "inner join " +  OrderProductTable + " as y on x.id = y.product_id where y.order_id = ?";
    	
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setInt(1, order_id);
    	
    	ResultSet rs = pstm.executeQuery();
    	while(rs.next()) {
    		int id = rs.getInt("id");
    		String name = rs.getString("name");
    		String nameUa = rs.getString("nameua");
    		int price_ = rs.getInt("price_");
    		int price__ = rs.getInt("price__");
    		int amount = rs.getInt("product_amount");
    		Product product = new Product(id, name, nameUa, price_, price__);
    		ProductWithAmount pr = new ProductWithAmount(product, amount);
    		products.add(pr);
    	}
    	return products;
    }
    
    public static List<Order> getAllOrders(Connection conn) throws SQLException{
    	List<Order> orders = new ArrayList<>();
    	
    	String sql = "Select * from " + OrderTable;
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	//pstm.setString(1, OrderTable);
    	
    	ResultSet rs = pstm.executeQuery();
    	while (rs.next()){
    		int id = rs.getInt("id");
    		Date date = rs.getDate("date_");
    		String userPhone = rs.getString("user_phone");
    		int price_ = rs.getInt("price_");
    		int price__ = rs.getInt("price__");
    		boolean isPayed = rs.getBoolean("is_payed");
    		
    		List<ProductWithAmount> products = getProducts(conn, id);
    		
    		Order order = new Order(id, date, userPhone, price_, price__, isPayed, products);
    		
    		orders.add(order);
    	}
    	
    	return orders;
    }
    
    public static List<Order> getUnPayedOrders(Connection conn) throws SQLException{
    	List<Order> orders = new ArrayList<>();
    	
    	String sql = "Select * from " + OrderTable + " as x where x.is_payed = false";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	
    	ResultSet rs = pstm.executeQuery();
    	while(rs.next()) {
    		int id = rs.getInt("id");
    		Date date = rs.getDate("date_");
    		String userPhone = rs.getString("user_phone");
    		int price_ = rs.getInt("price_");
    		int price__ = rs.getInt("price__");
    		
    		List<ProductWithAmount> products = getProducts(conn, id);
    		
    		Order order = new Order(id, date, userPhone, price_, price__, false, products);
    		orders.add(order);
    	}
    	return orders;
    }
    
   /* public static List<Order> getOrdersUnpayedByUser(Connection conn, String userPhone) throws SQLException{
    	List<Order> orders = new ArrayList<>();
    	
    	String sql = "Select * from ? as x where x.user_phone = ? and x.is_payed = false";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1,  OrderTable);
    	pstm.setString(2, userPhone);
    	
    	ResultSet rs = pstm.executeQuery();
    	while(rs.next()) {
    		Date date = rs.getDate("date_");
    		int price_ = rs.getInt("price_");
    		int price__ = rs.getInt("price__");
    		List<Product> products = getProducts(conn, id);
    		Order order = new Order(id, date, userId, price_, price__, false, products);
    		orders.add(order);
    	}
    	return orders;
    }*/
    
    public static List<Order> getOrdersUnpayedByUser(Connection conn, String phone) throws SQLException{
    	List<Order> orders = new ArrayList<>();
    	
    	String sql = "Select x.id, x.date_, x.price_, x.price__ from " +  OrderTable + " as x "
    			+ "inner join " + UserTable + " as y on x.user_phone = y.phone where y.phone = ? and x.is_payed = false";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1, phone);
    	
    	ResultSet rs = pstm.executeQuery();
    	while(rs.next()) {
    		int id = rs.getInt("id");
    		Date date = rs.getDate("date_");
    		int price_ = rs.getInt("price_");
    		int price__ = rs.getInt("price__");
    		List<ProductWithAmount> products = getProducts(conn, id);
    		Order order = new Order(id, date, phone, price_, price__, false, products);
    		orders.add(order);
    	}
    	return orders;
    }
    
    public static List<Order> getOrdersByUser(Connection conn, String phone) throws SQLException{
    	List<Order> orders = new ArrayList<>();
    	
    	String sql = "Select x.id, x.date_, x.price_, x.price__, x.is_payed from " +  OrderTable + " as x "
    			+ "inner join " + UserTable + " as y on x.user_phone = y.phone where y.phone = ?";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1, phone);
    	
    	ResultSet rs = pstm.executeQuery();
    	while(rs.next()) {
    		int id = rs.getInt("id");
    		Date date = rs.getDate("date_");
    		//int userId = rs.getInt("user_id");
    		int price_ = rs.getInt("price_");
    		int price__ = rs.getInt("price__");
    		boolean isPayed = rs.getBoolean("is_payed");
    		List<ProductWithAmount> products = getProducts(conn, id);
    		Order order = new Order(id, date, phone, price_, price__, isPayed, products);
    		orders.add(order);
    	}
    	return orders;
    }
    
    public static List<User> getUsersWithUnpayedOrders(Connection conn) throws SQLException{
    	List<User> users = new ArrayList<>();
    	
    	String sql = "Select x.phone, x.firstname, x.lastname, x.password, x.in_black_list from "
    			+ UserTable + " as x inner join " + OrderTable + " as y on x.phone = y.user_phone where y.is_payed = false";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	
    	ResultSet rs = pstm.executeQuery();
    	while(rs.next()) {
    		String phone = rs.getString("phone");
    		String firstName = rs.getString("firstName");
    		String lastName = rs.getString("lastName");
    		String password = rs.getString("password");
    		boolean inBlackList = rs.getBoolean("in_black_list");
    		
    		User user = new User(firstName, lastName, phone, password, inBlackList);
    		users.add(user);
    	}
    	return users;
    } 
    
    public static Admin getAdmin(Connection conn, String login, String password) throws SQLException{
    	String sql = "Select * from " + AdminTable + " as x where x.login = ? and x.password = ?";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1, login);
    	pstm.setString(2, password);
    	
    	ResultSet rs = pstm.executeQuery();
    	if(rs.next()) {
    		Admin admin = new Admin(login, password);
    		return admin;
    	}
    	return null;
    }
    
    public static void setUserInBlackList(Connection conn, String phone, boolean inBlackList) throws SQLException{
    	String sql = "Update " + UserTable + " set in_black_list = ? where phone = ?";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setBoolean(1, inBlackList);
    	pstm.setString(2, phone);
    	
    	pstm.executeUpdate();
    }
    
    public static void addOrder(Connection conn, Order order) throws SQLException{
    	String sql = "Insert into " + OrderTable + "(user_phone, date_, price_, price__, is_payed)"
    			     + " values(?, ?, ?, ?, ?) returning id";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1, order.getUserPhone());
    	pstm.setDate(2, order.getDate());
    	pstm.setInt(3, order.getPriceBeforePoint());
    	pstm.setInt(4,  order.getPriceAfterPoint());
    	pstm.setBoolean(5, order.getIsPayed());
    	
    	pstm.execute();
    	ResultSet rs = pstm.getResultSet();
    	if(rs.next()) {
    		int id = rs.getInt("id");
    		order.setId(id);
    	}
    	
    	sql = "Insert into " + OrderProductTable + "(order_id, product_id, product_amount) values";
    	for(ProductWithAmount prod : order.getProducts()) {
    		sql += " (" + order.getId() + ", " + prod.getProduct().getId() + ", " + prod.getAmount() + ")";
    		if(prod != order.getProducts().get(order.getProducts().size() - 1)) {
    			sql += ",";
    		}
    	}
    	
    	PreparedStatement pstm2 = conn.prepareStatement(sql);
    	
    	pstm2.executeUpdate();
    }
    
    public static void addUser(Connection conn, User user) throws SQLException{
    	String sql = "Insert into " + UserTable +"(phone, firstname, lastname, password, in_black_list)"
    			+ "values(?, ?, ?, ?, ?)";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1, user.getPhone());
    	pstm.setString(2, user.getFirstName());
    	pstm.setString(3, user.getLastName());
    	pstm.setString(4, user.getPassword());
    	pstm.setBoolean(5, user.isInBlackList());
    	
    	pstm.executeUpdate();
    }
    
    public static void addProduct(Connection conn, Product product) throws SQLException{
    	String sql = "Insert into " + ProductTable + "(name, nameua, price_, price__) "
    			+ "values(?, ?, ?, ?)";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1, product.getName());
    	pstm.setString(2, product.getNameUA());
    	pstm.setInt(3, product.getPriceBeforePoint());
    	pstm.setInt(4, product.getPriceAfterPoint());
    	
    	pstm.executeUpdate();
    }
    
    public static void updateProduct(Connection conn, Product product) throws SQLException{
    	String sql = "Update " + ProductTable + " set name = ?, nameua = ?, price_ = ?, price__ = ? where id = ?";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setString(1, product.getName());
    	pstm.setString(2, product.getNameUA());
    	pstm.setInt(3, product.getPriceBeforePoint());
    	pstm.setInt(4, product.getPriceAfterPoint());
    	pstm.setInt(5, product.getId());
    	
    	pstm.executeUpdate();
    }
    
    public static void deleteProduct(Connection conn, int id) throws SQLException{
    	String sql = "Delete from " + ProductTable + " where id = ?";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setInt(1, id);
    	
    	pstm.executeUpdate();
    }
    
    public static void setOrderPayed(Connection conn, int id) throws SQLException{
    	String sql = "Update " + OrderTable + " set is_payed = true where id = ?";
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setInt(1, id);
    	
    	pstm.executeUpdate();
    }
    
    public static Product getProduct(Connection conn, int id)  throws SQLException{
    	String sql = "Select * from product as x where x.id = ?";
    	
    	PreparedStatement pstm = conn.prepareStatement(sql);
    	pstm.setInt(1, id);
    	
    	ResultSet rs = pstm.executeQuery();
    	
    	if(rs.next()) {
    		String name = rs.getString("name");
    		String nameUa = rs.getString("nameua");
    		int price_ = rs.getInt("price_");
    		int price__ = rs.getInt("price__");
    		
    		Product product = new Product(id, name, nameUa, price_, price__);
    		
    		return product;
    	}
    	
    	return null;
    }
}
