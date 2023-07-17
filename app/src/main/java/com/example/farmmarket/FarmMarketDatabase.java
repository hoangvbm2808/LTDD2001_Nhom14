package com.example.farmmarket;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


    public FarmMarketDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CATEGORY +
                                " (" + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        COLUMN_CATEGORY_NAME + " TEXT); ";


        String queryProduct = "CREATE TABLE " + TABLE_PRODUCT +
                " (" + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT_NAME + " TEXT, "+
                COLUMN_PRODUCT_DESCRIPTION + " TEXT, "+
                COLUMN_PRODUCT_PRICE + " FLOAT, "+
                COLUMN_PRODUCT_IMAGE + " TEXT, "+
                COLUMN_PRODUCT_CREATED_DATE + " TEXT, "+
                COLUMN_PRODUCT_CURRENT_INVENTORY + " INTEGER, "+
                COLUMN_PRODUCT_CATEGORY_ID + " INTEGER); ";

        db.execSQL(query);
        db.execSQL(queryProduct);
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

    void addProduct() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<ContentValues> products = new ArrayList<>();
        ContentValues cv1 = createContentValuesProduct("Bắp cải tím", "100% không thuôc trừ sâu", 10000, "bapcaitim", "09/07/2023", 100, 1);
        ContentValues cv2 = createContentValuesProduct("Bắp cải trắng", "100% không thuôc trừ sâu", 16000, "bapcaitrang", "09/07/2023", 100, 1);
        ContentValues cv3 = createContentValuesProduct("Bắp cải xanh", "100% không thuôc trừ sâu", 12000, "bapcaixanh", "09/07/2023", 100, 1);
        ContentValues cv4 = createContentValuesProduct("Trái Bắp", "100% không thuôc trừ sâu", 10000, "bap", "09/07/2023", 100, 1);
        ContentValues cv5 = createContentValuesProduct("Bầu", "100% không thuôc trừ sâu", 16000, "bau", "09/07/2023", 100, 1);
        ContentValues cv6 = createContentValuesProduct("Bí đỏ", "100% không thuôc trừ sâu", 12000, "bido", "09/07/2023", 100, 1);
        ContentValues cv7 = createContentValuesProduct("Cà chua", "100% không thuôc trừ sâu", 10000, "cachua", "09/07/2023", 100, 1);
        ContentValues cv8 = createContentValuesProduct("Cà chua bi", "100% không thuôc trừ sâu", 16000, "cachuabi", "09/07/2023", 100, 1);
        ContentValues cv9 = createContentValuesProduct("Cải ngọt", "100% không thuôc trừ sâu", 12000, "caingot", "09/07/2023", 100, 1);
        ContentValues cv10 = createContentValuesProduct("Cải thảo", "100% không thuôc trừ sâu", 12000, "caithao", "09/07/2023", 100, 1);
        ContentValues cv11 = createContentValuesProduct("Cải xanh", "100% không thuôc trừ sâu", 10000, "caixanh", "09/07/2023", 100, 1);
        ContentValues cv12 = createContentValuesProduct("Củ su su", "100% không thuôc trừ sâu", 16000, "cu_susu", "09/07/2023", 100, 1);
        ContentValues cv13 = createContentValuesProduct("Xả", "100% không thuôc trừ sâu", 12000, "cu_xa", "09/07/2023", 100, 1);
        ContentValues cv14 = createContentValuesProduct("Củ cải đỏ", "100% không thuôc trừ sâu", 10000, "cucaido", "09/07/2023", 100, 1);
        ContentValues cv15 = createContentValuesProduct("Củ cải trắng", "100% không thuôc trừ sâu", 16000, "cucaitrang", "09/07/2023", 100, 1);
        ContentValues cv16 = createContentValuesProduct("Đậu cove", "100% không thuôc trừ sâu", 12000, "daucove", "09/07/2023", 100, 1);
        ContentValues cv17 = createContentValuesProduct("Đậu nành", "100% không thuôc trừ sâu", 10000, "dau_nanh", "09/07/2023", 100, 1);
        ContentValues cv18 = createContentValuesProduct("Dưa leo", "100% không thuôc trừ sâu", 16000, "dualeo", "09/07/2023", 100, 1);
        ContentValues cv19 = createContentValuesProduct("Khoai tây", "100% không thuôc trừ sâu", 12000, "khoaitay", "09/07/2023", 100, 1);
        //Thịt
        ContentValues cv20 = createContentValuesProduct("Khổ qua", "100% không thuôc trừ sâu", 12000, "khoqua", "09/07/2023", 100, 1);
        ContentValues cv21 = createContentValuesProduct("Thăn bò", "Được tuyển chọn kỹ càng từ những chú bò đạt chuẩn", 50000, "bo_than", "09/07/2023", 100, 2);
        ContentValues cv22 = createContentValuesProduct("Cánh gà", "Được tuyển chọn kỹ càng từ những con gà đạt chuẩn", 66000, "ga_canh", "09/07/2023", 100, 2);
        ContentValues cv23 = createContentValuesProduct("Chân gà", "Được tuyển chọn kỹ càng từ những con gà đạt chuẩn", 72000, "ga_chan", "09/07/2023", 100, 2);
        ContentValues cv24 = createContentValuesProduct("Đùi gà", "Được tuyển chọn kỹ càng từ những con gà đạt chuẩn", 80000, "ga_dui", "09/07/2023", 100, 2);
        ContentValues cv25 = createContentValuesProduct("Má đùi gà", "Được tuyển chọn kỹ càng từ những con gà đạt chuẩn", 56000, "ga_madui", "09/07/2023", 100, 2);
        ContentValues cv26 = createContentValuesProduct("Heo ba rọi", "Heo được nuôi theo công nghệ tiên tiến, quy trình lấy thịt khép kín, đảm bảo an toàn vệ sinh thực phẩm", 42000, "heo_3roi", "09/07/2023", 100, 2);
        ContentValues cv27 = createContentValuesProduct("Thịt heo cốt lết", "Heo được nuôi theo công nghệ tiên tiến, quy trình lấy thịt khép kín, đảm bảo an toàn vệ sinh thực phẩm", 30000, "heo_cottlet", "09/07/2023", 100, 2);
        ContentValues cv28 = createContentValuesProduct("Thit heo nạc dăm", "Heo được nuôi theo công nghệ tiên tiến, quy trình lấy thịt khép kín, đảm bảo an toàn vệ sinh thực phẩm", 86000, "heo_nacdam", "09/07/2023", 100, 2);
        ContentValues cv29 = createContentValuesProduct("Thịt vai heo", "Heo được nuôi theo công nghệ tiên tiến, quy trình lấy thịt khép kín, đảm bảo an toàn vệ sinh thực phẩm", 72000, "heo_thitvai", "09/07/2023", 100, 2);
        //Thủy sản
        ContentValues cv30 = createContentValuesProduct("Cá bạc má", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 120000, "ca_bacma", "09/07/2023", 100, 3);
        ContentValues cv31 = createContentValuesProduct("Cá basa", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 100000, "ca_basa", "09/07/2023", 100, 3);
        ContentValues cv32 = createContentValuesProduct("Cá bớp", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 86000, "ca_bop", "09/07/2023", 100, 3);
        ContentValues cv33 = createContentValuesProduct("Cá diêu hồng", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 92000, "ca_dieuhong", "09/07/2023", 100, 3);
        ContentValues cv34 = createContentValuesProduct("Cá hồi", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 100000, "ca_hoi", "09/07/2023", 100, 3);
        ContentValues cv35 = createContentValuesProduct("Cá lóc", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 96000, "ca_loc", "09/07/2023", 100, 3);
        ContentValues cv36 = createContentValuesProduct("Cá ngừ", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 82000, "ca_ngu", "09/07/2023", 100, 3);
        ContentValues cv37 = createContentValuesProduct("Cá thu", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 150000, "ca_thu", "09/07/2023", 100, 3);
        ContentValues cv38 = createContentValuesProduct("Bào ngư", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 140000, "baongu", "09/07/2023", 100, 3);
        ContentValues cv39 = createContentValuesProduct("Mực lá", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 72000, "mucla", "09/07/2023", 100, 3);
        ContentValues cv40 = createContentValuesProduct("Mực ống", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 92000, "mucong", "09/07/2023", 100, 3);
        ContentValues cv41 = createContentValuesProduct("Tôm sú", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 100000, "tom_su", "09/07/2023", 100, 3);
        ContentValues cv42 = createContentValuesProduct("Tôm thẻ", "Thực phẩm sạch 100%, an toàn cho sức khỏe và có giá trị, có nguồn gốc xuất xứ rõ ràng", 160000, "tom_the", "09/07/2023", 100, 3);
        //Trứng
        ContentValues cv43 = createContentValuesProduct("Trứng gà vỉ 6", "Sản phẩm đã được kiểm dịch chặt chẽ, đảm bảo an toàn cho người tiêu dùng.", 12000, "trung_ga6", "09/07/2023", 100, 4);
        ContentValues cv44 = createContentValuesProduct("Trứng gà vỉ 10", "Sản phẩm đã được kiểm dịch chặt chẽ, đảm bảo an toàn cho người tiêu dùng.", 10000, "trung_ga10", "09/07/2023", 100, 4);
        ContentValues cv45 = createContentValuesProduct("Trứng gà ta", "Sản phẩm đã được kiểm dịch chặt chẽ, đảm bảo an toàn cho người tiêu dùng.", 16000, "trung_gata", "09/07/2023", 100, 4);
        ContentValues cv46 = createContentValuesProduct("Trứng vịt vỉ 10", "Sản phẩm đã được kiểm dịch chặt chẽ, đảm bảo an toàn cho người tiêu dùng.", 12000, "trung_vit10", "09/07/2023", 100, 4);
        ContentValues cv47 = createContentValuesProduct("Trứng vịt bắc thảo", "Sản phẩm đã được kiểm dịch chặt chẽ, đảm bảo an toàn cho người tiêu dùng.", 10000, "trung_vitbacthao", "09/07/2023", 100, 4);
        ContentValues cv48 = createContentValuesProduct("Trứng vịt muối", "Sản phẩm đã được kiểm dịch chặt chẽ, đảm bảo an toàn cho người tiêu dùng.", 16000, "trung_vitmuoi", "09/07/2023", 100, 4);
        //Trái cây
        ContentValues cv49 = createContentValuesProduct("Chery Mỹ", "100% không thuôc trừ sâu", 12000, "cherry_my", "09/07/2023", 100, 5);
        ContentValues cv50 = createContentValuesProduct("Chuối cau", "100% không thuôc trừ sâu", 12000, "chuoi_cau", "09/07/2023", 100, 5);
        ContentValues cv51 = createContentValuesProduct("Chuối dole", "100% không thuôc trừ sâu", 10000, "chuoi_dole", "09/07/2023", 100, 5);
        ContentValues cv52 = createContentValuesProduct("Chuối Fesepiakid", "100% không thuôc trừ sâu", 16000, "chuoi_fesepiakid", "09/07/2023", 100, 5);
        ContentValues cv53 = createContentValuesProduct("Chuối sứ", "100% không thuôc trừ sâu", 12000, "chuoi_su", "09/07/2023", 100, 5);
        ContentValues cv54 = createContentValuesProduct("Dưa gang", "100% không thuôc trừ sâu", 10000, "duagang", "09/07/2023", 100, 5);
        ContentValues cv55 = createContentValuesProduct("Dua hấu không hạt", "100% không thuôc trừ sâu", 16000, "duahau_khonghat", "09/07/2023", 100, 5);
        ContentValues cv56 = createContentValuesProduct("Dưa lê", "100% không thuôc trừ sâu", 12000, "duale", "09/07/2023", 100, 5);
        ContentValues cv57 = createContentValuesProduct("Dưa lê bạch kim", "100% không thuôc trừ sâu", 10000, "duale_bachkim", "09/07/2023", 100, 5);
        ContentValues cv58 = createContentValuesProduct("Dưa lưới", "100% không thuôc trừ sâu", 16000, "dualuoi", "09/07/2023", 100, 5);
        ContentValues cv59 = createContentValuesProduct("Dừa xiêm", "100% không thuôc trừ sâu", 12000, "duaxiem", "09/07/2023", 100, 5);
        ContentValues cv60 = createContentValuesProduct("Kiwi vàng", "100% không thuôc trừ sâu", 12000, "kiwi_vang", "09/07/2023", 100, 5);
        ContentValues cv61 = createContentValuesProduct("Kiwi xanh", "100% không thuôc trừ sâu", 10000, "kiwi_xanh", "09/07/2023", 100, 5);
        ContentValues cv62 = createContentValuesProduct("Lê đỏ vàng Nam Phi", "100% không thuôc trừ sâu", 16000, "le_dovang_namphi", "09/07/2023", 100, 5);
        ContentValues cv63 = createContentValuesProduct("Lê Hàn Quốc", "100% không thuôc trừ sâu", 12000, "le_hanquoc", "09/07/2023", 100, 5);
        ContentValues cv64 = createContentValuesProduct("Mận hồng đào", "100% không thuôc trừ sâu", 10000, "man_hongdao.jpg", "09/07/2023", 100, 5);
        ContentValues cv65 = createContentValuesProduct("Mận sữa", "100% không thuôc trừ sâu", 16000, "man_sua", "09/07/2023", 100, 5);
        ContentValues cv66 = createContentValuesProduct("Mận đỏ Thái", "100% không thuôc trừ sâu", 12000, "mando_thai", "09/07/2023", 100, 5);
        ContentValues cv67 = createContentValuesProduct("Mít Thái", "100% không thuôc trừ sâu", 10000, "mit_thai", "09/07/2023", 100, 5);
        ContentValues cv68 = createContentValuesProduct("Nhãn xuồng cơm vàng", "100% không thuôc trừ sâu", 16000, "nhanxuong_comvang", "09/07/2023", 100, 5);
        ContentValues cv69 = createContentValuesProduct("Nho xanh không hạt của Úc", "100% không thuôc trừ sâu", 12000, "nho_xanhkhonghatuc", "09/07/2023", 100, 5);
        ContentValues cv70 = createContentValuesProduct("Nho đen không hạt Chile", "100% không thuôc trừ sâu", 12000, "nhoden_khonghat_chile", "09/07/2023", 100, 5);
        ContentValues cv71 = createContentValuesProduct("Nho đen không hạt Úc", "100% không thuôc trừ sâu", 10000, "nhoden_khonghat_uc", "09/07/2023", 100, 5);
        ContentValues cv72 = createContentValuesProduct("Nho đỏ không hạt Úc", "100% không thuôc trừ sâu", 16000, "nhodo_khonghat_uc", "09/07/2023", 100, 5);
        ContentValues cv73 = createContentValuesProduct("Táo Breeze Newzealand", "100% không thuôc trừ sâu", 12000, "tao_breeze_newzealand", "09/07/2023", 100, 5);
        ContentValues cv74 = createContentValuesProduct("Táo Dazzle Newzealand", "100% không thuôc trừ sâu", 10000, "tao_dazzle_newzealand", "09/07/2023", 100, 5);
        ContentValues cv75 = createContentValuesProduct("Táo Envy Newzealand", "100% không thuôc trừ sâu", 16000, "tao_envy_newzealand", "09/07/2023", 100, 5);
        ContentValues cv76 = createContentValuesProduct("Táo Fuij", "100% không thuôc trừ sâu", 12000, "tao_fuji", "09/07/2023", 100, 5);
        ContentValues cv77 = createContentValuesProduct("Táo Jazz Newzealand", "100% không thuôc trừ sâu", 10000, "tao_jazz_newzealand", "09/07/2023", 100, 5);
        ContentValues cv78 = createContentValuesProduct("Táo Juliet", "100% không thuôc trừ sâu", 16000, "tao_juliet", "09/07/2023", 100, 5);
        ContentValues cv79 = createContentValuesProduct("Táo Rockit Newzealand", "100% không thuôc trừ sâu", 12000, "tao_rockit_newzeland", "09/07/2023", 100, 5);
        ContentValues cv80 = createContentValuesProduct("Táo Tessa Hà Lan", "100% không thuôc trừ sâu", 12000, "tao_tessa_halan", "09/07/2023", 100, 5);
        ContentValues cv81 = createContentValuesProduct("Vải thiều", "100% không thuôc trừ sâu", 10000, "vaithieu", "09/07/2023", 100, 5);
        ContentValues cv82 = createContentValuesProduct("Bưởi 5 roi", "100% không thuôc trừ sâu", 16000, "buoi_5roi", "09/07/2023", 100, 5);
        ContentValues cv83 = createContentValuesProduct("Bưởi da xanh", "100% không thuôc trừ sâu", 12000, "buoi_daxanh", "09/07/2023", 100, 5);
        //Thực phẩm đông lạnh
        ContentValues cv84 = createContentValuesProduct("Viên bò", "Sản phẩm được chế biến từ thành phần thịt bò tươi ngon, đảm bảo. Sản phẩm không chứa hóa chất bảo quản, chất phụ gia ảnh hưởng đến sức khỏe người tiêu dùng.", 10000, "vien_bo", "09/07/2023", 100, 6);
        ContentValues cv85 = createContentValuesProduct("Viên cá", "Sản phẩm được chế biến từ thành phần thịt cá tươi ngon, đảm bảo. Sản phẩm không chứa hóa chất bảo quản, chất phụ gia ảnh hưởng đến sức khỏe người tiêu dùng", 16000, "vien_ca", "09/07/2023", 100, 6);
        ContentValues cv86 = createContentValuesProduct("Viên cá loại 2", "Sản phẩm được chế biến từ thành phần thịt cá tươi ngon, đảm bảo. Sản phẩm không chứa hóa chất bảo quản, chất phụ gia ảnh hưởng đến sức khỏe người tiêu dùng", 12000, "vien_ca2", "09/07/2023", 100, 6);
        ContentValues cv87 = createContentValuesProduct("Viên cá nhân xốt", "Sản phẩm được chế biến từ thành phần thịt cá tươi ngon, đảm bảo. Sản phẩm không chứa hóa chất bảo quản, chất phụ gia ảnh hưởng đến sức khỏe người tiêu dùng", 10000, "cachua", "09/07/2023", 100, 6);
        ContentValues cv88 = createContentValuesProduct("Viên chả cá", "Sản phẩm được chế biến từ thành phần thịt cá tươi ngon, đảm bảo. Sản phẩm không chứa hóa chất bảo quản, chất phụ gia ảnh hưởng đến sức khỏe người tiêu dùng", 16000, "vien_chaca", "09/07/2023", 100, 6);
        ContentValues cv89 = createContentValuesProduct("Viên đậu hũ", "Sản phẩm được chế biến từ thành phần đậu hũ, đảm bảo. Sản phẩm không chứa hóa chất bảo quản, chất phụ gia ảnh hưởng đến sức khỏe người tiêu dùng", 12000, "vien_dauhu", "09/07/2023", 100, 6);
        ContentValues cv90 = createContentValuesProduct("Viên thả lẩu loại 2", "Sản phẩm được chế biến từ thành phần thịt, cá, đậu hũ,... tươi ngon, đảm bảo. Sản phẩm không chứa hóa chất bảo quản, chất phụ gia ảnh hưởng đến sức khỏe người tiêu dùng", 12000, "vien_thailau2", "09/07/2023", 100, 6);
        ContentValues cv91 = createContentValuesProduct("Viên thả lẩu", "Sản phẩm được chế biến từ thành phần thịt, cá, đậu hũ,... ngon, đảm bảo. Sản phẩm không chứa hóa chất bảo quản, chất phụ gia ảnh hưởng đến sức khỏe người tiêu dùng", 10000, "vien_thalau", "09/07/2023", 100, 6);
        ContentValues cv92 = createContentValuesProduct("Viên thanh cua", "Sản phẩm được chế biến từ thành phần thịt cua tươi ngon, đảm bảo. Sản phẩm không chứa hóa chất bảo quản, chất phụ gia ảnh hưởng đến sức khỏe người tiêu dùng", 16000, "bapcaitrang", "09/07/2023", 100, 6);
        ContentValues cv93 = createContentValuesProduct("Viên tôm", "Sản phẩm được chế biến từ thành phần thịt tôm tươi ngon, đảm bảo. Sản phẩm không chứa hóa chất bảo quản, chất phụ gia ảnh hưởng đến sức khỏe người tiêu dùng", 12000, "bapcaixanh", "09/07/2023", 100, 6);
        ContentValues cv94 = createContentValuesProduct("Viên tôm loại 2", "Sản phẩm được chế biến từ thành phần thịt tôm tươi ngon, đảm bảo. Sản phẩm không chứa hóa chất bảo quản, chất phụ gia ảnh hưởng đến sức khỏe người tiêu dùng", 10000, "bap", "09/07/2023", 100, 6);
        //Thực phẩm đã chế biến
        ContentValues cv95 = createContentValuesProduct("Chả giò sốt phô mai", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 16000, "chagio_hssotphomai", "09/07/2023", 100, 7);
        ContentValues cv96 = createContentValuesProduct("Chả giò thịt", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 12000, "chagio_thit", "09/07/2023", 100, 7);
        ContentValues cv97 = createContentValuesProduct("Chả giò Tứ Xuyên", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 10000, "chagio_tuxuyen", "09/07/2023", 100, 7);
        ContentValues cv98 = createContentValuesProduct("Cơm chiên cá mặn", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 16000, "comchien_caman", "09/07/2023", 100, 7);
        ContentValues cv99 = createContentValuesProduct("Cơm chiên hải sản", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 12000, "comchien_haisan", "09/07/2023", 100, 7);
        ContentValues cv100 = createContentValuesProduct("Mandu kim chi gà", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 12000, "mandu_kimchiga", "09/07/2023", 100, 7);
        ContentValues cv101 = createContentValuesProduct("Mandu thịt", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 10000, "mandu_thit", "09/07/2023", 100, 7);
        ContentValues cv102 = createContentValuesProduct("Mandu thịt bắp", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 16000, "mandu_thitbap", "09/07/2023", 100, 7);
        ContentValues cv103 = createContentValuesProduct("Mandu tôm", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 12000, "mandu_tom", "09/07/2023", 100, 7);
        ContentValues cv104 = createContentValuesProduct("Mì ý bò bằm", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 10000, "miy_bobam", "09/07/2023", 100, 7);
        ContentValues cv105 = createContentValuesProduct("Mì ý cá hồi", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 16000, "miy_cahoi", "09/07/2023", 100, 7);
        ContentValues cv106 = createContentValuesProduct("Pizza bò bằm", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 12000, "pizza_bobam", "09/07/2023", 100, 7);
        ContentValues cv107 = createContentValuesProduct("Pizza hải sản", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 10000, "pizza_haisan", "09/07/2023", 100, 7);
        ContentValues cv108 = createContentValuesProduct("Pizza phô mai", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 16000, "pizza_phomai", "09/07/2023", 100, 7);
        ContentValues cv109 = createContentValuesProduct("Pizza xúc xích", "Sản phẩm đã được sơ chế sẵn và đóng gói kín đáo, dễ bảo quản", 12000, "pizza_xucxich", "09/07/2023", 100, 7);
        //Dau an gia vi
        ContentValues cv110 = createContentValuesProduct("Dầu đậu nành Simply", "100% nguồn gốc thiên nhiên thích hợp cho mọi hoạt động chế biến thực phẩm", 12000, "daudaunanh_simply", "09/07/2023", 100, 8);
        ContentValues cv111 = createContentValuesProduct("Dầu đậu nành Tường An", "100% nguồn gốc thiên nhiên thích hợp cho mọi hoạt động chế biến thực phẩm", 10000, "daudaunanh_tuongan", "09/07/2023", 100, 8);
        ContentValues cv113 = createContentValuesProduct("Dầu gạo Simple", "100% nguồn gốc thiên nhiên thích hợp cho mọi hoạt động chế biến thực phẩm", 16000, "daugao_nguyenchat_simply", "09/07/2023", 100, 8);
        ContentValues cv114 = createContentValuesProduct("Dầu mè Tường An", "100% nguồn gốc thiên nhiên thích hợp cho mọi hoạt động chế biến thực phẩm", 10000, "daugaolut_simply", "09/07/2023", 100, 8);
        ContentValues cv115 = createContentValuesProduct("Dầu oliu Tường An", "100% nguồn gốc thiên nhiên thích hợp cho mọi hoạt động chế biến thực phẩm", 16000, "dauoliu_nguyenchat_tuongan", "09/07/2023", 100, 8);
        ContentValues cv116 = createContentValuesProduct("Dầu oliu Olivoila", "100% nguồn gốc thiên nhiên thích hợp cho mọi hoạt động chế biến thực phẩm", 12000, "dauolive_olivoila", "09/07/2023", 100, 8);
        ContentValues cv117 = createContentValuesProduct("Dầu oliu Olivoila Pomace", "100% nguồn gốc thiên nhiên thích hợp cho mọi hoạt động chế biến thực phẩm", 10000, "dauolive_olivoilapomace", "09/07/2023", 100, 8);
        ContentValues cv118 = createContentValuesProduct("Dầu đậu phộng Tường An", "100% nguồn gốc thiên nhiên thích hợp cho mọi hoạt động chế biến thực phẩm", 16000, "dauphong_tuongan", "09/07/2023", 100, 8);
        //Tương ớt tương đen
        ContentValues cv119 = createContentValuesProduct("Tương ớt Chinsu", "Giúp món ăn trở nên ngon miệng hơn", 16000, "tuongot_chinsu", "09/07/2023", 100, 8);
        ContentValues cv120 = createContentValuesProduct("Tương ớt Cholimex chai thủy tinh", "Giúp món ăn trở nên ngon miệng hơn", 13000, "tuongot_cholimex_chaithuytinh", "09/07/2023", 100, 8);
        ContentValues cv121 = createContentValuesProduct("Tương ớt Cholimex nắp ngược", "Giúp món ăn trở nên ngon miệng hơn", 14000, "tuongot_cholimex_napnguoc", "09/07/2023", 100, 8);
        ContentValues cv122 = createContentValuesProduct("Tương ớt chua ngọt Cholimex", "Giúp món ăn trở nên ngon miệng hơn", 10000, "tuongot_chuangot_cholimex_chaithuytinh", "09/07/2023", 100, 8);
        ContentValues cv123 = createContentValuesProduct("Tương ớt Nam Dương", "Giúp món ăn trở nên ngon miệng hơn", 14000, "tuongot_dacbiet_namduong", "09/07/2023", 100, 8);
        ContentValues cv124 = createContentValuesProduct("Tương ớt Nam Dương siêu cay ", "Giúp món ăn trở nên ngon miệng hơn", 12000, "tuongot_namduong", "09/07/2023", 100, 8);
        ContentValues cv125 = createContentValuesProduct("Tương ớt Cholimex siêu cay", "Giúp món ăn trở nên ngon miệng hơn", 15000, "tuongot_sieucay_cholimex", "09/07/2023", 100, 8);
        //Nuoc tương
        ContentValues cv126 = createContentValuesProduct("Nước tương Maggi", "Bữa ăn thêm đậm vị", 18000, "nuoctuong_haohang_maggi", "09/07/2023", 100, 8);
        ContentValues cv127 = createContentValuesProduct("Nước tương Maggi đậm đặc", "Bữa ăn thêm đậm vị", 15000, "nuoctuong_maggi_damdac", "09/07/2023", 100, 8);
        ContentValues cv128 = createContentValuesProduct("Nước tương đậu nành Maggi", "Bữa ăn thêm đậm vị", 17000, "nuoctuong_maggi_daunanh", "09/07/2023", 100, 8);
        ContentValues cv129 = createContentValuesProduct("Nước tương Nam Dương", "Bữa ăn thêm đậm vị", 12000, "nuoctuong_namduong_daunanhlenmen", "09/07/2023", 100, 8);
        ContentValues cv130 = createContentValuesProduct("Nước tương Ajinomoto", "Bữa ăn thêm đậm vị", 18000, "nuoctuong_phusi_ajinomoto_pet", "09/07/2023", 100, 8);
        ContentValues cv131 = createContentValuesProduct("Nước tương Tam Thái Tử", "Bữa ăn thêm đậm vị", 15000, "nuoctuong_tamthaitunhica", "09/07/2023", 100, 8);
        ContentValues cv132 = createContentValuesProduct("Nước tương Nam Dương thượng hạng", "Bữa ăn thêm đậm vị", 17000, "nuoctuong_thuonghang_namduong", "09/07/2023", 100, 8);
        //Nuoc mắm
        ContentValues cv133 = createContentValuesProduct("Nước mắm cá cơm Hạnh Phúc", "Tinh hoa nước mắm Việt, thơm ngon tới giọt cuối cùng", 17000, "nuocmam_hanhphuc", "09/07/2023", 100, 8);
        ContentValues cv134 = createContentValuesProduct("Nước mắm Hồng Hạnh", "Tinh hoa nước mắm Việt, thơm ngon tới giọt cuối cùng", 18000, "nuocmam_honghanh_sieuhang", "09/07/2023", 100, 8);
        ContentValues cv135 = createContentValuesProduct("Nước mắm Liên Thành", "Tinh hoa nước mắm Việt, thơm ngon tới giọt cuối cùng", 16000, "nuocmam_lienthanh_nhandong", "09/07/2023", 100, 8);
        ContentValues cv136 = createContentValuesProduct("Nước Liên Thành thượng hạng", "Tinh hoa nước mắm Việt, thơm ngon tới giọt cuối cùng", 20000, "nuocmam_lienthanh_nhanvang", "09/07/2023", 100, 8);
        ContentValues cv137 = createContentValuesProduct("Nước mắm Nam Ngư", "Tinh hoa nước mắm Việt, thơm ngon tới giọt cuối cùng", 15000, "nuocmam_namngu3in1", "09/07/2023", 100, 8);
        ContentValues cv138 = createContentValuesProduct("Nước mắn Nam Ngư đậm đặc", "Tinh hoa nước mắm Việt, thơm ngon tới giọt cuối cùng", 17000, "nuocmam_namngu_phuquoc_damdac", "09/07/2023", 100, 8);
        //Muối
        ContentValues cv139 = createContentValuesProduct("Muối ớt chanh dây", "Sản phẩm không chứa hóa chất hay chất bảo quản, đảm bảo an toàn cho sức khỏe của người dùng.", 14000, "muoiot_chanhday_dhfood", "09/07/2023", 100, 8);
        ContentValues cv140 = createContentValuesProduct("Muối ớt chanh rừng", "Sản phẩm không chứa hóa chất hay chất bảo quản, đảm bảo an toàn cho sức khỏe của người dùng.", 15000, "muoiot_chanhgung_dhfood", "09/07/2023", 100, 8);
        ContentValues cv141 = createContentValuesProduct("Muối ớt hột", "Sản phẩm không chứa hóa chất hay chất bảo quản, đảm bảo an toàn cho sức khỏe của người dùng.", 13000, "muoiot_hot_dhfood", "09/07/2023", 100, 8);
        ContentValues cv142 = createContentValuesProduct("Muối ớt sấy", "Sản phẩm không chứa hóa chất hay chất bảo quản, đảm bảo an toàn cho sức khỏe của người dùng.", 16000, "muoiot_say_dhfood", "09/07/2023", 100, 8);
        ContentValues cv143 = createContentValuesProduct("Muối sấy IOD ", "Sản phẩm không chứa hóa chất hay chất bảo quản, đảm bảo an toàn cho sức khỏe của người dùng.", 11000, "muoisay_iodvietsalt", "09/07/2023", 100, 8);
        ContentValues cv144 = createContentValuesProduct("Muối tiêu Tây Ninh ", "Sản phẩm không chứa hóa chất hay chất bảo quản, đảm bảo an toàn cho sức khỏe của người dùng.", 14000, "muoitieu_dhfood", "09/07/2023", 100, 8);
        ContentValues cv145 = createContentValuesProduct("Muối tiêu lá chanh", "Sản phẩm không chứa hóa chất hay chất bảo quản, đảm bảo an toàn cho sức khỏe của người dùng.", 15000, "muoitieu_lachanh_dhfood", "09/07/2023", 100, 8);
        ContentValues cv146 = createContentValuesProduct("Muối tinh IOD", "Sản phẩm không chứa hóa chất hay chất bảo quản, đảm bảo an toàn cho sức khỏe của người dùng.", 13000, "muoitinh_iod", "09/07/2023", 100, 8);
        ContentValues cv147 = createContentValuesProduct("Muối tinh Vifon", "Sản phẩm không chứa hóa chất hay chất bảo quản, đảm bảo an toàn cho sức khỏe của người dùng.", 9000, "muoitinh_saycaocap_vifon", "09/07/2023", 100, 8);
        ContentValues cv148 = createContentValuesProduct("Muối ớt tôm", "Sản phẩm không chứa hóa chất hay chất bảo quản, đảm bảo an toàn cho sức khỏe của người dùng.", 8000, "muoitom_ot_dhfood", "09/07/2023", 100, 8);
        //Hat nem
        ContentValues cv149 = createContentValuesProduct("Hạt nêm Ajingon", "Làm từ thịt heo nguyên chất, giữ được hương vị đậm đà của thịt - Ngọt canh xương ống, đậm đà thịt thăn", 32000, "hatnem_ajingon_viheo", "09/07/2023", 100, 8);
        ContentValues cv150 = createContentValuesProduct("Hạt nêm Knorr nấm hương 170g", "Làm từ thịt heo nguyên chất, giữ được hương vị đậm đà của thịt - Ngọt canh xương ống, đậm đà thịt thăn", 32000, "hatnem_chay_knorr_namhuong", "09/07/2023", 100, 8);
        ContentValues cv151 = createContentValuesProduct("Hạt nêm Knorr nấm hương 380g", "Làm từ thịt heo nguyên chất, giữ được hương vị đậm đà của thịt - Ngọt canh xương ống, đậm đà thịt thăn", 32000, "hatnem_knorr_namhuongorganic", "09/07/2023", 100, 8);
        ContentValues cv152 = createContentValuesProduct("Hạt nêm Knorr thịt thăn", "Làm từ thịt heo nguyên chất, giữ được hương vị đậm đà của thịt - Ngọt canh xương ống, đậm đà thịt thăn", 32000, "hatnem_knorr_thitthan", "09/07/2023", 100, 8);
        ContentValues cv153 = createContentValuesProduct("Hạt nêm Knorr thịt thăn xương tủy", "Làm từ thịt heo nguyên chất, giữ được hương vị đậm đà của thịt - Ngọt canh xương ống, đậm đà thịt thăn", 32000, "hatnem_knorr_thitthanxuongongtuy", "09/07/2023", 100, 8);
        ContentValues cv154 = createContentValuesProduct("Hạt nêm Meizan", "Làm từ thịt heo nguyên chất, giữ được hương vị đậm đà của thịt - Ngọt canh xương ống, đậm đà thịt thăn", 32000, "hatnem_meizan_heo", "09/07/2023", 100, 8);
        ContentValues cv155 = createContentValuesProduct("Hạt nêm Nuptune 4in1", "Làm từ thịt heo nguyên chất, giữ được hương vị đậm đà của thịt - Ngọt canh xương ống, đậm đà thịt thăn", 32000, "hatnem_neptune_4in1_heo", "09/07/2023", 100, 8);
        ContentValues cv156 = createContentValuesProduct("Hạt nêm Nuptune vị nấm", "Làm từ thịt heo nguyên chất, giữ được hương vị đậm đà của thịt - Ngọt canh xương ống, đậm đà thịt thăn", 32000, "hatnem_neptune_nam", "09/07/2023", 100, 8);
        //Đường
        ContentValues cv157 = createContentValuesProduct("Đường ăn kiêng Equalstevia", "Đường Tinh Luyện được sản xuất từ giống mía tốt nhất, không sử dụng hóa chất tẩy trắng, mang đến vị ngon ngọt, hấp dẫn.", 22000, "duongankien_equalstevia", "09/07/2023", 100, 8);
        ContentValues cv158 = createContentValuesProduct("Đường ăn kiêng Isomalt", "Đường Tinh Luyện được sản xuất từ giống mía tốt nhất, không sử dụng hóa chất tẩy trắng, mang đến vị ngon ngọt, hấp dẫn.", 20000, "duongankieng_isomalt_bienhoa", "09/07/2023", 100, 8);
        ContentValues cv159 = createContentValuesProduct("Đường hữu cơ", "Đường Tinh Luyện được sản xuất từ giống mía tốt nhất, không sử dụng hóa chất tẩy trắng, mang đến vị ngon ngọt, hấp dẫn.", 19000, "duonghuuco_organic_bienhoa", "09/07/2023", 100, 8);
        ContentValues cv160 = createContentValuesProduct("Đường mía thượng hạng", "Đường Tinh Luyện được sản xuất từ giống mía tốt nhất, không sử dụng hóa chất tẩy trắng, mang đến vị ngon ngọt, hấp dẫn.", 22000, "duongmia_thuonghang_bienhoa", "09/07/2023", 100, 8);
        ContentValues cv161 = createContentValuesProduct("Đường Organic", "Đường Tinh Luyện được sản xuất từ giống mía tốt nhất, không sử dụng hóa chất tẩy trắng, mang đến vị ngon ngọt, hấp dẫn.", 20000, "duongorganic_bienhoa", "09/07/2023", 100, 8);
        ContentValues cv162 = createContentValuesProduct("Đường phèn", "Đường Tinh Luyện được sản xuất từ giống mía tốt nhất, không sử dụng hóa chất tẩy trắng, mang đến vị ngon ngọt, hấp dẫn.", 21000, "duongphen_tsu", "09/07/2023", 100, 8);
        ContentValues cv163 = createContentValuesProduct("Đường phèn vàng", "Đường Tinh Luyện được sản xuất từ giống mía tốt nhất, không sử dụng hóa chất tẩy trắng, mang đến vị ngon ngọt, hấp dẫn.", 19000, "duongphenvang_bienhoagold", "09/07/2023", 100, 8);
        ContentValues cv164 = createContentValuesProduct("Đường tinh luyện", "Đường Tinh Luyện được sản xuất từ giống mía tốt nhất, không sử dụng hóa chất tẩy trắng, mang đến vị ngon ngọt, hấp dẫn.", 20000, "duongtinhluyen_dacbiet_bienhoa", "09/07/2023", 100, 8);
        ContentValues cv165 = createContentValuesProduct("Đường trắng Nasuth", "Đường Tinh Luyện được sản xuất từ giống mía tốt nhất, không sử dụng hóa chất tẩy trắng, mang đến vị ngon ngọt, hấp dẫn.", 22000, "duongtrang_nasuth", "09/07/2023", 100, 8);
        ContentValues cv166 = createContentValuesProduct("Đường vàng Nasuth", "Đường Tinh Luyện được sản xuất từ giống mía tốt nhất, không sử dụng hóa chất tẩy trắng, mang đến vị ngon ngọt, hấp dẫn.", 18000, "duongvang_nasunatural", "09/07/2023", 100, 8);
        //9.Gạo, đậu, bột
        //9.1 Gạo
        ContentValues cv167 = createContentValuesProduct("Gạo hương lài", "Gạo sạch đảm bảo an toàn, với vị dẻo, vị thơm đặc trưng sẽ kích thích vị giác và đánh thức mọi giác quan thưởng thức khác trong bữa cơm.", 36000, "gao_huonglai", "09/07/2023", 100, 9);
        ContentValues cv168 = createContentValuesProduct("Gạo làng ta", "Gạo sạch đảm bảo an toàn, với vị dẻo, vị thơm đặc trưng sẽ kích thích vị giác và đánh thức mọi giác quan thưởng thức khác trong bữa cơm.", 42000, "gao_langta", "09/07/2023", 100, 9);
        ContentValues cv169 = createContentValuesProduct("Gạo lứt đen", "Gạo sạch đảm bảo an toàn, với vị dẻo, vị thơm đặc trưng sẽ kích thích vị giác và đánh thức mọi giác quan thưởng thức khác trong bữa cơm.", 40000, "gao_lutden", "09/07/2023", 100, 9);
        ContentValues cv170 = createContentValuesProduct("Gạo lứt huyết rồng ", "Gạo sạch đảm bảo an toàn, với vị dẻo, vị thơm đặc trưng sẽ kích thích vị giác và đánh thức mọi giác quan thưởng thức khác trong bữa cơm.", 33000, "gao_luthrong", "09/07/2023", 100, 9);
        ContentValues cv171 = createContentValuesProduct("Gạo lứt nâu", "Gạo sạch đảm bảo an toàn, với vị dẻo, vị thơm đặc trưng sẽ kích thích vị giác và đánh thức mọi giác quan thưởng thức khác trong bữa cơm.", 42000, "gao_lutnau", "09/07/2023", 100, 9);
        ContentValues cv172 = createContentValuesProduct("Gạo nàng thơm", "Gạo sạch đảm bảo an toàn, với vị dẻo, vị thơm đặc trưng sẽ kích thích vị giác và đánh thức mọi giác quan thưởng thức khác trong bữa cơm.", 33000, "gao_nangthom", "09/07/2023", 100, 9);
        ContentValues cv173 = createContentValuesProduct("Gạo ST25", "Gạo sạch đảm bảo an toàn, với vị dẻo, vị thơm đặc trưng sẽ kích thích vị giác và đánh thức mọi giác quan thưởng thức khác trong bữa cơm.", 42000, "gao_st25", "09/07/2023", 100, 9);
        ContentValues cv174 = createContentValuesProduct("Gạo ST25 dẻo", "Gạo sạch đảm bảo an toàn, với vị dẻo, vị thơm đặc trưng sẽ kích thích vị giác và đánh thức mọi giác quan thưởng thức khác trong bữa cơm.", 36000, "gao_st25deo", "09/07/2023", 100, 9);
        //9.2 Đậu
        ContentValues cv175 = createContentValuesProduct("Đậu đen", "Loại đậu có thành phần từ thiên nhiên, an toàn cho sức khỏe. Hạt đậu lớn tròn, vỏ căng tròn, bóng hạt.", 26000, "dau_den", "09/07/2023", 100, 9);
        ContentValues cv176 = createContentValuesProduct("Đậu đen xanh long", "Loại đậu có thành phần từ thiên nhiên, an toàn cho sức khỏe. Hạt đậu lớn tròn, vỏ căng tròn, bóng hạt.", 23000, "dau_denxanhlong", "09/07/2023", 100, 9);
        ContentValues cv177 = createContentValuesProduct("Đậu đỏ", "Loại đậu có thành phần từ thiên nhiên, an toàn cho sức khỏe. Hạt đậu lớn tròn, vỏ căng tròn, bóng hạt.", 21000, "dau_do", "09/07/2023", 100, 9);
        ContentValues cv178 = createContentValuesProduct("Đậu nành", "Loại đậu có thành phần từ thiên nhiên, an toàn cho sức khỏe. Hạt đậu lớn tròn, vỏ căng tròn, bóng hạt.", 21000, "dau_nanh", "09/07/2023", 100, 9);
        ContentValues cv179 = createContentValuesProduct("Đậu ngự", "Loại đậu có thành phần từ thiên nhiên, an toàn cho sức khỏe. Hạt đậu lớn tròn, vỏ căng tròn, bóng hạt.", 29000, "dau_ngu", "09/07/2023", 100, 9);
        ContentValues cv180 = createContentValuesProduct("Đậu phộng", "Loại đậu có thành phần từ thiên nhiên, an toàn cho sức khỏe. Hạt đậu lớn tròn, vỏ căng tròn, bóng hạt.", 25000, "dau_phong", "09/07/2023", 100, 9);
        ContentValues cv181 = createContentValuesProduct("Đậu trắng", "Loại đậu có thành phần từ thiên nhiên, an toàn cho sức khỏe. Hạt đậu lớn tròn, vỏ căng tròn, bóng hạt.", 26000, "dau_trang", "09/07/2023", 100, 9);
        ContentValues cv182 = createContentValuesProduct("Đậu trắng bi", "Loại đậu có thành phần từ thiên nhiên, an toàn cho sức khỏe. Hạt đậu lớn tròn, vỏ căng tròn, bóng hạt.", 28000, "dau_trangbi", "09/07/2023", 100, 9);
        ContentValues cv183 = createContentValuesProduct("Đậu xanh cà", "Loại đậu có thành phần từ thiên nhiên, an toàn cho sức khỏe. Hạt đậu lớn tròn, vỏ căng tròn, bóng hạt.", 22000, "dau_xanhca", "09/07/2023", 100, 9);
        ContentValues cv184 = createContentValuesProduct("Đậu xanh hạt", "Loại đậu có thành phần từ thiên nhiên, an toàn cho sức khỏe. Hạt đậu lớn tròn, vỏ căng tròn, bóng hạt.", 20000, "dau_xanhhat", "09/07/2023", 100, 9);
        //9.3 Bột
        ContentValues cv185 = createContentValuesProduct("Bột bánh xèo Meizan", "Sản phẩm đáp ứng tiêu chuẩn cao về an toàn vệ sinh thực phẩm, không chứa bromate, không chất tẩy trắng và được bổ sung vi chất dinh dưỡng sắt và kẽm.", 28000, "bot_banhxeo_meizan", "09/07/2023", 100, 9);
        ContentValues cv186 = createContentValuesProduct("Bột bánh xèo Tài Ký", "Sản phẩm đáp ứng tiêu chuẩn cao về an toàn vệ sinh thực phẩm, không chứa bromate, không chất tẩy trắng và được bổ sung vi chất dinh dưỡng sắt và kẽm.", 25000, "bot_banhxeo_taiky", "09/07/2023", 100, 9);
        ContentValues cv187 = createContentValuesProduct("Bột chiên giòn Meizan", "Sản phẩm đáp ứng tiêu chuẩn cao về an toàn vệ sinh thực phẩm, không chứa bromate, không chất tẩy trắng và được bổ sung vi chất dinh dưỡng sắt và kẽm.", 23000, "bot_chiengion_meizan", "09/07/2023", 100, 9);
        ContentValues cv188 = createContentValuesProduct("Bột chiên giòn Tài Ký", "Sản phẩm đáp ứng tiêu chuẩn cao về an toàn vệ sinh thực phẩm, không chứa bromate, không chất tẩy trắng và được bổ sung vi chất dinh dưỡng sắt và kẽm.", 28000, "bot_chiengion_taiky", "09/07/2023", 100, 9);
        ContentValues cv189 = createContentValuesProduct("Bột chiên xù Tài Ký", "Sản phẩm đáp ứng tiêu chuẩn cao về an toàn vệ sinh thực phẩm, không chứa bromate, không chất tẩy trắng và được bổ sung vi chất dinh dưỡng sắt và kẽm.", 25000, "bot_chienxu_taiky", "09/07/2023", 100, 9);
        ContentValues cv190 = createContentValuesProduct("Bột mì làm bánh bông lan", "Sản phẩm đáp ứng tiêu chuẩn cao về an toàn vệ sinh thực phẩm, không chứa bromate, không chất tẩy trắng và được bổ sung vi chất dinh dưỡng sắt và kẽm.", 23000, "bot_milambanhbonglan_meizan", "09/07/2023", 100, 9);
        ContentValues cv191 = createContentValuesProduct("Bột mì làm bánh bao", "Sản phẩm đáp ứng tiêu chuẩn cao về an toàn vệ sinh thực phẩm, không chứa bromate, không chất tẩy trắng và được bổ sung vi chất dinh dưỡng sắt và kẽm.", 28000, "bot_milambanhmy_meizan", "09/07/2023", 100, 9);
        ContentValues cv192 = createContentValuesProduct("Bột năng Meizan", "Sản phẩm đáp ứng tiêu chuẩn cao về an toàn vệ sinh thực phẩm, không chứa bromate, không chất tẩy trắng và được bổ sung vi chất dinh dưỡng sắt và kẽm.", 25000, "bot_nang_meizan", "09/07/2023", 100, 9);
        ContentValues cv193 = createContentValuesProduct("Bột năng Tài Ký", "Sản phẩm đáp ứng tiêu chuẩn cao về an toàn vệ sinh thực phẩm, không chứa bromate, không chất tẩy trắng và được bổ sung vi chất dinh dưỡng sắt và kẽm.", 23000, "bot_nang_taiky", "09/07/2023", 100, 9);
        //10. Mì, miến, phở, nui
        //Mì
        ContentValues cv194 = createContentValuesProduct("Mì trứng cao cấp Meizan", "Sản phẩm được đóng gói kỹ lưỡng, thuận lợi cho bạn mang đi du lịch hay dã ngoại cùng bạn bè, người thân.", 18000, "mi_trungcaocap_meizan", "09/07/2023", 100, 10);
        ContentValues cv195 = createContentValuesProduct("Mì trứng cao cấp Safoco", "Sản phẩm được đóng gói kỹ lưỡng, thuận lợi cho bạn mang đi du lịch hay dã ngoại cùng bạn bè, người thân.", 23000, "mi_trungcaocap_safoco", "09/07/2023", 100, 10);
        //Miến
        ContentValues cv196 = createContentValuesProduct("Miến khô Ottogi", "Sản phẩm được đóng gói kỹ lưỡng, thuận lợi cho bạn mang đi du lịch hay dã ngoại cùng bạn bè, người thân.", 28000, "mien_kho_ottogi", "09/07/2023", 100, 10);
        //Nui
        ContentValues cv197 = createContentValuesProduct("Nui cao cấp Mezian", "Sản phẩm được đóng gói kỹ lưỡng, thuận lợi cho bạn mang đi du lịch hay dã ngoại cùng bạn bè, người thân.", 18000, "nui_caocap_mezian", "09/07/2023", 100, 10);
        ContentValues cv198 = createContentValuesProduct("Nui gạo cao cấp Mezian", "Sản phẩm được đóng gói kỹ lưỡng, thuận lợi cho bạn mang đi du lịch hay dã ngoại cùng bạn bè, người thân.", 23000, "nui_gao_cao_cap_mezian", "09/07/2023", 100, 10);
        ContentValues cv200 = createContentValuesProduct("Nui hình tôm Safoco", "Sản phẩm được đóng gói kỹ lưỡng, thuận lợi cho bạn mang đi du lịch hay dã ngoại cùng bạn bè, người thân.", 48000, "nui_hinh_tom_kho_safoco", "09/07/2023", 100, 10);
        ContentValues cv201 = createContentValuesProduct("Nui ống lớn Safoco", "Sản phẩm được đóng gói kỹ lưỡng, thuận lợi cho bạn mang đi du lịch hay dã ngoại cùng bạn bè, người thân.", 38000, "nui_ong_lon_safoco", "09/07/2023", 100, 10);
        ContentValues cv202 = createContentValuesProduct("Nui xoắn Mezian cao cấp", "Sản phẩm được đóng gói kỹ lưỡng, thuận lợi cho bạn mang đi du lịch hay dã ngoại cùng bạn bè, người thân.", 27000, "nui_xoan_mezian_caocap", "09/07/2023", 100, 10);
        //11. Mi, pho, mien ăn liền (gói)
        //Mì
        ContentValues cv203 = createContentValuesProduct("Mì 3 miền bò", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 5000, "mi_bamienbo", "09/07/2023", 100, 11);
        ContentValues cv204 = createContentValuesProduct("Mì 3 miền tôm chua cay", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_bamientom", "09/07/2023", 100, 11);
        ContentValues cv205 = createContentValuesProduct("Mì Gấu đỏ gà", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 5000, "mi_gaudoga", "09/07/2023", 100, 11);
        ContentValues cv206 = createContentValuesProduct("Mì Gấu đỏ thịt bầm", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_gaudothitbam", "09/07/2023", 100, 11);
        ContentValues cv207 = createContentValuesProduct("Mì Gấu đỏ tôm chua cay", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 5000, "mi_gaudotomchuacay", "09/07/2023", 100, 11);
        ContentValues cv208 = createContentValuesProduct("Mì Goreng bò cay", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_gorengbocay", "09/07/2023", 100, 11);
        ContentValues cv209 = createContentValuesProduct("Mì Goreng cay nồng", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 5000, "mi_gorengcaynong", "09/07/2023", 100, 11);
        ContentValues cv210 = createContentValuesProduct("Mì Goreng đặc biệt", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_gorengdacbiet", "09/07/2023", 100, 11);
        ContentValues cv211 = createContentValuesProduct("Mì Hảo Hảo kim chi bò", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_hhkimchi", "09/07/2023", 100, 11);
        ContentValues cv212 = createContentValuesProduct("Mì Hảo Hảo sa tế hành tím", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 5000, "mi_hhsatehanhtim", "09/07/2023", 100, 11);
        ContentValues cv213 = createContentValuesProduct("Mì Hảo Hảo sườn heo phi tỏi", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_hhsuonheophitoi", "09/07/2023", 100, 11);
        ContentValues cv214 = createContentValuesProduct("Mì Hảo Hảo tôm chua cay", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 5000, "mi_hhtomchua", "09/07/2023", 100, 11);
        ContentValues cv215 = createContentValuesProduct("Mì Hảo Hảo tôm chua cay ly", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_hhtomchualy", "09/07/2023", 100, 11);
        ContentValues cv216 = createContentValuesProduct("Mì Hảo Hảo tomyum ly", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 5000, "mi_hhtomyumly", "09/07/2023", 100, 11);
        ContentValues cv217 = createContentValuesProduct("Mì Hảo Hảo xào vị tôm", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_hhxaotom", "09/07/2023", 100, 11);
        ContentValues cv218 = createContentValuesProduct("Mì hoành thánh", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 5000, "mi_hoanhthanh", "09/07/2023", 100, 11);
        ContentValues cv219 = createContentValuesProduct("Mì Mama thịt heo bầm", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_mamathitheobam", "09/07/2023", 100, 11);
        ContentValues cv220 = createContentValuesProduct("Mì Modern rêu cua ly", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 5000, "mi_modern_rieucualy", "09/07/2023", 100, 11);
        ContentValues cv221 = createContentValuesProduct("Mì Modern thịt bầm ly", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_modern_thitbamly", "09/07/2023", 100, 11);
        ContentValues cv222 = createContentValuesProduct("Mì Modern tôm chua cay ly", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 5000, "mi_modern_tomchuacayly", "09/07/2023", 100, 11);
        ContentValues cv223 = createContentValuesProduct("Mì Omachi xốt bò hầm", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_omachi_boham", "09/07/2023", 100, 11);
        ContentValues cv224 = createContentValuesProduct("Mì Omachi xốt bò hầm ly", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_omachi_bohamly", "09/07/2023", 100, 11);
        ContentValues cv225 = createContentValuesProduct("Mì Omachi sốt Spaghetti ", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 5000, "mi_omachi_spaghetti", "09/07/2023", 100, 11);
        ContentValues cv226 = createContentValuesProduct("Mì Omachi sườn hầm ngũ quả", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 6000, "mi_omachi_suonham", "09/07/2023", 100, 11);
        ContentValues cv227 = createContentValuesProduct("Mì Omachi sườn hầm ly", "Sản phẩm với từng sợi mì dai, mềm hòa quyện trong nước dùng đậm đà, thơm ngon của hương vị tôm chua cay đặc trưng.", 5000, "mi_omachi_suonhamly", "09/07/2023", 100, 11);
                //Pho
        ContentValues cv228 = createContentValuesProduct("Phở Cung đình vị bò", "Phở thơm ngon, bổ dưỡng, được sản xuất dựa trên các tiêu chuẩn và công nghệ hiện đại dưới sự hỗ trợ và giám sát liên tục, tỉ mỉ.", 16000, "pho_cungdinh_bo", "09/07/2023", 100, 11);
        ContentValues cv229 = createContentValuesProduct("Phở Cung đình vị gà", "Phở thơm ngon, bổ dưỡng, được sản xuất dựa trên các tiêu chuẩn và công nghệ hiện đại dưới sự hỗ trợ và giám sát liên tục, tỉ mỉ.", 16000, "pho_cungdinh_ga", "09/07/2023", 100, 11);
        ContentValues cv230 = createContentValuesProduct("Phở Đệ Nhất vị bò", "Phở thơm ngon, bổ dưỡng, được sản xuất dựa trên các tiêu chuẩn và công nghệ hiện đại dưới sự hỗ trợ và giám sát liên tục, tỉ mỉ.", 16000, "pho_denhat_bo", "09/07/2023", 100, 11);
        ContentValues cv231 = createContentValuesProduct("Phở Đệ Nhất vị gà", "Phở thơm ngon, bổ dưỡng, được sản xuất dựa trên các tiêu chuẩn và công nghệ hiện đại dưới sự hỗ trợ và giám sát liên tục, tỉ mỉ.", 16000, "pho_denhat_ga", "09/07/2023", 100, 11);
        ContentValues cv232 = createContentValuesProduct("Phở trộn Đệ Nhất thập cẩm cay", "Phở thơm ngon, bổ dưỡng, được sản xuất dựa trên các tiêu chuẩn và công nghệ hiện đại dưới sự hỗ trợ và giám sát liên tục, tỉ mỉ.", 16000, "pho_denhat_thamcamcay", "09/07/2023", 100, 11);
        ContentValues cv233 = createContentValuesProduct("Phở trộn Đệ Nhất bò ", "Phở thơm ngon, bổ dưỡng, được sản xuất dựa trên các tiêu chuẩn và công nghệ hiện đại dưới sự hỗ trợ và giám sát liên tục, tỉ mỉ.", 16000, "pho_tron_denhat_bo", "09/07/2023", 100, 11);
        //Mien
        ContentValues cv234 = createContentValuesProduct("Miến Phú Hương lẩu chua cay ", "Miến Phú Hương được bổ sung tinh bột đậu xanh giúp sợi miến dai, trơn, kết hợp với nước súp thơm ngon khó cưỡng.", 16000, "mien_phuhuong_lauchuacay", "09/07/2023", 100, 11);
        ContentValues cv235 = createContentValuesProduct("Miến Phú Hương sườn heo  ", "Miến Phú Hương được bổ sung tinh bột đậu xanh giúp sợi miến dai, trơn, kết hợp với nước súp thơm ngon khó cưỡng.", 16000, "mien_phuhuong_suonheo", "09/07/2023", 100, 11);
        ContentValues cv236 = createContentValuesProduct("Miến Phú Hương thịt băm ", "Miến Phú Hương được bổ sung tinh bột đậu xanh giúp sợi miến dai, trơn, kết hợp với nước súp thơm ngon khó cưỡng.", 16000, "mien_phuhuong_thitbam", "09/07/2023", 100, 11);
        ContentValues cv237 = createContentValuesProduct("Miến Phú Hương thịt heo nấu măng ", "Miến Phú Hương được bổ sung tinh bột đậu xanh giúp sợi miến dai, trơn, kết hợp với nước súp thơm ngon khó cưỡng.", 16000, "mien_phuhuong_thitheonaumang", "09/07/2023", 100, 11);
        //12.Bánh kẹo
        //Socola
        ContentValues cv238 = createContentValuesProduct("Socola Cadbury vị sữa", "Sản phẩm mang đến cho bạn hương vị thơm ngon, hấp dẫn, không chứa phẩm màu hoặc hóa chất độc hại, quá trình sản xuất được kiểm định nghiêm ngặt, đảm bảo chất lượng.", 16000, "socola_cadbury_sua", "09/07/2023", 100, 12);
        ContentValues cv239 = createContentValuesProduct("Socola Cadbury vị trái cây và hạt ", "Sản phẩm mang đến cho bạn hương vị thơm ngon, hấp dẫn, không chứa phẩm màu hoặc hóa chất độc hại, quá trình sản xuất được kiểm định nghiêm ngặt, đảm bảo chất lượng.", 16000, "socola_cadbury_trai_cay_va_hat", "09/07/2023", 100, 12);
        ContentValues cv240 = createContentValuesProduct("Socola Lindt Excellence vị ca cao ", "Sản phẩm mang đến cho bạn hương vị thơm ngon, hấp dẫn, không chứa phẩm màu hoặc hóa chất độc hại, quá trình sản xuất được kiểm định nghiêm ngặt, đảm bảo chất lượng.", 16000, "socola_lindt_excellence_vi_ca_cao", "09/07/2023", 100, 12);
        ContentValues cv241 = createContentValuesProduct("Socola Lindt Excellence vị cẩm thanh", "Sản phẩm mang đến cho bạn hương vị thơm ngon, hấp dẫn, không chứa phẩm màu hoặc hóa chất độc hại, quá trình sản xuất được kiểm định nghiêm ngặt, đảm bảo chất lượng.", 16000, "socola_lindt_excellence_vi_cam_thanh", "09/07/2023", 100, 12);
        ContentValues cv242 = createContentValuesProduct("Socola Lindt Excellence vị muối biển", "Sản phẩm mang đến cho bạn hương vị thơm ngon, hấp dẫn, không chứa phẩm màu hoặc hóa chất độc hại, quá trình sản xuất được kiểm định nghiêm ngặt, đảm bảo chất lượng.", 16000, "socola_lindt_excellence_vi_muoi_bien", "09/07/2023", 100, 12);
        //Snack ăn vặt
        ContentValues cv243 = createContentValuesProduct("Snack Oishi bắp cay", "Snack thơm ngon, là loại thức ăn nhanh tiện dụng, được ưa chuộng.", 16000, "snack_oishi_bap_cay", "09/07/2023", 100, 12);
        ContentValues cv244 = createContentValuesProduct("Snack Oishi bắp ngọt", "Snack thơm ngon, là loại thức ăn nhanh tiện dụng, được ưa chuộng.", 16000, "snack_oishi_bap_ngot", "09/07/2023", 100, 12);
        ContentValues cv245 = createContentValuesProduct("Snack Oishi hành tây", "Snack thơm ngon, là loại thức ăn nhanh tiện dụng, được ưa chuộng.", 16000, "snack_oishi_hanh_tay", "09/07/2023", 100, 12);
        ContentValues cv246 = createContentValuesProduct("Snack Oishi phô mai", "Snack thơm ngon, là loại thức ăn nhanh tiện dụng, được ưa chuộng.", 16000, "snack_oishi_pho_mai.jpg", "09/07/2023", 100, 12);
        ContentValues cv247 = createContentValuesProduct("Snack Oishi tôm muối ớt xanh", "Snack thơm ngon, là loại thức ăn nhanh tiện dụng, được ưa chuộng.", 16000, "snack_oishi_tom_muoi_ot_xanh", "09/07/2023", 100, 12);
        //Kẹo
        ContentValues cv248 = createContentValuesProduct("Kẹo Xyliton Bluebery việt quất", "Kẹo Cao Su Xylitol Fresh Mint đã được khoa học chứng minh là chất làm ngọt tự nhiên không gây hại cho sức khỏe răng miệng. Kẹo có hương vị thơm mát, giúp bạn chống lại những căng thẳng, buồn ngủ.", 16000, "keo_xyliton_bluebery_viet_quat", "09/07/2023", 100, 12);
        ContentValues cv249 = createContentValuesProduct("Kẹo Xyliton Cool", "Kẹo Cao Su Xylitol Fresh Mint đã được khoa học chứng minh là chất làm ngọt tự nhiên không gây hại cho sức khỏe răng miệng. Kẹo có hương vị thơm mát, giúp bạn chống lại những căng thẳng, buồn ngủ.", 16000, "keo_xyliton_cool", "09/07/2023", 100, 12);
        ContentValues cv250 = createContentValuesProduct("Kẹo Xyliton Fresh Mint", "Kẹo Cao Su Xylitol Fresh Mint đã được khoa học chứng minh là chất làm ngọt tự nhiên không gây hại cho sức khỏe răng miệng. Kẹo có hương vị thơm mát, giúp bạn chống lại những căng thẳng, buồn ngủ.", 16000, "keo_xyliton_fresh_mint", "09/07/2023", 100, 12);
        ContentValues cv251 = createContentValuesProduct("Kẹo Xyliton Lime Mint bạc hà", "Kẹo Cao Su Xylitol Fresh Mint đã được khoa học chứng minh là chất làm ngọt tự nhiên không gây hại cho sức khỏe răng miệng. Kẹo có hương vị thơm mát, giúp bạn chống lại những căng thẳng, buồn ngủ.", 16000, "keo_xyliton_lime_mint_bac_ha", "09/07/2023", 100, 12);
        //Banh ăn sáng
        ContentValues cv252 = createContentValuesProduct("Thức uống Quaker vị socola", "Sản phẩm thích hợp dành cho người ăn kiêng, giữ vóc dáng cân đối mà vẫn đảm bảo đủ chất cho cơ thể khỏe mạnh mỗi ngày.", 16000, "thuc_uong_quaker_socola", "09/07/2023", 100, 12);
        ContentValues cv253 = createContentValuesProduct("Thức uống yến mạch Quaker vị dâu", "Sản phẩm thích hợp dành cho người ăn kiêng, giữ vóc dáng cân đối mà vẫn đảm bảo đủ chất cho cơ thể khỏe mạnh mỗi ngày.", 16000, "thuc_uong_yen_mach_quaker_dau", "09/07/2023", 100, 12);
        ContentValues cv254 = createContentValuesProduct("Bánh Nestle KoKo Krunch", "Sản phẩm thích hợp dành cho người ăn kiêng, giữ vóc dáng cân đối mà vẫn đảm bảo đủ chất cho cơ thể khỏe mạnh mỗi ngày.", 16000, "banh_nestle_koko_krunch", "09/07/2023", 100, 12);
        ContentValues cv255 = createContentValuesProduct("Bánh Nestle Honey Stars", "Sản phẩm thích hợp dành cho người ăn kiêng, giữ vóc dáng cân đối mà vẫn đảm bảo đủ chất cho cơ thể khỏe mạnh mỗi ngày.", 16000, "banh_nestle_honey_stars", "09/07/2023", 100, 12);
        //Bánh
        ContentValues cv256 = createContentValuesProduct("Bánh Oreo vị socola ", "Sản phẩm đã được đóng thành từng gói bánh nhỏ gọn, tiện lợi khi sử dụng cũng như dễ dàng sẻ chia cùng bạn bè, người thân hương vị bánh thơm ngon.", 16000, "banh_oreo_socola", "09/07/2023", 100, 12);
        ContentValues cv257 = createContentValuesProduct("Bánh Oreo vị vani", "Sản phẩm đã được đóng thành từng gói bánh nhỏ gọn, tiện lợi khi sử dụng cũng như dễ dàng sẻ chia cùng bạn bè, người thân hương vị bánh thơm ngon.", 16000, "banh_oreo_vani", "09/07/2023", 100, 12);
        ContentValues cv258 = createContentValuesProduct("Bánh quy Cosy Marie vị sữa ", "Sản phẩm đã được đóng thành từng gói bánh nhỏ gọn, tiện lợi khi sử dụng cũng như dễ dàng sẻ chia cùng bạn bè, người thân hương vị bánh thơm ngon.", 16000, "banh_quy_cosy_marie_sua", "09/07/2023", 100, 12);
        ContentValues cv259 = createContentValuesProduct("Bánh Solite vị bơ sữa", "Sản phẩm đã được đóng thành từng gói bánh nhỏ gọn, tiện lợi khi sử dụng cũng như dễ dàng sẻ chia cùng bạn bè, người thân hương vị bánh thơm ngon.", 16000, "banh_solite_bo_sua", "09/07/2023", 100, 12);
        ContentValues cv260 = createContentValuesProduct("Bánh Solite v dâu", "Sản phẩm đã được đóng thành từng gói bánh nhỏ gọn, tiện lợi khi sử dụng cũng như dễ dàng sẻ chia cùng bạn bè, người thân hương vị bánh thơm ngon.", 16000, "banh_solite_dau", "09/07/2023", 100, 12);
        ContentValues cv261 = createContentValuesProduct("Bánh Solite vị lá dứa", "Sản phẩm đã được đóng thành từng gói bánh nhỏ gọn, tiện lợi khi sử dụng cũng như dễ dàng sẻ chia cùng bạn bè, người thân hương vị bánh thơm ngon.", 16000, "banh_solite_la_dua", "09/07/2023", 100, 12);
        //13. Nước ngọt, nước suối
        //Nước ngọt
        ContentValues cv262 = createContentValuesProduct("Nước Coco Cola Zero", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 10000, "nuoc_cocacolazero", "09/07/2023", 100, 13);
        ContentValues cv263 = createContentValuesProduct("Nước Coca Cola Zero Nhật Bản", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 10000, "nuoc_cocacolazero_nb", "09/07/2023", 100, 13);
        ContentValues cv264 = createContentValuesProduct("Nước Mirinda vị cam", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 10000, "nuoc_mirinda_cam", "09/07/2023", 100, 13);
        ContentValues cv265 = createContentValuesProduct("Nước Mirinda vị so đa kem", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 10000, "nuoc_mirinda_sodakem", "09/07/2023", 100, 13);
        ContentValues cv266 = createContentValuesProduct("Nước Mirinda vị xá xị", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 12000, "nuoc_mirinda_xaxi_chai", "09/07/2023", 100, 13);
        ContentValues cv267 = createContentValuesProduct("Nước Pesi Cola", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 10000, "nuoc_pepsicola", "09/07/2023", 100, 13);
        ContentValues cv268 = createContentValuesProduct("Nước Pesi Cola Sleek", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 10000, "nuoc_pepsicolasleek", "09/07/2023", 100, 13);
        ContentValues cv269 = createContentValuesProduct("Nước Pesi Zero Caloriees", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 13000, "nuoc_pepsizerocalories", "09/07/2023", 100, 13);
        ContentValues cv270 = createContentValuesProduct("Nước Pesi Zero Caloriees vị chanh", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 10000, "nuoc_pepsizerocalories_chanh", "09/07/2023", 100, 13);
        ContentValues cv271 = createContentValuesProduct("Nước ép Vfresh vị cam", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 43000, "nuoc_vfresh_cam", "09/07/2023", 100, 13);
        ContentValues cv272 = createContentValuesProduct("Nước ép Vfresh vị đào", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 42000, "nuoc_vfresh_dao", "09/07/2023", 100, 13);
        ContentValues cv273 = createContentValuesProduct("Nước ép Vfresh vị nho", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 40000, "nuoc_vfresh_nho", "09/07/2023", 100, 13);
        ContentValues cv274 = createContentValuesProduct("Nước ép Vfresh vị ổi", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 46000, "nuoc_vfresh_oi", "09/07/2023", 100, 13);
        ContentValues cv275 = createContentValuesProduct("Nước trái cây Pororo vị trái cây tổng hợp", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 15000, "nuoc_traicay_pororo_traicaytonghop", "09/07/2023", 100, 13);
        ContentValues cv276 = createContentValuesProduct("Nước trái cây Pororo vị vị việt quất", "Sản phẩm có hương vị thơm ngon, hấp dẫn giúp bạn giải tỏa cơn khát tức thì trong những ngày nóng bức, giúp bạn cung cấp năng lượng cho một ngày dài hoạt động.", 17000, "nuoc_traicay_pororo_vietquat", "09/07/2023", 100, 13);
        //Nươc suối
        ContentValues cv277 = createContentValuesProduct("Nước khoáng Aquafina", "Sản phẩm đóng chai tiện lợi, giúp bạn bổ sung nước và khoáng chất nhanh chóng.", 6000, "nuoc_khoang_aquafina", "09/07/2023", 100, 13);
        ContentValues cv278 = createContentValuesProduct("Nước Khoáng Vĩnh Hảo", "Sản phẩm đóng chai tiện lợi, giúp bạn bổ sung nước và khoáng chất nhanh chóng.", 7000, "nuoc_khoang_vinhhao", "09/07/2023", 100, 13);
        ContentValues cv279 = createContentValuesProduct("Nước Khoáng có ga Aquafina Soda", "Sản phẩm đóng chai tiện lợi, giúp bạn bổ sung nước và khoáng chất nhanh chóng.", 9000, "nuoc_khoangcoga_aquafina_soda", "09/07/2023", 100, 13);
        ContentValues cv280 = createContentValuesProduct("Nước khoáng có ga Vĩnh Hảo", "Sản phẩm đóng chai tiện lợi, giúp bạn bổ sung nước và khoáng chất nhanh chóng.", 10000, "nuoc_khoangcoga_vinhhao", "09/07/2023", 100, 13);
        //14. Rượu, bia
        //Rượu
        ContentValues cv281 = createContentValuesProduct("Rượu Antoine Reserve ", " Rượu mang hương thơm của trái cây chín mọng, trên vòm miệng vị rượu đầy đặn, tinh tế và với vị chát mềm mại hài hoà. ", 72000, "ruou_antoine_reserve", "09/07/2023", 100, 14);
        ContentValues cv282 = createContentValuesProduct("Rượu Chateau", " Rượu mang hương thơm của trái cây chín mọng, trên vòm miệng vị rượu đầy đặn, tinh tế và với vị chát mềm mại hài hoà. ", 92000, "ruou_chateau", "09/07/2023", 100, 14);
        ContentValues cv283 = createContentValuesProduct("Rượu Đà Lạt Classic", " Rượu mang hương thơm của trái cây chín mọng, trên vòm miệng vị rượu đầy đặn, tinh tế và với vị chát mềm mại hài hoà. ", 82000, "ruou_da_lat_classic", "09/07/2023", 100, 14);
        ContentValues cv284 = createContentValuesProduct("Rượu Geogre Wyndham Bin", " Rượu mang hương thơm của trái cây chín mọng, trên vòm miệng vị rượu đầy đặn, tinh tế và với vị chát mềm mại hài hoà. ", 92000, "ruou_geogre_wyndham_bin", "09/07/2023", 100, 14);
        ContentValues cv285 = createContentValuesProduct("Rượu Passion", " Rượu mang hương thơm của trái cây chín mọng, trên vòm miệng vị rượu đầy đặn, tinh tế và với vị chát mềm mại hài hoà. ", 102000, "ruou_passion", "09/07/2023", 100, 14);
        ContentValues cv286 = createContentValuesProduct("Rượu Soju Good Day hương dâu", "Rượu Soju được biết đến là loại rượu tinh khiết nguyên liệu sạch và được lọc sạch qua than tre thông qua các phương pháp tự nhiên. ", 122000, "ruou_soju_good_day_dau", "09/07/2023", 100, 14);
        ContentValues cv287 = createContentValuesProduct("Rượu Soju Good Day hương nho", "Rượu Soju được biết đến là loại rượu tinh khiết nguyên liệu sạch và được lọc sạch qua than tre thông qua các phương pháp tự nhiên. ", 72000, "ruou_soju_good_day_nho", "09/07/2023", 100, 14);
        ContentValues cv288 = createContentValuesProduct("Rượu Soju Good Day hương việt quất", "Rượu Soju được biết đến là loại rượu tinh khiết nguyên liệu sạch và được lọc sạch qua than tre thông qua các phương pháp tự nhiên. ", 82000, "ruou_soju_goodday_huongvietquat", "09/07/2023", 100, 14);
        ContentValues cv289 = createContentValuesProduct("Rượu Soju Korice hương đào", "Rượu Soju được biết đến là loại rượu tinh khiết nguyên liệu sạch và được lọc sạch qua than tre thông qua các phương pháp tự nhiên. ", 100000, "ruou_soju_korice_dao", "09/07/2023", 100, 14);
        ContentValues cv290 = createContentValuesProduct("Rượu Soju Korice hương táo", "Rượu Soju được biết đến là loại rượu tinh khiết nguyên liệu sạch và được lọc sạch qua than tre thông qua các phương pháp tự nhiên.", 160000, "ruou_soju_korice_tao", "09/07/2023", 100, 14);
        //Bia
        ContentValues cv291 = createContentValuesProduct("Bia 333", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 16000, "bia_333", "09/07/2023", 100, 14);
        ContentValues cv292 = createContentValuesProduct("Bia Budweiser", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 18000, "bia_budweiser", "09/07/2023", 100, 14);
        ContentValues cv293 = createContentValuesProduct("Bia Budweiser Aluminum", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 15000, "bia_budweiser_aluminum", "09/07/2023", 100, 14);
        ContentValues cv294 = createContentValuesProduct("Bia Henineken Silver", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 16000, "bia_heineken_silver", "09/07/2023", 100, 14);
        ContentValues cv295 = createContentValuesProduct("Bia Henineken Sleek", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 18000, "bia_henineken_sleek", "09/07/2023", 100, 14);
        ContentValues cv296 = createContentValuesProduct("Bia Hoegaarden vị đào", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 15000, "bia_hoegaarden_dao", "09/07/2023", 100, 14);
        ContentValues cv297 = createContentValuesProduct("Bia Hoegaarden Rose", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 16000, "bia_hoegaarden_rose", "09/07/2023", 100, 14);
        ContentValues cv298 = createContentValuesProduct("Bia Hoegaarden White", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 18000, "bia_hoegaarden_white", "09/07/2023", 100, 14);
        ContentValues cv299 = createContentValuesProduct("Bia Sài Gòn Chill", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 15000, "bia_saigon_chill", "09/07/2023", 100, 14);
        ContentValues cv300 = createContentValuesProduct("Bia Tiger bạc", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 16000, "bia_tiger_bac", "09/07/2023", 100, 14);
        ContentValues cv301 = createContentValuesProduct("Bia Tiger Platinum ", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 18000, "bia_tiger_platinum", "09/07/2023", 100, 14);
        ContentValues cv302 = createContentValuesProduct("Bia Tiger Soju vị dưa lưới", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 15000, "bia_tiger_soju_dualuoi", "09/07/2023", 100, 14);
        ContentValues cv303 = createContentValuesProduct("Bia Tiger Soju vị mận", "Mùi thơm đặc trưng, hương vị đậm đà, mang lại những trải nghiệm thú vị cho người thưởng thức.", 15000, "bia_tiger_soju_man", "09/07/2023", 100, 14);
        //15. Sức khỏe và làm đẹp
        //Mặt nạ
        ContentValues cv304 = createContentValuesProduct("Mặt nạ Dermal Collagen hồng sâm", " Mặt Nạ giúp tăng cường gấp đôi khả năng giữ ẩm và làm dịu nhanh chóng các vết bỏng rát khi tiếp xúc lâu với nắng.", 8000, "mat_na_dermalcollagen_hongsam", "09/07/2023", 100, 15);
        ContentValues cv305 = createContentValuesProduct("Mặt nạ Dermal Collagen ong chúa", " Mặt Nạ giúp tăng cường gấp đôi khả năng giữ ẩm và làm dịu nhanh chóng các vết bỏng rát khi tiếp xúc lâu với nắng.", 7000, "mat_na_dermalcollagen_ongchua", "09/07/2023", 100, 15);
        ContentValues cv306 = createContentValuesProduct("Mặt nạ Matrix La'cos nha dam", " Mặt Nạ giúp tăng cường gấp đôi khả năng giữ ẩm và làm dịu nhanh chóng các vết bỏng rát khi tiếp xúc lâu với nắng.", 6000, "mat_na_matrixlecos_nhadam", "09/07/2023", 100, 15);
        ContentValues cv307 = createContentValuesProduct("Mặt nạ Matrix La'cos nhau thai cừu", " Mặt Nạ giúp tăng cường gấp đôi khả năng giữ ẩm và làm dịu nhanh chóng các vết bỏng rát khi tiếp xúc lâu với nắng.", 8000, "mat_na_matrixlecos_nhauthaicuu", "09/07/2023", 100, 15);
        ContentValues cv308 = createContentValuesProduct("Mặt nạ Matrix La'cos trái cây và sữa tươi", " Mặt Nạ giúp tăng cường gấp đôi khả năng giữ ẩm và làm dịu nhanh chóng các vết bỏng rát khi tiếp xúc lâu với nắng.", 7000, "mat_na_matrixlecos_traicayvasuatuoi", "09/07/2023", 100, 15);
        ContentValues cv309 = createContentValuesProduct("Mặt nạ Senka Extra White", " Mặt Nạ giúp tăng cường gấp đôi khả năng giữ ẩm và làm dịu nhanh chóng các vết bỏng rát khi tiếp xúc lâu với nắng.", 6000, "mat_na_senkaextrawhite", "09/07/2023", 100, 15);
        ContentValues cv310 = createContentValuesProduct("Mặt nạ Senka Luminous Moist", " Mặt Nạ giúp tăng cường gấp đôi khả năng giữ ẩm và làm dịu nhanh chóng các vết bỏng rát khi tiếp xúc lâu với nắng.", 8000, "mat_na_senkaluminousmoist", "09/07/2023", 100, 15);
        ContentValues cv311 = createContentValuesProduct("Mặt nạ Senka Soothing White", " Mặt Nạ giúp tăng cường gấp đôi khả năng giữ ẩm và làm dịu nhanh chóng các vết bỏng rát khi tiếp xúc lâu với nắng.", 7000, "mat_na_senkasoothingwhite", "09/07/2023", 100, 15);
        //Sửa rửa mặt
        ContentValues cv312 = createContentValuesProduct("Sữa rửa mặt Biore sạch mụn và kháng khuẩn", " Sữa Rửa Mặt tạo bọt, nhẹ nhàng làm sạch bụi bẩn, bã nhờn, cung cấp dưỡng chát, thúc đẩy quá trình tái tạo da, đồng thời cân bằng độ ẩm, làm mềm da.", 30000, "sua_rua_mat_biore_sachmunvakhangkhuan", "09/07/2023", 100, 15);
        ContentValues cv313 = createContentValuesProduct("Sữa rửa mặt Biore sáng mịn và dưỡng ẩm", " Sữa Rửa Mặt tạo bọt, nhẹ nhàng làm sạch bụi bẩn, bã nhờn, cung cấp dưỡng chát, thúc đẩy quá trình tái tạo da, đồng thời cân bằng độ ẩm, làm mềm da.", 35000, "sua_rua_mat_biore_sangminvaduongam", "09/07/2023", 100, 15);
        ContentValues cv314 = createContentValuesProduct("Sữa rửa mặt Ponds", " Sữa Rửa Mặt tạo bọt, nhẹ nhàng làm sạch bụi bẩn, bã nhờn, cung cấp dưỡng chát, thúc đẩy quá trình tái tạo da, đồng thời cân bằng độ ẩm, làm mềm da.", 40000, "sua_rua_mat_ponds", "09/07/2023", 100, 15);
        ContentValues cv315 = createContentValuesProduct("Sữa rửa mặt Senka Perfect Whip", " Sữa Rửa Mặt tạo bọt, nhẹ nhàng làm sạch bụi bẩn, bã nhờn, cung cấp dưỡng chát, thúc đẩy quá trình tái tạo da, đồng thời cân bằng độ ẩm, làm mềm da.", 30000, "sua_rua_mat_ponds", "09/07/2023", 100, 15);
        ContentValues cv316 = createContentValuesProduct("Sữa rửa mặt Senka Perfect White Clay", " Sữa Rửa Mặt tạo bọt, nhẹ nhàng làm sạch bụi bẩn, bã nhờn, cung cấp dưỡng chát, thúc đẩy quá trình tái tạo da, đồng thời cân bằng độ ẩm, làm mềm da.", 35000, "sua_rua_mat_senkaperfectwhiteclay", "09/07/2023", 100, 15);
        ContentValues cv317 = createContentValuesProduct("Sữa rửa mặt St.Lves", " Sữa Rửa Mặt tạo bọt, nhẹ nhàng làm sạch bụi bẩn, bã nhờn, cung cấp dưỡng chát, thúc đẩy quá trình tái tạo da, đồng thời cân bằng độ ẩm, làm mềm da.", 40000, "sua_rua_mat_stlves_mo", "09/07/2023", 100, 15);
        //16. Mẹ và bé
        //Tã quần
        ContentValues cv318 = createContentValuesProduct("Tã quần Bobby 9-13kg", "Tã quần có lớp thấm nhanh siêu tốc nhanh chóng dàn đều và giữ sâu chất lỏng, thấm hút thật nhiều mà không lo dày bí giúp bé luôn khô thoáng.", 97000, "ta_quan_bobby_9_13kg", "09/07/2023", 100, 16);
        ContentValues cv319 = createContentValuesProduct("Tã quần Bobby 12-17kg", "Tã quần có lớp thấm nhanh siêu tốc nhanh chóng dàn đều và giữ sâu chất lỏng, thấm hút thật nhiều mà không lo dày bí giúp bé luôn khô thoáng.", 130000, "ta_quan_bobby_12_17kg", "09/07/2023", 100, 16);
        ContentValues cv320 = createContentValuesProduct("Tã quần Merries size XL", "Tã quần có lớp thấm nhanh siêu tốc nhanh chóng dàn đều và giữ sâu chất lỏng, thấm hút thật nhiều mà không lo dày bí giúp bé luôn khô thoáng.", 120000, "ta_quan_merries_xl", "09/07/2023", 100, 16);
        ContentValues cv321 = createContentValuesProduct("Tã quần Merries size XXL", "Tã quần có lớp thấm nhanh siêu tốc nhanh chóng dàn đều và giữ sâu chất lỏng, thấm hút thật nhiều mà không lo dày bí giúp bé luôn khô thoáng.", 97000, "ta_quan_merries_xxl", "09/07/2023", 100, 16);
        ContentValues cv322 = createContentValuesProduct("Tã quần MolFix size M", "Tã quần có lớp thấm nhanh siêu tốc nhanh chóng dàn đều và giữ sâu chất lỏng, thấm hút thật nhiều mà không lo dày bí giúp bé luôn khô thoáng.", 130000, "ta_quan_molfix_m", "09/07/2023", 100, 16);
        ContentValues cv323 = createContentValuesProduct("Tã quần MolFix size M", "Tã quần có lớp thấm nhanh siêu tốc nhanh chóng dàn đều và giữ sâu chất lỏng, thấm hút thật nhiều mà không lo dày bí giúp bé luôn khô thoáng.", 130000, "ta_quan_molfix_m", "09/07/2023", 100, 16);
        //Đồ chơi
        ContentValues cv324 = createContentValuesProduct("Bàn chải đánh răng trẻ em Oral Clean Formunla Soft", "Lông bàn chải được thiết kế mềm mại giúp chải sạch răng, không gây khó chịu cho nướu khi tiếp xúc.", 23000, "ban_chaidanhrang_treem_oralcleanformunla_soft", "09/07/2023", 100, 16);
        ContentValues cv325 = createContentValuesProduct("Bàn chải đánh răng trẻ em Oral Clean Royal Soft", "Lông bàn chải được thiết kế mềm mại giúp chải sạch răng, không gây khó chịu cho nướu khi tiếp xúc.", 23000, "ban_chaidanhrang_treem_oralcleanroyalsoft", "09/07/2023", 100, 16);
        ContentValues cv326 = createContentValuesProduct("Phấn trẻ em BaBi Mild", "Sản phẩm được sản xuất tại Việt Nam dựa trên công nghệ hiện đại theo tiêu chuẩn, đảm bảo an toàn cho sức khỏe của trẻ em.", 23000, "phan_treem_babimild", "09/07/2023", 100, 16);
        //17.Chăm sóc cá nhân
        ContentValues cv327 = createContentValuesProduct("Kem đánh răng BamBoo Salt", "Sản phẩm mang đến hương thơm bạc hà tươi mát, sảng khoái và ngăng ngừa sâu răng hiểu quả ", 43000, "kemdanhrang_bamboosalt", "09/07/2023", 100, 17);
        ContentValues cv328 = createContentValuesProduct("Kem đánh răng Close Up", "Sản phẩm mang đến hương thơm bạc hà tươi mát, sảng khoái và ngăng ngừa sâu răng hiểu quả ", 34000, "kemdanhrang_closeup", "09/07/2023", 100, 17);
        ContentValues cv329 = createContentValuesProduct("Kem đánh răng Colgate", "Sản phẩm mang đến hương thơm bạc hà tươi mát, sảng khoái và ngăng ngừa sâu răng hiểu quả ", 45000, "kemdanhrang_colgate", "09/07/2023", 100, 17);
        ContentValues cv330 = createContentValuesProduct("Kem đánh răng Median", "Sản phẩm mang đến hương thơm bạc hà tươi mát, sảng khoái và ngăng ngừa sâu răng hiểu quả ", 29000, "kemdanhrang_median", "09/07/2023", 100, 17);
        ContentValues cv331 = createContentValuesProduct("Kem đánh răng P/S", "Sản phẩm mang đến hương thơm bạc hà tươi mát, sảng khoái và ngăng ngừa sâu răng hiểu quả ", 23000, "kemdanhrang_ps", "09/07/2023", 100, 17);
        ContentValues cv332 = createContentValuesProduct("Sữa tắm Double Rich hoa lily", "Sữa Tắm mang đến cho bạn làn da tươi trẻ, thoảng hương thơm tinh tế, sang trọng.", 23000, "suatam_doublerich_hoalily", "09/07/2023", 100, 17);
        ContentValues cv333 = createContentValuesProduct("Sữa tắm Double Rich hoa hồng", "Sữa Tắm Double Rich Hoa Hồng 800G mang đến cho bạn làn da tươi trẻ, thoảng hương thơm tinh tế, sang trọng.", 23000, "suatam_douberich_hoahong", "09/07/2023", 100, 17);
        ContentValues cv334 = createContentValuesProduct("Sữa tăm Olay", "Sữa Tắm Double Rich Hoa Hồng 800G mang đến cho bạn làn da tươi trẻ, thoảng hương thơm tinh tế, sang trọng.", 23000, "suatam_olay", "09/07/2023", 100, 17);
        ContentValues cv335 = createContentValuesProduct("Sữa tăm Romano Attude", "Sữa Tắm Double Rich Hoa Hồng 800G mang đến cho bạn làn da tươi trẻ, thoảng hương thơm tinh tế, sang trọng.", 23000, "suatam_romanoattude", "09/07/2023", 100, 17);
        ContentValues cv336 = createContentValuesProduct("Sữa tắm Xmen", "Sữa Tắm Double Rich Hoa Hồng 800G mang đến cho bạn làn da tươi trẻ, thoảng hương thơm tinh tế, sang trọng.", 23000, "suatam_xmen", "09/07/2023", 100, 17);
        //18.Chăm sóc quần áo
            //Bột giặt, nước giặt
        ContentValues cv337 = createContentValuesProduct("Bột giặt Ariel hương nắng sớm", "Bột Giặt mang lại khả năng giặt sạch hiệu quả với công nghệ đột phá mới sẽ chứa các hạt tinh thể làm sạch sẽ giúp đánh bay nhiều loại vết bẩn một cách nhanh chóng và hương thơm nhẹ nhàng dài lâu ", 83000, "botgiat_ariel2", "09/07/2023", 100, 18);
        ContentValues cv338 = createContentValuesProduct("Bột giặt Ariel hương oải hương", "Bột Giặt mang lại khả năng giặt sạch hiệu quả với công nghệ đột phá mới sẽ chứa các hạt tinh thể làm sạch sẽ giúp đánh bay nhiều loại vết bẩn một cách nhanh chóng và hương thơm nhẹ nhàng dài lâu ", 103000, "botgiat_ariel3", "09/07/2023", 100, 18);
        ContentValues cv339 = createContentValuesProduct("Bột giặt Ariel dịu nhẹ", "Bột Giặt mang lại khả năng giặt sạch hiệu quả với công nghệ đột phá mới sẽ chứa các hạt tinh thể làm sạch sẽ giúp đánh bay nhiều loại vết bẩn một cách nhanh chóng và hương thơm nhẹ nhàng dài lâu ", 73000, "botgiat_ariel4", "09/07/2023", 100, 18);
        ContentValues cv340 = createContentValuesProduct("Bột giặt Attack hương ngày sảng khoái ", "Bột Giặt mang lại khả năng giặt sạch hiệu quả với công nghệ đột phá mới sẽ chứa các hạt tinh thể làm sạch sẽ giúp đánh bay nhiều loại vết bẩn một cách nhanh chóng và hương thơm nhẹ nhàng dài lâu ", 83000, "botgiat_attack", "09/07/2023", 100, 18);
        ContentValues cv341 = createContentValuesProduct("Bột giặt Attack hương anh đào", "Bột Giặt mang lại khả năng giặt sạch hiệu quả với công nghệ đột phá mới sẽ chứa các hạt tinh thể làm sạch sẽ giúp đánh bay nhiều loại vết bẩn một cách nhanh chóng và hương thơm nhẹ nhàng dài lâu ", 103000, "botgiat_attack2", "09/07/2023", 100, 18);
        ContentValues cv342 = createContentValuesProduct("Bột giặt Attack hương oải hương", "Bột Giặt mang lại khả năng giặt sạch hiệu quả với công nghệ đột phá mới sẽ chứa các hạt tinh thể làm sạch sẽ giúp đánh bay nhiều loại vết bẩn một cách nhanh chóng và hương thơm nhẹ nhàng dài lâu ", 73000, "botgiat_attack3", "09/07/2023", 100, 18);
        ContentValues cv343 = createContentValuesProduct("Nước giặt Lix hương nước hoa ", "Bột Giặt mang lại khả năng giặt sạch hiệu quả với công nghệ đột phá mới sẽ chứa các hạt tinh thể làm sạch sẽ giúp đánh bay nhiều loại vết bẩn một cách nhanh chóng và hương thơm nhẹ nhàng dài lâu ", 83000, "botgiat_lix1", "09/07/2023", 100, 18);
        ContentValues cv344 = createContentValuesProduct("Nước giặt Lix khử mùi ẩm mốc", "Bột Giặt mang lại khả năng giặt sạch hiệu quả với công nghệ đột phá mới sẽ chứa các hạt tinh thể làm sạch sẽ giúp đánh bay nhiều loại vết bẩn một cách nhanh chóng và hương thơm nhẹ nhàng dài lâu ", 103000, "botgiat_lix2.", "09/07/2023", 100, 18);
        ContentValues cv345 = createContentValuesProduct("Nước giặt Lix đậm đặc hương nước hoa", "Bột Giặt mang lại khả năng giặt sạch hiệu quả với công nghệ đột phá mới sẽ chứa các hạt tinh thể làm sạch sẽ giúp đánh bay nhiều loại vết bẩn một cách nhanh chóng và hương thơm nhẹ nhàng dài lâu ", 73000, "botgiat_lix3", "09/07/2023", 100, 18);
        ContentValues cv346 = createContentValuesProduct("Bột giặt Lix hương chanh", "Bột Giặt mang lại khả năng giặt sạch hiệu quả với công nghệ đột phá mới sẽ chứa các hạt tinh thể làm sạch sẽ giúp đánh bay nhiều loại vết bẩn một cách nhanh chóng và hương thơm nhẹ nhàng dài lâu ", 83000, "botgiat_lix4", "09/07/2023", 100, 18);
        ContentValues cv347 = createContentValuesProduct("Nước giặt Maxkleen hương hoa nắng", "Bột Giặt mang lại khả năng giặt sạch hiệu quả với công nghệ đột phá mới sẽ chứa các hạt tinh thể làm sạch sẽ giúp đánh bay nhiều loại vết bẩn một cách nhanh chóng và hương thơm nhẹ nhàng dài lâu ", 103000, "botgiat_maxkleen", "09/07/2023", 100, 18);
        ContentValues cv348 = createContentValuesProduct("Nước giặt Maxkleen hương vườn sớm mai", "Bột Giặt mang lại khả năng giặt sạch hiệu quả với công nghệ đột phá mới sẽ chứa các hạt tinh thể làm sạch sẽ giúp đánh bay nhiều loại vết bẩn một cách nhanh chóng và hương thơm nhẹ nhàng dài lâu ", 73000, "botgiat_maxkleen1", "09/07/2023", 100, 18);
            //Xả vải, xịt vải
        ContentValues cv349 = createContentValuesProduct("Xả vải Downy đóa hoa thơm mát", "Xả Vải với mùi hương độc đáo chứa những tinh thể hương giúp lưu lại trên quần áo lâu hơn, cho bạn thoải mái hoạt động suốt cả ngày mà không sợ ám mùi mồ hôi, bụi bẩn, mất tự tin.", 83000, "xavai_downy", "09/07/2023", 100, 18);
        ContentValues cv350 = createContentValuesProduct("Xả vải Downy bung tỏa hạnh phúc", "Xả Vải với mùi hương độc đáo chứa những tinh thể hương giúp lưu lại trên quần áo lâu hơn, cho bạn thoải mái hoạt động suốt cả ngày mà không sợ ám mùi mồ hôi, bụi bẩn, mất tự tin.", 83000, "xavai_downy2", "09/07/2023", 100, 18);
        ContentValues cv351 = createContentValuesProduct("Xả vải Downy hương nắng mai", "Xả Vải với mùi hương độc đáo chứa những tinh thể hương giúp lưu lại trên quần áo lâu hơn, cho bạn thoải mái hoạt động suốt cả ngày mà không sợ ám mùi mồ hôi, bụi bẩn, mất tự tin.", 83000, "xavai_downy3", "09/07/2023", 100, 18);
        ContentValues cv352 = createContentValuesProduct("Xả vải Downy huyền bí", "Xả Vải với mùi hương độc đáo chứa những tinh thể hương giúp lưu lại trên quần áo lâu hơn, cho bạn thoải mái hoạt động suốt cả ngày mà không sợ ám mùi mồ hôi, bụi bẩn, mất tự tin.", 83000, "xavai_downy4", "09/07/2023", 100, 18);
        ContentValues cv353 = createContentValuesProduct("Xả vải Downy làn gió mát", "Xả Vải với mùi hương độc đáo chứa những tinh thể hương giúp lưu lại trên quần áo lâu hơn, cho bạn thoải mái hoạt động suốt cả ngày mà không sợ ám mùi mồ hôi, bụi bẩn, mất tự tin.", 83000, "xavai_downy5", "09/07/2023", 100, 18);
        ContentValues cv354 = createContentValuesProduct("Xả vải Lix hương hoa hồng", "Xả Vải với mùi hương độc đáo chứa những tinh thể hương giúp lưu lại trên quần áo lâu hơn, cho bạn thoải mái hoạt động suốt cả ngày mà không sợ ám mùi mồ hôi, bụi bẩn, mất tự tin.", 83000, "xavai_lix1", "09/07/2023", 100, 18);
        ContentValues cv355 = createContentValuesProduct("Xả vải Lix hương sớm mai", "Xả Vải với mùi hương độc đáo chứa những tinh thể hương giúp lưu lại trên quần áo lâu hơn, cho bạn thoải mái hoạt động suốt cả ngày mà không sợ ám mùi mồ hôi, bụi bẩn, mất tự tin.", 83000, "xavai_lix2", "09/07/2023", 100, 18);
        ContentValues cv356 = createContentValuesProduct("Xịt vải Downy hương nắng mai", "Xả Vải với mùi hương độc đáo chứa những tinh thể hương giúp lưu lại trên quần áo lâu hơn, cho bạn thoải mái hoạt động suốt cả ngày mà không sợ ám mùi mồ hôi, bụi bẩn, mất tự tin.", 83000, "xitvai_downy1", "09/07/2023", 100, 18);
        ContentValues cv357 = createContentValuesProduct("Xịt vải Downy hương huyền bí", "Xả Vải với mùi hương độc đáo chứa những tinh thể hương giúp lưu lại trên quần áo lâu hơn, cho bạn thoải mái hoạt động suốt cả ngày mà không sợ ám mùi mồ hôi, bụi bẩn, mất tự tin.", 83000, "xitvai_downy2", "09/07/2023", 100, 18);
        ContentValues cv358 = createContentValuesProduct("Xịt vải Downy đam mê", "Xả Vải với mùi hương độc đáo chứa những tinh thể hương giúp lưu lại trên quần áo lâu hơn, cho bạn thoải mái hoạt động suốt cả ngày mà không sợ ám mùi mồ hôi, bụi bẩn, mất tự tin.", 83000, "xitvai_downy3", "09/07/2023", 100, 18);
        //19. Chăm sóc nhà cửa
            //Sáp thơm
        ContentValues cv359 = createContentValuesProduct("Sáp thơm Ami Relaxing", "Sáp thơm có hương thơm dễ chịu, hộp sáp dễ dùng, đảm bảo cho không gian sống của bạn có hương thơm an toàn và tinh khiết", 53000, "sapthom_ami1", "09/07/2023", 100, 19);
        ContentValues cv360 = createContentValuesProduct("Sáp thơm Ami Pleasant", "Sáp thơm có hương thơm dễ chịu, hộp sáp dễ dùng, đảm bảo cho không gian sống của bạn có hương thơm an toàn và tinh khiết", 43000, "sapthom_ami2", "09/07/2023", 100, 19);
        ContentValues cv361 = createContentValuesProduct("Sáp thơm Ami Sweet", "Sáp thơm có hương thơm dễ chịu, hộp sáp dễ dùng, đảm bảo cho không gian sống của bạn có hương thơm an toàn và tinh khiết", 63000, "sapthom_ami3", "09/07/2023", 100, 19);
        ContentValues cv362 = createContentValuesProduct("Sáp thơm Ami Jasmine", "Sáp thơm có hương thơm dễ chịu, hộp sáp dễ dùng, đảm bảo cho không gian sống của bạn có hương thơm an toàn và tinh khiết", 53000, "sapthom_ami4", "09/07/2023", 100, 19);
        ContentValues cv363 = createContentValuesProduct("Sáp thơm Farcent Passionate Rose", "Sáp thơm có hương thơm dễ chịu, hộp sáp dễ dùng, đảm bảo cho không gian sống của bạn có hương thơm an toàn và tinh khiết", 43000, "sapthom_farcent1", "09/07/2023", 100, 19);
        ContentValues cv364 = createContentValuesProduct("Sáp thơm Farcent Relaxing Lavender", "Sáp thơm có hương thơm dễ chịu, hộp sáp dễ dùng, đảm bảo cho không gian sống của bạn có hương thơm an toàn và tinh khiết", 63000, "sapthom_farcent2", "09/07/2023", 100, 19);
        ContentValues cv365 = createContentValuesProduct("Sáp thơm Farcent Refreshing Lemon", "Sáp thơm có hương thơm dễ chịu, hộp sáp dễ dùng, đảm bảo cho không gian sống của bạn có hương thơm an toàn và tinh khiết", 53000, "sapthom_farcent3", "09/07/2023", 100, 19);
        ContentValues cv366 = createContentValuesProduct("Sáp thơm Glade Lavender", "Sáp thơm có hương thơm dễ chịu, hộp sáp dễ dùng, đảm bảo cho không gian sống của bạn có hương thơm an toàn và tinh khiết", 43000, "sapthom_glade1", "09/07/2023", 100, 19);
        ContentValues cv367 = createContentValuesProduct("Sáp thơm Glade hương hoa", "Sáp thơm có hương thơm dễ chịu, hộp sáp dễ dùng, đảm bảo cho không gian sống của bạn có hương thơm an toàn và tinh khiết", 63000, "sapthom_glade2", "09/07/2023", 100, 19);
        //20. Trà và cà phê
            //Trà
        ContentValues cv368 = createContentValuesProduct("Trà Lipton Ice Tea chanh mật ong", "Trà sẽ giúp bạn giải khát tức thì, cung cấp thêm năng lượng, cung cấp nước và là nguồn bổ sung vitamin C nhằm tăng cường sức đề kháng cho cơ thể, bảo vệ tế bào khỏi tác hại của các gốc tự do.", 63000, "tra_liptonicetea_chanhmatong", "09/07/2023", 100, 20);
        ContentValues cv369 = createContentValuesProduct("Trà Nesta vị chanh", "Trà sẽ giúp bạn giải khát tức thì, cung cấp thêm năng lượng, cung cấp nước và là nguồn bổ sung vitamin C nhằm tăng cường sức đề kháng cho cơ thể, bảo vệ tế bào khỏi tác hại của các gốc tự do.", 83000, "tra_nestea_chanh", "09/07/2023", 100, 20);
        ContentValues cv370 = createContentValuesProduct("Trà sữa Royal Myanmar", "Trà sẽ giúp bạn giải khát tức thì, cung cấp thêm năng lượng, cung cấp nước và là nguồn bổ sung vitamin C nhằm tăng cường sức đề kháng cho cơ thể, bảo vệ tế bào khỏi tác hại của các gốc tự do.", 57000, "tra_sua_royalmyanmar", "09/07/2023", 100, 20);
        ContentValues cv371 = createContentValuesProduct("Trà sữa Thái Ranong Tea 3in1 Matcha", "Trà sẽ giúp bạn giải khát tức thì, cung cấp thêm năng lượng, cung cấp nước và là nguồn bổ sung vitamin C nhằm tăng cường sức đề kháng cho cơ thể, bảo vệ tế bào khỏi tác hại của các gốc tự do.", 63000, "tra_sua_thairanongtea_3in1_matcha", "09/07/2023", 100, 20);
        ContentValues cv372 = createContentValuesProduct("Trà sữa truyền thống no brand", "Trà sẽ giúp bạn giải khát tức thì, cung cấp thêm năng lượng, cung cấp nước và là nguồn bổ sung vitamin C nhằm tăng cường sức đề kháng cho cơ thể, bảo vệ tế bào khỏi tác hại của các gốc tự do.", 83000, "tra_sua_truyenthong", "09/07/2023", 100, 20);
            //Cà phê
        ContentValues cv373 = createContentValuesProduct("Cà phê G7 3in1", "Cà Phê Bột được sản xuất từ những hạt cà phê được tuyển chọn kĩ càng và sản xuất theo dây chuyền công nghệ hiện đại và bí quyết riêng không thể sao chép.", 83000, "caphe_g7_3in1", "09/07/2023", 100, 20);
        ContentValues cv374 = createContentValuesProduct("Cà phê Highlands Moka", "Cà Phê Bột được sản xuất từ những hạt cà phê được tuyển chọn kĩ càng và sản xuất theo dây chuyền công nghệ hiện đại và bí quyết riêng không thể sao chép.", 43000, "caphe_highlands_moka", "09/07/2023", 100, 20);
        ContentValues cv375 = createContentValuesProduct("Cà phê Passiona", "Cà Phê Bột được sản xuất từ những hạt cà phê được tuyển chọn kĩ càng và sản xuất theo dây chuyền công nghệ hiện đại và bí quyết riêng không thể sao chép.", 68000, "caphe_passiona", "09/07/2023", 100, 20);
        ContentValues cv376 = createContentValuesProduct("Cà phê Sữa Vinacafe", "Cà Phê Bột được sản xuất từ những hạt cà phê được tuyển chọn kĩ càng và sản xuất theo dây chuyền công nghệ hiện đại và bí quyết riêng không thể sao chép.", 83000, "caphe_sua_vinacafe", "09/07/2023", 100, 20);
        ContentValues cv377 = createContentValuesProduct("Cà phê Trung Nguyên", "Cà Phê Bột được sản xuất từ những hạt cà phê được tuyển chọn kĩ càng và sản xuất theo dây chuyền công nghệ hiện đại và bí quyết riêng không thể sao chép.", 43000, "caphe_trungnguyen", "09/07/2023", 100, 20);
        //21. Gia đình
        ContentValues cv378 = createContentValuesProduct("Máy ép Magic", "Máy thiết kế nhỏ gọn giúp giảm thiểu không gian cất giữ và an toàn cho người sử dụng.", 488000, "mayep_magic", "09/07/2023", 100, 21);
        ContentValues cv379 = createContentValuesProduct("Máy ép Tefal", "Máy thiết kế nhỏ gọn giúp giảm thiểu không gian cất giữ và an toàn cho người sử dụng.", 899000, "mayep_tefal", "09/07/2023", 100, 21);
        ContentValues cv380 = createContentValuesProduct("Máy say sinh tố Philips", "Máy thiết kế nhỏ gọn giúp giảm thiểu không gian cất giữ và an toàn cho người sử dụng.", 1050000, "maysaysinto_philips.jpg", "09/07/2023", 100, 21);
        ContentValues cv381 = createContentValuesProduct("Máy say sinh tố Super Chef", "Máy thiết kế nhỏ gọn giúp giảm thiểu không gian cất giữ và an toàn cho người sử dụng.", 488000, "maysaysinto_superchef.jpg", "09/07/2023", 100, 21);
        ContentValues cv382 = createContentValuesProduct("Máy sấy tóc Flyco", "Máy thiết kế nhỏ gọn giúp giảm thiểu không gian cất giữ và an toàn cho người sử dụng.", 899000, "maysaytoc_flyco", "09/07/2023", 100, 21);
        ContentValues cv383 = createContentValuesProduct("Máy sấy tóc Panasonic", "Máy thiết kế nhỏ gọn giúp giảm thiểu không gian cất giữ và an toàn cho người sử dụng.", 1050000, "maysaytoc_panasonic", "09/07/2023", 100, 21);
        ContentValues cv384 = createContentValuesProduct("Vợt muỗi Sunhouse", "Máy thiết kế nhỏ gọn giúp giảm thiểu không gian cất giữ và an toàn cho người sử dụng.", 488000, "votmuoi_sunhouse", "09/07/2023", 100, 21);
        ContentValues cv385 = createContentValuesProduct("Vợt muỗi Sunhouse", "Máy thiết kế nhỏ gọn giúp giảm thiểu không gian cất giữ và an toàn cho người sử dụng.", 899000, "votmuoi_superchef", "09/07/2023", 100, 21);
        ContentValues cv386 = createContentValuesProduct("Bàn ủi hơi nước cầm tay Tefal", "Máy thiết kế nhỏ gọn giúp giảm thiểu không gian cất giữ và an toàn cho người sử dụng.", 1050000, "banui_hoinuoc_camtay_tefal", "09/07/2023", 100, 21);
        ContentValues cv387 = createContentValuesProduct("Bàn ủi hơi nước Philip", "Máy thiết kế nhỏ gọn giúp giảm thiểu không gian cất giữ và an toàn cho người sử dụng.", 488000, "banui_hoinuoc_philips", "09/07/2023", 100, 21);
        //22.Đồ chơi
        ContentValues cv388 = createContentValuesProduct("Đồ chơi búp bê", "Đồ Chơi đã được kiểm chứng an toàn với sức khỏe bé, thích hợp làm quà tặng cho trẻ vào các dịp đặc biệt.", 83000, "do_choi_bup_be", "09/07/2023", 100, 16);
        ContentValues cv389 = createContentValuesProduct("Đồ chơi đàn piano", "Đồ Chơi đã được kiểm chứng an toàn với sức khỏe bé, thích hợp làm quà tặng cho trẻ vào các dịp đặc biệt.", 233000, "do_choi_dan_piano", "09/07/2023", 100, 16);
        ContentValues cv390 = createContentValuesProduct("Đồ chơi phi cơ", "Đồ Chơi đã được kiểm chứng an toàn với sức khỏe bé, thích hợp làm quà tặng cho trẻ vào các dịp đặc biệt.", 333000, "do_choi_phi_co", "09/07/2023", 100, 16);
        ContentValues cv391 = createContentValuesProduct("Đồ chơi thú nhồi bông con cún", "Đồ Chơi đã được kiểm chứng an toàn với sức khỏe bé, thích hợp làm quà tặng cho trẻ vào các dịp đặc biệt.", 83000, "do_choi_thu_nhoi_bong_cun", "09/07/2023", 100, 16);
        ContentValues cv392 = createContentValuesProduct("Đồ chơi thú nhồi bông con heo", "Đồ Chơi đã được kiểm chứng an toàn với sức khỏe bé, thích hợp làm quà tặng cho trẻ vào các dịp đặc biệt.", 233000, "do_choi_thu_nhoi_bong_heo", "09/07/2023", 100, 16);
        ContentValues cv393 = createContentValuesProduct("Đồ chơi thú nhồi bông con mèo", "Đồ Chơi đã được kiểm chứng an toàn với sức khỏe bé, thích hợp làm quà tặng cho trẻ vào các dịp đặc biệt.", 333000, "do_choi_thu_nhoi_bong_meo", "09/07/2023", 100, 16);

        products.add(cv1);
        products.add(cv2);
        products.add(cv3);
        products.add(cv4);
        products.add(cv5);
        products.add(cv6);
        products.add(cv7);
        products.add(cv8);
        products.add(cv9);
        products.add(cv10);
        products.add(cv11);
        products.add(cv12);
        products.add(cv13);
        products.add(cv14);
        products.add(cv15);
        products.add(cv16);
        products.add(cv17);
        products.add(cv18);
        products.add(cv19);
        products.add(cv20);
        products.add(cv21);
        products.add(cv22);
        products.add(cv23);
        products.add(cv24);
        products.add(cv25);
        products.add(cv26);
        products.add(cv27);
        products.add(cv28);
        products.add(cv29);
        products.add(cv30);
        products.add(cv31);
        products.add(cv32);
        products.add(cv33);
        products.add(cv34);
        products.add(cv35);
        products.add(cv36);
        products.add(cv37);
        products.add(cv38);
        products.add(cv39);
        products.add(cv40);
        products.add(cv41);
        products.add(cv42);
        products.add(cv43);
        products.add(cv44);
        products.add(cv45);
        products.add(cv46);
        products.add(cv47);
        products.add(cv48);
        products.add(cv49);
        products.add(cv50);
        products.add(cv51);
        products.add(cv52);
        products.add(cv53);
        products.add(cv54);
        products.add(cv55);
        products.add(cv56);
        products.add(cv57);
        products.add(cv58);
        products.add(cv59);
        products.add(cv60);
        products.add(cv61);
        products.add(cv62);
        products.add(cv63);
        products.add(cv64);
        products.add(cv65);
        products.add(cv66);
        products.add(cv67);
        products.add(cv68);
        products.add(cv69);
        products.add(cv70);
        products.add(cv71);
        products.add(cv72);
        products.add(cv73);
        products.add(cv74);
        products.add(cv75);
        products.add(cv76);
        products.add(cv77);
        products.add(cv78);
        products.add(cv79);
        products.add(cv80);
        products.add(cv81);
        products.add(cv82);
        products.add(cv83);
        products.add(cv84);
        products.add(cv85);
        products.add(cv86);
        products.add(cv87);
        products.add(cv88);
        products.add(cv89);
        products.add(cv90);
        products.add(cv91);
        products.add(cv92);
        products.add(cv93);
        products.add(cv94);
        products.add(cv95);
        products.add(cv96);
        products.add(cv97);
        products.add(cv98);
        products.add(cv99);
        products.add(cv100);
        products.add(cv101);
        products.add(cv102);
        products.add(cv103);
        products.add(cv104);
        products.add(cv105);
        products.add(cv106);
        products.add(cv107);
        products.add(cv108);
        products.add(cv109);
        products.add(cv110);
        products.add(cv111);
        products.add(cv113);
        products.add(cv114);
        products.add(cv115);
        products.add(cv116);
        products.add(cv117);
        products.add(cv118);
        products.add(cv119);
        products.add(cv120);
        products.add(cv121);
        products.add(cv122);
        products.add(cv123);
        products.add(cv124);
        products.add(cv125);
        products.add(cv126);
        products.add(cv127);
        products.add(cv128);
        products.add(cv129);
        products.add(cv130);
        products.add(cv131);
        products.add(cv132);
        products.add(cv133);
        products.add(cv134);
        products.add(cv135);
        products.add(cv136);
        products.add(cv137);
        products.add(cv138);
        products.add(cv139);
        products.add(cv140);
        products.add(cv141);
        products.add(cv142);
        products.add(cv143);
        products.add(cv144);
        products.add(cv145);
        products.add(cv146);
        products.add(cv147);
        products.add(cv148);
        products.add(cv149);
        products.add(cv150);
        products.add(cv151);
        products.add(cv152);
        products.add(cv153);
        products.add(cv154);
        products.add(cv155);
        products.add(cv156);
        products.add(cv157);
        products.add(cv158);
        products.add(cv159);
        products.add(cv160);
        products.add(cv161);
        products.add(cv162);
        products.add(cv163);
        products.add(cv164);
        products.add(cv165);
        products.add(cv166);
        products.add(cv167);
        products.add(cv168);
        products.add(cv169);
        products.add(cv170);
        products.add(cv171);
        products.add(cv172);
        products.add(cv173);
        products.add(cv174);
        products.add(cv175);
        products.add(cv176);
        products.add(cv177);
        products.add(cv178);
        products.add(cv179);
        products.add(cv180);
        products.add(cv181);
        products.add(cv182);
        products.add(cv183);
        products.add(cv184);
        products.add(cv185);
        products.add(cv186);
        products.add(cv187);
        products.add(cv188);
        products.add(cv189);
        products.add(cv190);
        products.add(cv191);
        products.add(cv192);
        products.add(cv193);
        products.add(cv194);
        products.add(cv195);
        products.add(cv196);
        products.add(cv197);
        products.add(cv198);
        products.add(cv200);
        products.add(cv201);
        products.add(cv202);
        products.add(cv203);
        products.add(cv204);
        products.add(cv205);
        products.add(cv206);
        products.add(cv207);
        products.add(cv208);
        products.add(cv209);
        products.add(cv210);
        products.add(cv211);
        products.add(cv212);
        products.add(cv213);
        products.add(cv214);
        products.add(cv215);
        products.add(cv216);
        products.add(cv217);
        products.add(cv218);
        products.add(cv219);
        products.add(cv220);
        products.add(cv221);
        products.add(cv222);
        products.add(cv223);
        products.add(cv224);
        products.add(cv225);
        products.add(cv226);
        products.add(cv227);
        products.add(cv228);
        products.add(cv229);
        products.add(cv230);
        products.add(cv231);
        products.add(cv232);
        products.add(cv233);
        products.add(cv234);
        products.add(cv235);
        products.add(cv236);
        products.add(cv237);
        products.add(cv238);
        products.add(cv239);
        products.add(cv240);
        products.add(cv241);
        products.add(cv242);
        products.add(cv243);
        products.add(cv244);
        products.add(cv245);
        products.add(cv246);
        products.add(cv247);
        products.add(cv248);
        products.add(cv249);
        products.add(cv250);
        products.add(cv251);
        products.add(cv252);
        products.add(cv253);
        products.add(cv254);
        products.add(cv255);
        products.add(cv256);
        products.add(cv257);
        products.add(cv258);
        products.add(cv259);
        products.add(cv260);
        products.add(cv261);
        products.add(cv262);
        products.add(cv263);
        products.add(cv264);
        products.add(cv265);
        products.add(cv266);
        products.add(cv267);
        products.add(cv268);
        products.add(cv269);
        products.add(cv270);
        products.add(cv271);
        products.add(cv272);
        products.add(cv273);
        products.add(cv274);
        products.add(cv275);
        products.add(cv276);
        products.add(cv277);
        products.add(cv278);
        products.add(cv279);
        products.add(cv280);
        products.add(cv281);
        products.add(cv282);
        products.add(cv283);
        products.add(cv284);
        products.add(cv285);
        products.add(cv286);
        products.add(cv287);
        products.add(cv288);
        products.add(cv289);
        products.add(cv290);
        products.add(cv291);
        products.add(cv292);
        products.add(cv293);
        products.add(cv294);
        products.add(cv295);
        products.add(cv296);
        products.add(cv297);
        products.add(cv298);
        products.add(cv299);
        products.add(cv300);
        products.add(cv301);
        products.add(cv302);
        products.add(cv303);
        products.add(cv304);
        products.add(cv305);
        products.add(cv306);
        products.add(cv307);
        products.add(cv308);
        products.add(cv309);
        products.add(cv310);
        products.add(cv311);
        products.add(cv312);
        products.add(cv313);
        products.add(cv314);
        products.add(cv315);
        products.add(cv316);
        products.add(cv317);
        products.add(cv318);
        products.add(cv319);
        products.add(cv320);
        products.add(cv321);
        products.add(cv322);
        products.add(cv323);
        products.add(cv324);
        products.add(cv325);
        products.add(cv326);
        products.add(cv327);
        products.add(cv328);
        products.add(cv329);
        products.add(cv330);
        products.add(cv331);
        products.add(cv332);
        products.add(cv333);
        products.add(cv334);
        products.add(cv335);
        products.add(cv336);
        products.add(cv337);
        products.add(cv338);
        products.add(cv339);
        products.add(cv340);
        products.add(cv341);
        products.add(cv342);
        products.add(cv343);
        products.add(cv344);
        products.add(cv345);
        products.add(cv346);
        products.add(cv347);
        products.add(cv348);
        products.add(cv349);
        products.add(cv350);
        products.add(cv351);
        products.add(cv352);
        products.add(cv353);
        products.add(cv354);
        products.add(cv355);
        products.add(cv356);
        products.add(cv357);
        products.add(cv358);
        products.add(cv359);
        products.add(cv360);
        products.add(cv361);
        products.add(cv362);
        products.add(cv363);
        products.add(cv364);
        products.add(cv365);
        products.add(cv366);
        products.add(cv367);
        products.add(cv368);
        products.add(cv369);
        products.add(cv370);
        products.add(cv371);
        products.add(cv372);
        products.add(cv373);
        products.add(cv374);
        products.add(cv375);
        products.add(cv376);
        products.add(cv377);
        products.add(cv378);
        products.add(cv379);
        products.add(cv380);
        products.add(cv381);
        products.add(cv382);
        products.add(cv383);
        products.add(cv384);
        products.add(cv385);
        products.add(cv386);
        products.add(cv387);
        products.add(cv388);
        products.add(cv389);
        products.add(cv390);
        products.add(cv391);
        products.add(cv392);
        products.add(cv393);
        products.forEach(p -> {
            db.insert(TABLE_PRODUCT, null, p);
        });

    }

    private ContentValues createContentValuesProduct(String name,String des, long price, String image,
                                                    String date, int ci, int cate_id){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCT_NAME, name);
        cv.put(COLUMN_PRODUCT_DESCRIPTION, des);
        cv.put(COLUMN_PRODUCT_PRICE, price);
        cv.put(COLUMN_PRODUCT_IMAGE, image);
        cv.put(COLUMN_PRODUCT_CREATED_DATE, date);
        cv.put(COLUMN_PRODUCT_CURRENT_INVENTORY, ci);
        cv.put(COLUMN_PRODUCT_CATEGORY_ID, cate_id);
        return cv;
    }

    public ArrayList<Product> getProductByCateId(int CateId) {
        String SQL = "SELECT * FROM Product WHERE category_id = " + CateId;
        SQLiteDatabase  query = getReadableDatabase();

        Cursor cursor = query.rawQuery(SQL,null);

        ArrayList<Product> products = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            Long price = cursor.getLong(3);
            String img = cursor.getString(4);
            String create_date = cursor.getString(5);
            int current_inventory = cursor.getInt(6);
            int cate_id = cursor.getInt(7);
            Product product = new Product(id,name, img,price,description,create_date,current_inventory,cate_id);

            products.add(product);
        }

        return products;

    }

    public ArrayList<String> getAllNameProduct() {
        String SQL = "SELECT * FROM Product";
        SQLiteDatabase  query = getReadableDatabase();

        Cursor cursor = query.rawQuery(SQL,null);
        ArrayList<String> nameProducts = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            nameProducts.add(name);
        }
        return nameProducts;
    }

    public ArrayList<Product> getProductByName(String nameproduct) {
        String SQL = "SELECT * FROM Product WHERE name like '%" + nameproduct +"%'";
        SQLiteDatabase  query = getReadableDatabase();

        Cursor cursor = query.rawQuery(SQL,null);

        ArrayList<Product> products = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            Long price = cursor.getLong(3);
            String img = cursor.getString(4);
            String create_date = cursor.getString(5);
            int current_inventory = cursor.getInt(6);
            int cate_id = cursor.getInt(7);
            Product product = new Product(id,name, img,price,description,create_date,current_inventory,cate_id);

            products.add(product);
        }

        return products;

    }

    public ArrayList<Product> getProductById(Set<Integer> listid) {

        ArrayList<Product> products = new ArrayList<>();

        listid.forEach(i -> {
            String SQL = "SELECT * FROM Product WHERE _id = " + i;
            SQLiteDatabase  query = getReadableDatabase();

            Cursor cursor = query.rawQuery(SQL,null);


            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                Float price = cursor.getFloat(3);
                String img = cursor.getString(4);
                String create_date = cursor.getString(5);
                int current_inventory = cursor.getInt(6);
                int cate_id = cursor.getInt(7);
                Product product = new Product(id,name, img,price,description,create_date,current_inventory,cate_id);

                products.add(product);
            }
        });

        return products;

    }

    public ArrayList<Product> getProductById(int productId) {

        ArrayList<Product> products = new ArrayList<>();
        String SQL = "SELECT * FROM Product WHERE _id = " + productId;
        SQLiteDatabase  query = getReadableDatabase();

        Cursor cursor = query.rawQuery(SQL,null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                Float price = cursor.getFloat(3);
                String img = cursor.getString(4);
                String create_date = cursor.getString(5);
                int current_inventory = cursor.getInt(6);
                int cate_id = cursor.getInt(7);
                Product product = new Product(id,name, img,price,description,create_date,current_inventory,cate_id);

                products.add(product);
            }
        return products;
    }

    public Float getPriceProductById(Integer id) {

        String SQL = "SELECT * FROM Product WHERE _id = " + id;
        SQLiteDatabase query = getReadableDatabase();

        Cursor cursor = query.rawQuery(SQL, null);

        Float price = null;
        while (cursor.moveToNext()) {
            int pid = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            price = cursor.getFloat(3);
            String img = cursor.getString(4);
            String create_date = cursor.getString(5);
            int current_inventory = cursor.getInt(6);
            int cate_id = cursor.getInt(7);
        }


        return price;

    }

}
