import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class SnakeGame extends JPanel implements ActionListener{
    private class Tile{
        int x;
        int y;
        protected Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    int boardWidth;
    int boardHeight;
    int tilesSize = 25;

    //Snake
    Tile snakeHead;
    //Food
    Tile food;

    Random random;

    Timer gameLoop;

    public SnakeGame(int width, int height){
        this.boardHeight = height;
        this.boardWidth = width;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight ));
        setBackground(Color.black);

        snakeHead = new Tile(5,5);

        food = new Tile(10,10);

        random = new Random();
        placeFood();

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    private void placeFood() {
        food.x = random.nextInt(boardWidth / tilesSize); // 600 / 25 = 24
        food.y = random.nextInt(boardHeight / tilesSize);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        //Grid
        for (int i = 0; i < boardWidth/tilesSize; i++) {
            g.drawLine(i*tilesSize, 0, i*tilesSize, boardHeight);
            g.drawLine(0,i*tilesSize,boardWidth, i*tilesSize);
        }
        //Food
        g.setColor(Color.RED);
        g.fillRect(food.x * tilesSize, food.y * tilesSize, tilesSize, tilesSize);

        //Snake
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tilesSize, snakeHead.y * tilesSize, tilesSize, tilesSize);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        repaint();
    }
}
