package dao;

import idao.IShopDao;
import model.Shop;
import until.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Jung on 2016/10/25.
 */
public class ShopDao implements IShopDao {
    @Override
    public boolean insert(Shop shop) {

        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO shop(shop_name, shop_tel, shop_addr, " +
                    "owner_account) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, shop.getShopName());
            preparedStatement.setString(2, shop.getShopTel());
            preparedStatement.setString(3, shop.getShopAddr());
            preparedStatement.setString(4, shop.getOwnerAccount());

            if (preparedStatement.executeUpdate() >= 1) {
                ret = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(null, preparedStatement, connection);
        }

        return ret;
    }

    @Override
    public boolean delete(int shopId) {

        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM shop WHERE shop_id = ?");
            preparedStatement.setInt(1, shopId);
            ret = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(null, preparedStatement, connection);
        }

        return ret;
    }

    @Override
    public boolean update(Shop shop) {

        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE shop SET shop_name = ?, shop_tel = ?, shop_addr = ?,"
                    + "nation_id = ? WHERE shop_id = ?");
            preparedStatement.setString(1, shop.getShopName());
            preparedStatement.setString(2, shop.getShopTel());
            preparedStatement.setString(3, shop.getShopAddr());
            preparedStatement.setInt(4, shop.getNationId());
            preparedStatement.setInt(5, shop.getShopId());
            ret = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(null, preparedStatement, connection);
        }

        return ret;
    }

    @Override
    public Shop findShopByName(String name) {

        Shop shop = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM shop WHERE shop_name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    shop = new Shop();
                    shop.setShopId(resultSet.getInt("shop_id"));
                    shop.setShopName(resultSet.getString("shop_name"));
                    shop.setShopTel(resultSet.getString("shop_tel"));
                    shop.setShopAddr(resultSet.getString("shop_addr"));
                    shop.setOwnerAccount(resultSet.getString("owner_account"));
                    shop.setNationId(resultSet.getInt("nation_id"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(resultSet, preparedStatement, connection);
        }

        return shop;
    }

    @Override
    public ArrayList<Shop> findAllShop() {

        ArrayList<Shop> shopArrayList = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM shop");
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                shopArrayList = new ArrayList();
                while (resultSet.next()) {
                    Shop shop = new Shop();
                    shop.setShopId(resultSet.getInt("shop_id"));
                    shop.setShopName(resultSet.getString("shop_name"));
                    shop.setShopTel(resultSet.getString("shop_tel"));
                    shop.setShopAddr(resultSet.getString("shop_addr"));
                    shop.setOwnerAccount(resultSet.getString("owner_account"));
                    shop.setNationId(resultSet.getInt("nation_id"));
                    shopArrayList.add(shop);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(resultSet, preparedStatement, connection);
        }

        return shopArrayList;
    }

    public Shop findShopByOwner(String owner) {

        Shop shop = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM shop WHERE owner_account = ?");
            preparedStatement.setString(1, owner);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    shop = new Shop();
                    shop.setShopAddr(resultSet.getString("shop_addr"));
                    shop.setOwnerAccount(resultSet.getString("owner_account"));
                    shop.setShopId(resultSet.getInt("shop_id"));
                    shop.setShopName(resultSet.getString("shop_name"));
                    shop.setShopTel(resultSet.getString("shop_tel"));
                    shop.setNationId(resultSet.getInt("nation_id"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(resultSet, preparedStatement, connection);
        }

        return shop;
    }

    @Override
    public Shop findShopById(int id) {
        Shop shop = new Shop();;

        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM shop WHERE shop_id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    shop.setShopName(resultSet.getString("shop_name"));
                    shop.setShopTel(resultSet.getString("shop_tel"));
                    shop.setShopAddr(resultSet.getString("shop_addr"));
                    shop.setOwnerAccount(resultSet.getString("owner_account"));
                    shop.setShopId(resultSet.getInt("shop_id"));
                    shop.setNationId(resultSet.getInt("nation_id"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(resultSet, preparedStatement, connection);
        }

        return shop;
    }
}
