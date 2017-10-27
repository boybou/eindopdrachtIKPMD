package com.example.boy.fortniteleaderbords;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.boy.fortniteleaderbords.Database.DatabaseHelper;
import com.example.boy.fortniteleaderbords.Fragments.LeaderbordsFragment;
import com.example.boy.fortniteleaderbords.Fragments.StatBreakdownFragment;
import com.example.boy.fortniteleaderbords.Fragments.UpdateStatsFragment;
import com.example.boy.fortniteleaderbords.Models.StaticValues;

public class MainActivity extends AppCompatActivity {



    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    final DatabaseHelper dbHelper=DatabaseHelper.getHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager =(ViewPager) findViewById(R.id.container);

        mViewPager.setCurrentItem(0);

        setupViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.getAdapter().notifyDataSetChanged();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        if(StaticValues.getUpdated()==1){
            mViewPager.setCurrentItem(1);
            Toast.makeText(MainActivity.this,"Update Successful",Toast.LENGTH_LONG).show();
            StaticValues.setUpdated(0);
        }
        else if(StaticValues.getUpdated()==2){
            mViewPager.setCurrentItem(1);
            Toast.makeText(MainActivity.this,"No internet connection, saving stats locally",Toast.LENGTH_LONG).show();
            StaticValues.setUpdated(0);
        }
        else{
            StaticValues.setUpdated(0);
        }




    }
    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new LeaderbordsFragment(),"Leaderbords");
        adapter.addFragment(new UpdateStatsFragment(),"Update Your Stats");
        adapter.addFragment(new StatBreakdownFragment(),"Your Stats Breakdown");

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
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();
        switch (id){
            case R.id.ChangeUser:
                StaticValues.setCurrentuserName("");
                dbHelper.onChangeUser(dbHelper.msqldb);
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
