package TetrisFiles;

/**
 * Created by jameslowry on 12/5/16.
 */
public class Square {
    /*Array holding coordinates of square*/
    private int[] coords;
    public Square(int x, int y){
        coords = new int[2];
        coords[0] = x;
        coords[1] = y;
    }

    public int[] getCoords(){
        return coords;
    }

    public void setCoords(int x, int y){
        coords[0] = x;
        coords[1] = y;
    }

    public void moveDown(){
        coords[1] += 1;
    }

    public void moveRight(){
        coords[0] += 1;
    }

    public void moveLeft(){
        coords[0] -= 1;
    }
}
