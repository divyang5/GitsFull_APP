package com.example.gitsfull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gitsfull.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String us;
    String uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.etLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                us=binding.etGitname.getText().toString();
                uri="https://api.github.com/users/" +us;



                RequestQueue requestQueue;
                requestQueue= Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, uri , null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                                Intent intent=new Intent(MainActivity.this,userActivity.class);
                                intent.putExtra("username",response.getString("login"));
                                startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "User Not found", Toast.LENGTH_LONG).show();
                        binding.etGitname.setError("Enter Valid Username");
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}