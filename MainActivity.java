package com.example.futscherj2hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] mGameButtons = new Button[3][3];

    private boolean p1Turn=true;
    private int rndcont;
    private int p1Score;
    private int p2Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        for(int i=0; i<3; i++){
            for(int j=0;j<3;j++){
                String btnID = "button_" + i + j;
                int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                mGameButtons[i][j]=findViewById(resID);
                mGameButtons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.button_newgame);
        buttonReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }
            if(p1Turn){
                ((Button) v).setText("X");
            }
            else{
                ((Button) v).setText("O");

            }

        if(checkForWinner()){
            if(p1Turn){
                p1Win();
            }
            else{
                p2Win();
            }
        }
        else if(rndcont==9){
            draw();
        }
        else{
            p1Turn = !p1Turn;

        }

    }
    private boolean checkForWinner() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = mGameButtons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }
        //set draw method
    private void draw(){
        Toast.makeText(this, "Draw, No One Won!", Toast.LENGTH_SHORT).show();

        resetBoard();
    }
        //set win methods
    private void p1Win(){
        p1Score++;
        Toast.makeText(this, "Player 1 Wins!!", Toast.LENGTH_SHORT).show();
        resetBoard();

    }
    private void p2Win(){
        p2Score++;
        Toast.makeText(this, "Player 2 Wins!!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mGameButtons[i][j].setText("");
            }
        }
         rndcont = 0;
        p1Turn= true;
    }

}
