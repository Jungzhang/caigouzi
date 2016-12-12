package service;

import idao.DAOFactor;
import idao.INationDao;
import model.Nation;

import java.util.ArrayList;

/**
 * Created by Jung on 2016/11/22.
 */
public class NationSrv {
    private INationDao nationDao = DAOFactor.createNationDao();

    public ArrayList<Nation> fetchAllProvince() {
        return nationDao.findAllProvince();
    }

    public ArrayList<Nation> fetchAllCityByProvince(String province, int type) {
        return nationDao.findAllCityByProvince(province, type);
    }

    public ArrayList<Nation> fetchAllCountyByCityAndProvince(Nation nation, int type) {
        return nationDao.findAllCountyByCityAndProvince(nation, type);
    }

    public int fetchCountyIdByNationObject(Nation nation, int type) {
        return nationDao.getCountyIdByNationObject(nation, type);
    }

    public int fetchCityIdByNationObject(Nation nation) {
        return nationDao.getCityIdByNationObject(nation);
    }

    public int fetchProvinceIdByObject(Nation nation) {
        return nationDao.getProvinceIdByNationObject(nation);
    }

    public Nation fetchNationByCountyId(int id) {
        return nationDao.findNationByCountyId(id);
    }

    public boolean addNation(Nation nation) {
        return nationDao.insertData(nation);
    }

    public boolean modify(Nation nation) {
        return nationDao.updateData(nation);
    }

    public boolean delNation(String code, int type) {
        return nationDao.deleteData(code, type);
    }

}
