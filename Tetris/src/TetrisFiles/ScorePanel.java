package TetrisFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jameslowry on 12/8/16.
 */
public class ScorePanel extends JPanel{
    /*Gamewindow to reference and access*/
    GameWindow window;
    /*Contain's the player's score*/
    JLabel score;
    /*Contain's system message*/
    JLabel message;
    /*Button to restart game*/
    JButton restart;

    public ScorePanel(GameWindow win){
        window = win;
        restart = new JButton("Restart");
        restart.addActionListener(new RestartButton(window));
        score = new JLabel(String.valueOf(win.score));
        message = new JLabel("");
        setLayout(new GridLayout(1,3));
        add(score);
        add(restart);
        add(message);
    }

    public void paintComponent(Graphics g){
        if(window.gameEnded) {
            restart.setText("Play Again?");
        }
        score.setText("Score: "+String.valueOf(window.score));
        message.setText(window.message);
    }

    class RestartButton implements ActionListener{
        GameWindow window;
        RestartButton(GameWindow win){
            window = win;
        }

        public void actionPerformed(ActionEvent e){
            if(window.gameEnded) {
                window.message = ("New game.");
                restart.setText("Restart");
                window.gameEnded = false;
                window.score = 0;
                window.clearBoard();
            }
            else{
                window.timer.cancel();
                message.setText("Game restarted.");
            }
            window.score = 0;
            window.clearBoard();
            window.currentPiece = null;
            window.firstPiece = true;
            window.repaint();
            window.startTimer();
        }
    }
}
