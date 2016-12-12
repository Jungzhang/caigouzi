package model;

/**
 * Created by Jung on 2016/10/25.
 */
public class Shop {
    private int shopId;
    private String shopName;
    private String shopTel;
    private String shopAddr;
    private String ownerAccount;
    private int nationId;

    public Shop(int shopId, String shopName, String shopTel, String shopAddr, String ownerAccount, int nationId) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopTel = shopTel;
        this.shopAddr = shopAddr;
        this.ownerAccount = ownerAccount;
        this.nationId = nationId;
    }

    public Shop(String ownerAccount) {
        this.ownerAccount = ownerAccount;
    }

    public Shop() {
    }

    public int getNationId() {
        return nationId;
    }

    public void setNationId(int nationId) {
        this.nationId = nationId;
    }

    public String getOwnerAccount() {
        return ownerAccount;
    }

    public void setOwnerAccount(String ownerAccount) {
        this.ownerAccount = ownerAccount;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shop_id) {
        this.shopId = shop_id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }
}
