/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.uu.it.dao;

import java.util.List;
import se.uu.it.bean.OrderBean;
import se.uu.it.bean.ProductBean;

/**
 *
 * @author wnt
 */
public interface OrderDao {
    public void save(OrderBean order, List<String>  products,  List<Integer> quaList);
    public void add(OrderBean order, List<String>  products,  List<Integer> quaList);
    public void update(int order_id, int product_id, int quantity);
    public List<ProductBean> getProductListByOrderId(int order_id);
    public List<Integer> getUnPaidQuaList(int order_id);
    public void deleteProduct(int order_id,int product_id);
    public Boolean haveUnpaidOrder(int user_id);
    public OrderBean getUnPaidOrderByUserId(int user_id);
    public void payOrder(int order_id);
}
