package com.example.farmmarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FarmMarketDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "FarmMarket.db";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_CATEGORY = "Category";
    private static final String COLUMN_CATEGORY_ID = "_id";
    private static final String COLUMN_CATEGORY_NAME = "name";


    private static final String TABLE_PRODUCT = "Product";
    private static final String COLUMN_PRODUCT_ID = "_id";
    private static final String COLUMN_PRODUCT_NAME = "name";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "description";
    private static final String COLUMN_PRODUCT_PRICE = "price";
    private static final String COLUMN_PRODUCT_IMAGE = "image";
    private static final String COLUMN_PRODUCT_CREATED_DATE = "create_date";
    private static final String COLUMN_PRODUCT_CURRENT_INVENTORY = "current_inventory";
    private static final String COLUMN_PRODUCT_CATEGORY_ID = "category_id";


    private static final String TABLE_ORDER = "Order";
    private static final String COLUMN_ORDER_ID = "_id";
    private static final String COLUMN_ORDER_CREATE_DATE = "create_date";
    private static final String COLUMN_ORDER_AMOUNT = "amount";
    private static final String COLUMN_ORDER_USER_ID = "user_id";


    private static final String TABLE_ORDER_DETAIL = "Order_detail";
    private static final String COLUMN_ORDER_DETAIL_ID = "_id";
    private static final String COLUMN_ORDER_DETAIL_ORDER_ID = "order_id";
    private static final String COLUMN_ORDER_DETAIL_PRODUCT_ID = "product_id";
    private static final String COLUMN_ORDER_DETAIL_UNIT_PRICE = "unit_price";
    private static final String COLUMN_ORDER_DETAIL_QUANTITY = "quantity";


    private static final String TABLE_USER = "USER";
    private static final String COLUMN_USER_ID = "_id";
    private static final String COLUMN_USER_FIRST_NAME = "first_name";
    private static final String COLUMN_USER_LAST_NAME = "last_name";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PHONE = "phone";
    private static final String COLUMN_USER_USERNAME = "username";
    private static final String COLUMN_USER_PASSWORD = "password";







    public FarmMarketDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CATEGORY +
                                " (" + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        COLUMN_CATEGORY_NAME + " TEXT); ";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        onCreate(db);
    }

    void addCategory() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> categories = new ArrayList<>();
        categories.add("Rau củ");
        categories.add("Thịt");
        categories.add("Thủy Sản");
        categories.add("Trứng");
        categories.add("Trái cây");
        categories.add("Thực phẩm đông lạnh");
        categories.add("Dầu ăn, gia vị");
        categories.add("Gạo, Mì, Bún, Đậu");
        categories.add("Thực Phẩm Sơ Chế");
        categories.add("Thực phẩm khô");
        categories.add("Bánh Kẹo");
        categories.add("Món Ăn Nhanh");
        categories.add("Nước Ngọt");
        categories.add("Thực Phẩm Ăn Liền");
        categories.add("Rượu, Bia");
        categories.add("Thực Phẩm Bổ Sung");
        categories.add("Sức Khỏe, Làm Đẹp");
        categories.add("Mẹ Và Bé");
        categories.add("Chăm Sóc Cá Nhân");;
        categories.add("Dụng Cụ Nhà Bếp");
        categories.add("Dụng Cụ Nhà Tắm");
        categories.add("Thực Phẩm Bổ Sung Sức Khỏe");
        categories.add("Trà, Cà Phê");
        categories.add("Gia Đình");
        categories.add("Đồ Chơi Trẻ Em");
        categories.add("Sức Khỏe, Làm Đẹp");
        categories.add("Mẹ Và Bé");
        categories.add("Chăm Sóc Cá Nhân");
        categories.add("Dụng Cụ Nhà Bếp");
        categories.add("Dụng Cụ Nhà Tắm");
        categories.add("Thực Phẩm Bổ Sung Sức Khỏe");
        categories.add("Trà, Cà Phê");
        categories.add("Gia Đình");
        categories.add("Đồ Chơi Trẻ Em");

        categories.forEach(c -> {
            ContentValues cv =new ContentValues();
            cv.put(COLUMN_CATEGORY_NAME,c);
            db.insert(TABLE_CATEGORY, null, cv);
        });
    }
}
