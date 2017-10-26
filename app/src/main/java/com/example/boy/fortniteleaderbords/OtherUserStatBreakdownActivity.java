package com.example.boy.fortniteleaderbords;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.boy.fortniteleaderbords.Database.DatabaseHelper;
import com.example.boy.fortniteleaderbords.Database.DatabaseInfo;
import com.example.boy.fortniteleaderbords.Models.CurrentUser;
import com.example.boy.fortniteleaderbords.Models.User;
import com.example.boy.fortniteleaderbords.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OtherUserStatBreakdownActivity extends AppCompatActivity {

    private User user;
    private PieChart averageKillsPieChart;
    private PieChart winPercentagePieChart;
    private PieChart gamesPlayedPieChart;
    private PieChart totalKillsPieCHart;
    private DecimalFormat decimalFormat = new DecimalFormat("0.0");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_stat_breakdown);

        TextView userName = (TextView) findViewById(R.id.userName);
        Bundle b = getIntent().getExtras();
        if(b != null){
            user = new User(b.getString("userName"),b.getInt("soloKills"),b.getInt("soloGames"),b.getInt("soloWins"),b.getInt("duoKills"),b.getInt("duoGames"),b.getInt("duoWins"),b.getInt("squadKills"),b.getInt("squadGames"),b.getInt("squadWins"));
        }
        userName.setText("Stats of: "+user.getUserName());
        userName.setTextSize(20);
        userName.setTextColor(Color.BLACK);
        Button backToLBButton = (Button)findViewById(R.id.backToLB);
        backToLBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherUserStatBreakdownActivity.this,MainActivity.class));
            }
        });




        averageKillsPieChart =(PieChart) findViewById(R.id.averageKillsPieChart1);
        winPercentagePieChart = (PieChart) findViewById(R.id.WinPercentagePieChart1);
        gamesPlayedPieChart = (PieChart) findViewById(R.id.gamesPlayedPieChart1);
        totalKillsPieCHart = (PieChart) findViewById(R.id.totalKillsPieChart1);
        averageKillsPieChart.setTouchEnabled(false);
        winPercentagePieChart.setTouchEnabled(false);
        gamesPlayedPieChart.setTouchEnabled(false);
        totalKillsPieCHart.setTouchEnabled(false);
        averageKillsPieChart.setDescription("");
        winPercentagePieChart.setDescription("");
        gamesPlayedPieChart.setDescription("");
        totalKillsPieCHart.setDescription("");
        averageKillsPieChart.setTag("0");
        winPercentagePieChart.setTag("1");
        gamesPlayedPieChart.setTag("2");
        totalKillsPieCHart.setTag("3");
        averageKillsPieChart.getLegend().setEnabled(false);
        winPercentagePieChart.getLegend().setEnabled(false);
        gamesPlayedPieChart.getLegend().setEnabled(false);
        totalKillsPieCHart.getLegend().setEnabled(false);
        averageKillsPieChart.animateY(700, Easing.EasingOption.EaseInOutQuad);
        winPercentagePieChart.animateY(700, Easing.EasingOption.EaseInOutQuad);
        gamesPlayedPieChart.animateY(700, Easing.EasingOption.EaseInOutQuad);
        totalKillsPieCHart.animateY(700, Easing.EasingOption.EaseInOutQuad);




        setAverageData(user.getSoloKills(),user.getSoloGames(),user.getDuoKills(),user.getDuoGames(),user.getsquadKills(),user.getsquadGames(), "Average Solo Kills", "Average Duo Kills", "Average squad Kills", averageKillsPieChart);
        if((user.getDuoKills()+user.getSoloKills()+user.getsquadKills())!=0){
            setData(user.getSoloKills(), user.getDuoKills(), user.getsquadKills(), "Solo Kills", "Duo Kills", "squad Kills", totalKillsPieCHart);
        }
        setData(user.getSoloGames(), user.getDuoGames(), user.getsquadGames(), "Solo Games", "Duo Games", "squad Games", gamesPlayedPieChart);


        setPercentageData(user.getSoloWins(),user.getSoloGames(),user.getDuoWins(),user.getDuoGames(),user.getsquadWins(),user.getsquadGames(), "Solo Win Percentage", "Duo Win Percentage", "squad Win Percentage", winPercentagePieChart);




    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void setData(int soloInteger1,int duoInteger2,int squadInteger3,String int1Name,String int2Name,String int3Name,PieChart pieChart){

        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();

        boolean soloIntegerentry =false;
        boolean duoIntegerentry =false;
        boolean squadIntegerentry =false;
        if(soloInteger1!=0) {
            yValues.add(new Entry(soloInteger1, 0));
            xValues.add(int1Name);
            soloIntegerentry = true;

        }
        if(duoInteger2!=0) {
            yValues.add(new Entry(duoInteger2, 1));
            xValues.add(int2Name);
            duoIntegerentry = true;
        }
        if(squadInteger3!=0) {
            yValues.add(new Entry(squadInteger3, 2));
            xValues.add(int3Name);
            squadIntegerentry = true;
        }
        ArrayList<Integer> colors = new ArrayList<>();
        if(soloIntegerentry) {
            colors.add(Color.parseColor("#5c6bc0"));
        }
        if(duoIntegerentry) {
            colors.add(Color.parseColor("#8e99f3"));
        }
        if(squadIntegerentry) {
            colors.add(Color.parseColor("#26418f"));
        }
        if(pieChart.getTag().equals("0")){

            pieChart.setCenterText("Average Kills: "+ ((soloInteger1+duoInteger2+squadInteger3)/3));

        }else if(pieChart.getTag().equals("1")){

            pieChart.setCenterText("Average Win Percentage: "+ ((soloInteger1+duoInteger2+squadInteger3)/3)+"%");

        }else if(pieChart.getTag().equals("2")){

            pieChart.setCenterText("Total Games Played: "+(soloInteger1+duoInteger2+squadInteger3));

        }else if(pieChart.getTag().equals("3"))
        {
            pieChart.setCenterText("Total kills: "+(soloInteger1+duoInteger2+squadInteger3));
        }


        PieDataSet dataSet = new PieDataSet(yValues,int1Name);
        dataSet.setValueTextSize(10);
        dataSet.setColors(colors);
        PieData pieData = new PieData(xValues,dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();



    }
    private void setPercentageData(int soloInt1,int soloInt2,int duoInt1,int duoInt2,int squadInt1,int squadInt2,String int1Name,String int2Name,String int3Name,PieChart pieChart){

        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();

        float int1 = ((((float)soloInt1/(float)soloInt2)*100));
        float int2 = ((((float)duoInt1/(float)duoInt2)*100));
        float int3 = ((((float)squadInt1/(float)squadInt2)*100));
        boolean soloIntegerentry =false;
        boolean duoIntegerentry =false;
        boolean squadIntegerentry =false;
        if(int1!=0) {
            yValues.add(new Entry(int1, 0));
            xValues.add(int1Name);
            soloIntegerentry = true;

        }
        if(int2!=0) {
            yValues.add(new Entry(int2, 1));
            xValues.add(int2Name);
            duoIntegerentry = true;
        }
        if(int3!=0) {
            yValues.add(new Entry(int3, 2));
            xValues.add(int3Name);
            squadIntegerentry = true;
        }
        ArrayList<Integer> colors = new ArrayList<>();
        if(soloIntegerentry) {
            colors.add(Color.parseColor("#5c6bc0"));
        }
        if(duoIntegerentry) {
            colors.add(Color.parseColor("#8e99f3"));
        }
        if(squadIntegerentry) {
            colors.add(Color.parseColor("#26418f"));
        }
        pieChart.setCenterText("Average Win Percentage: "+ decimalFormat.format((int1+int2+int3)/3)+"%");


        PieDataSet dataSet = new PieDataSet(yValues,int1Name);
        dataSet.setValueTextSize(10);
        dataSet.setColors(colors);
        PieData pieData = new PieData(xValues,dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
    private void setAverageData(int soloInt1,int soloInt2,int duoInt1,int duoInt2,int squadInt1,int squadInt2,String int1Name,String int2Name,String int3Name,PieChart pieChart){

        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();

        float int1 = ((((float)soloInt1/(float)soloInt2)));
        float int2 = ((((float)duoInt1/(float)duoInt2)));
        float int3 = ((((float)squadInt1/(float)squadInt2)));
        boolean soloIntegerentry =false;
        boolean duoIntegerentry =false;
        boolean squadIntegerentry =false;
        if(int1!=0) {
            yValues.add(new Entry(int1, 0));
            xValues.add(int1Name);
            soloIntegerentry = true;

        }
        if(int2!=0) {
            yValues.add(new Entry(int2, 1));
            xValues.add(int2Name);
            duoIntegerentry = true;
        }
        if(int3!=0) {
            yValues.add(new Entry(int3, 2));
            xValues.add(int3Name);
            squadIntegerentry = true;
        }
        ArrayList<Integer> colors = new ArrayList<>();
        if(soloIntegerentry) {
            colors.add(Color.parseColor("#5c6bc0"));
        }
        if(duoIntegerentry) {
            colors.add(Color.parseColor("#8e99f3"));
        }
        if(squadIntegerentry) {
            colors.add(Color.parseColor("#26418f"));
        }

        pieChart.setCenterText("Average Kills: "+ decimalFormat.format((int1+int2+int3)/3));

        if(yValues.size()==0){

            pieChart.setCenterText("Average Kills: "+ "Less then 0.5, not able to draw chart");
        }

        PieDataSet dataSet = new PieDataSet(yValues,int1Name);
        dataSet.setValueTextSize(10);

        dataSet.setColors(colors);
        PieData pieData = new PieData(xValues,dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

}