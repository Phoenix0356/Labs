package Program.Lab_1.Lab_2;

import java.awt.*;

import static Program.Lab_1.Lab_2.Data.xValue;
import static Program.Lab_1.Lab_2.Data.yValue;

public class MyPoints {
    public static void paintPoint(Graphics g) {
        Data.button.addActionListener(e -> {
            if (MyPolygon.checkPoint()) {
                g.setColor(new Color(0, 0, 0));
                g.drawOval(xValue - 5, yValue - 5, 9, 9);
                g.setColor(new Color(10, 150, 255));
                g.fillOval(xValue - 5, yValue - 5, 9, 9);
            } else {
                g.setColor(new Color(0, 0, 0));
                g.drawOval(xValue - 5, yValue -5, 9, 9);
                g.setColor(new Color(255, 10, 10));
                g.fillOval(xValue - 5, yValue - 5, 9, 9);
            }

        });
    }
}
