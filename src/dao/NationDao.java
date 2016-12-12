package dao;

import idao.INationDao;
import model.Nation;
import until.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Jung on 2016/11/20.
 */
public class NationDao implements INationDao {
    @Override
    public ArrayList<Nation> findAllProvince() {

        ArrayList<Nation> nations = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM nation WHERE parent = '1'");
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    Nation nation = new Nation();
                    nation.setId(resultSet.getInt("id"));
                    nation.setCode(resultSet.getString("code"));
                    nation.setProvince(resultSet.getString("province"));
                    nations.add(nation);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return nations;
    }

    @Override
    public ArrayList<Nation> findAllCityByProvince(String province, int type) {
        ArrayList<Nation> nations = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            if (type == 1) {
                preparedStatement = connection.prepareStatement("SELECT * FROM nation WHERE parent = " +
                        "(SELECT id FROM nation WHERE province = ?)");
            } else {
                preparedStatement = connection.prepareStatement("SELECT * FROM nation WHERE province = ?");
            }

            preparedStatement.setString(1, province);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    Nation nation = new Nation();
                    nation.setId(resultSet.getInt("id"));
                    nation.setCode(resultSet.getString("code"));
                    nation.setCity(resultSet.getString("city"));
                    nations.add(nation);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return nations;
    }

    @Override
    public ArrayList<Nation> findAllCountyByCityAndProvince(Nation nation, int type) {

        ArrayList<Nation> nations = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            if (type == 1) {
                preparedStatement = connection.prepareStatement("SELECT * FROM nation WHERE parent = " +
                        "(SELECT id FROM nation WHERE parent = (SELECT id FROM nation WHERE province = ?) AND city = ?)");
                preparedStatement.setString(1, nation.getProvince());
                preparedStatement.setString(2, nation.getCity());
            } else {
                preparedStatement = connection.prepareStatement("SELECT * FROM nation WHERE parent = " +
                        "(SELECT id FROM nation WHERE province = ?)");
                preparedStatement.setString(1, nation.getProvince());
            }

            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    Nation nation1 = new Nation();
                    nation1.setId(resultSet.getInt("id"));
                    nation1.setCode(resultSet.getString("code"));
                    nation1.setDistrict(resultSet.getString("district"));
                    nations.add(nation1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return nations;
    }

    @Override
    public int getCountyIdByNationObject(Nation nation, int type) {
        int id = -1;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            if (type == 1) {
                preparedStatement = connection.prepareStatement("SELECT id FROM nation WHERE parent = " +
                        "(SELECT id FROM nation WHERE parent = (SELECT id FROM nation WHERE province = ?) AND city = ?)" +
                        " AND district = ?");
                preparedStatement.setString(1, nation.getProvince());
                preparedStatement.setString(2, nation.getCity());
                preparedStatement.setString(3, nation.getDistrict());
            } else {
                preparedStatement = connection.prepareStatement("SELECT id FROM nation WHERE parent = " +
                        "(SELECT id FROM nation WHERE province = ?) AND district = ?");
                preparedStatement.setString(1, nation.getProvince());
                preparedStatement.setString(2, nation.getDistrict());
            }

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
    public int getCityIdByNationObject(Nation nation) {
        int id = -1;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT id FROM nation WHERE parent = " +
                    "(SELECT id FROM nation WHERE province = ?) AND city = ?");
            preparedStatement.setString(1, nation.getProvince());
            preparedStatement.setString(2, nation.getCity());
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
    public int getProvinceIdByNationObject(Nation nation) {
        int id = -1;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT id FROM nation WHERE province = ?");
            preparedStatement.setString(1, nation.getProvince());
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
    public Nation findNationByCountyId(int id) {

        Nation nation = new Nation();
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM nation WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                nation.setId(resultSet.getInt("id"));
                nation.setDistrict(resultSet.getString("district"));
                nation.setParent(resultSet.getString("parent"));
                nation.setCode(resultSet.getString("code"));
            } else {
                return null;
            }

            preparedStatement.setInt(1, Integer.parseInt(nation.getParent()));
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                nation.setCity(resultSet.getString("city"));
                int tmp = Integer.parseInt(resultSet.getString("parent"));
                preparedStatement.setInt(1, tmp);
            } else {
                return null;
            }

            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                nation.setProvince(resultSet.getString("province"));
            }

            if (nation.getProvince().equals("中华人民共和国")) {
                nation.setProvince(nation.getCity());
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return nation;
    }

    @Override
    public boolean insertData(Nation nation) {

        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO nation (code, province, city, district," +
                    " parent) VALUES (?, ?, ?, ?, ?)");

            preparedStatement.setString(1, nation.getCode());
            preparedStatement.setString(2, nation.getProvince());
            preparedStatement.setString(3, nation.getCity());
            preparedStatement.setString(4, nation.getDistrict());
            preparedStatement.setString(5, nation.getParent());

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
    public boolean updateData(Nation nation) {
        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE nation SET code = ?, province = ?, city = ?, " +
                    "district = ?, parent = ? WHERE id = ?");
            preparedStatement.setString(1, nation.getCode());
            preparedStatement.setString(2, nation.getProvince());
            preparedStatement.setString(3, nation.getCity());
            preparedStatement.setString(4, nation.getDistrict());
            preparedStatement.setString(5, nation.getParent());
            preparedStatement.setInt(6, nation.getId());

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
    public boolean deleteData(String code, int type) {
        boolean ret = false;

        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM nation WHERE code LIKE ?");

            if (type == Nation.PROVINCE || type == Nation.CITY || type == Nation.DISTRICT) {
                //此处需要PROVINCE = 1, CITY = 2, DISTRICT = 3
                preparedStatement.setString(1, code.substring(0, 2 * type)+"%");
                if (preparedStatement.executeUpdate() >= 1) {
                    ret = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(null, preparedStatement, connection);
        }

        return ret;
    }
}
