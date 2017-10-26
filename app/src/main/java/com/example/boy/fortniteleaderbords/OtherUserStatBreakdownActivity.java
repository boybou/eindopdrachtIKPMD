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




        averageKillsPieChart =(PieChart) findViewById(R.id.averageKillsPieChart);
        winPercentagePieChart = (PieChart) findViewById(R.id.WinPercentagePieChart);
        gamesPlayedPieChart = (PieChart) findViewById(R.id.gamesPlayedPieChart);
        totalKillsPieCHart = (PieChart) findViewById(R.id.totalKillsPieChart);
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



            if(user.getSoloGames()!=0&&user.getDuoGames()!=0&&user.getsquadGames()!=0) {
                setData((user.getSoloKills() / user.getSoloGames()), (user.getDuoKills() / user.getDuoGames()), (user.getsquadKills() / user.getsquadGames()), "Average Solo Kills", "Average Duo Kills", "Average squad Kills", averageKillsPieChart);
            }
            if((user.getDuoKills()+user.getSoloKills()+user.getsquadKills())!=0){
                setData(user.getSoloKills(), user.getDuoKills(), user.getsquadKills(), "Solo Kills", "Duo Kills", "squad Kills", totalKillsPieCHart);
            }
            if((user.getSoloGames()+user.getDuoGames()+user.getsquadGames())!=0) {
                setData(user.getSoloGames(), user.getDuoGames(), user.getsquadGames(), "Solo Games", "Duo Games", "squad Games", gamesPlayedPieChart);
            }
            if(user.getSoloGames()!=0&&user.getDuoGames()!=0&&user.getsquadGames()!=0) {
                setData(((user.getSoloWins() / user.getSoloGames())*100), ((user.getDuoWins() / user.getDuoGames())*100), ((user.getsquadWins() / user.getsquadGames())*100), "Solo Win Percentage", "Duo Win Percentage", "squad Win Percentage", winPercentagePieChart);
            }




    }

    @Override
    public void onBackPressed() {

    }

    private void setData(int soloInteger1, int duoInteger2, int squadInteger3, String int1Name, String int2Name, String int3Name, PieChart pieChart){

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
            colors.add(Color.rgb(0, 51, 204));
        }
        if(duoIntegerentry) {
            colors.add(Color.rgb(153, 102, 255));
        }
        if(squadIntegerentry) {
            colors.add(Color.rgb(102, 0, 102));
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
    private void setData(float soloInteger1,float duoInteger2,float squadInteger3,String int1Name,String int2Name,String int3Name,PieChart pieChart){

        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();


        yValues.add(new Entry(soloInteger1,0));
        xValues.add(int1Name);

        yValues.add(new Entry(duoInteger2,1));
        xValues.add(int2Name);

        yValues.add(new Entry(squadInteger3,2));
        xValues.add(int3Name);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(0, 51, 204));
        colors.add(Color.rgb(153, 102, 255));
        colors.add(Color.rgb(102, 0, 102));

        if(pieChart.getTag().equals("0")){
            pieChart.setCenterText("Average Kills: "+ ((soloInteger1+duoInteger2+squadInteger3)/3));
        }else if(pieChart.getTag().equals("1")){

            pieChart.setCenterText("Average Win Percentage"+ ((soloInteger1+duoInteger2+squadInteger3)/3));

        }else if(pieChart.getTag().equals("2")){

            pieChart.setCenterText("Total Games Played: "+(soloInteger1+duoInteger2+squadInteger3));


        }else if(pieChart.getTag().equals("3"))
        {
            pieChart.setCenterText("Total kills: "+(soloInteger1+duoInteger2+squadInteger3));
        }



        PieDataSet dataSet = new PieDataSet(yValues,int1Name);
        dataSet.setValueTextSize(15);
        dataSet.setColors(colors);
        PieData pieData = new PieData(xValues,dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();



    }

}