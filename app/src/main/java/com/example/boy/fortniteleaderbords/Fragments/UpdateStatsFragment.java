package com.example.boy.fortniteleaderbords.Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boy.fortniteleaderbords.Database.DatabaseHelper;
import com.example.boy.fortniteleaderbords.Database.DatabaseInfo;
import com.example.boy.fortniteleaderbords.MainActivity;
import com.example.boy.fortniteleaderbords.Models.CurrentUser;
import com.example.boy.fortniteleaderbords.Models.User;
import com.example.boy.fortniteleaderbords.R;

import static java.lang.Integer.parseInt;


public class UpdateStatsFragment extends Fragment {
    private static final String TAG = "1";




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(container.getContext());


        View view = inflater.inflate(R.layout.fragment_update_stats,container,false);
        final EditText soloKills = (EditText) view.findViewById(R.id.soloKills);
        final EditText soloGames = (EditText) view.findViewById(R.id.soloGames);
        final EditText soloWins = (EditText) view.findViewById(R.id.soloWins);
        final EditText duoKills = (EditText) view.findViewById(R.id.duoKills);
        final EditText duoGames = (EditText) view.findViewById(R.id.duoGames);
        final EditText duoWins = (EditText) view.findViewById(R.id.duoWins);
        final EditText sqaudKills = (EditText) view.findViewById(R.id.sqaudKills);
        final EditText sqaudGames = (EditText) view.findViewById(R.id.sqaudGames);
        final EditText sqaudWins = (EditText) view.findViewById(R.id.sqaudWins);
        Button updateStatsButton = (Button)view.findViewById(R.id.updateStatsButton);
        Cursor rs = dbHelper.query(DatabaseInfo.userTableName,new String[]{"*"},DatabaseInfo.userTableCollumnNames.userName+"= '" +CurrentUser.getCurrentuserName()+"'",null,null,null,null);

        if(rs.moveToFirst()){
            User user = dbHelper.returnUser(CurrentUser.getCurrentuserName());
            if(user.getSoloKills()!=0){
                soloKills.setText(""+user.getSoloKills());
            }
            if(user.getSoloGames()!=0){
                soloGames.setText(""+user.getSoloGames());
            }
            if(user.getSoloWins()!=0){
                soloWins.setText(""+user.getSoloWins());
            }
            if(user.getDuoKills()!=0){
                duoKills.setText(""+user.getDuoKills());
            }
            if(user.getDuoGames()!=0){
                duoGames.setText(""+user.getDuoGames());
            }
            if(user.getDuoWins()!=0){
                duoWins.setText(""+user.getDuoWins());
            }
            if(user.getSqaudKills()!=0){
                sqaudKills.setText(""+user.getSqaudKills());
            }
            if(user.getSqaudGames()!=0){
                sqaudGames.setText(""+user.getSqaudGames());
            }
            if(user.getSqaudWins()!=0){
                sqaudWins.setText(""+user.getSqaudWins());
            }



        }



        updateStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    insertIntoDatabase(container.getContext(),new User(CurrentUser.getCurrentuserName(),new Integer(parseInt(soloKills.getText().toString())),
                            new Integer(parseInt(soloGames.getText().toString())),new Integer(parseInt(soloWins.getText().toString())),
                            new Integer(parseInt(duoKills.getText().toString())),new Integer(parseInt(duoGames.getText().toString())),
                            new Integer(parseInt(duoWins.getText().toString())),new Integer(parseInt(sqaudKills.getText().toString())),
                            new Integer(parseInt(sqaudGames.getText().toString())),new Integer(parseInt(sqaudWins.getText().toString()))));
                    startActivity(new Intent(container.getContext(),MainActivity.class));
                }catch (java.lang.NumberFormatException e){
                    Toast.makeText(container.getContext(),"Make sure you fill in all the number fields and make sure there are no numeric values over 10 million", Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }

    protected void insertIntoDatabase(Context context, User user){
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);
        ContentValues cv = new ContentValues();
        Cursor rs = dbHelper.query(DatabaseInfo.userTableName,new String[]{"*"},DatabaseInfo.userTableCollumnNames.userName+"= '" +user.getUserName()+"'",null,null,null,null);
        if(rs.moveToFirst()){
            dbHelper.statUpdate(user.getUserName());
        }
        cv.put(DatabaseInfo.userTableCollumnNames.userName,user.getUserName());
        cv.put(DatabaseInfo.userTableCollumnNames.soloKills,user.getSoloKills());
        cv.put(DatabaseInfo.userTableCollumnNames.soloGames,user.getSoloGames());
        cv.put(DatabaseInfo.userTableCollumnNames.soloWins,user.getSoloWins());
        cv.put(DatabaseInfo.userTableCollumnNames.duoKills,user.getDuoKills());
        cv.put(DatabaseInfo.userTableCollumnNames.duoGames,user.getDuoGames());
        cv.put(DatabaseInfo.userTableCollumnNames.duoWins,user.getDuoWins());
        cv.put(DatabaseInfo.userTableCollumnNames.sqaudKills,user.getSqaudKills());
        cv.put(DatabaseInfo.userTableCollumnNames.sqaudGames,user.getSqaudGames());
        cv.put(DatabaseInfo.userTableCollumnNames.sqaudWins,user.getSqaudWins());
        dbHelper.insert(DatabaseInfo.userTableName,null,cv);
        Cursor rs2 = dbHelper.query(DatabaseInfo.userTableName,new String[]{"*"},DatabaseInfo.userTableCollumnNames.userName+"= '" +user.getUserName()+"'",null,null,null,null);
        DatabaseUtils.dumpCursor(rs2);
        Toast.makeText(context,"Update Succesfull",Toast.LENGTH_SHORT).show();



    }
}