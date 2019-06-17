package com.example.naweed.foodmania.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.naweed.foodmania.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productManager";
    private static final String TABLE_PRODUCTS = "product";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "description";
    private static final String KEY_PRICE = "price";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_AVAIL = "avail";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        // Create tables again
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PRICE + " DOUBLE," + KEY_WEIGHT + " NUMBER,"+KEY_DESC+" TEXT," +KEY_AVAIL+" TEXT"+")";
        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new product
   public void addProduct(Product product) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, product.getName()); //Product Name
        values.put(KEY_PRICE, product.getPrice().doubleValue()); //Product Price
        values.put(KEY_DESC,product.getDescription());
        values.put(KEY_WEIGHT,product.getWeight());
        values.put(KEY_AVAIL,product.getAvail());

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Product product = new Product();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS + " WHERE id = "+id,null);
        if (cursor != null)
            cursor.moveToFirst();
        do {
            product.setId(Integer.parseInt(cursor.getString(0)));
            product.setName(cursor.getString(1));
            product.setPrice(BigDecimal.valueOf(cursor.getDouble(2)));
            product.setWeight(cursor.getDouble(3));
            product.setDescription(cursor.getString(4));
            product.setAvail(cursor.getString(5));
            // Adding contact to list
        } while (cursor.moveToNext());

        // return product
        return product;
    }

    public Product getProductByName(String name) {
        List<Product> productList = getAllProducts();

        for (Product p :
                productList) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // code to get all contacts in a list view
    public List<Product> getAllProducts(){
        List<Product> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setName(cursor.getString(1));
                product.setPrice(BigDecimal.valueOf(cursor.getDouble(2)));
                product.setWeight(cursor.getDouble(3));
                product.setDescription(cursor.getString(4));
                product.setAvail(cursor.getString(5));
                // Adding contact to list
                contactList.add(product);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single product
    public int updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getName());
        values.put(KEY_PRICE, String.valueOf(product.getPrice()));
        values.put(KEY_AVAIL, product.getAvail());
        values.put(KEY_WEIGHT, product.getWeight());
        values.put(KEY_DESC, product.getDescription());

        // updating row
        return db.update(TABLE_PRODUCTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(product.getId()) });
    }

    public void makeAvail(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_AVAIL,"AVAILABLE");

         db.update(TABLE_PRODUCTS,values,KEY_ID + " = ?",
                new String[] { String.valueOf(product.getId()) });
        System.out.println("ID = " +String.valueOf(product.getId()));
        System.out.println("AFTER MAKE KEK = " + getProduct(product.getId()).getAvail());
    }

    public void makeNotAvail(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_AVAIL, "NOT AVAILABLE");

        db.update(TABLE_PRODUCTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(product.getId())});
        System.out.println("ID = " + String.valueOf(product.getId()));
        System.out.println("AFTER MAKE KEK = " + getProduct(product.getId()).getAvail());
    }

    // Deleting single product
    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_ID + " = ?",
                new String[] { String.valueOf(product.getId()) });
        db.close();
    }

    // Getting contacts Count
    public int getProductCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public List<Product> search(String term) {
        String searchQuery = "SELECT  * FROM " + TABLE_PRODUCTS + " WHERE " + KEY_NAME + " LIKE '%" + term + "%' OR " + KEY_DESC + " LIKE '%" + term + "%';";
        SQLiteDatabase db = this.getWritableDatabase();

        List<Product> searchedList = new ArrayList<>();
        Cursor cursor = db.rawQuery(searchQuery, null);
        System.out.println("Coming to the search Method");
        if (cursor.moveToFirst()) {
            do {

                System.out.println("Going inside the loop");
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setName(cursor.getString(1));
                product.setPrice(BigDecimal.valueOf(cursor.getDouble(2)));
                product.setWeight(cursor.getDouble(3));
                product.setDescription(cursor.getString(4));
                product.setAvail(cursor.getString(5));
                System.out.println("PRODUCT ___>" + product);
                // Adding contact to list
                searchedList.add(product);
            } while (cursor.moveToNext());

            System.out.println("LI" + searchedList);
        }


        return searchedList;
    }


}