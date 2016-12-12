package idao;

import dao.*;

/**
 * Created by Jung on 2016/10/25.
 */
public class DAOFactor {

    public static IOrdersDao createOrdersDao() {
        return new OrdersDao();
    }

    public static IFoodDao createFoodDao() {
        return new FoodDao();
    }

    public static IPasswdDao createPasswdDao() {
        return new PasswdDao();
    }

    public static IShopDao createShopDao() {
        return new ShopDao();
    }

    public static IUserDao createUserDao() {
        return new UserDao();
    }

    public static INationDao createNationDao() {
        return new NationDao();
    }

    public static IClassifyDao createClassifyDao() {
        return new ClassifyDao();
    }
}
