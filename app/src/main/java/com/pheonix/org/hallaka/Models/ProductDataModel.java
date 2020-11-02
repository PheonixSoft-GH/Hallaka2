package com.pheonix.org.hallaka.Models;

public class ProductDataModel {

    String name,brand,price,quantity,image,uid,tag;

    public ProductDataModel() {
    }

    public ProductDataModel(String name, String brand, String price, String quantity, String image, String uid, String tag) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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
