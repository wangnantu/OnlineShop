/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.uu.it.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.uu.it.bean.ItemBean;
import se.uu.it.bean.ProductBean;
import se.uu.it.dao.ItemDao;
import se.uu.it.dao.ProductDao;
import se.uu.it.dao.impl.ItemDaoImpl;
import se.uu.it.dao.impl.ProductDaoImpl;

/**
 *
 * @author wnt
 */
public class ProductServlet extends HttpServlet {
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
//                                     }else if(action != null &&action.equals("delete")){
//                                         this.delete(request, response);
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
        
        public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                                                    ItemDao dao = new ItemDaoImpl();
                                                    List<ItemBean> list = dao.list();
                                                    ProductDao pDao = new ProductDaoImpl();
                                                    List<ProductBean> pList = pDao.list();
                                                    request.setAttribute("itemList", list);
                                                    request.setAttribute("productList", pList);
                                                    RequestDispatcher rd = null; 
		ServletContext sc = getServletContext(); 
                                                   rd = sc.getRequestDispatcher("/productList.jsp");    
                                                   rd.forward(request, response); 
        }

        public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                                                  String name = request.getParameter("name");
                                                  Float price = Float.valueOf((String)request.getParameter("price"));
                                                  String[] components = request.getParameterValues("components");
                                                  List list = Arrays.asList(components);
                                                  ProductBean product = new ProductBean();
                                                  product.setName(name);
                                                  product.setPrice(price);
                                                  ProductDao dao = new ProductDaoImpl();
                                                  dao.save(product,list);
                                                  RequestDispatcher rd = null; 
                                                  ServletContext sc = getServletContext(); 
                                                  rd = sc.getRequestDispatcher("/product.html");    
                                                  rd.forward(request, response); 
        }
        public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                                                 int id = Integer.parseInt(request.getParameter("id"));
                                                 ProductDao dao = new ProductDaoImpl();
                                                 ProductBean product = new ProductBean();
                                                 product=dao.getProductById(id);
                                                 List<String> components = new ArrayList<String>();
                                                components = dao.getComponentsByProductName(product.getName());
                                                 request.setAttribute("product", product);
                                                 request.setAttribute("components", components);
                                                 ItemDao idao = new ItemDaoImpl();
                                                 List<ItemBean> itemList = idao.list();
                                                 request.setAttribute("itemList", itemList);
                                                 RequestDispatcher rd = null; 
                                                  ServletContext sc = getServletContext(); 
                                                  rd = sc.getRequestDispatcher("/productDetail.jsp");    
                                                  rd.forward(request, response); 
        }
        public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
        }
}
