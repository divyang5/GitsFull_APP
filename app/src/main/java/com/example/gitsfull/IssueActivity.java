package com.example.gitsfull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gitsfull.Adapters.IssueAdapter;
import com.example.gitsfull.Adapters.repoAdapter;
import com.example.gitsfull.Model.IssueModel;
import com.example.gitsfull.databinding.ActivityIssueBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class IssueActivity extends AppCompatActivity {

    ActivityIssueBinding binding;
    int extraNo;
    String username,reponame;
    ArrayList<IssueModel> issueList;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityIssueBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog=new ProgressDialog(IssueActivity.this);
        progressDialog.setTitle(" Finding Repositories");
        progressDialog.setMessage(" We're updating request");

        extraNo=getIntent().getExtras().getInt("issueNumber");
        binding.issueCount.setText(Integer.toString(extraNo));
        username=getIntent().getExtras().getString("issueUsername");
        reponame=getIntent().getExtras().getString("issueReponame");


        issueList=new ArrayList<>();

        String uri="https://api.github.com/repos/" + username+"/"+ reponame+"/issues";
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(IssueActivity.this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, uri , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<response.length();i++) {
                        JSONObject obj=response.getJSONObject(i);
                        JSONObject user=obj.getJSONObject("user");
                        String inUser=user.getString("login");
                        issueList.add(new IssueModel(i+1,obj.getString("title"),inUser));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(IssueActivity.this, "Issue Not found", Toast.LENGTH_LONG).show();
//                binding.etGitname.setError("Enter Valid Username");
            }
        });
        requestQueue.add(jsonArrayRequest);


        binding.showIssues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                IssueAdapter adapter=new IssueAdapter(issueList,IssueActivity.this);
                binding.issueRecycleView.setAdapter(adapter);

                LinearLayoutManager layoutManager=new LinearLayoutManager(IssueActivity.this);
                binding.issueRecycleView.setLayoutManager(layoutManager);
                progressDialog.dismiss();
            }
        });

    }

}