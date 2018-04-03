package id.tiregdev.si_pemandu.utils;

/**
 * Created by ilhamalfajri on 3/22/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper    {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sipeman1_ka";

    // Login table name
    private static final String TABLE_USER = "kaders";

    //Data Anak table name
    private static final String TABLE_DATA_ANAK = "data_anak";

    // Login Table Columns names
    private static final String KEY_ID = "id_kader";
    private static final String KEY_NAME = "nama_admin";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_NO_TELP = "no_hp";
    private static final String KEY_TANGGAL_JOIN = "tgl_join";
    private static final String KEY_TANGGAL_LAHIR = "tgl_lahir";
    private static final String KEY_BIO = "bio";


    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_USERNAME + " TEXT," + KEY_EMAIL + " TEXT UNIQUE,"
                + KEY_ALAMAT + " TEXT," + KEY_NO_TELP + " TEXT,"
                + KEY_TANGGAL_LAHIR + " TEXT,"+ KEY_TANGGAL_JOIN +" TEXT," + KEY_BIO + " TEXT" + ")" ;
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }



    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }
    public void addUser(String id_kader, String nama_admin, String username,
                        String email, String alamat, String no_hp,
                        String tgl_lahir, String tgl_join, String bio ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id_kader);
        values.put(KEY_NAME, nama_admin); // Name
        values.put(KEY_USERNAME, username);
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_ALAMAT, alamat); // Created At
        values.put(KEY_NO_TELP, no_hp);
        values.put(KEY_TANGGAL_LAHIR, tgl_lahir);
        values.put(KEY_TANGGAL_JOIN, tgl_join);
        values.put(KEY_BIO, bio);
        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    public void updateUser(String id_kader, String nama_admin, String username,String email,  String alamat, String no_hp,
                           String tanggal_lahir, String bio) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id_kader);
        values.put(KEY_NAME, nama_admin); // Name
        values.put(KEY_USERNAME, username); // Email
        values.put(KEY_EMAIL, email);
        values.put(KEY_ALAMAT, alamat); // Created At
        values.put(KEY_NO_TELP, no_hp);
        values.put(KEY_TANGGAL_LAHIR, tanggal_lahir);
        values.put(KEY_BIO, bio);


        // Inserting Row
        long id = db.update(TABLE_USER, values, KEY_ID + " = ? ", new String[] { String.valueOf(id_kader) });
        db.close(); // Closing database connection

        Log.d(TAG, "user edited into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("id_kader", cursor.getString(0));
            user.put("nama_admin", cursor.getString(1));
            user.put("username", cursor.getString(2));
            user.put("email", cursor.getString(3));
            user.put("alamat", cursor.getString(4));
            user.put("no_hp", cursor.getString(5));
            user.put("tgl_lahir", cursor.getString(6));
            user.put("tgl_join", cursor.getString(7));
            user.put("bio", cursor.getString(8));


        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}