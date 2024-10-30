// MainActivity.java
package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private final int gridSize = 8; // Grid size, 8x8
    private Cell[][] cells = new Cell[gridSize][gridSize];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);

        // Initialize the grid
        for(int i = 0; i < gridSize; i++) {
            for(int j = 0; j < gridSize; j++) {
                Cell cell = new Cell(this);
                cell.setOnClickListener(this::onCellClicked);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 0;
                params.height = 0;
                params.rowSpec = GridLayout.spec(i, 1, 1f);
                params.columnSpec = GridLayout.spec(j, 1, 1f);
                gridLayout.addView(cell, params);
                cells[i][j] = cell;
            }
        }

        // Place mines randomly
        placeMines(10); // e.g., 10 mines
    }

    private void onCellClicked(View v) {
        Cell cell = (Cell) v;
        cell.reveal();
        // Add more sophisticated game logic here, like revealing nearby cells if empty
    }

    private void placeMines(int number) {
        Random random = new Random();
        int count = 0;
        while(count < number) {
            int i = random.nextInt(gridSize);
            int j = random.nextInt(gridSize);
            if(!cells[i][j].isMine()) {
                cells[i][j].setMine(true);
                updateSurroundingMineCounts(i, j);
                count++;
            }
        }
    }

    private void updateSurroundingMineCounts(int i, int j) {
        for(int di = -1; di <= 1; di++) {
            for(int dj = -1; dj <= 1; dj++) {
                int ni = i + di, nj = j + dj;
                if(ni >= 0 && ni < gridSize && nj >= 0 && nj < gridSize && !(di == 0 && dj == 0)) {
                    cells[ni][nj].setSurroundingMines(cells[ni][nj].getSurroundingMines() + 1);
                }
            }
        }
    }
}
