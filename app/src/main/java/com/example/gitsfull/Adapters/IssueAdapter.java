package com.example.gitsfull.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gitsfull.Model.IssueModel;
import com.example.gitsfull.R;

import java.util.ArrayList;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {

    ArrayList<IssueModel> issueList;
    Context context;

    public IssueAdapter(ArrayList<IssueModel> issueList, Context context) {
        this.issueList = issueList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_show_issue,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueAdapter.ViewHolder holder, int position) {
        IssueModel issue=issueList.get(position);
        holder.issueNo.setText(Integer.toString(issue.getIssueNum()) + ".");
        holder.issueTittle.setText(issue.getIssueTitleModel());
        holder.issueDesc.setText("by " +issue.getIssueTitleModel());
    }

    @Override
    public int getItemCount() {
        return issueList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView issueNo, issueTittle,issueDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            issueNo=itemView.findViewById(R.id.issueNo);
            issueTittle=itemView.findViewById(R.id.issueTittle);
            issueDesc=itemView.findViewById(R.id.issueDesc);
        }
    }
}
