package com.example.firstapp;

import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SpeedDialTest implements View.OnClickListener, View.OnLongClickListener {
    private TextView txtView;
    private int id;
    private boolean onLongClick;

    public SpeedDialTest(TextView txt, int id){
        txtView = txt;
        this.id = id;
        onLongClick = false;
    }
    @Override
    public void onClick(View v) {
        if (onLongClick){
            onLongClick = false;
            return;
        }
        txtView.setText(txtView.getText()+v.getTag().toString());
    }

    @Override
    public boolean onLongClick(View v) {
        onLongClick = true;
        Log.d("My App","working...");
        Intent intent = new Intent(v.getContext(),SpeedDialScreen.class);
        String[] sts =new String[] {((Button)v).getText().toString(),(String)v.getTag(),String.valueOf(id)};
        intent.putExtra("Data",sts);
        v.getContext().startActivity(intent);
        return false;
    }
}
