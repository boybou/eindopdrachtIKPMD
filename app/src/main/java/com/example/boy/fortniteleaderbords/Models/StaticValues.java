package com.example.boy.fortniteleaderbords.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boy on 10/24/2017.
 */

public class StaticValues {
    public static String CurrentuserName = "";
    public static List<User> list = new ArrayList<>();

    public static List<User> getList() {
        return list;
    }

    public static void setList(List<User> list) {
        StaticValues.list = list;
    }

    public static int updated = 0;
    public static void setCurrentuserName(String name){
        CurrentuserName = name;
    }
    public static String getCurrentuserName(){
        return CurrentuserName;
    }
    public static void setUpdated(int bolean){
        updated=bolean;
    }
    public static int getUpdated(){
        return updated;
    }
}
