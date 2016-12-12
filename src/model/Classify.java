package model;

/**
 * Created by Jung on 2016/11/24.
 */
public class Classify {
    String name;
    int id;

    public Classify(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Classify(String name) {
        this.name = name;
    }

    public Classify() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
