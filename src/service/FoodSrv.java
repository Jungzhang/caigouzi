package service;

import idao.DAOFactor;
import idao.IFoodDao;
import model.Food;

import java.util.ArrayList;

/**
 * Created by Jung on 2016/10/25.
 */
public class FoodSrv {
    private IFoodDao foodDao = DAOFactor.createFoodDao();

    public boolean add(Food food) {
        return foodDao.insert(food);
    }

    public boolean modify(Food food) {
        return foodDao.update(food);
    }

    public boolean delete(int foodId) {
        return foodDao.delete(foodId);
    }

    public ArrayList<Food> fetchAllFoodByShop(int shopId, int cPage) {
        return foodDao.findAllFoodByShopId(shopId, cPage);
    }

    public Food fetchFoodByNameAndShopId(String foodName, int shopId) {
        return foodDao.findFoodByNameAndShopId(foodName, shopId);
    }

    public ArrayList<Food> fetchAllFoodByClassifyId(int id, int cPage) {
        return foodDao.findAllFoodByClassifyId(id, cPage);
    }

    public Food fetchFoodById(int id) {
        return foodDao.findFoodById(id);
    }

    public int getPageCount() {
        return foodDao.getPageCount();
    }

    public int getAllCount() {
        return foodDao.getAllCount();
    }

    public int getCurrentPage() {
        return foodDao.getCurrentPage();
    }

    public int getCurrentId() {
        return foodDao.getCurrentId();
    }
}
