package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCopy = (Button) findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                copyText();
            }
        });
    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    public void copyText(){
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        if (message.length()>0) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("kekw", message);
            clipboard.setPrimaryClip(clip);
            Log.d("MainActivity", clipboard.getPrimaryClip().toString());
            Toast.makeText(this, "Copiado com sucesso", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Não tem texto na caixa de texto",Toast.LENGTH_LONG).show();
        }
    }
    public void pasteText() {
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            clip.getItemAt(0);
        }
    }
}

