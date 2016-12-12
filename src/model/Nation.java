package model;

/**
 * Created by Jung on 2016/11/20.
 */
public class Nation {
    private int id;
    private String code;
    private String province;
    private String city;
    private String district;
    private String parent;

    static public int PROVINCE = 1;
    static public int CITY = 2;
    static public int DISTRICT = 3;

    public Nation() {
        this.setProvince("");
        this.setCity("");
        this.setDistrict("");
    }

    public Nation(String province, String city) {
        this.province = province;
        this.city = city;
    }

    public Nation(String province, String city, String district) {
        this.province = province;
        this.city = city;
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
