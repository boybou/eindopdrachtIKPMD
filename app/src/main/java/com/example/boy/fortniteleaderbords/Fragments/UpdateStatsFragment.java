package com.example.boy.fortniteleaderbords.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boy.fortniteleaderbords.Database.DatabaseHelper;
import com.example.boy.fortniteleaderbords.Database.DatabaseInfo;
import com.example.boy.fortniteleaderbords.Models.CurrentUser;
import com.example.boy.fortniteleaderbords.Models.User;
import com.example.boy.fortniteleaderbords.R;

import static java.lang.Integer.parseInt;


public class UpdateStatsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        updateStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    insertIntoDatabase(container.getContext(),new User(CurrentUser.getCurrentuserName(),new Integer(parseInt(soloKills.getText().toString())),
                            new Integer(parseInt(soloGames.getText().toString())),new Integer(parseInt(soloWins.getText().toString())),
                            new Integer(parseInt(duoKills.getText().toString())),new Integer(parseInt(duoGames.getText().toString())),
                            new Integer(parseInt(duoWins.getText().toString())),new Integer(parseInt(sqaudKills.getText().toString())),
                            new Integer(parseInt(sqaudGames.getText().toString())),new Integer(parseInt(sqaudWins.getText().toString()))));
                }catch (java.lang.NumberFormatException e){
                    Toast.makeText(container.getContext(),"Make sure you fill in all the number Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
    protected void insertIntoDatabase(Context context,User user){
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);
       Cursor rs= dbHelper.query(DatabaseInfo.currentUserTableName,new String[]{"*"},null,null,null,null,null);
        Toast.makeText(context, ""+rs.moveToFirst(), Toast.LENGTH_SHORT).show();

    }
}