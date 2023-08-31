package Program.Lab_1;

import javax.swing.*;

public class Draw extends JFrame {
    public Draw(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add( new MyPanel());
        this.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Draw();
            }
        });
    }
}
