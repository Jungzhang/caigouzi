package service;

import idao.DAOFactor;
import idao.IOrdersDao;
import model.Order;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jung on 2016/11/28.
 */
public class OrderSrv {

    IOrdersDao ordersDao = DAOFactor.createOrdersDao();

    //插入订单
    public boolean addOrder(Order order) {
        return ordersDao.insertOrder(order);
    }

    //根据订单ID更改订单状态
    public boolean modifyOrderStatusByOrderId(int orderId, int status) {
        return ordersDao.updateOrderStatusByOrderId(orderId, status);
    }

    //根据账号获取该账号下的所有订单
    public ArrayList<Order> fetchAllOrdersByAccount(String account, int type) {
        return ordersDao.findOrdersByAccount(account, type);
    }

    //根据商铺ID获取该店铺下所有的订单
    public ArrayList<Order> fetchAllOrderByShopId(int shopId, int type) {
        return ordersDao.findOrdersByShopId(shopId, type);
    }

    //根据订单ID获取订单
    public Order fetchOrderByOrderId(int orderId) {
        return ordersDao.findOrderByOrderId(orderId);
    }

    public ArrayList<Order> fetchOrdersByShopIdAndDate(int shopId, Date start, Date end) {
        return ordersDao.findOrderByShopIdAndTime(shopId, start, end);
    }

    public Double fetchCountPriceByShopIdAndDate(int shopId, Date start, Date end) {
        return ordersDao.findCountPriceByShopIdAndTime(shopId, start, end);
    }

}
