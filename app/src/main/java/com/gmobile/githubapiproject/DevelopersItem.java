package com.gmobile.githubapiproject;

/**
 * Created by mark on 3/13/17.
 */
public class DevelopersItem {
    String userimage;
    String username;
    String profieUrl;

    public String getProfieUrl() {
        return profieUrl;
    }

    public void setProfieUrl(String profieUrl) {
        this.profieUrl = profieUrl;
    }

    public DevelopersItem(String userimage, String username){
        this.userimage = userimage;
        this.username = username;

    }
    public DevelopersItem(){

    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
