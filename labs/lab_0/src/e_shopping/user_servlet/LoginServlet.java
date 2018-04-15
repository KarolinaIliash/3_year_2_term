package e_shopping.user_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import e_shopping.beans.User;
import e_shopping.utils.DBUtils;
import e_shopping.utils.StoreUtils;
 
@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
    }
 
    // �������� �������� Login.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPhone = request.getParameter("userPhone");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
 
        User user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (userPhone == null || password == null || userPhone.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = StoreUtils.getStoredConnection(request);
            try {
                // ����� user � DB.
                user = DBUtils.findUser(conn, userPhone, password);
 
                if (user == null) {
                    hasError = true;
                    errorString = "User Phone or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // � ������, ���� ���� ������,
        // forward (�������������) � /WEB-INF/views/login.jsp
        if (hasError) {
            user = new User();
            user.setPhone(userPhone);
            user.setPassword(password);
 
            // ��������� ���������� � request attribute ����� forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward (�������������) � �������� /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
 
            dispatcher.forward(request, response);
        }
        // � ������, ���� ��� ������.
        // ��������� ���������� ������������ � Session.
        // � ������������� � �������� userInfo.
        else {
            HttpSession session = request.getSession();
            StoreUtils.storeLoginedUser(session, user);
            StoreUtils.storeLoginedAdmin(session, null);
            // ���� ������������ �������� ������� "Remember me".
            if (remember) {
                StoreUtils.storeUserCookie(response, user);
            }
            // ��������, ������� Cookie
            else {
                StoreUtils.deleteUserCookie(response);
            }
            
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}