package primaryColor.service;

import primaryColor.dao.impl.UserDaoImpl;
import primaryColor.pojo.User;

/**
 * @author PrimaryColor
 * @date 2023/1/30
 * @apiNote
 */
public class UserService {
    /**
     * 登陆方法
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password){
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        return userDaoImpl.select(username,password);
    }

    public boolean register(User user){
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        // 判断是否存在
        User u = userDaoImpl.selectByUsername(user.getUsername());
        if (u == null) {
            // 用户不存在
            //System.out.println("123");
            userDaoImpl.addUser(user);
            //System.out.println("123456");
        }
        return u==null;
    }

    public boolean findByUsername(String username){
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        // 判断是否存在
        User u = userDaoImpl.selectByUsername(username);
        return u!=null;
    }
}
