// Cell.java
package com.example.minesweeper;

import android.content.Context;
import androidx.appcompat.widget.AppCompatButton;

public class Cell extends AppCompatButton {
    private boolean isMine;
    private boolean isRevealed;
    private int surroundingMines;

    public Cell(Context context) {
        super(context);
        this.isMine = false;
        this.isRevealed = false;
        this.surroundingMines = 0;
    }

    // Setters and Getters
    public void setMine(boolean mine) { this.isMine = mine; }
    public boolean isMine() { return isMine; }
    public void setRevealed(boolean revealed) { this.isRevealed = revealed; }
    public boolean isRevealed() { return isRevealed; }
    public void setSurroundingMines(int count) { this.surroundingMines = count; }
    public int getSurroundingMines() { return surroundingMines; }
    // Reveal this cell and show the count or mine
    public void reveal() {
        if(isRevealed)
            return;

        setRevealed(true);
        if(isMine) {
            setText("*");
            setBackgroundColor(0xFFFF0000); // Red color if mine
        } else {
            setText(surroundingMines > 0 ? String.valueOf(surroundingMines) : "");
            setBackgroundColor(0xFFCCCCCC); // Light gray if safe
        }
    }
}
