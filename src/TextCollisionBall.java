import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by WZES on 2017/2/18.
 */
class BallFrame extends JFrame implements ActionListener, ChangeListener {
    private static final long serialVisionUID = 1L;
    JPanel panel; //面板
    JPanel preview; //预览面板
    JSlider red;  //红色滑块
    JSlider green;
    JSlider blue;
    JSlider JS_SIZE; //滑块大小
    int x = 45, y = 45;  //方位
    int BALL_SIZE = 30; //球的大小
    public BallFrame(){
        super("collision ball");
        panel = new JPanel();
        panel.setBounds(20, 0, 450, 200);
        panel.setBackground(Color.WHITE);
        preview = new JPanel();
        preview.setBounds(350, 220, 120, 120);
        preview.setBackground(Color.white);

        JTextField status = new JTextField("请选择球的颜色、大小然后单击按钮");
        status.setBounds(1, 404, 492, 20);
        status.setEditable(false);
        JLabel redLabel = new JLabel("红");
        redLabel.setBounds(20, 215, 30, 20);
        JLabel greenLabel = new JLabel("绿");
        greenLabel.setBounds(20, 260, 30, 20);
        JLabel blueLabel = new JLabel("蓝");
        blueLabel.setBounds(20, 305, 30, 20);
        JLabel sizeLabel = new JLabel("大小");
        sizeLabel.setBounds(20, 350, 30, 20);
        red = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 127);
        red.setBounds(50, 210, 250, 45);
        red.putClientProperty("JSlider.isFilled", Boolean.TRUE);
        red.setPaintTicks(true);
        red.setMajorTickSpacing(50); //主要的勾号标记大小
        red.setMinorTickSpacing(20);
        red.setPaintLabels(true);
        red.addChangeListener(this);

        blue = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 127);
        blue.setBounds(50, 300, 250, 45);
        blue.putClientProperty("JSlider.isFilled", Boolean.TRUE);
        blue.setPaintTicks(true);
        blue.setMajorTickSpacing(50); //主要的勾号标记大小
        blue.setMinorTickSpacing(20);
        blue.setPaintLabels(true);
        blue.addChangeListener(this);

        green = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 127);
        green.setBounds(50, 255, 250, 45);
        green.putClientProperty("JSlider.isFilled", Boolean.TRUE);
        green.setPaintTicks(true);
        green.setMajorTickSpacing(50); //主要的勾号标记大小
        green.setMinorTickSpacing(20);
        green.setPaintLabels(true);
        green.addChangeListener(this);

        JS_SIZE = new JSlider(SwingConstants.HORIZONTAL, 10, 50, 30);
        JS_SIZE.setBounds(50, 345, 250, 45);
        JS_SIZE.putClientProperty("JSlider.isFilled", Boolean.TRUE);
        JS_SIZE.setPaintTicks(true);
        JS_SIZE.setMajorTickSpacing(50); //主要的勾号标记大小
        JS_SIZE.setMinorTickSpacing(20);
        JS_SIZE.setPaintLabels(true);
        JS_SIZE.addChangeListener(this);

        JButton jb = new JButton("注入球");
        jb.setBounds(350, 360, 120, 30);
        jb.addActionListener(this);

        Container c = this.getContentPane();
        c.setLayout(null);
        c.add(panel);
        c.add(preview);
        c.add(redLabel);
        c.add(blueLabel);
        c.add(greenLabel);
        c.add(sizeLabel);
        c.add(blue);
        c.add(green);
        c.add(red);
        c.add(JS_SIZE);
        c.add(jb);
        c.add(status);

        this.setBounds(100, 50, 500, 450);
        this.setResizable(true);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Color ball_color = new Color(red.getValue(), green.getValue(), blue.getValue());
        RightBall r = new RightBall(panel, JS_SIZE.getValue(), ball_color);
        r.start();

        LeftBall l = new LeftBall(panel, JS_SIZE.getValue(), ball_color);
        l.start();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Graphics g = preview.getGraphics();
        g.setColor(Color.white);
        g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
        x = 60 - JS_SIZE.getValue() / 2;
        y = 60 - JS_SIZE.getValue() / 2;
        BALL_SIZE = JS_SIZE.getValue();
        g.setColor(new Color(red.getValue(), green.getValue(), blue.getValue()));
        g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
        g.dispose();
    }

}
class LeftBall extends Thread{
    JPanel LEFTPANEL;
    int BALL_SIZE;
    Color BALL_COLOR;
    public LeftBall(JPanel panel, int size, Color color){
        this.LEFTPANEL = panel;
        this.BALL_SIZE = size;
        this.BALL_COLOR = color;
    }
    public void run(){
        Graphics g = LEFTPANEL.getGraphics();
        int x = 0, y = 0;
        int LEFT_X = 450 - BALL_SIZE;
        int LEFT_Y = 200 - BALL_SIZE;
        int x_increase = 5, y_increase = 5;
        while(true){
            g.setColor(Color.white);
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
            g.setColor(BALL_COLOR);
            x += x_increase;
            y += y_increase;
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
            if(x <= 0 || x >= LEFT_X){
                x_increase = - x_increase;
            }
            if(y <= 0 || y >= LEFT_Y){
                y_increase = -y_increase;
            }
            try{
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class RightBall extends Thread{
    JPanel RIGHTPANEL;
    int BALL_SIZE;
    Color BALL_COLOR;
    public RightBall(JPanel panel, int size, Color color){
        this.RIGHTPANEL = panel;
        this.BALL_SIZE = size;
        this.BALL_COLOR = color;
    }
    public void run(){
        Graphics g = RIGHTPANEL.getGraphics();
        int x = 450 - BALL_SIZE, y = 0;
        int RIGHT_X = x;
        int RIGHT_Y = 200 - BALL_SIZE;
        int x_increase = -5, y_increase = 5;
        while(true){
            g.setColor(Color.white);
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
            g.setColor(BALL_COLOR);
            x += x_increase;
            y += y_increase;
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
            if(x <= 0 || x >= RIGHT_X){
                x_increase = - x_increase;
            }
            if(y <= 0 || y >= RIGHT_Y){
                y_increase = -y_increase;
            }
            try{
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class TextCollisionBall {
    public static void main(String[] args) {
        new BallFrame();


    }
}
