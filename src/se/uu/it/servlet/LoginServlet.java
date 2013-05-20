package se.uu.it.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import se.uu.it.dao.UserDao;
import se.uu.it.dao.impl.UserDaoImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDao dao = new UserDaoImpl();
		String DBPassword = dao.getPasswordFromUser(username);
		if(password.equals(DBPassword)){
			HttpSession s=request.getSession();
                                                                              s.setAttribute("username", username);
		                          s.setAttribute("login", true);
		}else{
			System.out.print("Fail");
		}
                                                    if(username.equals("admin")){
                                                       response.sendRedirect("/OnlineShop/admin.jsp");
                                                    }else{
                                                        response.sendRedirect("/OnlineShop/shopping.html");
                                                    }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
