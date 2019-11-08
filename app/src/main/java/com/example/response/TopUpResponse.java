package com.example.response;

import com.google.gson.annotations.SerializedName;

public class TopUpResponse {
    @SerializedName("status")
    protected Integer status;
    @SerializedName("message")
    protected String message;
    @SerializedName("nominal")
    protected String nominal;
    @SerializedName("saldo_akhir")
    protected String saldo_akhir;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getSaldo_akhir() {
        return saldo_akhir;
    }

    public void setSaldo_akhir(String saldo_akhir) {
        this.saldo_akhir = saldo_akhir;
    }
}
