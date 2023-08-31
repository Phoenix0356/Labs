package Program.Lab_1;

import javax.swing.*;
import java.awt.*;

import static Program.Lab_1.Constants.*;

public class MyPanel extends JPanel {
   @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);

       for (int i=0;i<COORDINATE_OF_RECT.length;i++){
           g.setColor(COLORS[i]);
           g.drawRect(COORDINATE_OF_RECT[i][0],COORDINATE_OF_RECT[i][1],SIZE_OF_RECT[0],SIZE_OF_RECT[1]);
           g.fillRect(COORDINATE_OF_RECT[i][0],COORDINATE_OF_RECT[i][1],SIZE_OF_RECT[0],SIZE_OF_RECT[1]);
       }
       g.setColor(Color.black);
       for (int i = 0; i < FIRST_POINT.length; i++){
           g.drawLine(FIRST_POINT[i][0], FIRST_POINT[i][1], SECOND_POINT[i][0], SECOND_POINT[i][1]);
   }
        }

}
