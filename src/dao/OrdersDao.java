package dao;

import idao.IOrdersDao;
import model.Order;
import until.ConnectionManager;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Jung on 2016/11/28.
 */
public class OrdersDao implements IOrdersDao {
    @Override
    public boolean insertOrder(Order order) {
        boolean ret = false;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO orders(account, foodId, shopId, price," +
                    " orderDate, timeOut, status) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, order.getAccount());
            preparedStatement.setInt(2, order.getFoodId());
            preparedStatement.setInt(3, order.getShopId());
            preparedStatement.setFloat(4, order.getPrice());
            preparedStatement.setTimestamp(5, new java.sql.Timestamp(order.getOrderDate().getTime()));
            preparedStatement.setTimestamp(6, new java.sql.Timestamp(order.getTimeOutDate().getTime()));
            preparedStatement.setInt(7, order.getStatus());

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
    public boolean updateOrderStatusByOrderId(int orderId, int status) {

        boolean ret = false;

        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE orders SET status = ? WHERE order_id = ?");
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, orderId);

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
    public ArrayList<Order> findOrdersByAccount(String account, int type) {

        ArrayList<Order> orders = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            if (type == 0) {
                preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE account = ?");
                preparedStatement.setString(1, account);
            } else {
                preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE account = ? AND status = ?");
                preparedStatement.setString(1, account);
                if (type == 1) {
                    preparedStatement.setInt(2, Order.DONE);
                } else if (type == 2) {
                    preparedStatement.setInt(2, Order.WAIT);
                } else if (type == 3) {
                    preparedStatement.setInt(2, Order.TIMEOUT);
                } else {
                    preparedStatement.setInt(2, -1);
                }
            }


            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setOrderId(resultSet.getInt("order_id"));
                    order.setAccount(resultSet.getString("account"));
                    order.setFoodId(resultSet.getInt("foodId"));
                    order.setShopId(resultSet.getInt("shopId"));
                    order.setPrice(resultSet.getFloat("price"));
                    order.setOrderDate(new java.util.Date(resultSet.getTimestamp("orderDate").getTime()));
                    order.setTimeOutDate(new java.util.Date(resultSet.getTimestamp("timeOut").getTime()));
                    order.setStatus(resultSet.getInt("status"));
                    orders.add(order);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return orders;
    }

    @Override
    public ArrayList<Order> findOrdersByShopId(int shopId, int type) {

        ArrayList<Order> orders = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            if (type == 0) {
                preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE shopId = ?");
                preparedStatement.setInt(1, shopId);
            } else {
                preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE shopId = ? AND status = ?");
                preparedStatement.setInt(1, shopId);
                if (type == 1) {
                    preparedStatement.setInt(2, Order.DONE);
                } else if (type == 2) {
                    preparedStatement.setInt(2, Order.WAIT);
                } else if (type == 3) {
                    preparedStatement.setInt(2, Order.TIMEOUT);
                } else {
                    preparedStatement.setInt(2, -1);
                }
            }

            resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setPrice(resultSet.getFloat("price"));
                    order.setOrderDate(new java.util.Date(resultSet.getTimestamp("orderDate").getTime()));
                    order.setTimeOutDate(new java.util.Date(resultSet.getTimestamp("timeOut").getTime()));
                    order.setStatus(resultSet.getInt("status"));
                    order.setOrderId(resultSet.getInt("order_id"));
                    order.setAccount(resultSet.getString("account"));
                    order.setFoodId(resultSet.getInt("foodId"));
                    order.setShopId(resultSet.getInt("shopId"));
                    orders.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return orders;
    }

    @Override
    public Order findOrderByOrderId(int orderId) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Order order = new Order();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE order_id = ?");
            preparedStatement.setInt(1, orderId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                order.setPrice(resultSet.getFloat("price"));
                order.setOrderDate(new java.util.Date(resultSet.getTimestamp("orderDate").getTime()));
                order.setTimeOutDate(new java.util.Date(resultSet.getTimestamp("timeOut").getTime()));
                order.setStatus(resultSet.getInt("status"));
                order.setOrderId(resultSet.getInt("order_id"));
                order.setAccount(resultSet.getString("account"));
                order.setFoodId(resultSet.getInt("foodId"));
                order.setShopId(resultSet.getInt("shopId"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return order;
    }

    @Override
    public ArrayList<Order> findOrderByShopIdAndTime(int shop, java.util.Date start, java.util.Date end) {

        ArrayList<Order> orders = new ArrayList<>();

        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            if (start == null) {
                preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE shopId = ? AND status = ?");
                preparedStatement.setInt(1, shop);
                preparedStatement.setInt(2, Order.DONE);
            } else {
                Timestamp startDate = new Timestamp(start.getTime());
                Timestamp endDate = new Timestamp(end.getTime());
                preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE shopId = ? AND " +
                        "? <= orderDate AND orderDate <= ? AND status = ?");
                preparedStatement.setInt(1, shop);
                preparedStatement.setTimestamp(2, startDate);
                preparedStatement.setTimestamp(3, endDate);
                preparedStatement.setInt(4, Order.DONE);
            }

            resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setPrice(resultSet.getFloat("price"));
                    order.setOrderDate(new java.util.Date(resultSet.getTimestamp("orderDate").getTime()));
                    order.setTimeOutDate(new java.util.Date(resultSet.getTimestamp("timeOut").getTime()));
                    order.setStatus(resultSet.getInt("status"));
                    order.setOrderId(resultSet.getInt("order_id"));
                    order.setAccount(resultSet.getString("account"));
                    order.setFoodId(resultSet.getInt("foodId"));
                    order.setShopId(resultSet.getInt("shopId"));
                    orders.add(order);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return orders;
    }

    @Override
    public Double findCountPriceByShopIdAndTime(int shop, java.util.Date start, java.util.Date end) {
        Double count = 0.0;
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            if (start == null) {
                preparedStatement = connection.prepareStatement("SELECT SUM(price) AS counts FROM orders WHERE shopId = ? AND status = ?");
                preparedStatement.setInt(1, shop);
                preparedStatement.setInt(2, Order.DONE);
            } else {
                Timestamp startDate = new Timestamp(start.getTime());
                Timestamp endDate = new Timestamp(end.getTime());
                preparedStatement = connection.prepareStatement("SELECT SUM(price) AS counts FROM orders WHERE shopId = ? AND " +
                        "? <= orderDate AND orderDate <= ? AND status = ?");
                preparedStatement.setInt(1, shop);
                preparedStatement.setTimestamp(2, startDate);
                preparedStatement.setTimestamp(3, endDate);
                preparedStatement.setInt(4, Order.DONE);
            }

            resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                count = resultSet.getDouble("counts");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(resultSet, preparedStatement, connection);
        }

        return count;
    }
}
