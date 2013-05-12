package se.uu.it.dao;
import java.util.List;

import se.uu.it.bean.ItemBean;


public interface ItemDao {

	public void save(ItemBean item);
	public void update(ItemBean item);
                          public ItemBean getItemById(int id);
	public void delete(int id);
	public List<ItemBean> list();
                        
   
}
