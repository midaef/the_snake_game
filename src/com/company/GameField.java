package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int sizeGrid = 320;
    private final int dotSize = 16;
    private final int allDots = 400;
    private Image snakeHead;
    private Image snakeBody;
    private Image backGround;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[allDots];
    private int[] y = new int[allDots];
    private int dots;
    private  Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean down = false;
    private boolean up = false;
    private boolean inGame = true;

    public GameField() {
        loadImageBackGround();
        loadImageSnakeBody();
        loadImageSnakeHead();
        loadImageApple();
        initGame();
    }

    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i * dotSize;
            y[i] = 48;
        }
        timerForInit();
    }

    public void timerForInit() {
        timer = new Timer(250, this);
        timer.start();
        createApple();
    }

    public void createApple() {
        appleX = new Random().nextInt(20) * dotSize;
        appleY = new Random().nextInt(20) * dotSize;
    }

    public void loadImageSnakeHead() {
        ImageIcon ImageIconSnakeHead = new ImageIcon("snake_head.png");
        snakeHead = ImageIconSnakeHead.getImage();
    }

    public void loadImageSnakeBody() {
        ImageIcon ImageIconSnakeBody = new ImageIcon("snake_body_1.png");
        snakeBody = ImageIconSnakeBody.getImage();
    }

    public void loadImageApple() {
        ImageIcon ImageIconApple = new ImageIcon("apple.png");
        apple = ImageIconApple.getImage();
    }

    public void loadImageBackGround() {
        ImageIcon ImageIconBackGround = new ImageIcon("bg.png");
        backGround = ImageIconBackGround.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(backGround, 0, 0, this);
            g.drawImage(apple, appleX, appleY, this);
            g.drawImage(snakeHead, x[0], y[0], this);
            for (int i = 1; i < dots; i++) {
                g.drawImage(snakeBody, x[i], y[i], this);
            }
        }
    }

    public void moveSnake() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if (left) {
            x[0] -= dotSize;
        }
        if (right) {
            x[0] += dotSize;
        }
        if (up) {
            y[0] -= dotSize;
        }
        if (down) {
            y[0] += dotSize;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            moveSnake();
        }
        repaint();
    }
}
