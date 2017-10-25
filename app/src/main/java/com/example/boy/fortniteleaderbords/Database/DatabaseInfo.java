package com.example.boy.fortniteleaderbords.Database;

/**
 * Created by Boy on 10/24/2017.
 */

public class DatabaseInfo {
   public static final String userTableName = "user";
    public static final String currentUserTableName = "currentUser";
    public class userTableCollumnNames {
        public static final String userName = "userName";
        public static final String soloGames= "soloGames";
        public static final String duoGames= "duoGames";
        public static final String squadGames= "squadGames";
        public static final String soloKills= "soloKills";
        public static final String duoKills= "duoKills";
        public static final String squadKills= "squadKills";
        public static final String soloWins= "soloWins";
        public static final String duoWins= "duoWins";
        public static final String squadWins= "squadWins";
    }
    public class currentUserTableCollumnNames{
        public static final String userName = "currentUserUserName";
    }

}
