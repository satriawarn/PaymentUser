package com.example.response;

import com.google.gson.annotations.SerializedName;

public class VerifResponse {
    @SerializedName("id_transaksi_user")
    protected String id_transaksi_user;
    @SerializedName("id_merchant")
    protected String id_merchant;
    @SerializedName("nama_merchant")
    protected String nama_merchant;
    @SerializedName("nominal")
    protected String nominal;

    public String getId_transaksi_user() {
        return id_transaksi_user;
    }

    public void setId_transaksi_user(String id_transaksi_user) {
        this.id_transaksi_user = id_transaksi_user;
    }

    public String getId_merchant() {
        return id_merchant;
    }

    public void setId_merchant(String id_merchant) {
        this.id_merchant = id_merchant;
    }

    public String getNama_merchant() {
        return nama_merchant;
    }

    public void setNama_merchant(String nama_merchant) {
        this.nama_merchant = nama_merchant;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }
}
