package com.example.paymentuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.helper.Utils;


public class InputCCActivity extends AppCompatActivity {

    private EditText NoKartu;
    private Button Simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_cc);

        NoKartu = (EditText)findViewById(R.id.NoKartu);
//        Simpan = (Button) findViewById(R.id.Simpan);

    }


    public void gethome(View view) {
        Utils.saveSharedSetting(this,"no_kartu",NoKartu.getText().toString());
        Intent intent = new Intent(InputCCActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
