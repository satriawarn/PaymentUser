package com.example.response;

import com.google.gson.annotations.SerializedName;

public class NomorResponse {
    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    @SerializedName("no_hp")
    private String no_hp;
}
