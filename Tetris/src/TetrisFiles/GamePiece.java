package TetrisFiles;

/**
 * Created by jameslowry on 11/29/16.
 */
import java.util.ArrayList;


public abstract class GamePiece {
        /*Square array containing four squares which make up each piece*/
        protected Square[] squareCoords;
        /*Character indicating the type of piece*/
        protected char typeOfPiece;
        /*int indicating which number piece this piece is in the overall game of tetris*/
        protected int numPiece;
        /*Origin Square around which the piece rotates*/
        protected Square origin;
        /*Unique piece id*/
        protected String pieceID;

        public GamePiece(int numPiece){
                this.numPiece = numPiece;
        }

        public Square[] getCoordinates(){
                return squareCoords;
        }

        public char getTypeOfPiece(){
                return typeOfPiece;
        }

        public int getNumPiece(){
                return numPiece;
        }

        public String getPieceID(){
                return typeOfPiece + String.valueOf(numPiece);
        }

        /*Return true if piece is touching either border of window or another piece in the direction given
        * @arg - String[][] - array holding game board with pieces in it
        * @arg - char - direction in which to check if touching
        * @return - boolean - true if touching, false otherwise*/
        protected boolean isTouching(String[][] board,char direction){
                if(direction == 'd') {
                        for (int[] coords : getLowestCoords()) {
                                if (coords[1] + 1 >= board[0].length || board[coords[0]][coords[1] + 1] != null)
                                        return true;
                        }
                }
                else if(direction == 'l'){
                        for (int[] coords : getLeftMostCoords()) {
                                if (coords[0] - 1 == -1 || board[coords[0]-1][coords[1]] != null)
                                        return true;
                        }

                }
                else if(direction == 'r'){
                        for(int[] x: getRightMostCoords())
                                if(x[0]+1>=board.length || board[x[0]+1][x[1]]!= null)
                                        return true;
                }
                return false;
        }
        /*Returns coordinates of lowest square(s) in piece
        * @return - int[][] - 2d array with coords of lowest square(s)*/
        public int[][] getLowestCoords(){
                int[][] lowestPieces = new int[4][2];
                int index = 0;
                int lowest = 0;
                for(int i = 0; i < squareCoords.length; i++)
                {
                        if(squareCoords[i].getCoords()[1] > lowest){
                                lowest = squareCoords[i].getCoords()[1];
                        }
                }
                for(Square x: squareCoords)
                        if(x.getCoords()[1] == lowest){
                                lowestPieces[index] = x.getCoords();
                                index++;
                        }
                return lowestPieces;
        }
        /*Returns coordinates of left-most square(s) in piece
         * @return - int[][] - 2d array with coords of left-most square(s)*/
        private int[][] getLeftMostCoords(){
                ArrayList<int[]> leftMostPieces = new ArrayList<int[]>();
                int leftmost = 11;
                for(Square x:squareCoords)
                {
                        if(x.getCoords()[0] < leftmost)
                                leftmost = x.getCoords()[0];
                }
                for(Square x: squareCoords)
                        if(x.getCoords()[0] == leftmost)
                                leftMostPieces.add(x.getCoords());
                int[][] leftMostArray = new int[leftMostPieces.size()][2];
                for(int i = 0; i < leftMostPieces.size();i++) {
                        leftMostArray[i] = leftMostPieces.get(i);
                }
                return leftMostArray;
        }
        /*Returns coordinates of right-most square(s) in piece
        * @return - int[][] - 2d array with coords of right-most square(s)*/
        private int[][] getRightMostCoords(){
                int[][] rightMostPieces = new int[4][2];
                int index = 0;
                int rightMost = 0;
                for(Square x:squareCoords)
                {
                        if(x.getCoords()[0] > rightMost)
                                rightMost = x.getCoords()[0];
                }
                for(Square x: squareCoords)
                        if(x.getCoords()[0] == rightMost) {
                                rightMostPieces[index][0] = x.getCoords()[0];
                                rightMostPieces[index][1] = x.getCoords()[1];
                                index++;
                        }
                return rightMostPieces;
        }
        /*Returns coordinates of top-most square(s) in piece
        * @return - int[][] - 2d array with coords of top-most square(s)*/
        private int[][] getTopMostCoords(){
                int[][] topMostPieces = new int[4][2];
                int index = 0;
                int topMost = 100000;
                for(Square x:squareCoords)
                {
                        if(x.getCoords()[1] < topMost)
                                topMost = x.getCoords()[1];
                }
                for(Square x: squareCoords)
                        if(x.getCoords()[1] == topMost) {
                                topMostPieces[index][0] = x.getCoords()[0];
                                topMostPieces[index][1] = x.getCoords()[1];
                                index++;
                        }
                return topMostPieces;
        }

