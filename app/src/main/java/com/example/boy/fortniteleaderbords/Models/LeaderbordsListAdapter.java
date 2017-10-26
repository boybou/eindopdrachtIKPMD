package com.example.boy.fortniteleaderbords.Models;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.boy.fortniteleaderbords.R;

import java.util.List;

/**
 * Created by Boy on 10/26/2017.
 */

public class LeaderbordsListAdapter extends ArrayAdapter<User> {

    public LeaderbordsListAdapter(Context context, int resource, List<User> objects){
        super(context,resource,objects);
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null) {
            vh = new ViewHolder();
            LayoutInflater li = LayoutInflater.from(getContext());
            convertView = li.inflate(R.layout.view_content_row,parent,false);
            vh.name = (TextView) convertView.findViewById(R.id.subjectName);
            vh.kills = (TextView) convertView.findViewById(R.id.subjectKills);
            vh.games = (TextView) convertView.findViewById(R.id.subjectGames);
            vh.wins = (TextView) convertView.findViewById(R.id.subjectWins);
            vh.winPercentage = (TextView) convertView.findViewById(R.id.subjectWinPercentage);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder) convertView.getTag();
        }
        User user = getItem(position);
        vh.name.setText((CharSequence) user.getUserName());
        if(user.getUserName().equals(CurrentUser.getCurrentuserName())){
            vh.name.setText(""+ user.getUserName()+"(YOU)");
            vh.name.setTextColor(Color.RED);

        }
        vh.kills.setText((CharSequence) Integer.toString((user.getSoloKills()+user.getDuoKills()+user.getsquadKills()))+" Kills");
        vh.games.setText((CharSequence) Integer.toString((user.getDuoGames()+user.getSoloGames()+user.getsquadGames()))+" Games");
        vh.wins.setText((CharSequence) Integer.toString((user.getDuoWins()+user.getSoloWins()+user.getsquadWins()))+" Wins");
        vh.winPercentage.setText((CharSequence) Integer.toString((((((user.getSoloWins() / user.getSoloGames())*100)+((user.getDuoWins() / user.getDuoGames())*100)+((user.getsquadWins() / user.getsquadGames())*100))))/3)+"% winPercentage ");

        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView kills;
        TextView games;
        TextView wins;
        TextView winPercentage;
    }
}
