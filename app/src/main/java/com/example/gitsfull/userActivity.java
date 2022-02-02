package com.example.gitsfull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gitsfull.Adapters.repoAdapter;
import com.example.gitsfull.Model.repoModel;
import com.example.gitsfull.databinding.ActivityUserBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class userActivity extends AppCompatActivity {
    ActivityUserBinding binding;
    String extra;
    ArrayList<repoModel> list;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog=new ProgressDialog(userActivity.this);
        progressDialog.setTitle(" Finding Repositories");
        progressDialog.setMessage(" We're updating request");
        list=new ArrayList<>();
        extra=getIntent().getExtras().getString("username");
        binding.username.setText(extra);
        RequestQueue requestQueue;

        requestQueue= Volley.newRequestQueue(userActivity.this);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, "https://api.github.com/users/" + extra + "/repos", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {

                    list.clear();
                    progressDialog.dismiss();
                    if(response.length()<2){
                        Toast.makeText(userActivity.this, "Repository not found", Toast.LENGTH_LONG).show();

                    }else{

                        for(int i=0;i<response.length();i++) {
                            JSONObject obj=response.getJSONObject(i);
//                            Log.d("github api", "The log in is " + obj.getString("name"));
//                        repo.setRepoName(obj.getString("full_name"));
                            list.add(new repoModel(obj.getString("name"),obj.getBoolean("has_issues"),obj.getInt("open_issues_count"),extra));
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("github api " , "error is" + error);
                Toast.makeText(userActivity.this, "Repository not found", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);



        binding.showRepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                repoAdapter adapter=new repoAdapter(list,userActivity.this);
                binding.repoRecycleView.setAdapter(adapter);

                LinearLayoutManager layoutManager=new LinearLayoutManager(userActivity.this);
                binding.repoRecycleView.setLayoutManager(layoutManager);
                progressDialog.dismiss();

            }
        });
    }
}