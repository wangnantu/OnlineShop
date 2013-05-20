/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.uu.it.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.uu.it.bean.ProductBean;
import se.uu.it.dao.ProductDao;
import se.uu.it.dao.impl.ProductDaoImpl;

/**
 *
 * @author wnt
 */
public class ShoppingServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        String action = request.getParameter("action");
                                     if(action == null || action.equals("list")){
                                         this.list(request, response);
                                    }else if(action != null &&action.equals("add")){
                                         this.add(request, response);
                                     }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        doGet(request,response);
    }
    
    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                      ProductDao dao = new ProductDaoImpl();
                      List<ProductBean> productList = new ArrayList<ProductBean>();
                      productList = dao.list();
                      request.setAttribute("productList", productList);
                      RequestDispatcher rd = null; 
                      ServletContext sc = getServletContext(); 
                      rd = sc.getRequestDispatcher("/main.jsp");    
                      rd.forward(request, response); 
    }
    
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
