package com.example.paymentuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BelanjaConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belanja_confirmation);
    }

    public void BelanjaConfirmBackHome(View view) {
        Intent intent = new Intent(BelanjaConfirmationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BelanjaConfirmationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
