import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
public class SnakeGame extends JPanel{
    int boardWidth;
    int boardHeight;
    public SnakeGame(int width, int height){
        this.boardHeight = height;
        this.boardWidth = width;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight ));
        setBackground(Color.black);
    }
}
