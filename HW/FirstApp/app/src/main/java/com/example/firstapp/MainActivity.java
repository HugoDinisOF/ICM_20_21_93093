package com.example.firstapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int REQUESTCODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txtView = findViewById(R.id.txtView);

        NumberOCL dialN = new NumberOCL(txtView);
        findViewById(R.id.btnN0).setOnClickListener(dialN);
        findViewById(R.id.btnN1).setOnClickListener(dialN);
        findViewById(R.id.btnN2).setOnClickListener(dialN);
        findViewById(R.id.btnN3).setOnClickListener(dialN);
        findViewById(R.id.btnN4).setOnClickListener(dialN);
        findViewById(R.id.btnN5).setOnClickListener(dialN);
        findViewById(R.id.btnN6).setOnClickListener(dialN);
        findViewById(R.id.btnN7).setOnClickListener(dialN);
        findViewById(R.id.btnN8).setOnClickListener(dialN);
        findViewById(R.id.btnN9).setOnClickListener(dialN);
        findViewById(R.id.btnATK).setOnClickListener(dialN);
        findViewById(R.id.btnHT).setOnClickListener(dialN);
        findViewById(R.id.btnDel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtView = (TextView) findViewById(R.id.txtView);
                if (txtView.getText().toString().equals("")){
                    return;
                }
                String s = txtView.getText().toString();
                s= s.substring(0,s.length()-1);
                txtView.setText(s);
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(75);
            }
        });

        findViewById(R.id.btnCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:"+txtView.getText());
                Intent it = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(it);
            }
        });
        SpeedDial Speed1 = new SpeedDial(txtView,R.id.btnSpeed1);
        SpeedDial Speed2 = new SpeedDial(txtView,R.id.btnSpeed2);
        SpeedDial Speed3 = new SpeedDial(txtView,R.id.btnSpeed3);
        findViewById(R.id.btnSpeed1).setOnClickListener(Speed1);
        findViewById(R.id.btnSpeed2).setOnClickListener(Speed2);
        findViewById(R.id.btnSpeed3).setOnClickListener(Speed3);
        findViewById(R.id.btnSpeed1).setOnLongClickListener(Speed1);
        findViewById(R.id.btnSpeed2).setOnLongClickListener(Speed2);
        findViewById(R.id.btnSpeed3).setOnLongClickListener(Speed3);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","OnResume");
        try {
            String[] sts = getIntent().getStringArrayExtra("BackData");
            Log.d("MainActivity",sts[0]);
        }catch(Exception e){

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","OnPause");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MainActivity","chegou aqui");
        if (REQUESTCODE == requestCode){
            try {
                String[] sts = data.getStringArrayExtra("BackData");
                String name = sts[0];
                String number = sts[1];
                int id = Integer.parseInt(sts[2]);
                Button btn = findViewById(id);
                btn.setText(name);
                btn.setTag(number);
                Log.d("MainActivity",sts[0]);
            }catch(Exception e){
                //e.printStackTrace();
            }
        }
    }
    private class SpeedDial implements View.OnClickListener, View.OnLongClickListener {
        private TextView txtView;
        private int id;

        public SpeedDial(TextView txt, int id){
            txtView = txt;
            this.id = id;
        }
        @Override
        public void onClick(View v) {
            try{
                txtView.setText(txtView.getText()+v.getTag().toString());
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(75);
            }catch (Exception e){
                Toast.makeText(MainActivity.this, "Não tem contacto guardado", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public boolean onLongClick(View v) {
            Log.d("My App","working...");
            Intent intent = new Intent(v.getContext(),SpeedDialScreen.class);
            String[] sts =new String[] {((Button)v).getText().toString(),(String)v.getTag(),String.valueOf(id)};
            intent.putExtra("Data",sts);
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(80);
            startActivityForResult(intent,REQUESTCODE);
            return false;
        }
    }


}