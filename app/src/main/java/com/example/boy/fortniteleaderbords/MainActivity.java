package com.example.boy.fortniteleaderbords;

import android.Manifest;
import android.app.Application;
import android.app.DownloadManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.boy.fortniteleaderbords.Database.DatabaseHelper;
import com.example.boy.fortniteleaderbords.Fragments.LeaderbordsFragment;
import com.example.boy.fortniteleaderbords.Fragments.StatBreakdownFragment;
import com.example.boy.fortniteleaderbords.Fragments.UpdateStatsFragment;
import com.example.boy.fortniteleaderbords.Models.CurrentUser;
import com.example.boy.fortniteleaderbords.Models.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String insertUrl = "https://boybou.nl/insert.php";
    private String deleteUrl = "https://boybou.nl/delete.php";
    private RequestQueue requestQueue;
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    final DatabaseHelper dbHelper=DatabaseHelper.getHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
//        Toast.makeText(this,CurrentUser.getCurrentuserName(),Toast.LENGTH_SHORT).show();
//        final DatabaseHelper dbHelper=DatabaseHelper.getHelper(this);
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager =(ViewPager) findViewById(R.id.container);


        setupViewPager(mViewPager);
        mViewPager.getAdapter().notifyDataSetChanged();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);




    }
    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new UpdateStatsFragment(),"Update Stats");
        adapter.addFragment(new StatBreakdownFragment(),"Stat Breakdown");
        adapter.addFragment(new LeaderbordsFragment(),"Leaderbords");
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();
        switch (id){
            case R.id.ChangeUser:
                CurrentUser.setCurrentuserName("");
                dbHelper.onChangeUser(dbHelper.msqldb);
                Log.d("ah oh","ah oh");
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                return true;
            case R.id.UploadStats:
                uploadStats(dbHelper.returnUser(CurrentUser.getCurrentuserName()));
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void uploadStats(final User user){

        if(CurrentUser.getUpdated()) {
            deleteUser(user.getUserName());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("userName",user.getUserName());
                    params.put("soloKills",new String(Integer.toString(user.getSoloKills())));
                    params.put("soloGames",new String(Integer.toString(user.getSoloGames())));
                    params.put("soloWins",new String(Integer.toString(user.getSoloWins())));
                    params.put("duoKills",new String(Integer.toString(user.getDuoKills())));
                    params.put("duoGames",new String(Integer.toString(user.getDuoGames())));
                    params.put("duoWins",new String(Integer.toString(user.getDuoWins())));
                    params.put("squadKills",new String(Integer.toString(user.getsquadKills())));
                    params.put("squadGames",new String(Integer.toString(user.getsquadGames())));
                    params.put("squadWins",new String(Integer.toString(user.getsquadWins())));
                    return params;
                }
            };
            Log.d("whoooo","whowohwohwoh");
            requestQueue.add(stringRequest);
            finish();
        }else if(!CurrentUser.getUpdated()){
            Toast.makeText(MainActivity.this,"Please make sure to update your stats before uploading",Toast.LENGTH_LONG).show();
        }
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
        };requestQueue.add(stringRequest);
        finish();
    }
}
