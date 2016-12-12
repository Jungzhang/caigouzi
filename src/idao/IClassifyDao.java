package idao;

import model.Classify;

import java.util.ArrayList;

/**
 * Created by Jung on 2016/11/24.
 */
public interface IClassifyDao {
    boolean insert(Classify classify);

    boolean delete(String name);

    boolean update(String oldName, String newName);

    ArrayList<Classify> findAllClassify();

    int findIdByName(String name);

    String findNameById(int id);
}
