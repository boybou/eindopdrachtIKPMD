package com.example.boy.fortniteleaderbords;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.boy.fortniteleaderbords.Database.DatabaseHelper;
import com.example.boy.fortniteleaderbords.Database.DatabaseInfo;
import com.example.boy.fortniteleaderbords.Models.CurrentUser;

public class EnterStatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_stats);
        final DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
        Button but = (Button) findViewById(R.id.changeUsernameButton);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor rs = dbHelper.query(DatabaseInfo.currentUserTableName,new String[]{"*"},null,null,null,null,null);
                Toast toast = Toast.makeText(EnterStatsActivity.this,""+rs.moveToFirst(),Toast.LENGTH_SHORT);
                toast.show();
//                CurrentUser.setCurrentuserName("");
//                dbHelper.onChangeUser(dbHelper.msqldb);
//                startActivity(new Intent(EnterStatsActivity.this, LoginActivity.class));
            }
        });
    }
}
