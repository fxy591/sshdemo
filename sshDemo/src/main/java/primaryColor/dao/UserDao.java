package primaryColor.dao;

import primaryColor.pojo.User;

/**
 * @author PrimaryColor
 * @date 2023/1/30
 * @apiNote
 */
public interface UserDao {
    User select(String username, String password);
    User selectByUsername(String username);
    void addUser(User user);

}
