package com.example.boy.fortniteleaderbords.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.boy.fortniteleaderbords.Models.CurrentUser;
import com.example.boy.fortniteleaderbords.Models.LeaderbordsListAdapter;
import com.example.boy.fortniteleaderbords.Models.User;
import com.example.boy.fortniteleaderbords.OtherUserStatBreakdownActivity;
import com.example.boy.fortniteleaderbords.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LeaderbordsFragment extends Fragment {
    private ListView mListView;
    private RequestQueue requestQueue;
    private String getUrl = "https://boybou.nl/showUsers.php";
    private LeaderbordsListAdapter mAdapter;
    private List<User> userModels = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderbords,container,false);

        requestQueue = Volley.newRequestQueue(container.getContext());
        mListView = (ListView) view.findViewById(R.id.leaderbordslv);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(container.getContext(), OtherUserStatBreakdownActivity.class);
                Bundle b = new Bundle();
                userModels = CurrentUser.getList();
                b.putString("userName",userModels.get(i).getUserName());
                b.putInt("soloKills",userModels.get(i).getSoloKills());
                b.putInt("soloGames",userModels.get(i).getSoloGames());
                b.putInt("soloWins",userModels.get(i).getSoloWins());
                b.putInt("duoKills",userModels.get(i).getDuoKills());
                b.putInt("duoGames",userModels.get(i).getDuoGames());
                b.putInt("duoWins",userModels.get(i).getDuoWins());
                b.putInt("squadKills",userModels.get(i).getsquadKills());
                b.putInt("squadGames",userModels.get(i).getsquadGames());
                b.putInt("squadWins",userModels.get(i).getsquadWins());
                intent.putExtras(b);
                startActivity(intent);


            }
        });



        getUsers();
        mAdapter = new LeaderbordsListAdapter(container.getContext(),0,CurrentUser.getList());
        mListView.setAdapter(mAdapter);

        return view;
    }
    public void getUsers(){
       final List<User> list = new ArrayList<User>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, getUrl, (String) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray users = response.getJSONArray("users");
                    for (int i = 0; i < users.length(); i++) {
                        JSONObject user = users.getJSONObject(i);
                        User user111 = new User(user.getString("userName"),user.getInt("soloKills"),user.getInt("soloGames"),user.getInt("soloWins"),user.getInt("duoKills"),user.getInt("duoGames"),user.getInt("duoWins"),user.getInt("squadKills"),user.getInt("squadGames"),user.getInt("squadWins"));


                        list.add(new User(user.getString("userName"),user.getInt("soloKills"),user.getInt("soloGames"),user.getInt("soloWins"),user.getInt("duoKills"),user.getInt("duoGames"),user.getInt("duoWins"),user.getInt("squadKills"),user.getInt("squadGames"),user.getInt("squadWins")));



                    }

                    CurrentUser.setList(list);





                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(container.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }); requestQueue.add(jsonObjectRequest);


    }


}