        /*Clear old position of piece
        * @arg - String[][] - Game board containing GamePieces and previous location of piece*/
        protected void clearOldPosition(String[][] board){
                for(Square coords: squareCoords)
                        board[coords.getCoords()[0]][coords.getCoords()[1]] = null;
        }

        /*Move piece down
        *@arg - String[][] - Game board containing current state of game
        * @return - boolean - true if successfully moved down*/
        public boolean moveDown(String[][] board){
                boolean validMove = true;
                for(Square x: squareCoords)
                {
                        int col = x.getCoords()[0];
                        int row = x.getCoords()[1];
                        if(isTouching(board,'d') || (board[col][row+1] != null && !board[col][row+1].equals(getPieceID())))
                                validMove = false;

                }
                if(validMove) {
                        clearOldPosition(board);
                        for (Square x : squareCoords) {
                                x.moveDown();
                                board[x.getCoords()[0]][x.getCoords()[1]] = getPieceID();
                        }
                        return true;
                }
                return false;
        }
        /*Move piece left
        * @arg - String[][] board - Array containing current state of game
        * @return - boolean -  true successfully moved left*/
        public boolean moveLeft(String[][] board){
                boolean validMove = true;
                for(Square x: squareCoords)
                {
                        int col = x.getCoords()[0];
                        int row = x.getCoords()[1];
                        if(isTouching(board,'l') || (board[col-1][row] != null && !board[col-1][row].equals(getPieceID())))
                                validMove = false;

                }
                if(validMove) {
                        clearOldPosition(board);
                        for (Square x : squareCoords) {
                                x.moveLeft();
                                board[x.getCoords()[0]][x.getCoords()[1]] = getPieceID();
                        }
                        return true;
                }
                return false;
        }

        /*Move piece right
        * @arg - String[][] board - Array containing current state of game
        * @return - boolean -  true successfully moved right*/
        public boolean moveRight(String[][] board){
                boolean validMove = true;
                for(Square x: squareCoords)
                {
                        int col = x.getCoords()[0];
                        int row = x.getCoords()[1];
                        if(isTouching(board,'r') || (board[col+1][row] != null && !board[col+1][row].equals(getPieceID())))
                                validMove = false;

                }
                if(validMove) {
                        clearOldPosition(board);
                        for (Square x : squareCoords) {
                                x.moveRight();
                                board[x.getCoords()[0]][x.getCoords()[1]] = getPieceID();
                        }
                        return true;
                }
                return false;
        }

        /*Adjust piece if outside of board to be inside of board
        * @arg - String[][] board - Array containing current state of game*/
        public void adjustPiece(String[][] board){
                int diff = 0;
                for(Square coords: squareCoords){
                        if(coords.getCoords()[0]>=board.length) {
                                diff = getRightMostCoords()[0][0] - (board.length-1);
                                for (Square x: squareCoords)
                                        x.setCoords(x.getCoords()[0]-diff, x.getCoords()[1]);
                        }
                        if(coords.getCoords()[0]<0){
                                diff =  0 - getLeftMostCoords()[0][0];
                                for (Square x: squareCoords)
                                        x.setCoords(x.getCoords()[0]+diff, x.getCoords()[1]);
                        }
                        if(coords.getCoords()[1]>=board[0].length){
                                diff = (board[0].length - 1) - getLowestCoords()[0][1];
                                for (Square x: squareCoords)
                                        x.setCoords(x.getCoords()[0], x.getCoords()[1]+diff);
                        }
                        if(coords.getCoords()[1]<0){
                                diff = 0 - (getTopMostCoords()[0][1]);
                                for(Square x: squareCoords)
                                        x.setCoords(x.getCoords()[0],x.getCoords()[1]+diff);
                        }
                }

        }
        /*Rotate piece right
        * @arg - String[][] - array with current state of game*/
        public abstract void rotateRight(String[][] board);
        /*Rotate piece left
        * @arg - String[][] - array with current state of game*/
        public abstract void rotateLeft(String[][] board);

}
