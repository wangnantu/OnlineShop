/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.uu.it.dao;

import java.util.List;
import se.uu.it.bean.ProductBean;

/**
 *
 * @author wnt
 */
public interface ProductDao {
                          public void save(ProductBean product,List<String> list);
	public void update(ProductBean product,List<String> list);
                          public ProductBean getProductById(int id);
                           public List<String> getComponentsByProductName(String name);
	public void delete(int id);
	public List<ProductBean> list();
}
