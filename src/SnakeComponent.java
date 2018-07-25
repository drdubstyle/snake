

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SnakeComponent extends JPanel implements ActionListener{

    private static final int SCALE = 20;
    private int partsOfSnake;
    protected Timer timer;
    private int snakePosX[];
    private int snakePosY[];
    protected boolean left  = false,
                    right = true,
                    up  = false,
                    down = false;
    private int dotX;
    private int dotY;
    private int delay;



    public SnakeComponent(){
        Dimension size = new Dimension(500,500);
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        //setSize(500,500);
        gameStart();

    }

    public void gameStart(){
        snakePosX = new int[500];
        snakePosY = new int[500];
        delay = 600;
        timer = new Timer(delay,this);
        timer.start();
        partsOfSnake = 3;
        for (int i = 0; i < partsOfSnake; i++) {
            snakePosX[i] = (partsOfSnake * SCALE) - i * SCALE;
            snakePosY[i] = (partsOfSnake * SCALE);
        }

        spawnDot();
    }

    public void move(){

        if (snakePosX[0] == dotX && snakePosY[0] == dotY){
            partsOfSnake++;
            timer.setDelay(delay -= 10);
            spawnDot();
        }
        for (int i = 1 ; i < partsOfSnake; i++ ) {
            if (snakePosX[0] == snakePosX[i] && snakePosY[0] == snakePosY[i]) {
                timer.stop();
                gameStart();
            }
        }


        for (int i = partsOfSnake; i > 0; i--){
            snakePosX[i] = snakePosX[i - 1];
            snakePosY[i] = snakePosY[i - 1];
        }

        if(right){
            snakePosX[0] += SCALE;
            if (snakePosX[0] >= 500) snakePosX[0] = 0;
        }

        if(left){
            snakePosX[0] -= SCALE;
            if (snakePosX[0] < 0) snakePosX[0] = 450;
        }
        if(up){
            snakePosY[0] -= SCALE;
            if (snakePosY[0] < 0) snakePosY[0] = 450;
        }
        if(down){
            snakePosY[0] += SCALE;
            if (snakePosY[0] >= 500) snakePosY[0] = 0;
        }


    }

    public void spawnDot(){

        dotX = new Random().nextInt(500/SCALE) * SCALE;
        dotY = new Random().nextInt(500/SCALE) * SCALE;

        for (int i = partsOfSnake; i > 0; i--){
            if(snakePosX[i] == dotX && snakePosY[i] == dotY){
                dotX = new Random().nextInt(500/SCALE) * SCALE;
                dotY = new Random().nextInt(500/SCALE) * SCALE;
                System.out.println("Наложение");
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,500,500);

        g.setColor(Color.GREEN);
        g.fillOval(dotX,dotY,SCALE,SCALE);

        g.setColor(Color.WHITE);
        for (int i = 0; i < partsOfSnake; i++){
            g.fillRect(snakePosX[i],snakePosY[i],SCALE,SCALE);
        }

        g.setColor(Color.RED);
        g.fillRect(snakePosX[0],snakePosY[0],SCALE,SCALE);




    }

    @Override
    public void actionPerformed(ActionEvent e) {

        move();
        repaint();
    }
}
