/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.uu.it.dao;

import java.util.List;
import se.uu.it.bean.OrderBean;

/**
 *
 * @author wnt
 */
public interface OrderDao {
    public void save(OrderBean order, List<String>  products,  List<Integer> quantity);
    public void update();
    public void delete();
    public void list();
}
