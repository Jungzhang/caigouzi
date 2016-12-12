package idao;

import model.Order;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jung on 2016/11/27.
 */
public interface IOrdersDao {

    boolean insertOrder(Order order);                               //插入订单

    boolean updateOrderStatusByOrderId(int orderId, int status);    //根据订单ID更改订单状态

    ArrayList<Order> findOrdersByAccount(String account, int type);    //根据账号获取该账号下的所有订单

    ArrayList<Order> findOrdersByShopId(int shopId, int status);         //根据商铺ID获取该店铺下所有的订单

    Order findOrderByOrderId(int orderId);                      //根据订单ID获取订单

    ArrayList<Order> findOrderByShopIdAndTime(int shop, Date start, Date end);  //查询某个时间段内该店铺完成销售的订单

    Double findCountPriceByShopIdAndTime(int shop, Date start, Date end);       //查询某一时间段内该店铺的总收入

}
