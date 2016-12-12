package model;

import java.util.Date;

/**
 * Created by Jung on 2016/11/27.
 */
public class Order {
    public static final int WAIT = 0;      //未完成
    public static final int DONE = 1;      //已完成
    public static final int TIMEOUT = 2;   //过期
    int orderId;
    String account;
    int foodId;
    int shopId;
    float price;
    Date orderDate;
    Date timeOutDate;
    int status;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getTimeOutDate() {
        return timeOutDate;
    }

    public void setTimeOutDate(Date timeOutDate) {
        this.timeOutDate = timeOutDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
