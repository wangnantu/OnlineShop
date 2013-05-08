package se.uu.it.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.uu.it.bean.ItemBean;
import se.uu.it.dao.ItemDao;
import se.uu.it.dao.impl.ItemDaoImpl;



public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		String name  = request.getParameter("name");
                                                    float price = Float.parseFloat(request.getParameter("price"));
                                                    int stock = Integer.parseInt(request.getParameter("stock"));
                                                    ItemBean item = new ItemBean();
                                                    item.setName(name);
                                                    item.setPrice(price);
                                                    item.setStock(stock);
                                                   ItemDao dao = new ItemDaoImpl();
                                                   dao.save(item);
//		RequestDispatcher rd = null; 
//		ServletContext sc = getServletContext(); 
//                                                    rd = sc.getRequestDispatcher("/index.jsp");    
//                                                    rd.forward(request, response); 
//		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request,response);
	}

}
