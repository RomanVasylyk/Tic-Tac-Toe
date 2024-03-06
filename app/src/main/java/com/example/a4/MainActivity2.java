package com.example.a4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private String player1Name;
    private String player2Name;
    private TextView currentPlayerTextView;
    private GridLayout gameBoard;

    private boolean isPlayer1Turn = true;
    private static final int ROW_INDEX_TAG = 3;
    private static final int COLUMN_INDEX_TAG = 3;
    private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        player1Name = getIntent().getStringExtra("player1Name");
        player2Name = getIntent().getStringExtra("player2Name");
        buttons = new Button[ROW_INDEX_TAG][COLUMN_INDEX_TAG];
        currentPlayerTextView = findViewById(R.id.current_player_text_view);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < ROW_INDEX_TAG; i++) {
            for (int j = 0; j < COLUMN_INDEX_TAG; j++) {
                Button button = new Button(this);
                button.setLayoutParams(new GridLayout.LayoutParams(
                        GridLayout.spec(i, 1f),
                        GridLayout.spec(j, 1f)
                ));
                button.setTextSize(30);
                buttons[i][j] = button;
                gridLayout.addView(button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button clickedButton = (Button) v;
                        onButtonClick(clickedButton);
                    }
                });
            }
        }

        updateCurrentPlayer();

    }

    private void updateCurrentPlayer() {
        if (isPlayer1Turn) {
            currentPlayerTextView.setText(player1Name + "'s Turn");
        } else {
            currentPlayerTextView.setText(player2Name + "'s Turn");
        }
    }

    private void onButtonClick(Button button) {
        if (button.getText().toString().isEmpty()) {
            if (isPlayer1Turn) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            isPlayer1Turn = !isPlayer1Turn;
            updateCurrentPlayer();
            if (checkForWin()) {
                endGame();
            } else if (checkForDraw()) {
                endGame();
            }
        } else {
            Toast.makeText(MainActivity2.this, "This cell is already occupied", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkForWin() {
        return checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin() || checkForDraw();
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < ROW_INDEX_TAG; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int j = 0; j < COLUMN_INDEX_TAG; j++) {
            if (buttons[0][j].getText().equals(buttons[1][j].getText()) &&
                    buttons[0][j].getText().equals(buttons[2][j].getText()) &&
                    !buttons[0][j].getText().toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().toString().isEmpty()) {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean checkForDraw() {
        for (int i = 0; i < ROW_INDEX_TAG; i++) {
            for (int j = 0; j < COLUMN_INDEX_TAG; j++) {
                if (buttons[i][j].getText().toString().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }



    private void endGame() {
        String result = "";
        if(checkForDraw()){
            result = "It's a draw!";
        }else{
            if (checkForWin()) {
                if (!isPlayer1Turn) {
                    result = player1Name + " wins!";
                } else {
                    result = player2Name + " wins!";
                }
            } else {
                result = "It's a draw!";
            }
        }

        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("result", result);
        startActivity(intent);
        finish();
    }
}




