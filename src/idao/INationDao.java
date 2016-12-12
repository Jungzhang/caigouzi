package idao;

import model.Nation;

import java.util.ArrayList;

/**
 * Created by Jung on 2016/11/20.
 */
public interface INationDao {
    ArrayList<Nation> findAllProvince();            //获取所有的省
    ArrayList<Nation> findAllCityByProvince(String province, int type); //获取某省下的所有市
    ArrayList<Nation> findAllCountyByCityAndProvince(Nation nation, int type);   //获取某市下的所有区县
    int getCountyIdByNationObject(Nation nation, int type);   //通过nation对象中的省市县信息获取县的ID
    int getCityIdByNationObject(Nation nation);     //通过nation对象中的省市信息获取市ID
    int getProvinceIdByNationObject(Nation nation); //通过nation对象中省信息获取省ID
    Nation findNationByCountyId(int id);            //通过县Id获取完整的所在地
    boolean insertData(Nation nation);    //插入数据
    boolean updateData(Nation nation);    //修改数据
    boolean deleteData(String code, int type);  //删除数据
}
