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
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.boy.fortniteleaderbords.Database.DatabaseHelper;
import com.example.boy.fortniteleaderbords.Database.DatabaseInfo;
import com.example.boy.fortniteleaderbords.Models.StaticValues;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final DatabaseHelper dbHelper = DatabaseHelper.getHelper(this);
        if(!dbHelper.query(DatabaseInfo.userTableName,new String[]{"*"},null,null,null,null,null).moveToFirst())
        {
            dbHelper.onUpgrade(dbHelper.msqldb,2,2);
        }


        StaticValues.setUpdated(0);
        if(checkUsername(dbHelper)){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
        final Button recentUsersBut = (Button) findViewById(R.id.addUserRecentUserButton);
        final Button continueButton = (Button) findViewById(R.id.continueButton);
        final EditText userNameEditText = (EditText) findViewById(R.id.usernameEditText);
        final boolean[] clicked = {false};
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.recentUserssv);
        final ScrollView recentUserScrolv = (ScrollView)findViewById(R.id.recentUserscrolv);
        recentUsersBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(clicked[0]){
                    userNameEditText.setVisibility(View.VISIBLE);
                    continueButton.setVisibility(View.VISIBLE);
                    recentUsersBut.setText("Recent Users");
                    linearLayout.removeAllViews();
                    recentUserScrolv.setVisibility(View.INVISIBLE);

                    clicked[0] =false;
                }else if(!clicked[0]) {

                    userNameEditText.setVisibility(View.INVISIBLE);
                    continueButton.setVisibility(View.INVISIBLE);
                    drawRecentUsers(linearLayout, dbHelper);
                    recentUserScrolv.setVisibility(View.VISIBLE);
                    recentUsersBut.setText("Add User");
                    clicked[0] =true;
                }


            }
        });
        recentUsersBut.performClick();


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emptyString = "";
                if(!userNameEditText.getText().toString().equals(emptyString)){
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseInfo.userTableCollumnNames.userName,userNameEditText.getText().toString());
                    dbHelper.insert(DatabaseInfo.userTableName,null,cv);
                    ContentValues cv2 = new ContentValues();
                    cv2.put(DatabaseInfo.currentUserTableCollumnNames.userName,userNameEditText.getText().toString());
                    dbHelper.insert(DatabaseInfo.currentUserTableName,null,cv2);
                    StaticValues.setCurrentuserName(userNameEditText.getText().toString());
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else{
                    ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.Clayout);
                    Snackbar snackbar = Snackbar.make(constraintLayout,"Enter a Username",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private boolean checkUsername(DatabaseHelper dbHelper){
        Cursor rs = dbHelper.query(DatabaseInfo.currentUserTableName,new String[]{"*"},null,null,null,null,null);
        if(rs.moveToFirst()) {
            StaticValues.setCurrentuserName(rs.getString(rs.getColumnIndex(DatabaseInfo.currentUserTableCollumnNames.userName)));
        }
        Cursor rs2 = dbHelper.query(DatabaseInfo.userTableName,new String[]{DatabaseInfo.userTableCollumnNames.userName},DatabaseInfo.userTableCollumnNames.userName+"= '" + StaticValues.getCurrentuserName()+"'",null,null,null,null);
        return rs2.moveToFirst();

    }
    private void drawRecentUsers(LinearLayout linearLayout, final DatabaseHelper dbHelper){
        final Cursor rs = dbHelper.query(DatabaseInfo.userTableName,new String[]{DatabaseInfo.userTableCollumnNames.userName},null,null,null,null,null);
//        Log.d("okok",""+rs.getCount());
        for(int i =0;i<rs.getCount();i++)
        {
            final Button button = new Button(this);
            rs.moveToNext();
            button.setText(rs.getString(rs.getColumnIndex(DatabaseInfo.userTableCollumnNames.userName)));
            linearLayout.addView(button,WRAP_CONTENT);

            final ContentValues cv2 = new ContentValues();

            cv2.put(DatabaseInfo.currentUserTableCollumnNames.userName,rs.getString(rs.getColumnIndex(DatabaseInfo.userTableCollumnNames.userName)));final String name = rs.getString(rs.getColumnIndex(DatabaseInfo.userTableCollumnNames.userName));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbHelper.onChangeUser(dbHelper.msqldb);
                    dbHelper.insert(DatabaseInfo.currentUserTableName,null,cv2);
                    StaticValues.setCurrentuserName(name);
//                    Toast.makeText(LoginActivity.this,StaticValues.getCurrentuserName(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
            });





        }

    }
}
