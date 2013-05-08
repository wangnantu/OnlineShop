package se.uu.it.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import se.uu.it.bean.UserBean;
import se.uu.it.dao.UserDao;
import se.uu.it.dao.impl.UserDaoImpl;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDao dao = new UserDaoImpl();
		UserBean user = new UserBean();
		user.setUsername(username);
		user.setPassword(password);
		dao.save(user);
		HttpSession s=request.getSession();
		s.setAttribute("username", username);
		s.setAttribute("login", true);
		RequestDispatcher rd = null; 
		ServletContext sc = getServletContext(); 
        rd = sc.getRequestDispatcher("/index.jsp");    
        rd.forward(request, response); 
		//request.getRequestDispatcher("/NewFile.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request,response);
	}

}
