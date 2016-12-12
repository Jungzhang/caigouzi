package idao;

import model.Food;

import java.util.ArrayList;

/**
 * Created by Jung on 2016/10/25.
 */
public interface IFoodDao {

    boolean insert(Food food);       //增

    boolean delete(int foodId);     //删

    boolean update(Food food);       //改

    Food findFoodByNameAndShopId(String name, int shopId);  //根据店铺id和食品名字获取食品

    ArrayList<Food> findAllFoodByShopId(int shopId, int cPage);       //根据店铺ID获取店铺中的所有食品(分页)

    ArrayList<Food> findAllFoodByClassifyId(int classifyId, int cPage); //根据分类名字获取食品

    Food findFoodById(int id);

    int getPageCount();     //获取总页数

    int getCurrentPage();   //获取当前页

    int getAllCount();      //获取数据库中的总条数

    int getCurrentId();
}
