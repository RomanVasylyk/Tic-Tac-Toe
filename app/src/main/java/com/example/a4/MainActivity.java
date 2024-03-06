package com.example.a4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText player1NameEditText;
    private EditText player2NameEditText;
    private Button startGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1NameEditText = findViewById(R.id.player1_name_edit_text);
        player2NameEditText = findViewById(R.id.player2_name_edit_text);
        startGameButton = findViewById(R.id.start_game_button);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String player1Name = player1NameEditText.getText().toString();
                String player2Name = player2NameEditText.getText().toString();

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("player1Name", player1Name);
                intent.putExtra("player2Name", player2Name);
                startActivity(intent);
            }
        });
    }
}
