package com.example.naweed.foodmania.models;

import java.math.BigDecimal;


public class Product {
   private int id;
    private BigDecimal price;
    private double weight;
    private String description;
    private String name;
    private String imgLoc;
    private String avail;

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

    public Product() {
    }

    public Product(BigDecimal price, double weight, String description, String name) {
        this.price = price;
        this.weight = weight;
        this.description = description;
        this.name = name;
        this.avail = "NOT AVAILABLE";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID : "+getId()+"\nName : "+ getName() +"\nPrice : " + getPrice() + "\nWeight : "+ getWeight() + "\nDescription : "+ getDescription()+ "\nisAvail : "+ getAvail();
    }

    public String getImgLoc() {
        return imgLoc;
    }

    public void setImgLoc(String imgLoc) {
        this.imgLoc = imgLoc;
    }
}
