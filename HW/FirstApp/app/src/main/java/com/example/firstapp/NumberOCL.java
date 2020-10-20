package com.example.firstapp;

import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NumberOCL implements View.OnClickListener {
    private TextView txtView;
    public NumberOCL(TextView textView){
        this.txtView = textView;
    }
    @Override
    public void onClick(View v) {
        String s = txtView.getText().toString();
        s += ((Button)v).getText().toString();
        txtView.setText(s);
        Vibrator vibrator = (Vibrator) v.getContext().getSystemService(v.getContext().VIBRATOR_SERVICE);
        vibrator.vibrate(75);
    }
}
