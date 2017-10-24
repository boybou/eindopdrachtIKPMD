package com.example.boy.fortniteleaderbords;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.boy.fortniteleaderbords.Database.DatabaseHelper;
import com.example.boy.fortniteleaderbords.Database.DatabaseInfo;
import com.example.boy.fortniteleaderbords.Models.CurrentUser;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
//        dbHelper.onUpgrade(dbHelper.msqldb,1,1);

//        dbHelper.query(DatabaseInfo.userTableName,new String[]{DatabaseInfo.userTableCollumnNames.userName},null,null,null,null,null);


        if(checkUsername(dbHelper)){
            startActivity(new Intent(LoginActivity.this,EnterStatsActivity.class));
        }
//        ScrollView scrollView = (ScrollView) findViewById(R.id.recentUserssv);
//        scrollView.addView(new TextView(this));
        Button continueButton = (Button) findViewById(R.id.continueButton);
        final EditText userNameEditText = (EditText) findViewById(R.id.usernameEditText);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emptyString = "";
                if(!userNameEditText.getText().toString().equals(emptyString)){
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseInfo.userTableCollumnNames.userName,userNameEditText.getText().toString());
                    dbHelper.insert(DatabaseInfo.userTableName,null,cv);
                    CurrentUser.setCurrentuserName(userNameEditText.getText().toString());
                    startActivity(new Intent(LoginActivity.this,EnterStatsActivity.class));
                    Bundle b = new Bundle();
                }else{
                    ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.Clayout);
                    Snackbar snackbar = Snackbar.make(constraintLayout,"Enter a Username",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });

    }
    private boolean checkUsername(DatabaseHelper dbHelper){
//        dbHelper.onUpgrade(dbHelper.msqldb,1,1);
        Cursor rs = dbHelper.query(DatabaseInfo.currentUserTableName,new String[]{"*"},null,null,null,null,null);
        if(rs.moveToFirst()) {
            CurrentUser.setCurrentuserName(rs.getString(rs.getColumnIndex(DatabaseInfo.currentUserTableCollumnNames.userName)));
        }
        Cursor rs2 = dbHelper.query(DatabaseInfo.userTableName,new String[]{DatabaseInfo.userTableCollumnNames.userName}, CurrentUser.getCurrentuserName(),null,null,null,null);
        return rs2.moveToFirst();

    }
}
