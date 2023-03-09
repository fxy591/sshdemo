package primaryColor.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import primaryColor.dao.UserDao;
import primaryColor.pojo.User;
import primaryColor.utils.HibernateUtils;

/**
 * @author PrimaryColor
 * @date 2023/1/30
 * @apiNote
 */
public class UserDaoImpl implements UserDao {
    /**
     * 根据用户名、密码查询对象
     * @param username
     * @param password
     * @return
     */
    @Override
    public User select(String username, String password) {
        String sql = "select * from tb_user where username = :username and password = :password";
        Session session = HibernateUtils.openSession();
        //System.out.println(sql);
        Query query = session.createSQLQuery(sql).addEntity(User.class).setParameter("username",username).setParameter("password",password);
        //List<User> list = query.list();
        User user = (User) query.uniqueResult();
        //System.out.println(user);
        session.beginTransaction().commit();
        session.close();
        //if (list.size() > 0) {
        //    System.out.println(list.get(0).toString());
        //    return list.get(0);
        //}
        return user;
        //return null;
    }

    /**
     * 根据用户名查询对象
     * @param username
     * @return
     */
    @Override
    public User selectByUsername(String username) {
        String sql = "select * from tb_user where username=:username";
        Session session = HibernateUtils.openSession();
        Query query = session.createSQLQuery(sql).addEntity(User.class).setParameter("username",username);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    public void addUser(User user) {
        Session session = HibernateUtils.openSession();
        session.save(user);
        session.beginTransaction().commit();
        session.close();
    }

}
