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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.boy.fortniteleaderbords.Database.DatabaseHelper;
import com.example.boy.fortniteleaderbords.Database.DatabaseInfo;
import com.example.boy.fortniteleaderbords.MainActivity;
import com.example.boy.fortniteleaderbords.Models.CurrentUser;
import com.example.boy.fortniteleaderbords.Models.User;
import com.example.boy.fortniteleaderbords.R;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;


public class UpdateStatsFragment extends Fragment {
    private static final String TAG = "1";
    private String insertUrl = "https://boybou.nl/insert.php";
    private String deleteUrl = "https://boybou.nl/delete.php";
    private boolean localUpdateSucces;
    private  boolean serverUpdateSucces;

    private RequestQueue requestQueue0;
    private RequestQueue requestQueue1;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final DatabaseHelper dbHelper = DatabaseHelper.getHelper(container.getContext());

        if(localUpdateSucces){
            if(serverUpdateSucces){
                Toast.makeText(getContext(),"Update Successful",Toast.LENGTH_SHORT).show();
                localUpdateSucces = false;
                serverUpdateSucces = false;
            }else {
                localUpdateSucces = false;
                Toast.makeText(getContext(),"No internet connection, saving stats locally",Toast.LENGTH_SHORT).show();
            }
        }
        requestQueue0 = Volley.newRequestQueue(container.getContext());
        requestQueue1 = Volley.newRequestQueue(container.getContext());

        View view = inflater.inflate(R.layout.fragment_update_stats,container,false);
        final EditText soloKills = (EditText) view.findViewById(R.id.soloKills);
        final EditText soloGames = (EditText) view.findViewById(R.id.soloGames);
        final EditText soloWins = (EditText) view.findViewById(R.id.soloWins);
        final EditText duoKills = (EditText) view.findViewById(R.id.duoKills);
        final EditText duoGames = (EditText) view.findViewById(R.id.duoGames);
        final EditText duoWins = (EditText) view.findViewById(R.id.duoWins);
        final EditText squadKills = (EditText) view.findViewById(R.id.squadKills);
        final EditText squadGames = (EditText) view.findViewById(R.id.squadGames);
        final EditText squadWins = (EditText) view.findViewById(R.id.squadWins);
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
            if(user.getsquadKills()!=0){
                squadKills.setText(""+user.getsquadKills());
            }
            if(user.getsquadGames()!=0){
                squadGames.setText(""+user.getsquadGames());
            }
            if(user.getsquadWins()!=0){
                squadWins.setText(""+user.getsquadWins());
            }



        }



        updateStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(Integer.parseInt(duoWins.getText().toString())<=Integer.parseInt(duoGames.getText().toString())&&Integer.parseInt(soloWins.getText().toString())<=Integer.parseInt(soloGames.getText().toString())&&Integer.parseInt(squadWins.getText().toString())<=Integer.parseInt(squadGames.getText().toString())) {

                        insertIntoDatabase(container.getContext(), new User(CurrentUser.getCurrentuserName(), new Integer(parseInt(soloKills.getText().toString())),
                                new Integer(parseInt(soloGames.getText().toString())), new Integer(parseInt(soloWins.getText().toString())),
                                new Integer(parseInt(duoKills.getText().toString())), new Integer(parseInt(duoGames.getText().toString())),
                                new Integer(parseInt(duoWins.getText().toString())), new Integer(parseInt(squadKills.getText().toString())),
                                new Integer(parseInt(squadGames.getText().toString())), new Integer(parseInt(squadWins.getText().toString()))));
                        CurrentUser.setUpdated(true);
                        localUpdateSucces = true;
                        uploadStats(dbHelper.returnUser(CurrentUser.getCurrentuserName()));
                        startActivity(new Intent(getContext(),MainActivity.class));

                    }else{
                    Toast.makeText(container.getContext(),"You cant win more games than you have play",Toast.LENGTH_LONG).show();
                }
                }catch (java.lang.NumberFormatException e){
                    Toast.makeText(container.getContext(),"Make sure you fill in all the number fields and make sure there are no numeric values over 10 million", Toast.LENGTH_LONG).show();
                }


            }
        });


        return view;
    }
    public void uploadStats(final User user) {

        deleteUser(user.getUserName());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                serverUpdateSucces = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                serverUpdateSucces = false;
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userName", user.getUserName());
                params.put("soloKills", new String(Integer.toString(user.getSoloKills())));
                params.put("soloGames", new String(Integer.toString(user.getSoloGames())));
                params.put("soloWins", new String(Integer.toString(user.getSoloWins())));
                params.put("duoKills", new String(Integer.toString(user.getDuoKills())));
                params.put("duoGames", new String(Integer.toString(user.getDuoGames())));
                params.put("duoWins", new String(Integer.toString(user.getDuoWins())));
                params.put("squadKills", new String(Integer.toString(user.getsquadKills())));
                params.put("squadGames", new String(Integer.toString(user.getsquadGames())));
                params.put("squadWins", new String(Integer.toString(user.getsquadWins())));
                return params;

            }
        };

        requestQueue0.add(stringRequest);
    }
    public void deleteUser(final String userName){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, deleteUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<String,String>();
                params.put("userName",userName);
                return params;
            }
        };requestQueue1.add(stringRequest);
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
        cv.put(DatabaseInfo.userTableCollumnNames.squadKills,user.getsquadKills());
        cv.put(DatabaseInfo.userTableCollumnNames.squadGames,user.getsquadGames());
        cv.put(DatabaseInfo.userTableCollumnNames.squadWins,user.getsquadWins());
        dbHelper.insert(DatabaseInfo.userTableName,null,cv);
        Cursor rs2 = dbHelper.query(DatabaseInfo.userTableName,new String[]{"*"},DatabaseInfo.userTableCollumnNames.userName+"= '" +user.getUserName()+"'",null,null,null,null);
        DatabaseUtils.dumpCursor(rs2);




    }
}