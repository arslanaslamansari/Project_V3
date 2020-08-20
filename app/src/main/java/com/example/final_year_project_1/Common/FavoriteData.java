package com.example.final_year_project_1.Common;

import android.net.Uri;

public class FavoriteData {

/*
    public String Fav;

    public FavoriteData() {
    }

    public FavoriteData(String fav) {
        this.Fav = Fav;
    }

    public String getFav() {
        return Fav;
    }

*/


    public String fav;
    public String uri;

    public FavoriteData() {
    }

    public FavoriteData(String fav, String uri) {
        this.fav = fav;
        this.uri = uri;
    }

    public FavoriteData(String fav) {

        this.fav = fav;
    }
    public FavoriteData(Uri uri) {

        this.uri = uri.toString();
    }

    public String getfav() {

        return fav;
    }

    public String geturi() {

        return uri;
    }


}
