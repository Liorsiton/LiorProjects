package com.example.liorita.tictac;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     TicTac ticTac = new TicTac();
    int input;

    //0=yellow , 1=red
    int activePlayer = 0;

    boolean gameIsActive = true;


    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        String tag = counter.getTag().toString();
        Point p = getGridPosition(tag);
        if (ticTac.isCellFree(p.getX(), p.getY()) && gameIsActive) {
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                ticTac.add(p.getX(), p.getY(), 0);
                input = 0;
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                ticTac.add(p.getX(), p.getY(), 1);
                input = 1;
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotationBy(360).setDuration(300);
        }

        if (ticTac.checkHorizontalVictory(input) || ticTac.checkVerticalVictory(input) || ticTac.checkDiagonalVictory(input)) {
            // someone won
            TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
            gameIsActive = false;
            String winner = "Red";

            if (input == 0) {
                winner = "Yellow";
            }

            winnerMessage.setText(winner + " won!");
            layout.setVisibility(View.VISIBLE);
        }
        else{
            if(ticTac.isBoardFull()){
                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                gameIsActive = false;

                winnerMessage.setText("this is a drew!!");
                layout.setVisibility(View.VISIBLE);

            }
        }
    }

    public void playAgain(View view){
        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        ticTac.init();
        activePlayer = 0;
        GridLayout  gridLayout = (GridLayout) findViewById(R.id.board);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private  Point getGridPosition(String tag){
        if(tag == null){
            return null;
        }
        int tagNum =Integer.parseInt(tag);

        switch(tagNum){
            case 0:
                return new Point(0,0);

            case 1:
                return  new Point(0,1);

            case 2:
                return new Point(0,2);

            case 3:
                return new Point(1,0);

            case 4:
                return new Point(1,1);

            case 5:
                return  new Point(1,2);

            case 6:
                return  new Point(2,0);

            case 7:
                return new Point(2,1);

            case 8: return new Point(2,2);

            default:
                return null;


        }
    }
}
