package com.example.paymentuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helper.ApiClient;
import com.example.helper.ApiInterface;
import com.example.helper.Utils;
import com.example.presenter.SaldoPresenter;
import com.example.response.SaldoResponse;
import com.example.view.SaldoView;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements SaldoView {
    public static final String TAG="log"+ MainActivity.class.getSimpleName();
    private TextView saldo;
    private SaldoPresenter presenter;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;
    private String no_kartu;
    private ProgressBar progressBar;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saldo = findViewById(R.id.balance);
        progressBar = findViewById(R.id.progress_bar);

        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        presenter = new SaldoPresenter(this, compositeDisposable, apiInterface);

        no_kartu = Utils.readSharedSetting(this,"no_kartu","");

        getSaldo();
    }

    public void topup(View view) {
        Intent intent = new Intent(MainActivity.this, TopUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void mutasi(View view) {
        Intent intent = new Intent(MainActivity.this, MutasiActivity.class);
        startActivity(intent);
        finish();
    }

    public void withdraw(View view) {
        Intent intent = new Intent(MainActivity.this, WithdrawActivity.class);
        startActivity(intent);
        finish();
    }

    public void belanja(View view) {
        Intent intent = new Intent(MainActivity.this, BelanjaActivity.class);
        startActivity(intent);
        finish();
    }

    public void ppob(View view) {
        Intent intent = new Intent(MainActivity.this, PPOBActivity.class);
        startActivity(intent);
        finish();
    }

    public void transfer(View view) {
        Intent intent = new Intent(MainActivity.this, TransferActivity.class);
        startActivity(intent);
        finish();
    }

    private void getSaldo(){
        progressBar.setVisibility(View.VISIBLE);
        presenter.getSaldo(no_kartu);
    }

    @Override
    public void onSuccess() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSaldo(SaldoResponse saldoResponse) {
        if (saldoResponse != null){
            progressBar.setVisibility(View.GONE);
            saldo.setText(saldoResponse.getSaldo());
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Klik sekali lagi untuk keluar aplikasi.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
