package com.example.boy.fortniteleaderbords.Models;

/**
 * Created by Boy on 10/24/2017.
 */

public class CurrentUser {
    public static String CurrentuserName = "";
    public static void setCurrentuserName(String name){
        CurrentuserName = name;
    }
    public static String getCurrentuserName(){
        return CurrentuserName;
    }
}
