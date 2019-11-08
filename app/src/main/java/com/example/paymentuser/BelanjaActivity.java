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
import com.example.presenter.NomorPresenter;
import com.example.response.NomorResponse;
import com.example.view.NomorView;

import io.reactivex.disposables.CompositeDisposable;

public class BelanjaActivity extends AppCompatActivity implements NomorView {
    private TextView nohp;
    private NomorPresenter presenter;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;
    private String no_kartu;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belanja);

        nohp = findViewById(R.id.textView18);
        progressBar = findViewById(R.id.progress_bar);
        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        presenter = new NomorPresenter(this, compositeDisposable, apiInterface);
        no_kartu = Utils.readSharedSetting(this,"no_kartu","");

        getNoHp();


    }

    public void BelanjaVerify(View view) {
        Intent intent = new Intent(BelanjaActivity.this, BelanjaVerifyActivity.class);
        startActivity(intent);
        finish();
    }

    private void getNoHp(){
        progressBar.setVisibility(View.VISIBLE);
        presenter.getNoHp(no_kartu);
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
    public void getNohp(NomorResponse nomorResponse) {
        progressBar.setVisibility(View.GONE);
        if (nomorResponse!=null){
            nohp.setText(nomorResponse.getNo_hp());
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BelanjaActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
