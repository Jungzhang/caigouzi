package idao;

import model.Passwd;
import model.User;
import java.util.ArrayList;

/**
 * Created by Jung on 2016/10/23.
 */
public interface IUserDao {
    public boolean insert(User user, Passwd passwd);       //增
    public boolean delete(String account);     //删
    public boolean update(User user);       //改
    public ArrayList<User> findUserAll();   //查看所有用户
    public ArrayList<User> findUserByName(String name);    //根据送货姓名查找
    public User finaUserByAccount(String account);  //根据账号查找
}
