package com.example.boy.fortniteleaderbords.Models;

/**
 * Created by Boy on 10/24/2017.
 */

public class CurrentUser {
    public static String CurrentuserName = "";
    public static boolean updated = false;
    public static void setCurrentuserName(String name){
        CurrentuserName = name;
    }
    public static String getCurrentuserName(){
        return CurrentuserName;
    }
    public static void setUpdated(boolean bolean){
        updated=bolean;
    }
    public static boolean getUpdated(){
        return updated;
    }
}
