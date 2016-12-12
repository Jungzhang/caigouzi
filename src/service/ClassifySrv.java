package service;

import idao.DAOFactor;
import idao.IClassifyDao;
import model.Classify;

import java.util.ArrayList;

/**
 * Created by Jung on 2016/11/24.
 */
public class ClassifySrv {
    IClassifyDao classifyDao = DAOFactor.createClassifyDao();

    //增加分类
    public boolean add(Classify classify) {
        return classifyDao.insert(classify);
    }
    //删除分类
    public boolean delete(String name) {
        return classifyDao.delete(name);
    }
    //修改分类
    public boolean modify(String oldName, String newName) {
        return classifyDao.update(oldName, newName);
    }
    //通过名字获取id
    public int fetchIdByName(String name) {
        return classifyDao.findIdByName(name);
    }

    //获取所有的分类
    public ArrayList<Classify> fetchAllClassify() {
        return classifyDao.findAllClassify();
    }

    public String fetchNameById(int id) {
        return classifyDao.findNameById(id);
    }
}
