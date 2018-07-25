
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public SnakeComponent sk;


    public Main(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        sk = new SnakeComponent();
        setTitle("Snake");
        //setSize(WIDTH,HEIGHT );


        setLocationRelativeTo(null);
        setResizable(false);
        setLocation((dimension.width - WIDTH)  / 2 , (dimension.height - HEIGHT) / 2 );
        setVisible(true);
        this.add(sk);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_LEFT && !sk.right){
                    sk.left = true;
                    sk.up = false;
                    sk.down = false;
                }
                if(key == KeyEvent.VK_RIGHT && !sk.left){
                    sk.right = true;
                    sk.up = false;
                    sk.down = false;
                }

                if(key == KeyEvent.VK_UP && !sk.down){
                    sk.right = false;
                    sk.up = true;
                    sk.left = false;
                }
                if(key == KeyEvent.VK_DOWN && !sk.up){
                    sk.right = false;
                    sk.down = true;
                    sk.left = false;
                }

            }

        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        Main n = new Main();
        n.setContentPane(n.sk);
        n.pack();

    }
}