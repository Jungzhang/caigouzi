package dao;

import idao.IUserDao;
import model.Passwd;
import model.User;
import until.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Jung on 2016/10/20.
 */
public class UserDao implements IUserDao {

    @Override
    public boolean insert(User user, Passwd passwd) {

        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatementOfUser = null;
        PreparedStatement preparedStatementOfPasswd = null;

        //此处使用数据库的事务管理保证注册用户时写入的两张表都能正确写入
        try {
            connection.setAutoCommit(false);
            preparedStatementOfUser = connection.prepareStatement("INSERT INTO user(account, type)" +
                    "VALUES (?, ?)");
            preparedStatementOfUser.setString(1, user.getAccount());
            preparedStatementOfUser.setInt(2, user.getAccountType());
            preparedStatementOfPasswd = connection.prepareStatement("INSERT INTO passwd(account, passwd) VALUES (?, ?)");
            preparedStatementOfPasswd.setString(1, passwd.getAccount());
            preparedStatementOfPasswd.setString(2, passwd.getPasswd());
            preparedStatementOfUser.execute();
            preparedStatementOfPasswd.execute();
            connection.commit();
            connection.setAutoCommit(true);
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    ret = false;
                    connection.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectionManager.getInstance().closeConnection(null, preparedStatementOfPasswd, null);
            ConnectionManager.getInstance().closeConnection(null, preparedStatementOfUser, connection);
        }

        return ret;
    }

    @Override
    public boolean update(User user) {

        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE user SET tel = ?, addr = ?, name = ?, nation_id = ?" +
                    " WHERE account = ?");
            preparedStatement.setString(1, user.getTel());
            preparedStatement.setString(2, user.getAddr());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, user.getNationId());
            preparedStatement.setString(5, user.getAccount());
            if (preparedStatement.executeUpdate() >= 1) {
                ret = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public ArrayList<User> findUserAll() {

        ArrayList<User> users = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user");
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setAccount(resultSet.getString("account"));
                    user.setTel(resultSet.getString("tel"));
                    user.setAddr(resultSet.getString("addr"));
                    user.setName(resultSet.getString("name"));
                    user.setAccountType(resultSet.getInt("type"));
                    user.setNationId(resultSet.getInt("nation_id"));
                    users.add(user);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(resultSet, preparedStatement, connection);
        }

        return users;
    }

    @Override
    public ArrayList<User> findUserByName(String name) {

        ArrayList<User> users = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        User user = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                users = new ArrayList<>();
                while (resultSet.next()) {
                    user = new User();
                    user.setAccount(resultSet.getString("account"));
                    user.setTel(resultSet.getString("tel"));
                    user.setAddr(resultSet.getString("addr"));
                    user.setName(resultSet.getString("name"));
                    user.setAccountType(resultSet.getInt("type"));
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(resultSet, preparedStatement, connection);
        }

        return users;
    }

    @Override
    public User finaUserByAccount(String account) {

        User user = null;

        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement("SELECT  * FROM user WHERE account = ?");
            preparedStatement.setString(1, account);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                user = new User();
                user.setAccount(resultSet.getString("account"));
                user.setTel(resultSet.getString("tel"));
                user.setAddr(resultSet.getString("addr"));
                user.setName(resultSet.getString("name"));
                user.setAccountType(resultSet.getInt("type"));
                user.setNationId(resultSet.getInt("nation_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(resultSet, preparedStatement, connection);
        }

        return user;
    }

    public boolean delete(String account) {

        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatementUser = null;
        PreparedStatement preparedStatementPasswd = null;

        try {
            connection.setAutoCommit(false);
            preparedStatementUser = connection.prepareStatement("DELETE FROM user WHERE account = ?");
            preparedStatementUser.setString(1, account);
            preparedStatementPasswd = connection.prepareStatement("DELETE FROM passwd WHERE account = ?");
            preparedStatementPasswd.setString(1, account);
            preparedStatementUser.execute();
            preparedStatementPasswd.execute();
            connection.commit();
            connection.setAutoCommit(true);
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    ret = false;
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectionManager.getInstance().closeConnection(null, preparedStatementPasswd, null);
            ConnectionManager.getInstance().closeConnection(null, preparedStatementUser, connection);
        }

        return ret;
    }

}
