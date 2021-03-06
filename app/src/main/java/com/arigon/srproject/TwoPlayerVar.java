


package com.arigon.srproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;


public class TwoPlayerVar extends AppCompatActivity {

    //board lengths, they don't need to be static
    public static int lsize = 8;
    public static int wsize = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); This caused the null pointer exception
        setContentView(R.layout.twoplayervs);   //The correct code

        TableLayout gameboard = (TableLayout) findViewById(R.id.gameboard);

        //generic parameters that I set most of the buttons to use
        TableRow.LayoutParams bparams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        bparams.weight = 1.0f;

        //preparation of the checkerboard
        SquareButton[] boardButtons = new SquareButton[lsize*wsize];

        for(int i = 0; i < lsize; i++) {

            TableRow row = new TableRow(this);

            for (int j = 0; j < wsize; j++) {
                SquareButton b = new SquareButton(this);
                b.setLayoutParams(bparams);
                if (((wsize * i + j) % 2 == 0) ) {
                    b.setBackgroundColor(ContextCompat.getColor(this, R.color.light));
                } else {
                    b.setBackgroundColor(ContextCompat.getColor(this, R.color.dark));
                }
                row.addView(b);
                boardButtons[wsize*i +j] = b;
            }
            row.setWeightSum(wsize);
            gameboard.addView(row, TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        }

        //preparation of the number board
        TableLayout numberboard = (TableLayout) findViewById(R.id.numberboard);
        int numberRange = lsize*wsize/2;
        int rowRange = (int) Math.ceil(numberRange/4);
        Button[] numberButtons = new Button[numberRange];
        for(int i = 0; i < 4; i++){
            TableRow numrow = new TableRow(this);

            for(int j = 0; j < rowRange; j++){
                Button b = new Button(this);
                b.setLayoutParams(bparams);
                if (i % 2 == j % 2){
                    b.setBackgroundColor(ContextCompat.getColor(this, R.color.grey));
                } else {
                    b.setBackgroundColor(Color.BLUE);
                }
                numrow.addView(b);
                numberButtons[rowRange*i + j] = b;
            }
            numrow.setWeightSum(rowRange);
            numberboard.addView(numrow, TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        }

        //setting up click listeners and text for the gameboard
        for(int i = 0; i < boardButtons.length; i++)
        {
            SquareButton b = boardButtons[i];
            if(i % 2 == 0){
                b.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v){
                        v.setBackgroundColor(Color.GRAY);
                    }

                });
            }else {
                b.setText(">");
            }

        }

        //setting up click listeners and text for the numberboard
        for(int i = 0; i < numberButtons.length; i++)
        {
            Button b = numberButtons[i];
            b.setText(String.valueOf(i));

            b.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    v.setBackgroundColor(Color.WHITE);
                }

            });

        }
    }
}
