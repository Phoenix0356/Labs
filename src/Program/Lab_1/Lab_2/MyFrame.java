package Program.Lab_1.Lab_2;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame(){
        this.setLayout(new GridLayout(2,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,50,400,900);
        this.setTitle("Lab_2");
        this.setVisible(true);
    }
}
