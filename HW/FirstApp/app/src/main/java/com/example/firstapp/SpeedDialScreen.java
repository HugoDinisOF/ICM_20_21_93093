package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SpeedDialScreen extends AppCompatActivity {
    private String id;
    private final int REQUESTCODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_dial_screen);
        Log.d("SpeedDialScreen",getIntent().getStringArrayExtra("Data")[0]);
        String[] sts = getIntent().getStringArrayExtra("Data");
        id = sts[2];
        ((EditText)findViewById(R.id.editTxtName)).setText(sts[0]);
        ((EditText)findViewById(R.id.editTxtPhone)).setText(sts[1]);
        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String name = ((EditText)findViewById(R.id.editTxtName)).getText().toString();
                String phone = ((EditText)findViewById(R.id.editTxtPhone)).getText().toString();
                String[] sts = new String[] {name,phone,id};
                intent.putExtra("BackData",sts);
                setResult(REQUESTCODE,intent);
                Toast.makeText(SpeedDialScreen.this, "Contacto criado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*Intent intent = new Intent();
        String name = ((EditText)findViewById(R.id.editTxtName)).getText().toString();
        String phone = ((EditText)findViewById(R.id.editTxtPhone)).getText().toString();
        String[] sts = new String[] {name,phone,id};
        intent.putExtra("BackData",sts);
        setResult(REQUESTCODE,intent);
        super.onBackPressed();
        finish();*/
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        String name = ((EditText)findViewById(R.id.editTxtName)).getText().toString();
        String phone = ((EditText)findViewById(R.id.editTxtPhone)).getText().toString();
        String[] sts = new String[] {name,phone,id};
        getIntent().putExtra("BackData",sts);

    }*/
}