package TetrisFiles;

import java.awt.*;

/**
 * Created by jameslowry on 12/3/16.
 */
public class SPiece extends GamePiece{
    public SPiece(int numPiece){
        super(numPiece);
        squareCoords = new Square[4];
        squareCoords[0] = new Square(4,0);
        squareCoords[1] = new Square(5,0);
        squareCoords[2] = new Square(3,1);
        squareCoords[3] = new Square(4,1);
        origin = squareCoords[3];
        typeOfPiece = 's';
    }
    /*Rotate piece right by multiplying it by matrix{0,-1}
                                                    {1,0}
     * @arg- String[][]- array with current state of game*/
    public void rotateRight(String[][] board){
        int[][] matrix = new int[][]{{0,1},{-1,0}};
        int originX = origin.getCoords()[0];
        int originY = origin.getCoords()[1];
        clearOldPosition(board);
        for(Square x: squareCoords){
            x.setCoords(x.getCoords()[0] -= originX, x.getCoords()[1]-= originY);;
            x.setCoords(((x.getCoords()[0]*matrix[0][0])+(x.getCoords()[1]*matrix[1][0]))+originX, ((x.getCoords()[0]*matrix[0][1])+(x.getCoords()[1]*matrix[1][1]))+originY);
        }
        adjustPiece(board);
        for (Square coords : squareCoords)
            board[coords.getCoords()[0]][coords.getCoords()[1]] = getPieceID();
    }

    /*Rotate piece left by multiplying it by matrix{0,1}
                                                   {-1,0}
       * @arg- String[][]- array with current state of game*/
    public void rotateLeft(String[][] board){
        int[][] matrix = new int[][]{{0,-1},{1,0}};
        int originX = origin.getCoords()[0];
        int originY = origin.getCoords()[1];
        clearOldPosition(board);
        for(Square x: squareCoords){
            x.setCoords(x.getCoords()[0] -= originX, x.getCoords()[1]-= originY);;
            x.setCoords(((x.getCoords()[0]*matrix[0][0])+(x.getCoords()[1]*matrix[1][0]))+originX, ((x.getCoords()[0]*matrix[0][1])+(x.getCoords()[1]*matrix[1][1]))+originY);
        }
        adjustPiece(board);
        for (Square coords : squareCoords)
            board[coords.getCoords()[0]][coords.getCoords()[1]] = getPieceID();
    }
}
