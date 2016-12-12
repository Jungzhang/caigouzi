package model;

/**
 * Created by Jung on 2016/10/24.
 * 密码
 */
public class Passwd {
    public static short MANAGER = 0;
    public static short CUSTOMER = 1;

    private String account;
    private String passwd;

    public Passwd(){

    }

    public Passwd(String account, String passwd) {
        this.account = account;
        this.passwd = passwd;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}
