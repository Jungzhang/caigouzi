package service;

import idao.DAOFactor;
import idao.IUserDao;
import model.Passwd;
import model.User;

import java.util.ArrayList;

/**
 * Created by Jung on 2016/10/25.
 */
public class UserSrv {
    private IUserDao userDao = DAOFactor.createUserDao();

    public boolean add(User user, Passwd passwd) {
        return userDao.insert(user, passwd);
    }

    public boolean delete(String account) {
        return userDao.delete(account);
    }

    public boolean modify(User user) {
        return userDao.update(user);
    }

    public ArrayList<User> fetchUserAll() {
        return userDao.findUserAll();
    }

    public ArrayList<User> fetchUserByName(String name) {
        return userDao.findUserByName(name);
    }

    public User fetchUserByAccount(String account) {
        return userDao.finaUserByAccount(account);
    }

}
