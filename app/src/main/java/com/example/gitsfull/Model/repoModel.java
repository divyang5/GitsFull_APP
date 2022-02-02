package com.example.gitsfull.Model;

public class repoModel {
    String   repoName , username;
    boolean issue ;
    int issueCount;


    public repoModel(String repoName, boolean issue, int issueCount, String username) {
        this.repoName = repoName;
        this.username = username;
        this.issue = issue;
        this.issueCount = issueCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public repoModel(String repoName, boolean issue) {
        this.repoName = repoName;
        this.issue = issue;
    }

    public repoModel(){}

    public repoModel(String repoName) {
        this.repoName = repoName;
    }

    public boolean isIssue() {
        return issue;
    }

    public int getIssueCount() {
        return issueCount;
    }

    public void setIssueCount(int issueCount) {
        this.issueCount = issueCount;
    }

    public void setIssue(boolean issue) {
        this.issue = issue;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

}
