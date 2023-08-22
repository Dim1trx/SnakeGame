import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class SnakeGame extends JPanel implements ActionListener, KeyListener{

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
    ArrayList<Tile> snakeBody;
    //Food
    Tile food;

    Random random;

    Timer gameLoop;

    int velocityX;
    int velocityY;

    public SnakeGame(int width, int height){
        this.boardHeight = height;
        this.boardWidth = width;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight ));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5,5);
        snakeBody = new ArrayList<Tile>();

        food = new Tile(10,10);

        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;

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

        //Snake Head
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tilesSize, snakeHead.y * tilesSize, tilesSize, tilesSize);

        //SnakeBody
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            g.fillRect(snakePart.x * tilesSize, snakePart.y * tilesSize, tilesSize, tilesSize);
        }
    }
    public void move(){
        if(collision(snakeHead, food)) {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }

        //Snake body
        for (int i = snakeBody.size() - 1; i >= 0  ; i--) {
            Tile snakePart = snakeBody.get(i);
            if(i == 0){
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else {
                Tile prevSnakeParty = snakeBody.get(i-1);
                snakePart.x = prevSnakeParty.x;
                snakePart.y = prevSnakeParty.y;
            }
        }

        //SnakeHead
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
    }

    public boolean collision(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        move();
        repaint();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1){
            velocityX = 0;
            velocityY = -1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1){
            velocityX = 0;
            velocityY = 1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1){
            velocityX = 1;
            velocityY = 0;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1){
            velocityX = -1;
            velocityY = 0;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
