package Program.Lab_1.Lab_4;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

public class main {
    public static void main(String[] args) throws ParseException, IOException {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Lab3, Chen Jiaqi, 21321105");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.setSize(600,300);
            frame.setLocation(600,200);
            frame.setLayout(new GridLayout(0,3));

            Gui.DataArea dataArea = new Gui.DataArea();
            frame.add(dataArea,BorderLayout.WEST);

            Gui.ButtonArea buttonArea=new Gui.ButtonArea();
            frame.add(buttonArea,BorderLayout.CENTER);

            Gui.DetailArea detailArea=new Gui.DetailArea();
            frame.add(detailArea,BorderLayout.EAST);

            frame.setVisible(true);
        });
    }
}
