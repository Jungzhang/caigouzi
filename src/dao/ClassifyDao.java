package dao;

import idao.IClassifyDao;
import model.Classify;
import until.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Jung on 2016/11/24.
 */
public class ClassifyDao implements IClassifyDao {
    @Override
    public boolean insert(Classify classify) {
        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO classify(name) VALUES (?)");
            preparedStatement.setString(1, classify.getName());

            if (preparedStatement.executeUpdate() >= 1) {
                ret = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(null, preparedStatement, connection);
        }

        return ret;
    }

    @Override
    public boolean delete(String name) {
        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM classify WHERE name = ?");
            preparedStatement.setString(1, name);
            if (preparedStatement.executeUpdate() >= 1) {
                ret = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(null, preparedStatement, connection);
        }

        return ret;
    }

    @Override
    public boolean update(String oldName, String newName) {
        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE classify SET name = ? WHERE name = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, oldName);

            if (preparedStatement.executeUpdate() >= 1) {
                ret = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(null, preparedStatement, connection);
        }

        return ret;
    }

    @Override
    public ArrayList<Classify> findAllClassify() {
        ArrayList<Classify> classifies = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM classify");
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Classify classify = new Classify(resultSet.getString("name"), resultSet.getInt("id"));
                    classifies.add(classify);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return classifies;
    }

    @Override
    public int findIdByName(String name) {
        int id = -1;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT id FROM classify WHERE name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return id;
    }

    @Override
    public String findNameById(int id) {
        String name = "";
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT name FROM classify WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return name;
    }
}


