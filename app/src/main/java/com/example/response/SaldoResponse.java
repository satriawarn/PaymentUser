package com.example.response;

import com.google.gson.annotations.SerializedName;

public class SaldoResponse {
    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    @SerializedName("saldo")
    protected String saldo;

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    @SerializedName("no_hp")
    protected String no_hp;
}
