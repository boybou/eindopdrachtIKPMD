package com.example.boy.fortniteleaderbords.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.boy.fortniteleaderbords.Models.CurrentUser;
import com.example.boy.fortniteleaderbords.Models.User;

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
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.userTableName +" ("
                + DatabaseInfo.userTableCollumnNames.userName + " TEXT,"
                + DatabaseInfo.userTableCollumnNames.soloGames + " INTEGER,"
                + DatabaseInfo.userTableCollumnNames.soloKills + " INTEGER,"
                + DatabaseInfo.userTableCollumnNames.soloWins + " INTEGER,"
                + DatabaseInfo.userTableCollumnNames.duoGames + " INTEGER,"
                + DatabaseInfo.userTableCollumnNames.duoKills + " INTEGER,"
                + DatabaseInfo.userTableCollumnNames.duoWins + " INTEGER,"
                + DatabaseInfo.userTableCollumnNames.squadGames + " INTEGER,"
                + DatabaseInfo.userTableCollumnNames.squadKills + " INTEGER,"
                + DatabaseInfo.userTableCollumnNames.squadWins + " INTEGER); ");
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.currentUserTableName +" (" + DatabaseInfo.currentUserTableCollumnNames.userName + " TEXT);");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.userTableName);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.currentUserTableName);
        onCreate(sqLiteDatabase);
    }
    public void onChangeUser(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseInfo.currentUserTableName);
        sqLiteDatabase.execSQL("CREATE TABLE " + DatabaseInfo.currentUserTableName +" (" + DatabaseInfo.currentUserTableCollumnNames.userName + " TEXT);");
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
    public void statUpdate(String userName){
        msqldb.execSQL("DELETE FROM "+DatabaseInfo.userTableName+" WHERE "+DatabaseInfo.userTableCollumnNames.userName+" = '"+userName+"'");

    }
    public User returnUser(String userName){
       Cursor rs = msqldb.query(DatabaseInfo.userTableName,new String[]{"*"},DatabaseInfo.userTableCollumnNames.userName+"= '" + userName+"'",null,null,null,null);
        rs.moveToFirst();
        return new User(userName,rs.getInt(rs.getColumnIndex(DatabaseInfo.userTableCollumnNames.soloKills))
                ,rs.getInt(rs.getColumnIndex(DatabaseInfo.userTableCollumnNames.soloGames))
                ,rs.getInt(rs.getColumnIndex(DatabaseInfo.userTableCollumnNames.soloWins))
                ,rs.getInt(rs.getColumnIndex(DatabaseInfo.userTableCollumnNames.duoKills))
        ,rs.getInt(rs.getColumnIndex(DatabaseInfo.userTableCollumnNames.duoGames))
        ,rs.getInt(rs.getColumnIndex(DatabaseInfo.userTableCollumnNames.duoWins))
        ,rs.getInt(rs.getColumnIndex(DatabaseInfo.userTableCollumnNames.squadKills))
        ,rs.getInt(rs.getColumnIndex(DatabaseInfo.userTableCollumnNames.squadGames))
        ,rs.getInt(rs.getColumnIndex(DatabaseInfo.userTableCollumnNames.squadWins)));
    }
}
