package OnlineShopping.dao;

import OnlineShopping.entity.User;

public interface UserDao {
	User findByUsername(String username);
	void save(User user);

}
