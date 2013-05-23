/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.uu.it.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
                                     if(action == null || action.equals("listCart")){
                                         this.listCart(request, response);
                                    }else if(action != null &&action.equals("delete")){
                                         this.delete(request, response);
                                   }else if(action != null &&action.equals("checkout")){
                                         this.checkout(request, response);
                                   }else if(action !=null && action.equals("pay")){
                                        this.pay(request, response);
                                   }else if(action != null && action.equals("list")){
                                       this.list(request, response);
                                   }else if(action != null && action.equals("detail")){
                                       this.detail(request, response);
                                   }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                        doGet(request,response);
    }
    public void listCart(HttpServletRequest request, HttpServletResponse response)
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
        rd = sc.getRequestDispatcher("/order.html?action=listCart"); 
        rd.forward(request, response); 
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException{
        String username = (String)request.getSession().getAttribute("username");
         UserDao uDao = new UserDaoImpl();
         int user_id = uDao.getIdFromUser(username);
        OrderDao oDao = new OrderDaoImpl();
        OrderBean order = oDao.getUnPaidOrderByUserId(user_id);
        int order_id = order.getId();
        List<Integer> quaList = oDao.getUnPaidQuaList(order_id);
        List<ProductBean> products = oDao.getProductListByOrderId(order_id);
        List<Integer> quaListNew = new ArrayList<Integer>();
         for(int i=0;i<products.size();i++){
                         String qu = products.get(i).getId()+"+quantity";
                         int quantity = Integer.parseInt((String)request.getParameter(qu));
                         quaListNew.add(quantity);
                     }
         for(int i=0;i<quaList.size();i++){
             if(quaList.get(i) != quaListNew.get(i)){
                 int product_id = products.get(i).getId();
                 oDao.update(order_id, product_id,quaListNew.get(i));
             }
         }
         float totalPrice = 0;
         for(int i=0;i<products.size();i++){
             float price = products.get(i).getPrice();
             totalPrice = totalPrice+ price*quaListNew.get(i);
             
         }
         DecimalFormat   fnum   =   new   DecimalFormat("##0.00"); 
         String tPrice = fnum.format(totalPrice);
         request.setAttribute("totalPrice", tPrice);
          RequestDispatcher rd = null; 
         ServletContext sc = getServletContext(); 
         rd = sc.getRequestDispatcher("/checkout.jsp"); 
         rd.forward(request, response); 
    }

    public void pay(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("username");
         UserDao uDao = new UserDaoImpl();
         int user_id = uDao.getIdFromUser(username);
        OrderDao oDao = new OrderDaoImpl();
        OrderBean order = oDao.getUnPaidOrderByUserId(user_id);
        int order_id = order.getId();
        oDao.payOrder(order_id);
         response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Order Received</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div align='center'>");
            out.println("<h3> We have received your order. The goods will be delivered to you soon.</h3>");
            out.println("<a href='shopping.html'  align='center'>Back to homepage</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }
    
    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            OrderDao oDao = new OrderDaoImpl();
            List<OrderBean> orderList = oDao.list();
            request.setAttribute("orderList", orderList);
            RequestDispatcher rd = null; 
            ServletContext sc = getServletContext(); 
            rd = sc.getRequestDispatcher("/orderList.jsp");    
            rd.forward(request, response); 
    }
    public void detail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             int id = Integer.parseInt(request.getParameter("id"));
             OrderDao oDao = new OrderDaoImpl();
             List<ProductBean> products = oDao.getProductListByOrderId(id);
             List<Integer> quaList = oDao.getUnPaidQuaList(id);
             request.setAttribute("products", products);
             request.setAttribute("quaList", quaList);
             
             RequestDispatcher rd = null; 
             ServletContext sc = getServletContext(); 
             rd = sc.getRequestDispatcher("/orderDetail.jsp");    
             rd.forward(request, response); 
    }
    
    public void prepare(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
