package com.courier365cloud.faztrex.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonRequestModel {

    @SerializedName(value = "cid", alternate = "Cid")
    @Expose
    private String cid;

    @SerializedName(value = "bid", alternate = "Bid")
    @Expose
    private String bid;

    @SerializedName(value = "sortcol", alternate = "Sortcol")
    @Expose
    private Integer sortcol;

    @SerializedName(value = "sortdir", alternate = "Sortdir")
    @Expose
    private String sortdir;

    @SerializedName(value = "pagefrom", alternate = "Pagefrom")
    @Expose
    private Integer pagefrom;

    @SerializedName(value = "pagesize", alternate = "Pagesize")
    @Expose
    private Integer pagesize;

    @SerializedName(value = "search", alternate = "Search")
    @Expose
    private String search;

    @SerializedName(value = "fromdate", alternate = "Fromdate")
    @Expose
    private String fromdate;

    @SerializedName(value = "todate", alternate = "Todate")
    @Expose
    private String todate;

    @SerializedName(value = "assignid", alternate = "Assignid")
    @Expose
    private String assignid;

    @SerializedName(value = "statusid", alternate = "Statusid")
    @Expose
    private String statusid;

    @SerializedName(value = "userid", alternate = "Userid")
    @Expose
    private String userid;

    @SerializedName(value = "id", alternate = "Id")
    @Expose
    private String id;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Integer getSortcol() {
        return sortcol;
    }

    public void setSortcol(Integer sortcol) {
        this.sortcol = sortcol;
    }

    public String getSortdir() {
        return sortdir;
    }

    public void setSortdir(String sortdir) {
        this.sortdir = sortdir;
    }

    public Integer getPagefrom() {
        return pagefrom;
    }

    public void setPagefrom(Integer pagefrom) {
        this.pagefrom = pagefrom;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getAssignid() {
        return assignid;
    }

    public void setAssignid(String assignid) {
        this.assignid = assignid;
    }

    public String getStatusid() {
        return statusid;
    }

    public void setStatusid(String statusid) {
        this.statusid = statusid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
