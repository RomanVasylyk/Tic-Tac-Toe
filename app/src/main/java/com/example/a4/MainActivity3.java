package com.example.a4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView resultTextView = findViewById(R.id.result_text_view);
        Button restartButton = findViewById(R.id.restart_button);

        Intent intent = getIntent();
        String result = intent.getStringExtra("result");

        resultTextView.setText(result);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restartIntent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(restartIntent);
                finish();
            }
        });
    }
}