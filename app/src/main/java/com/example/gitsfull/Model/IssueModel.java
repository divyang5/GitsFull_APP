package com.example.gitsfull.Model;

public class IssueModel {
    int issueNum;
    String issueTitleModel , issueDescription;

    public IssueModel(String issueTitleModel, String issueDescription) {
        this.issueTitleModel = issueTitleModel;
        this.issueDescription = issueDescription;
    }
    public IssueModel(int issueNum, String issueTitleModel, String issueDescription) {
        this.issueNum = issueNum;
        this.issueTitleModel = issueTitleModel;
        this.issueDescription = issueDescription;
    }
    public IssueModel(String issueTitleModel) {
        this.issueTitleModel = issueTitleModel;
    }
    public IssueModel(){}

    public int getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(int issueNum) {
        this.issueNum = issueNum;
    }

    public String getIssueTitleModel() {
        return issueTitleModel;
    }

    public void setIssueTitleModel(String issueTitleModel) {
        this.issueTitleModel = issueTitleModel;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }
}
