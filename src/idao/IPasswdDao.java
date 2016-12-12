package idao;

import model.Passwd;

/**
 * Created by Jung on 2016/10/24.
 */
public interface IPasswdDao {
    public boolean update(Passwd passwd);       //改
    public boolean isMatch(Passwd passwd);      //根据传入的密码类看账密是否匹配
}
