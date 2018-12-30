package TetrisFiles;

/**
 * Created by jameslowry on 12/2/16.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by jlowrya on 30/11/2016.
 */
public class GamePanel extends JPanel implements MouseListener, KeyListener{
    /*GameWindow for reference*/
    GameWindow game;

    public GamePanel(GameWindow window){
        game = window;
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    /*Paint array held in GameWindow as grid on screen*/
    public void paintComponent(Graphics g){
        int row;   // Row number, from 0 to 7
        int col;   // Column number, from 0 to 7
        int x,y;   // Top-left corner of square

        for ( row = 0;  row < 20;  row++ ) {
            for ( col = 0;  col < 10;  col++) {
                x = col * 30;
                y = row * 30;
                if(game.window[col][row]!= null) {
                    if (game.window[col][row].charAt(0) == 'i')
                        g.setColor(Color.cyan);
                    else if (game.window[col][row].charAt(0) == 'o')
                        g.setColor(Color.yellow);
                    else if (game.window[col][row].charAt(0) == 's')
                        g.setColor(Color.red);
                    else if (game.window[col][row].charAt(0) == 'z')
                        g.setColor(Color.GREEN);
                    else if (game.window[col][row].charAt(0) == 'l')
                        g.setColor(Color.ORANGE);
                    else if (game.window[col][row].charAt(0) == 'j')
                        g.setColor(Color.BLUE);
                    else if (game.window[col][row].charAt(0) == 't')
                        g.setColor(Color.MAGENTA);
                }
                else g.setColor(Color.white);
                g.fillRect(x, y, 29, 29);
            }

        } // end for row

    }  // end paint

    public void mousePressed(MouseEvent e){

    }

    public void mouseReleased(MouseEvent e){

    }

    public void mouseEntered(MouseEvent e){

    }

    public void mouseExited(MouseEvent e){

    }

    public void mouseClicked(MouseEvent e){
        if(!game.gameEnded) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                game.currentPiece.moveLeft(game.window);
            } else if (SwingUtilities.isRightMouseButton(e)) game.currentPiece.moveRight(game.window);
            else game.currentPiece.rotateRight(game.window);
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!game.gameEnded) {
            int event = e.getKeyCode();
            if (event == KeyEvent.VK_RIGHT) {
                game.currentPiece.moveRight(game.window);
            } else if (event == KeyEvent.VK_LEFT) {
                game.currentPiece.moveLeft(game.window);
            } else if (event == KeyEvent.VK_DOWN) {
                game.currentPiece.rotateRight(game.window);
            } else if (event == KeyEvent.VK_UP) {
                game.currentPiece.rotateLeft(game.window);
            }
            else if(event == KeyEvent.VK_SPACE){
                game.currentPiece.moveDown(game.window);
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(!game.gameEnded) {
            int event = e.getKeyCode();
            if (event == KeyEvent.VK_RIGHT) {
                game.currentPiece.moveRight(game.window);
            } else if (event == KeyEvent.VK_LEFT) {
                game.currentPiece.moveLeft(game.window);
            } else if (event == KeyEvent.VK_DOWN) {
                game.currentPiece.rotateRight(game.window);
            } else if (event == KeyEvent.VK_UP) {
                game.currentPiece.rotateLeft(game.window);
            }
            else if(event == KeyEvent.VK_SPACE){
                game.currentPiece.moveDown(game.window);
            }
            repaint();
        }
    }
}
