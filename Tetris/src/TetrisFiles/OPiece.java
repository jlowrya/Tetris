package TetrisFiles;

import java.awt.*;

/**
 * Created by jameslowry on 11/29/16.
 */
public class OPiece extends GamePiece{
    public OPiece(int numPiece){
        super(numPiece);
        squareCoords = new Square[4];
        squareCoords[0] = new Square(4,0);
        squareCoords[1] = new Square(5,0);
        squareCoords[2] = new Square(4,1);
        squareCoords[3] = new Square(5,1);
        typeOfPiece = 'o';
    }
    //No need to implement rotations because a square rotated is same square
    public void rotateRight(String[][] board){

    }
    public void rotateLeft(String[][] board){

    }
}
