package TetrisFiles;

import javax.swing.*;
import java.awt.*;
import java.lang.Math;
import java.util.TimerTask;
import java.util.Timer;
import java.util.ArrayList;

/**
 * Created by jameslowry on 11/24/16.
 */
public class GameWindow extends JFrame{
    /*Array to hold the state of game*/
    String[][] window;
    /*GamePiece which the user is able to control*/
    GamePiece currentPiece;
    /*Timer to move piece down screen at interval*/
    Timer timer;
    /*GamePanel to show window to screen*/
    GamePanel game;
    /*Score Panel to show score and allow for restart of game*/
    ScorePanel scorePane;
    /*char[] containing all possible piece types*/
    final char[] pieceTypes = {'o','i','s','z','l','j','t'};
    /*Message supplied to scorePane to output to screen*/
    String message;
    /*Player's score*/
    int score;
    /*Number of the piece to be inserted = nth time piece inserted into window, i.e 2nd piece to be inserted has numPiece of 2*/
    int numPiece;
    /*True if unable to move piece down upon spawning it*/
    boolean gameEnded = false;
    /*True if very first piece to be inserted*/
    boolean firstPiece;


    public GameWindow(){
        window = new String[10][20];
        score = 0;
        numPiece = 0;
        message = "Good luck!";
        setTitle("Tetris by James Lowry");
        game = new GamePanel(this);
        scorePane = new ScorePanel(this);
        add(game, BorderLayout.CENTER);
        add(scorePane,BorderLayout.SOUTH);
        firstPiece = true;
        setSize(315,670);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        startTimer();
    }


    /*Inserts a new piece into window*/
    private void insertNewPiece(){
        currentPiece = getRandPiece();
        int[][] temp = currentPiece.getLowestCoords();
        /*Check to see if there is a piece currently occupying area where we want to place piece
        * if so, move all Squares up by one, then only print the ones that are still in the window*/
        for(int[] x: temp)
            if(window[x[0]][x[1]]!=null)
                for(Square y: currentPiece.getCoordinates())
                    y.setCoords(y.getCoords()[0], y.getCoords()[1]-1);
        for(Square x: currentPiece.getCoordinates()){
            if(x.getCoords()[1]>=0)
                window[x.getCoords()[0]][x.getCoords()[1]] = currentPiece.getPieceID();
        }
        numPiece++;
    }

    public void startTimer(){
        timer =  new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(firstPiece){
                    game.requestFocus();
                    insertNewPiece();
                    firstPiece = false;
                }
                else if(!gameEnded) {
                    if (getFullRows().length != 0 && currentPiece.isTouching(window,'d')) {
                        score+=10*getFullRows().length;
                        moveRowsDown();
                        message = "Nice job!";
                    }
                    if (!currentPiece.moveDown(window)) {
                        insertNewPiece();
                    }
                    if ((currentPiece.getCoordinates()[0].getCoords()[1] == 0 || currentPiece.getCoordinates()[1].getCoords()[1] == 0
                            || currentPiece.getCoordinates()[2].getCoords()[1] == 0 || currentPiece.getCoordinates()[3].getCoords()[1] == 0)
                            && currentPiece.isTouching(window, 'd'))
                        gameEnded = true;
                }
                else{
                    message = "GAME OVER";
                    timer.cancel();
                    currentPiece = null;
                }
                repaint();
            }
        };
        timer.scheduleAtFixedRate(task,0,750);
    }
    /*Get the row numbers of all full rows
    * @return - int[] - array containing row numbers of full rows*/
    private int[] getFullRows(){
        ArrayList<Integer> fullRows = new ArrayList<Integer>();
        boolean full = true;
        for(int row = window[0].length-1; row >=0; row--){
            full = true;
            for(int col = 0; col < window.length; col++){
                if(window[col][row] == null)full=false;
            }
            if(full)
                fullRows.add(row);
        }
        int[] retVal = new int[fullRows.size()];
        for(int i= 0; i<fullRows.size();i++)
            retVal[i] = fullRows.get(i);
        return retVal;
    }

    /*Clears full rows in window*/
    private void removeFullRow(int row){
        for(int i = 0; i < window.length;i++)
            window[i][row] = null;
    }

    /*Remove highest full row then move all rows above down by one*/
    private void moveRowsDown(){
        while(getFullRows().length!=0){
            int startRow = getFullRows()[getFullRows().length-1];
            removeFullRow(startRow);
            for(int col = 0; col < window.length; col++){
                for(int row =  startRow; row > 0; row--){
                    window[col][row] = window[col][row-1];
                }
            }
        }
    }

    /*Return a randomly chosen GamePiece
    * @return - GamePiece - random GamePiece*/
    private GamePiece getRandPiece(){
        char piece = pieceTypes[(int)(Math.random()*pieceTypes.length)];
        if(piece=='o')
            return new OPiece(numPiece);
        else if(piece=='s')
            return new SPiece(numPiece);
        else if(piece=='z')
            return new ZPiece(numPiece);
        else if(piece == 'l')
            return new LPiece(numPiece);
        else if(piece == 'j')
            return new JPiece(numPiece);
        else if(piece=='t')
            return new TPiece(numPiece);
        return new IPiece(numPiece);
    }


    /*Clears board of all pieces*/
    public void clearBoard(){
        for(int i = 0; i < window.length;i++)
            for(int j = 0; j < window[0].length; j++)
                window[i][j] = null;
    }

}
