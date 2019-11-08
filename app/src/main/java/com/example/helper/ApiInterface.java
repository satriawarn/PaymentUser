package com.example.helper;

import com.example.response.BelanjaResponse;
import com.example.response.CekBayarResponse;
import com.example.response.MutasiResponse;
import com.example.response.NomorResponse;
import com.example.response.SaldoResponse;
import com.example.response.TopUpResponse;
import com.example.response.VerifResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("user/mutasi_user")
    Observable<MutasiResponse> mutasi(@Field("no_kartu") String no_kartu);

    @FormUrlEncoded
    @POST("user/get_saldo")
    Observable<SaldoResponse> main(@Field("no_kartu") String no_kartu);

    @FormUrlEncoded
    @POST("user/belanja_get_no_hp")
    Observable<NomorResponse> nohp(@Field("no_kartu") String no_kartu);

    @FormUrlEncoded
    @POST("user/verifikasi_belanja_get_data")
    Observable<VerifResponse> verifBelanja(@Field("no_kartu") String no_kartu);

    @FormUrlEncoded
    @POST("user/top_up")
    Observable<TopUpResponse> topup(@Field("no_kartu") String no_kartu, @Field("nominal") String nominal);

    @FormUrlEncoded
    @POST("user/input_belanja")
    Observable<BelanjaResponse> inputBelanja(@Field("no_kartu") String no_kartu, @Field("id_transaksi_user") String id_transaksi_user);

    @FormUrlEncoded
    @POST("cek_bayar_dengan_smartphone")
    Observable<CekBayarResponse> cekBayar(@Field("id_transaksi_user") String id_transaksi_user);


}
