package com.example.farmmarket;

public class Product {
    private String name;
    private String pic;
    private long price;

    public Product(String name, String pic, long price) {
        this.name = name;
        this.pic = pic;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
