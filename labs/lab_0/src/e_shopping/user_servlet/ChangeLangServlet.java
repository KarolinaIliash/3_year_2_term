package e_shopping.user_servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import e_shopping.utils.StoreUtils;

// Switch languages
@WebServlet(urlPatterns = {"/changeLang", "/admin/changeLang"})
public class ChangeLangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ChangeLangServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lang = request.getParameter("lang");
		HttpSession session = request.getSession();
		Locale locale;
		if(lang.equals("ua")) {
			locale = new Locale("uk", "UA");
		}
		else {
			locale = new Locale("en", "US");
		}		
		// Update bundle
		StoreUtils.storeBundle(session, locale);
		StoreUtils.storeLang(session, lang);
		String url = request.getHeader("referer");
		response.sendRedirect(url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
