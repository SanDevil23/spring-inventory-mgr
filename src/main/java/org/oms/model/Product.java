package org.oms.model;

public class Product {

    private int productID;
    private String name;
    private String brand;
    private long price;
    private long qty;

    public Product(int productID, String name, String brand, long price, long qty) {
        this.productID = productID;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.qty = qty;
    }

    public Product(){
        this.productID = -1;
        this.name = "";
        this.brand = "";
        this.price = 0;
        this.qty = -1;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }
}
