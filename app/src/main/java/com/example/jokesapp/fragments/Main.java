package com.example.jokesapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jokesapp.Adapters.JokeAdapter;
import com.example.jokesapp.R;
import com.example.jokesapp.model.Joke;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static android.content.ContentValues.TAG;


public class Main extends Fragment {

   String jokeApiURL;
   RecyclerView jokesList;
   JokeAdapter adapter;
   List<Joke> allJokes;

   public Main(String url){
       this.jokeApiURL = url;
       allJokes = new ArrayList<>();
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main2, container, false);
        jokesList = v.findViewById(R.id.jokesList);
        jokesList.setLayoutManager(new LinearLayoutManager((v.getContext())));
        adapter = new JokeAdapter(allJokes);
        jokesList.setAdapter(adapter);
        getJokes(jokeApiURL);
        return v;
    }

    public  void getJokes(String url){
       //extract the json data using volley

        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d(TAG, "onResponse: " + response);
                try {
                    JSONArray jokesArray = response.getJSONArray("jokes");
                    for(int i = 0; i < Integer.parseInt(response.getString("amount")); i++){
                        //Log.d(TAG, "onResponse: " + jokesArray.getJSONObject(i));
                        JSONObject jokes = jokesArray.getJSONObject(i);
                        Joke j = new Joke();
                        j.setType(jokes.getString("type"));
                        if(jokes.getString("type").equals("single")){
                            j.setJoke(jokes.getString("joke"));
                        }else {
                            j.setSetup(jokes.getString("setup"));
                            j.setDelivery(jokes.getString("delivery"));
                        }

                        allJokes.add(j);
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getLocalizedMessage());
            }
        });

        queue.add(objectRequest);
    }
}