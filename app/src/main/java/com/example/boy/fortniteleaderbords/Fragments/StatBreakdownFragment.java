package com.example.boy.fortniteleaderbords.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

public class StatBreakdownFragment extends Fragment {

    private PieChart averageKillsPieChart;
    private PieChart winPercentagePieChart;
    private PieChart gamesPlayedPieChart;
    private PieChart totalKillsPieCHart;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat_breakdown,container,false);
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(container.getContext());



        averageKillsPieChart =(PieChart) view.findViewById(R.id.averageKillsPieChart);
        winPercentagePieChart = (PieChart) view.findViewById(R.id.WinPercentagePieChart);
        gamesPlayedPieChart = (PieChart) view.findViewById(R.id.gamesPlayedPieChart);
        totalKillsPieCHart = (PieChart) view.findViewById(R.id.totalKillsPieChart);
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

        if(dbHelper.query(DatabaseInfo.userTableName,new String[]{DatabaseInfo.userTableCollumnNames.userName},DatabaseInfo.userTableCollumnNames.userName+"= '" +CurrentUser.getCurrentuserName()+"'",null,null,null,null).moveToFirst());
        {
            User user = dbHelper.returnUser(CurrentUser.getCurrentuserName());
            if(user.getSoloGames()!=0&&user.getDuoGames()!=0&&user.getSqaudGames()!=0) {
                setData((user.getSoloKills() / user.getSoloGames()), (user.getDuoKills() / user.getDuoGames()), (user.getSqaudKills() / user.getSqaudGames()), "Average Solo Kills", "Average Duo Kills", "Average Sqaud Kills", averageKillsPieChart);
            }
                setData(user.getSoloKills(), user.getDuoKills(), user.getSqaudKills(), "Solo Kills", "Duo Kills", "Sqaud Kills", totalKillsPieCHart);
        }


        return view;
    }
    private void setData(int soloInteger1,int duoInteger2,int sqaudInteger3,String int1Name,String int2Name,String int3Name,PieChart pieChart){

        ArrayList<Entry> yValues = new ArrayList<>();
        ArrayList<String> xValues = new ArrayList<>();


        yValues.add(new Entry(soloInteger1,0));
        xValues.add(int1Name);

        yValues.add(new Entry(duoInteger2,1));
        xValues.add(int2Name);

        yValues.add(new Entry(sqaudInteger3,2));
        xValues.add(int3Name);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(0, 51, 204));
        colors.add(Color.rgb(153, 102, 255));
        colors.add(Color.rgb(102, 0, 102));

        if(pieChart.getTag().equals("0")){
            pieChart.setCenterText("Average Kills: "+ ((soloInteger1+duoInteger2+sqaudInteger3)/3));
        }else if(pieChart.getTag().equals("1")){


        }else if(pieChart.getTag().equals("2")){


        }else if(pieChart.getTag().equals("3"))
        {
            pieChart.setCenterText("Total kills: "+(soloInteger1+duoInteger2+sqaudInteger3));
        }



        PieDataSet dataSet = new PieDataSet(yValues,int1Name);
        dataSet.setValueTextSize(15);
        dataSet.setColors(colors);
        PieData pieData = new PieData(xValues,dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();



    }

}