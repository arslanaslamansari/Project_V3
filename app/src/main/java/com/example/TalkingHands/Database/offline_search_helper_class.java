package com.example.TalkingHands.Database;

import android.net.Uri;

import com.example.TalkingHands.R;

public class offline_search_helper_class {

    public int search(String search) {

        if (search.equals("ا") || search.equals("الف")) {
            return R.raw.alif;
        } else if (search.equals("ب")) {
            return R.raw.bay;
        } else if (search.equals("پ")) {
            return R.raw.pay;
        } else if (search.equals("ت")) {
            return R.raw.tay;
        } else if (search.equals("ٹ")) {
            return R.raw.ty;
        } else if (search.equals("ث")) {
            return R.raw.say;
        } else if (search.equals("ج")) {
            return R.raw.gaeen;
        } else if (search.equals("چ")) {
            return R.raw.chy;
        } else if (search.equals("ح")) {
            return R.raw.hy;
        } else if (search.equals("خ")) {
            return R.raw.khy;
        } else if (search.equals("د")) {
            return R.raw.dal;
        } else if (search.equals("ڈ")) {
            return R.raw.dhal;
        } else if (search.equals("ذ")) {
            return R.raw.zhal;
        } else if (search.equals("ر")) {
            return R.raw.ray;
        } else if (search.equals("ڑ")) {
            return R.raw.rhay;
        } else if (search.equals("ز")) {
            return R.raw.zay;
        } else if (search.equals("ژ")) {
            return R.raw.zay2;
        } else if (search.equals("س")) {
            return R.raw.seen;
        } else if (search.equals("ش")) {
            return R.raw.sheen;
        } else if (search.equals("ص")) {
            return R.raw.swad;
        } else if (search.equals("ض")) {
            return R.raw.zwad;
        } else if (search.equals("ط")) {
            return R.raw.toen;
        } else if (search.equals("ظ")) {
            return R.raw.zoen;
        } else if (search.equals("ع")) {
            return R.raw.aaen;
        } else if (search.equals("غ")) {
            return R.raw.gaeen;
        } else if (search.equals("ف")) {
            return R.raw.fay;
        } else if (search.equals("ق")) {
            return R.raw.kafh;
        } else if (search.equals("ک")) {
            return R.raw.kafh;
        } else if (search.equals("گ")) {
            return R.raw.ghaf;
        } else if (search.equals("ل")) {
            return R.raw.laam;
        } else if (search.equals("م")) {
            return R.raw.meem;
        } else if (search.equals("ن")) {
            return R.raw.noon;
        } else if (search.equals("ں")) {
            return R.raw.noon;
        } else if (search.equals("و")) {
            return R.raw.wao;
        } else if (search.equals("ہ")) {
            return R.raw.hy2;
        } else if (search.equals("ھ")) {
            return R.raw.hamza;
        } else if (search.equals("ی")) {
            return R.raw.ya;
        } else if (search.equals("ے")) {
            return R.raw.ya2;
        } else
            return 0;
    }
}
