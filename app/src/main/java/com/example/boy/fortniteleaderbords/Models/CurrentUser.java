package com.example.boy.fortniteleaderbords.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boy on 10/24/2017.
 */

public class CurrentUser {
    public static String CurrentuserName = "";
    public static List<User> list = new ArrayList<>();

    public static List<User> getList() {
        return list;
    }

    public static void setList(List<User> list) {
        CurrentUser.list = list;
    }

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
