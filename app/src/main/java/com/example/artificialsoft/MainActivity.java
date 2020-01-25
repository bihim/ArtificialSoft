package com.example.artificialsoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ArtificialJsonAdapter.OnItemClickListner {
    private RecyclerView recyclerView;
    private ArtificialJsonAdapter artificialJsonAdapter;
    private ArrayList<ArtificalJsonTest> artificalJsonTests;
    private RequestQueue requestQueue;

    public static final String URL = "forwardingURL";
    public static final String ID = "forwardingID";
    public static final String NAME = "forwardingNAME";
    public static final String DESIGNATION = "forwardingDESIGNATION";
    public static final String USER = "forwardingUSER";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recyclerview stuffs
        recyclerView = findViewById(R.id.recycler_view_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //calling arraylist
        artificalJsonTests = new ArrayList<>();

        //for network requests
        requestQueue = Volley.newRequestQueue(this);
        parseJSON(); //method for parsing json

    }

    private void parseJSON()
    {
        String url = "https://api.myjson.com/bins/15baeq";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try
                {
                    //in an array
                    JSONArray jsonArray = response.getJSONArray("search_result");
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        //within an array
                        JSONObject search_result = jsonArray.getJSONObject(i);
                        String id = search_result.getString("id");
                        String user = search_result.getString("User");
                        String name = search_result.getString("name");
                        String who = search_result.getString("who");
                        String image = search_result.getString("image");

                        //adding item to the array using constructor of ArtificialJsonTest constructor
                        artificalJsonTests.add(new ArtificalJsonTest(image, id, user, name, who));
                    }

                    //adding adapter to an recyclerview
                    artificialJsonAdapter = new ArtificialJsonAdapter(MainActivity.this, artificalJsonTests);
                    recyclerView.setAdapter(artificialJsonAdapter);
                    artificialJsonAdapter.setOnItemClickListener(MainActivity.this);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                //printing error
                error.printStackTrace();
            }
        });

        //fetching json data
        requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position)
    {
        Intent detailsIntent = new Intent(this, DetailListActivity.class);
        ArtificalJsonTest clickedItem = artificalJsonTests.get(position);
        detailsIntent.putExtra(URL, clickedItem.getImageURL());
        detailsIntent.putExtra(ID, clickedItem.getId());
        detailsIntent.putExtra(DESIGNATION, clickedItem.getDesignation());
        detailsIntent.putExtra(NAME, clickedItem.getName());
        detailsIntent.putExtra(USER, clickedItem.getUser());
        startActivity(detailsIntent);
    }
}
