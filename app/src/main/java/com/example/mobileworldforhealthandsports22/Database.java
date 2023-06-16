package com.example.mobileworldforhealthandsports22;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "your_database_name.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String TABLE_CART = "cart";
    private static final String TABLE_ORDER = "orderplace";

    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PRODUCT = "product";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_otype = "otype";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableQuery = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT"
                + ")";
        db.execSQL(createUserTableQuery);

        String createCartTableQuery = "CREATE TABLE " + TABLE_CART + "("
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PRODUCT + " TEXT,"
                + COLUMN_PRICE + " REAL,"
                + COLUMN_otype + " TEXT"
                + ")";
        db.execSQL(createCartTableQuery);

        String createOrderTableQuery = "CREATE TABLE " + TABLE_ORDER + "("
                + COLUMN_USERNAME + " TEXT,"
                + "fullname TEXT,"
                + "address TEXT,"
                + "contactno TEXT,"
                + "pincode INTEGER,"
                + "date TEXT,"
                + "time TEXT,"
                + "amount REAL,"
                + "otype TEXT"
                + ")";
        db.execSQL(createOrderTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Perform any necessary database upgrade tasks
    }

    public void register(String username, String email, String password) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);

        db.insert(TABLE_USERS, null, cv);
        db.close();
    }

    public int login(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();

        String[] selectionArgs = {username, password};
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?", selectionArgs);

        int result = cursor.moveToFirst() ? 1 : 0;

        cursor.close();
        db.close();

        return result;
    }

    public void addCart(String username, String product, float price, String otype) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_PRODUCT, product);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_otype, otype);

        db.insert(TABLE_CART, null, cv);
        db.close();
    }

    public int checkCart(String username, String product) {
        SQLiteDatabase db = getReadableDatabase();

        String[] selectionArgs = {username, product};
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CART + " WHERE " +
                COLUMN_USERNAME + "=? AND " + COLUMN_PRODUCT + "=?", selectionArgs);

        int result = cursor.moveToFirst() ? 1 : 0;

        cursor.close();
        db.close();

        return result;
    }

    public void removeCart(String username, String otype) {
        SQLiteDatabase db = getWritableDatabase();

        String[] whereArgs = {username, otype};
        db.delete(TABLE_CART, "username=? AND " + COLUMN_otype + "=?", whereArgs);
        db.close();
    }

    public ArrayList<String> getCartData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CART + " WHERE username = ? AND otype = ?", str);
        if (c.moveToFirst()) {
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "$" + price);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return arr;
    }

    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ORDER, null, cv);
        db.close();
    }

    public ArrayList getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from orderplace where username = ?", str);
        if (c.moveToFirst()) {
            do {
                arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3) + "$" + c.getString(4) + "$" + c.getString(5) + "$" +c.getString(6)+"$" + c.getString(7) + "$"+c.getString(8));
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public int checkAppointmentExists(String username,String fullname,String address,String contact,String date,String time){
        int result=0;
        String str[] = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where username = ? and fullname = ? and address = ? and contactno = ? and date = ? and time = ?",str);
        if (c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }
}

