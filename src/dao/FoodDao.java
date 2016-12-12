package dao;

import idao.IFoodDao;
import model.Food;
import until.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Jung on 2016/10/25.
 */

public class FoodDao implements IFoodDao {

    private int allCount = 0;       //数据库记录总条数
    private int allPageCount = 0;   //总页数
    private int currentPage = 1;    //当前页
    private int pageSize = 14;       //页面大小

    @Override
    public boolean insert(Food food) {

        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean ret = false;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO food (shop_id, price, food_name, " +
                    "introduce, photo_url, sales_volume_month, sales_volume_count, classify_id) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, food.getShopId());
            preparedStatement.setFloat(2, food.getPrice());
            preparedStatement.setString(3, food.getFoodName());
            preparedStatement.setString(4, food.getIntroduce());
            preparedStatement.setString(5, food.getPhotoUrl());
            preparedStatement.setInt(6, food.getSales_volume_month());
            preparedStatement.setInt(7, food.getSales_volume_count());
            preparedStatement.setInt(8, food.getClassifyId());

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
    public boolean delete(int foodId) {

        boolean ret = false;

        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM food WHERE food_id = ?");
            preparedStatement.setInt(1, foodId);
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
    public boolean update(Food food) {

        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        boolean ret = false;

        try {
            preparedStatement = connection.prepareStatement("UPDATE food SET price = ? ,food_name = ? ," +
                    "introduce = ?,photo_url = ?, classify_id = ? WHERE food_id = ?");
            preparedStatement.setFloat(1, food.getPrice());
            preparedStatement.setString(2, food.getFoodName());
            preparedStatement.setString(3, food.getIntroduce());
            preparedStatement.setString(4, food.getPhotoUrl());
            preparedStatement.setInt(5, food.getClassifyId());
            preparedStatement.setInt(6, food.getFoodId());
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
    public Food findFoodByNameAndShopId(String name, int shopId) {

        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        Food food = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM food WHERE food_name = ? AND shop_id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, shopId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                food = new Food();
                while (resultSet.next()) {
                    food.setFoodId(resultSet.getInt("food_id"));
                    food.setShopId(resultSet.getInt("shop_id"));
                    food.setPrice(resultSet.getFloat("price"));
                    food.setFoodName(resultSet.getString("food_name"));
                    food.setIntroduce(resultSet.getString("introduce"));
                    food.setPhotoUrl(resultSet.getString("photo_url"));
                    food.setSales_volume_month(resultSet.getInt("sales_volume_month"));
                    food.setSales_volume_count(resultSet.getInt("sales_volume_count"));
                    food.setClassifyId(resultSet.getInt("classify_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(null, preparedStatement, connection);
        }

        return food;
    }

    @Override
    public ArrayList<Food> findAllFoodByShopId(int shopId, int cPage) {

        ArrayList<Food> foodList = new ArrayList<>();
        currentPage = cPage;

        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement("SELECT COUNT(food_id) AS all_account FROM food WHERE shop_id = ?");
            preparedStatement.setInt(1, shopId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                allCount = resultSet.getInt("all_account");
                allPageCount = (allCount + pageSize - 1) / pageSize;
                if (currentPage > allPageCount) {
                    currentPage = allPageCount;
                }
            }

            if (currentPage <= 0) {
                currentPage = 1;
            }

            preparedStatement = connection.prepareStatement("SELECT * FROM food WHERE shop_id = ? LIMIT ?,?");
            preparedStatement.setInt(1, shopId);
            System.out.println("cPage = " + currentPage);
            preparedStatement.setInt(2, pageSize * (currentPage - 1));
            preparedStatement.setInt(3, pageSize);
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    Food food = new Food();
                    food.setSales_volume_month(resultSet.getInt("sales_volume_month"));
                    food.setSales_volume_count(resultSet.getInt("sales_volume_count"));
                    food.setFoodId(resultSet.getInt("food_id"));
                    food.setShopId(resultSet.getInt("shop_id"));
                    food.setPrice(resultSet.getFloat("price"));
                    food.setFoodName(resultSet.getString("food_name"));
                    food.setIntroduce(resultSet.getString("introduce"));
                    food.setPhotoUrl(resultSet.getString("photo_url"));
                    food.setClassifyId(resultSet.getInt("classify_id"));
                    foodList.add(food);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.getInstance().closeConnection(resultSet, preparedStatement, connection);
        }

        return foodList;
    }

    @Override
    public ArrayList<Food> findAllFoodByClassifyId(int classifyId, int cPage) {
        ArrayList<Food> foods = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        currentPage = cPage;

        try {
            if (classifyId == 1) {
                preparedStatement = connection.prepareStatement("SELECT COUNT(food_id) AS all_account FROM food");
            } else {
                preparedStatement = connection.prepareStatement("SELECT COUNT(food_id) AS all_account FROM food WHERE classify_id = ?");
                preparedStatement.setInt(1, classifyId);
            }

            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                allCount = resultSet.getInt("all_account");
                allPageCount = (allCount + pageSize - 1) / pageSize;

                if (allPageCount >= 0 && currentPage > allPageCount) {
                    currentPage = allPageCount;
                }
            }

            if (currentPage <= 0) {
                currentPage = 1;
            }

            if (classifyId == 1) {
                preparedStatement = connection.prepareStatement("SELECT * FROM food LIMIT ?,?");
                preparedStatement.setInt(1, pageSize * (currentPage - 1));
                preparedStatement.setInt(2, pageSize);
            } else {
                preparedStatement = connection.prepareStatement("SELECT * FROM food WHERE classify_id = ? LIMIT ?,?");
                preparedStatement.setInt(1, classifyId);
                preparedStatement.setInt(2, pageSize * (currentPage - 1));
                preparedStatement.setInt(3, pageSize);
            }
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    Food food = new Food();
                    food.setFoodId(resultSet.getInt("food_id"));
                    food.setShopId(resultSet.getInt("shop_id"));
                    food.setPrice(resultSet.getFloat("price"));
                    food.setFoodName(resultSet.getString("food_name"));
                    food.setIntroduce(resultSet.getString("introduce"));
                    food.setPhotoUrl(resultSet.getString("photo_url"));
                    food.setSales_volume_month(resultSet.getInt("sales_volume_month"));
                    food.setSales_volume_count(resultSet.getInt("sales_volume_count"));
                    food.setClassifyId(resultSet.getInt("classify_id"));
                    foods.add(food);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return foods;
    }

    @Override
    public Food findFoodById(int id) {
        Food food = new Food();

        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM food WHERE food_id = ?");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                food.setSales_volume_month(resultSet.getInt("sales_volume_month"));
                food.setSales_volume_count(resultSet.getInt("sales_volume_count"));
                food.setClassifyId(resultSet.getInt("classify_id"));
                food.setFoodId(resultSet.getInt("food_id"));
                food.setShopId(resultSet.getInt("shop_id"));
                food.setPrice(resultSet.getFloat("price"));
                food.setFoodName(resultSet.getString("food_name"));
                food.setIntroduce(resultSet.getString("introduce"));
                food.setPhotoUrl(resultSet.getString("photo_url"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return food;
    }

    @Override
    public int getPageCount() {
        return allPageCount;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getAllCount() {
        return allCount;
    }

    @Override
    public int getCurrentId() {
        int count = 0;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SHOW TABLE STATUS WHERE Name='food'");
            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                count = resultSet.getInt("Auto_increment");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return count;
    }
}
