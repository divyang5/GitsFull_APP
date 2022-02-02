package com.example.gitsfull.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gitsfull.IssueActivity;
import com.example.gitsfull.Model.repoModel;
import com.example.gitsfull.R;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;

public class repoAdapter extends RecyclerView.Adapter<repoAdapter.ViewHolder> {

    ArrayList<repoModel> list;
    Context context;
//    TextView ext = (TextView) ((userActivity)context).findViewById(R.id.username);
//    String extra=ext.getText().toString();
    public repoAdapter(ArrayList<repoModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_show_repo,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        repoModel repo=list.get(position);
        holder.repositoryName.setText(repo.getRepoName());

        if(repo.isIssue()){
            holder.issueButton.setVisibility(View.VISIBLE);
        }else{
            holder.issueButton.setText("No Issues");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(repo.getIssueCount()>0 && repo.isIssue()){
                    Intent intent=new Intent(context, IssueActivity.class);
                    intent.putExtra("issueNumber",repo.getIssueCount());
                    intent.putExtra("issueUsername",repo.getUsername());
                    intent.putExtra("issueReponame", repo.getRepoName());
                    context.startActivity(intent);
                }else if(!repo.isIssue()){
                    Toast.makeText(context, "NO ISSUE FOUND ON  " +repo.getRepoName(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "ISSUE IS PRIVATE ON " + repo.getRepoName(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView repositoryName;
        Button issueButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            repositoryName=itemView.findViewById(R.id.repositoryName);
            issueButton=itemView.findViewById(R.id.issueButton);
        }
    }
}
