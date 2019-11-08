package com.example.response;

import com.google.gson.annotations.SerializedName;

public class BelanjaResponse {
    @SerializedName("status")
    protected Integer status;
    @SerializedName("message")
    protected String message;
    @SerializedName("nominal_transaksi")
    protected String nominal_transaksi;
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

    public String getNominal_transaksi() {
        return nominal_transaksi;
    }

    public void setNominal_transaksi(String nominal_transaksi) {
        this.nominal_transaksi = nominal_transaksi;
    }

    public String getSaldo_akhir() {
        return saldo_akhir;
    }

    public void setSaldo_akhir(String saldo_akhir) {
        this.saldo_akhir = saldo_akhir;
    }
}
