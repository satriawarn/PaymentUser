package com.example.paymentuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helper.ApiClient;
import com.example.helper.ApiInterface;
import com.example.helper.Utils;
import com.example.presenter.BelanjaPresenter;
import com.example.presenter.VerifPresenter;
import com.example.response.BelanjaResponse;
import com.example.response.VerifResponse;
import com.example.view.BelanjaView;
import com.example.view.VerifView;

import io.reactivex.disposables.CompositeDisposable;

public class BelanjaVerifyActivity extends AppCompatActivity implements VerifView, BelanjaView {
    public static final String TAG="log"+BelanjaVerifyActivity.class.getSimpleName();
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;
    private VerifPresenter presenter;
    private BelanjaPresenter presenter1;
    private String no_kartu,merchant,total,idmerchant,user;
    private TextView namadannominal;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belanja_verify);

        namadannominal = findViewById(R.id.textView20);
        progressBar = findViewById(R.id.progress_bar);
        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        presenter = new VerifPresenter(this, compositeDisposable, apiInterface);
        presenter1 = new BelanjaPresenter(this, compositeDisposable, apiInterface);
        no_kartu = Utils.readSharedSetting(this,"no_kartu","");
        verifBelanja();
    }

    public void BelanjaConfirmation(View view) {
        bayar();
    }

    public void BelanjaBackHome(View view) {
        Intent intent = new Intent(BelanjaVerifyActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void verifBelanja(){
        progressBar.setVisibility(View.VISIBLE);
        presenter.getVerif(no_kartu);
    }
    private void bayar(){
        progressBar.setVisibility(View.VISIBLE);
        presenter1.inputBelanja(no_kartu,user);
    }

    @Override
    public void onSuccess() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getBelanja(BelanjaResponse belanjaResponse) {
        progressBar.setVisibility(View.GONE);
        if (belanjaResponse.getStatus()==1){
            Toast.makeText(this, ""+belanjaResponse.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BelanjaVerifyActivity.this, BelanjaConfirmationActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, ""+belanjaResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getVerifData(VerifResponse verifResponse) {
        progressBar.setVisibility(View.GONE);
        if (verifResponse!=null){
            namadannominal.setText(verifResponse.getNama_merchant()+"\n"+verifResponse.getNominal());
            idmerchant = verifResponse.getId_merchant();
            user = verifResponse.getId_transaksi_user();
        } else {
            Toast.makeText(this, "Transaksi Gagal", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BelanjaVerifyActivity.this, BelanjaActivity.class);
        startActivity(intent);
        finish();
    }
    
}
