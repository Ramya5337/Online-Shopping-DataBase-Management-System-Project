package OnlineShopping.Services;

import OnlineShopping.dao.UserDao;
import OnlineShopping.dao.UserDaoIMPL;
import OnlineShopping.entity.User;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDaoIMPL();
    }

    public boolean login(String username, String password) {
        User user = userDao.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public void register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Consider hashing the password before saving
        userDao.save(user);
    }

    public User getUserByUsername(String username) {
        return userDao.findByUsername(username); // Call the DAO to get the User object
    }
}
