package service;

import idao.DAOFactor;
import idao.IShopDao;
import model.Shop;

import java.util.ArrayList;

/**
 * Created by Jung on 2016/10/25.
 */
public class ShopSrv {
    private IShopDao shopDao = DAOFactor.createShopDao();

    public boolean add(Shop shop) {
        return shopDao.insert(shop);
    }

    public boolean delete(int shopId) {
        return shopDao.delete(shopId);
    }

    public boolean modify(Shop shop) {
        return shopDao.update(shop);
    }

    public Shop fetchShopByName(String name) {
        return shopDao.findShopByName(name);
    }

    public ArrayList<Shop> fetchAllShop() {
        return shopDao.findAllShop();
    }

    public Shop fetchShopByOwner(String account) {
        return shopDao.findShopByOwner(account);
    }

    public Shop fetchShopById(int id) {
        return shopDao.findShopById(id);
    }
}
