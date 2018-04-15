package e_shopping.utils;

import java.sql.Connection;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import e_shopping.beans.User;
import e_shopping.beans.Admin;
import e_shopping.beans.Product;
import e_shopping.beans.ProductWithAmount;

// Utiks for storing info in session and request
public class StoreUtils {
	public static final String AttrConnectionName = "AttrConnectionName";
	public static final String AttrUserPhoneCookie = "AttrUserPhoneCookie";
	public static final String AttrLoginedUserSession = "loginedUser";
	public static final String Basket = "Basket";
	public static final String Sum = "Sum";
	public static final String Bundle = "Bundle";
	public static final String AttrLoginedAdminSession = "loginedAdmin";
	public static final String Lang = "Lang";
	
	public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(AttrConnectionName, conn);
    }
	
	public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(AttrConnectionName);
        return conn;
    }
	
    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute(AttrLoginedUserSession, loginedUser);
    }
 
    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute(AttrLoginedUserSession);
        return loginedUser;
    }
    
    public static void storeLoginedAdmin(HttpSession session, Admin loginedAdmin) {
    	session.setAttribute(AttrLoginedAdminSession, loginedAdmin);
    }
    
    public static Admin getLoginedAdmin(HttpSession session) {
    	Admin loginedAdmin = (Admin) session.getAttribute(AttrLoginedAdminSession);
    	return loginedAdmin;
    }
    public static void storeBasket(HttpSession session, List<ProductWithAmount> products) {
    	session.setAttribute(Basket, products);
    }
    
    public static List<ProductWithAmount> getBasket(HttpSession session){
    	List<ProductWithAmount> products = (List<ProductWithAmount>)session.getAttribute(Basket);
    	return products;
    }
    
    public static void storeSum(HttpSession session, String sum) {
    	session.setAttribute(Sum, sum);
    }
    
    public static String getSum(HttpSession session){
    	String sum = (String)session.getAttribute(Sum);
    	return sum;
    }
    
    public static void storeLang(HttpSession session, String lang) {
    	session.setAttribute(Lang, lang);
    }
    
    public static String getLang(HttpSession session) {
    	String lang = (String) session.getAttribute(Lang);
    	return lang;
    }
    
    public static void storeBundle(HttpSession session, Locale locale) {
    	ResourceBundle bundle = ResourceBundle.getBundle("e_shopping.locale.Content", locale);
    	session.setAttribute(Bundle, bundle);
    }
    
    public static ResourceBundle getBundle(HttpSession session) {
    	ResourceBundle bundle = (ResourceBundle)session.getAttribute(Bundle);
    	return bundle;
    }
    
    public static void storeUserCookie(HttpServletResponse response, User user) {
       // System.out.println("Store user cookie");
        Cookie cookieUserPhone = new Cookie(AttrUserPhoneCookie, user.getPhone());
        // 1 day in seconds
        cookieUserPhone.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserPhone);
    }
 
    public static String getUserPhoneInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (AttrUserPhoneCookie.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserPhone = new Cookie(AttrUserPhoneCookie, null);
        // Cookie become unavailable immediately
        cookieUserPhone.setMaxAge(0);
        response.addCookie(cookieUserPhone);
    }
}
