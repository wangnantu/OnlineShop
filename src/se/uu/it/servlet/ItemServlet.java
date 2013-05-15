
package se.uu.it.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.uu.it.bean.ItemBean;
import se.uu.it.dao.ItemDao;
import se.uu.it.dao.impl.ItemDaoImpl;

/**
 *
 * @author wnt
 */
public class ItemServlet extends HttpServlet {

      @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
                                    String action = request.getParameter("action");
                                     if(action == null || action.equals("list")){
                                         this.list(request, response);
                                     }else if(action != null &&action.equals("add")){
                                         this.add(request, response);
                                     }else if(action != null &&action.equals("get")){
                                         this.get(request, response);
                                     }else if(action != null &&action.equals("update")){
                                         this.update(request, response);
                                     }else if(action != null &&action.equals("delete")){
                                         this.delete(request, response);
                                     }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request,response);
	}
    
        public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                                                    ItemDao dao = new ItemDaoImpl();
                                                    List<ItemBean> list = dao.list();
                                                    request.setAttribute("itemList", list);
                                                    RequestDispatcher rd = null; 
		ServletContext sc = getServletContext(); 
                                                   rd = sc.getRequestDispatcher("/itemList.jsp");    
                                                   rd.forward(request, response); 
        }
        
        public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            
                                                    String name  = request.getParameter("name");
                                                    float price = Float.valueOf((String)request.getParameter("price"));
                                                    int stock = Integer.parseInt(request.getParameter("stock"));
                                                    ItemBean item = new ItemBean();
                                                    item.setName(name);
                                                    item.setPrice(price);
                                                    item.setStock(stock);
                                                   ItemDao dao = new ItemDaoImpl();
                                                   dao.save(item);
		RequestDispatcher rd = null; 
		ServletContext sc = getServletContext(); 
                                                   rd = sc.getRequestDispatcher("/item.html?action=list");    
                                                   rd.forward(request, response); 
        }
        
        public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                                        int id = Integer.parseInt(request.getParameter("id"));
                                        ItemDao dao = new ItemDaoImpl();
                                        ItemBean item = new ItemBean();
                                         item = dao.getItemById(id);
                                         request.setAttribute("item", item);
                                         RequestDispatcher rd = null; 
                                         ServletContext sc = getServletContext(); 
                                         rd = sc.getRequestDispatcher("/itemDetail.jsp"); 
                                         rd.forward(request, response); 
                                         
        }
        
        public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                                                    int id = Integer.parseInt((String)request.getParameter("id"));
                                                    String name  = request.getParameter("name");
                                                    float price = Float.parseFloat((String)request.getParameter("price"));
                                                    int stock = Integer.parseInt(request.getParameter("stock"));
                                                    ItemBean item = new ItemBean();
                                                    item.setId(id);
                                                    item.setName(name);
                                                    item.setPrice(price);
                                                    item.setStock(stock);
                                                   ItemDao dao = new ItemDaoImpl();
                                                   dao.update(item);
                                                   RequestDispatcher rd = null; 
                                                   ServletContext sc = getServletContext();
                                                   rd = sc.getRequestDispatcher("/item.html?action=list");    
                                                   rd.forward(request, response); 
        }
        public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
                                                    int id = Integer.parseInt((String)request.getParameter("id"));
                                                     ItemDao dao = new ItemDaoImpl();
                                                     dao.delete(id);
                                                    RequestDispatcher rd = null; 
                                                    ServletContext sc = getServletContext(); 
                                                    rd = sc.getRequestDispatcher("/item.html?action=list"); 
                                                    rd.forward(request, response); 
        }
}
