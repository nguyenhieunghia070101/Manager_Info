package com.example.appqlkh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
public class DBHandle extends SQLiteOpenHelper {
    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "qlkh";
    // below int is our database version
    private static final int DB_VERSION = 1;
    // below variable is for our table name.
    private static final String TABLE_NAME = "KhachHang";
    private static final String ID_COL = "id_cus";
    private static final String NAME_COL = "name_cus";
    private static final String ADDRESS_COL = "address_cus";
    private static final String PHONE_COL = "phone_cus";
    private static final String EMAIL_COL = "email_cus";
    // creating a constructor for our database handler.
    public DBHandle(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + ADDRESS_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + EMAIL_COL + " TEXT)";
        db.execSQL(query);
    }
    // this method is use to add new course to our sqlite database.
    public void addNewCus(String CusName, String CusAddress, String CusPhone, String CusEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, CusName);
        values.put(ADDRESS_COL, CusAddress);
        values.put(PHONE_COL, CusPhone);
        values.put(EMAIL_COL, CusEmail);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public ArrayList<CustomersModel> readCustomers()
    {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();
        // on below line we are creating a cursor with query to
        // read data from database.
        Cursor cursorCus
                = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        // on below line we are creating a new array list.
        ArrayList<CustomersModel> customersModelArrayList
                = new ArrayList<>();
        // moving our cursor to first position.
        if (cursorCus.moveToFirst()) {
            do {
                customersModelArrayList.add(new CustomersModel(
                        cursorCus.getString(0),
                        cursorCus.getString(1),
                        cursorCus.getString(2),
                        cursorCus.getString(3),
                        cursorCus.getString(4)
                        ));
            } while (cursorCus.moveToNext());
            // moving our cursor to next.
        }
        cursorCus.close();
        return customersModelArrayList;
    }

    public void updateCus(String originalID, String cusName, String cusAddress, String cusPhone, String cusEmail) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(ID_COL, cusId);
        values.put(NAME_COL, cusName);
        values.put(ADDRESS_COL ,cusAddress );
        values.put(PHONE_COL, cusPhone);
        values.put(EMAIL_COL, cusEmail);
        db.update(TABLE_NAME, values, "id_cus=?", new String[]{originalID});
        db.close();
    }

    // below is the method for deleting our course.
    public void deleteCus(String cusId) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "id_cus=?", new String[]{cusId});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}

