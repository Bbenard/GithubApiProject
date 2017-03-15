package com.gmobile.githubapiproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gmobile.githubapiproject.helper.H;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<DevelopersItem> mDevelopersItem;
    private DevelopersListAdapter mDevelopersAdapter;
    private ListView developersList;
    private String developerURL = APIconst.BASE_URL;
    private JSONArray items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        mDevelopersItem = new ArrayList<DevelopersItem>();
        mDevelopersAdapter = new DevelopersListAdapter(this, mDevelopersItem);

        developerRequest(developerURL);
        developersList.setAdapter(mDevelopersAdapter);

    }

    private void initializeView() {

        developersList = (ListView) findViewById(R.id.developerList);
    }


    public void developerRequest(String url) {
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    parseDevelopersJson(response);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        MainApplication.getInstance().addToRequestQueue(jsonReq);
    }

    public void parseDevelopersJson(JSONObject response) {
        try {

            items = response.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject itemobject = items.getJSONObject(i);
                DevelopersItem item = new DevelopersItem();
                item.setUsername(itemobject.getString("login"));
                item.setUserimage(itemobject.getString("avatar_url"));
                item .setProfieUrl(itemobject.getString("url"));
                ///Log.i("username", itemobject.getString("login"));
                mDevelopersItem.add(item);
            }
            mDevelopersAdapter.notifyDataSetChanged();
            // setListViewHeightBasedOnChildren(commentsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
