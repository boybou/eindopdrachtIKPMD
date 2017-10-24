package com.example.boy.fortniteleaderbords.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Boy on 10/24/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static SQLiteDatabase msqldb;
    private static DatabaseHelper mInstance;
    public static final String dbname = "Knights.db";
    public static final int dbversion = 1;

    public DatabaseHelper(Context ctx){
        super(ctx,dbname,null,dbversion);
    }
    public static synchronized DatabaseHelper getHelper(Context ctx){
        if(mInstance == null){
            mInstance = new DatabaseHelper(ctx);
            msqldb = mInstance.getWritableDatabase();
        }
        return mInstance;
    }
    //    public void newTable()
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.currentUserTableName +" ("
                + DatabaseInfo.currentUserTableCollumnNames.userName + " TEXT,"
                + DatabaseInfo.currentUserTableCollumnNames.soloGames + " TEXT,"
                + DatabaseInfo.currentUserTableCollumnNames.soloKills + " TEXT,"
                + DatabaseInfo.currentUserTableCollumnNames.soloWins + " TEXT,"
                + DatabaseInfo.currentUserTableCollumnNames.duoGames + " TEXT,"
                + DatabaseInfo.currentUserTableCollumnNames.duoKills + " TEXT,"
                + DatabaseInfo.currentUserTableCollumnNames.duoWins + " TEXT,"
                + DatabaseInfo.currentUserTableCollumnNames.sqaudGames + " TEXT,"
                + DatabaseInfo.currentUserTableCollumnNames.sqaudKills + " TEXT,"
                + DatabaseInfo.currentUserTableCollumnNames.sqaudWins + " TEXT);");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.currentUserTableName);
        onCreate(sqLiteDatabase);
    }

    public DatabaseHelper(Context context ,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
    }
    public void insert(String table, String nullColumnHack,ContentValues values){
        msqldb.insert(table,nullColumnHack,values);
    }
    public Cursor query(String table, String[] columns, String selection, String[] selectargs, String groupBy, String having, String orderBy){
        return msqldb.query(table,columns,selection,selectargs,groupBy,having,orderBy);
    }
}
