package model;

/**
 * Created by Jung on 2016/10/25.
 */
public class Food {
    private int foodId;
    private int shopId;
    private float price;
    private String foodName;
    private String introduce;
    private String photoUrl;
    private int sales_volume_month;
    private int sales_volume_count;
    private int classifyId;

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getSales_volume_month() {
        return sales_volume_month;
    }

    public void setSales_volume_month(int sales_volume_month) {
        this.sales_volume_month = sales_volume_month;
    }

    public int getSales_volume_count() {
        return sales_volume_count;
    }

    public void setSales_volume_count(int sales_volume_count) {
        this.sales_volume_count = sales_volume_count;
    }
}
