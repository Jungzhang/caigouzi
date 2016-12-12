package dao;

import idao.IPasswdDao;
import model.Passwd;
import until.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Jung on 2016/10/25.
 */

public class PasswdDao implements IPasswdDao {
    @Override
    public boolean update(Passwd passwd) {

        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE passwd SET passwd = ? WHERE account = ?");
            preparedStatement.setString(1, passwd.getPasswd());
            preparedStatement.setString(2, passwd.getAccount());
            ret = (preparedStatement.executeUpdate() >= 1 ? true : false);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(null, preparedStatement, connection);
        }

        return ret;
    }

    @Override
    public boolean isMatch(Passwd passwd) {

        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        Passwd queryPasswd;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM passwd WHERE account = ?");
            preparedStatement.setString(1, passwd.getAccount());
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                if (resultSet.next()) {
                    queryPasswd  = new Passwd();
                    queryPasswd.setPasswd(resultSet.getString("passwd"));
                    queryPasswd.setAccount(resultSet.getString("account"));
                    if (passwd.getPasswd().equals(queryPasswd.getPasswd())) {
                        ret = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(null, preparedStatement, connection);
        }

        return ret;
    }
}
