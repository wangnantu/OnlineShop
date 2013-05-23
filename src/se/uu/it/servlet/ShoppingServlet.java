/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.uu.it.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.uu.it.bean.OrderBean;
import se.uu.it.bean.ProductBean;
import se.uu.it.dao.OrderDao;
import se.uu.it.dao.ProductDao;
import se.uu.it.dao.UserDao;
import se.uu.it.dao.impl.OrderDaoImpl;
import se.uu.it.dao.impl.ProductDaoImpl;
import se.uu.it.dao.impl.UserDaoImpl;

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
                     String username = (String)request.getSession().getAttribute("username");
                     UserDao uDao = new UserDaoImpl();
                     int user_id = uDao.getIdFromUser(username);
                     Date date = new Date();
                     SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                     String order_date = sf.format(date);
                     OrderBean order = new OrderBean();
                     order.setUser_id(user_id);
                     order.setOrderdate(order_date);
                     String[] products = request.getParameterValues("products");
                     List list = Arrays.asList(products);
                     List<Integer> quaList = new ArrayList<Integer>();
                     for(int i=0;i<list.size();i++){
                         String qu = list.get(i)+"+quantity";
                         int quantity = Integer.parseInt((String)request.getParameter(qu));
                         quaList.add(quantity);
                     }
                     OrderDao oDao = new OrderDaoImpl();
                     if(oDao.haveUnpaidOrder(user_id)){
                         OrderBean order1 = oDao.getUnPaidOrderByUserId(user_id);
                         oDao.add(order1,list,quaList);
                     }else{
                     oDao.save(order, list, quaList);
                     }
                     RequestDispatcher rd = null; 
                      ServletContext sc = getServletContext(); 
                      rd = sc.getRequestDispatcher("/order.html?action=list");    
                      rd.forward(request, response); 
    }
}
