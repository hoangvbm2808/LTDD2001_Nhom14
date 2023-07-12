package com.example.farmmarket;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Product implements Parcelable {
    private int id;
    private String name;
    private String pic;
    private long price;
    private String description;
    private String create_date;
    private int current_inventory;
    private int cate_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getCurrent_inventory() {
        return current_inventory;
    }

    public void setCurrent_inventory(int current_inventory) {
        this.current_inventory = current_inventory;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }



    public Product(String name, String pic, long price) {
        this.name = name;
        this.pic = pic;
        this.price = price;
    }

    public Product(int id, String name, String pic, long price, String description, String create_date, int current_inventory, int cate_id) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.price = price;
        this.description = description;
        this.create_date = create_date;
        this.current_inventory = current_inventory;
        this.cate_id = cate_id;

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

    protected Product(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.pic = in.readString();
        this.price = in.readLong();
        this.description = in.readString();
        this.create_date = in.readString();
        this.current_inventory =in.readInt();
        this.cate_id = in.readInt();

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(pic);
        dest.writeLong(price);
        dest.writeString(description);
        dest.writeString(create_date);
        dest.writeInt(current_inventory);
        dest.writeInt(cate_id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
