package se.uu.it.dao;

import se.uu.it.bean.UserBean;

public interface UserDao {

	public void save(UserBean user);
	public void update(UserBean user);
	public void delete(UserBean user);
	public String getPasswordFromUser(String username);
                          public Integer getIdFromUser(String username);
	
}
