package com.pheonix.org.hallaka.Models;

public class ProductDataModel {

    String name,price,image,uid,tag;

    public ProductDataModel() {
    }

    public ProductDataModel(String name, String price, String image, String uid, String tag) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.uid = uid;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
