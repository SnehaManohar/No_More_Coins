package com.example.ss4ne.bus;

/**
 * Created by ss4ne on 09-05-2017.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_NAM = "name";
    static final String KEY_MON = "money";
    static final String KEY_PASS = "password";

    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "MyDB3";
    static final String DATABASE_TABLE = "contacts";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE =
            "create table contacts (_id integer primary key autoincrement, name text not null, money text not null, password text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }
    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }
    //---insert a contact into the database---
    public long insertUser(String name, String password)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAM, name);
        initialValues.put(KEY_PASS, password);
        initialValues.put(KEY_MON,100);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    public Cursor getAllContacts()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAM,
                KEY_PASS,KEY_MON }, null, null, null, null, null);
    }
    public Cursor getContact(long rowId) throws SQLException
    {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_NAM, KEY_PASS,KEY_MON}, KEY_ROWID + "=" + rowId, null,null, null, null, null);
        if (mCursor != null)
        {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public boolean updatesub(int row,String mon)
    {
        ContentValues cv= new ContentValues();
        cv.put(KEY_MON,mon);
        return db.update(DATABASE_TABLE,cv,KEY_ROWID + "=" + row,null)>0;
    }

}