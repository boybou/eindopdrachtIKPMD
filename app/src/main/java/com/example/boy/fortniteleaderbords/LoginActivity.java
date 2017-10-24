package com.example.boy.fortniteleaderbords;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boy.fortniteleaderbords.Database.DatabaseHelper;
import com.example.boy.fortniteleaderbords.Database.DatabaseInfo;

import java.sql.ResultSet;


public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
        Cursor rs = dbHelper.query(DatabaseInfo.currentUserTableName,new String[]{DatabaseInfo.currentUserTableCollumnNames.userName},null,null,null,null,null);
//        dbHelper.onUpgrade(dbHelper.msqldb,1,1);

        if(rs.moveToFirst()){
            startActivity(new Intent(LoginActivity.this,EnterStatsActivity.class));
        }

        Button continueButton = (Button) findViewById(R.id.continueButton);
        final EditText userNameEditText = (EditText) findViewById(R.id.usernameEditText);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emptyString = "";
                if(!userNameEditText.getText().toString().equals(emptyString)){
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseInfo.currentUserTableCollumnNames.userName,userNameEditText.getText().toString());
                    dbHelper.insert(DatabaseInfo.currentUserTableName,null,cv);
                    startActivity(new Intent(LoginActivity.this,EnterStatsActivity.class));

                }else{
                    ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.Clayout);
                    Snackbar snackbar = Snackbar.make(constraintLayout,"Enter a Username",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });

    }
}
