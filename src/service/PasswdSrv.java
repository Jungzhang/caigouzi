package service;

import idao.DAOFactor;
import idao.IPasswdDao;
import model.Passwd;

/**
 * Created by Jung on 2016/10/25.
 */
public class PasswdSrv {
    private IPasswdDao passwdDao = DAOFactor.createPasswdDao();

    //修改密码
    public boolean modify(Passwd passwd) {
        return passwdDao.update(passwd);
    }

    //账号密码是否匹配
    public boolean isMatch(Passwd passwd) {
        return passwdDao.isMatch(passwd);
    }
}
