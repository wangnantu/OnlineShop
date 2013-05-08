package se.uu.it.dao;
import java.util.List;

import se.uu.it.bean.ItemBean;


public interface ItemDao {

	public void save(ItemBean item);
	public void update(ItemBean item);
	public void delete(ItemBean item);
	public List<ItemBean> list();
}
