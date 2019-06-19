package com.example.examprep;

public class Article {
    private int id;
    private String name;
    private double price;
    private String imageName;

    public Article() {}

    public Article(int id, String name, double price, String imageName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageName = imageName;
    }

    public Article(String name, double price, String imageName) {
        this.name = name;
        this.price = price;
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
