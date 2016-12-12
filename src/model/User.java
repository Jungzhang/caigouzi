package model;

/**
 * Created by Jung on 2016/10/23.
 * 用户
 */
public class User {
    private String account;
    private String tel;
    private String addr;
    private int accountType;
    private String name;

    public int getNationId() {
        return nationId;
    }

    public User() {
    }

    public User(String account, String tel, String addr, String name, int nationId) {
        this.account = account;
        this.tel = tel;
        this.addr = addr;
        this.name = name;
        this.nationId = nationId;
    }

    public void setNationId(int nationId) {
        this.nationId = nationId;
    }

    private int nationId;

    public static final int SHOP_MANAGER  = 1;
    public static final int CUSTOMER = 2;
    public static final int SYS_MANAGER = 3;
    public static final int NONE = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
