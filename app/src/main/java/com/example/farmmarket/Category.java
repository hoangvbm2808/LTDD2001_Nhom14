package com.example.farmmarket;

public class Category {

    public static int count;

    private int id = ++count;
    private String title;
    private String pic;

    public Category(String title, String pic) {
        this.title = title;
        this.pic = pic;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
