package com.habibfr.puzzle_angka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int GRID_SIZE = 3;
    private Button[][] buttons = new Button[GRID_SIZE][GRID_SIZE];
    private ArrayList<Integer> numbers = new ArrayList<>();
    Button btnReset;
    private TextView timeTextView;
    private Handler handler = new Handler();
    private int seconds = 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        btnReset = findViewById(R.id.btnReset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbers.clear();
                isRunning = false;
                stopTimer();
                shuffle();

            }
        });

        // Create and add buttons to the grid layout
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Button button = new Button(this);
                button.setText("");
                button.setTextSize(28);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(i, 1f);
                params.columnSpec = GridLayout.spec(j, 1f);
                params.width = 0;
                params.height = 0;
                params.setMargins(4, 4, 4, 4);
                button.setLayoutParams(params);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        move(view);
                    }
                });
                buttons[i][j] = button;
                gridLayout.addView(button);
            }
        }

        shuffle();
        timeTextView = findViewById(R.id.timeTextView);
    }
    private void shuffle() {
        // Generate a list of numbers from 1 to 8
        numbers.clear();
        for (int i = 1; i < GRID_SIZE * GRID_SIZE; i++) {
            numbers.add(i);
        }

        // Add an empty space to the list of numbers
        numbers.add(0);

        // Shuffle the numbers randomly
        Collections.shuffle(numbers);

        // Set the shuffled numbers to the buttons
        int index = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (index < numbers.size()) {
                    int number = numbers.get(index);
                    buttons[i][j].setText(number == 0 ? "" : String.valueOf(number));
                }
                index++;
            }
        }
    }

//    private void shuffle() {
//        // Generate a list of numbers from 1 to 8
//        for (int i = 1; i < GRID_SIZE * GRID_SIZE; i++) {
//            numbers.add(i);
//        }
//
//        // Shuffle the numbers randomly
//        Collections.shuffle(numbers);
//
//        // Set the shuffled numbers to the buttons
//        int index = 0;
//        for (int i = 0; i < GRID_SIZE; i++) {
//            for (int j = 0; j < GRID_SIZE; j++) {
//                if (index < numbers.size()) {
//                    buttons[i][j].setText(String.valueOf(numbers.get(index)));
//                }
//                index++;
//            }
//        }
//    }

    private void move(View view) {
        if (!isRunning) {
            isRunning = true;
            startTimer();
        }
        Button button = (Button) view;
        int row = -1, col = -1;

        // Find the position of the clicked button
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (buttons[i][j] == button) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // Move the button if possible
        if (col > 0 && buttons[row][col - 1].getText().equals("")) {
            swap(row, col, row, col - 1);
        } else if (col < GRID_SIZE - 1 && buttons[row][col + 1].getText().equals("")) {
            swap(row, col, row, col + 1);
        } else if (row > 0 && buttons[row - 1][col].getText().equals("")) {
            swap(row, col, row - 1, col);
        } else if (row < GRID_SIZE - 1 && buttons[row + 1][col].getText().equals("")) {
            swap(row, col, row + 1, col);
        }

        // Check if the game is won
        if (checkWin()) {
            Toast.makeText(this, "Congratulations, you won!, time " + timeTextView.getText().toString(), Toast.LENGTH_LONG).show();
            isRunning = false;
            stopTimer();


        }
    }

    private void swap(int row1, int col1, int row2, int col2) {
        String temp = buttons[row1][col1].getText().toString();
        buttons[row1][col1].setText(buttons[row2][col2].getText().toString());
        buttons[row2][col2].setText(temp);
    }

    private boolean checkWin() {
        String[][] current = new String[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                current[i][j] = buttons[i][j].getText().toString();
            }
        }

        String[][] goal = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", ""}};

        return compare(current, goal);
    }

    private boolean compare(String[][] a, String[][] b) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (!a[i][j].equals(b[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private void startTimer() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                seconds++;
                int minutes = seconds / 60;
                int secs = seconds % 60;
                String time = String.format("%02d:%02d", minutes, secs);
                timeTextView.setText(time);
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void stopTimer() {
        handler.removeCallbacksAndMessages(null);
        seconds = 0;
        timeTextView.setText("00:00");
    }


    // Get row of a tile

}