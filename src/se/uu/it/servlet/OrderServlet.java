/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.uu.it.servlet;

import java.io.IOException;
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
import se.uu.it.dao.UserDao;
import se.uu.it.dao.impl.OrderDaoImpl;
import se.uu.it.dao.impl.UserDaoImpl;

/**
 *
 * @author wnt
 */
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        String action = request.getParameter("action");
                                     if(action == null || action.equals("list")){
                                         this.list(request, response);
                                    }else if(action != null &&action.equals("delete")){
                                         this.delete(request, response);
                                     }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        doGet(request,response);
    }
    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String username = (String)request.getSession().getAttribute("username");
                UserDao uDao = new UserDaoImpl();
                int user_id = uDao.getIdFromUser(username);
                OrderDao oDao = new OrderDaoImpl();
                OrderBean order = oDao.getUnPaidOrderByUserId(user_id);
                int order_id = order.getId();
                List<ProductBean> products = oDao.getProductListByOrderId(order_id);
                List<Integer> quaList = oDao.getUnPaidQuaList(order_id);
                request.setAttribute("products", products);
                request.setAttribute("quaList", quaList);
                RequestDispatcher rd = null; 
                ServletContext sc = getServletContext(); 
                rd = sc.getRequestDispatcher("/myCart.jsp");    
                rd.forward(request, response); 
    }
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String username = (String)request.getSession().getAttribute("username");
         UserDao uDao = new UserDaoImpl();
         int user_id = uDao.getIdFromUser(username);
        OrderDao oDao = new OrderDaoImpl();
        OrderBean order = oDao.getUnPaidOrderByUserId(user_id);
        int order_id = order.getId();
        int product_id = Integer.parseInt((String)request.getParameter("id"));
        oDao.deleteProduct(order_id,product_id);
        RequestDispatcher rd = null; 
        ServletContext sc = getServletContext(); 
        rd = sc.getRequestDispatcher("/order.html?action=list"); 
        rd.forward(request, response); 
    }

}
