package com.example.paymentuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.helper.ApiClient;
import com.example.helper.ApiInterface;
import com.example.helper.Utils;
import com.example.presenter.TopUpPresenter;
import com.example.response.TopUpResponse;
import com.example.view.TopUpView;
import com.gdacciaro.iOSDialog.iOSDialog;
import com.gdacciaro.iOSDialog.iOSDialogBuilder;
import com.gdacciaro.iOSDialog.iOSDialogClickListener;


import io.reactivex.disposables.CompositeDisposable;

public class TopUpActivity extends AppCompatActivity implements TopUpView {
    public static final String TAG="log"+TopUpActivity.class.getSimpleName();
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;
    private TopUpPresenter presenter;
    private EditText input;
    private String no_kartu,nominal;
    private ProgressBar progressBar;
    private Boolean isValid = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        input = findViewById(R.id.editText2);
        progressBar = findViewById(R.id.progress_bar);

        no_kartu = Utils.readSharedSetting(this,"no_kartu","");

        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        presenter = new TopUpPresenter(this, compositeDisposable, apiInterface);

    }

    public void confirmation(View view) {
        topUp();
    }

    private void topUp(){
        isValid = validation();
        if (isValid){
            new iOSDialogBuilder(TopUpActivity.this)
                    .setTitle("Peringatan")
                    .setSubtitle("Anda akan melakukan top up"+"\n"
                            +"Sejumlah : Rp "+nominal+"\n")
                    .setBoldPositiveLabel(true)
                    .setCancelable(false)
                    .setPositiveListener("Oke",new iOSDialogClickListener() {
                        @Override
                        public void onClick(iOSDialog dialog) {
                            progressBar.setVisibility(View.VISIBLE);
                            presenter.topUp(no_kartu,nominal);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeListener("Tidak", new iOSDialogClickListener() {
                        @Override
                        public void onClick(iOSDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .build().show();

        }

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
    public void getTopup(TopUpResponse topUpResponse) {
        progressBar.setVisibility(View.GONE);
        if (topUpResponse.getStatus()==1){
            Intent intent = new Intent(TopUpActivity.this, TopUpConfirmationActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, ""+topUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TopUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validation() {
        nominal = input.getText().toString();
        if (nominal.isEmpty()){
            Toast.makeText(this, "Silakan masukkan nominal", Toast.LENGTH_SHORT).show();
            input.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
