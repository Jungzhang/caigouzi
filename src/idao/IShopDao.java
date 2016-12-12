package idao;

import model.Shop;

import java.util.ArrayList;

/**
 * Created by Jung on 2016/10/25.
 */
public interface IShopDao {
    public boolean insert(Shop shop);           //增
    public boolean delete(int shopId);          //删
    public boolean update(Shop shop);           //改
    public Shop findShopByName(String name);    //根据店铺名字获取店铺详情
    public ArrayList<Shop> findAllShop();       //获取所有店铺
    public Shop findShopByOwner(String owner);  //根据拥有者账号获取店铺详情
    public Shop findShopById(int id);
}
