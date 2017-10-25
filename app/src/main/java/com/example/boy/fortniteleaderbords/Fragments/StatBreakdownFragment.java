package com.example.boy.fortniteleaderbords.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.boy.fortniteleaderbords.Models.CurrentUser;
import com.example.boy.fortniteleaderbords.R;

public class StatBreakdownFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat_breakdown,container,false);
        TextView textView = (TextView)view.findViewById(R.id.wanks);
        textView.setText(CurrentUser.getCurrentuserName());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getView();
    }
}