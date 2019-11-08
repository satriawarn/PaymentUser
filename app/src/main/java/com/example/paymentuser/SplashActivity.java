package com.example.paymentuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.helper.Utils;

public class SplashActivity extends AppCompatActivity {
    public static final String TAG="logv"+SplashActivity.class.getSimpleName();
    private String nokartu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        nokartu = Utils.readSharedSetting(this,"no_kartu","null");
        Log.d(TAG, "onCreate: "+nokartu);

        if (nokartu.equalsIgnoreCase("null")){
            belumada();
        } else {
            sudahada();
        }
    }

    private void sudahada(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 2000);
    }

    private void belumada(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), InputCCActivity.class));
                finish();
            }
        }, 2000);
    }

}
