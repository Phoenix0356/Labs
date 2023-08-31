package Program.Lab_1.Lab_2;

import javax.swing.*;
import java.awt.*;


import static Program.Lab_1.Lab_2.Data.*;


public class PanelOfGraphics extends JPanel {
    public PanelOfGraphics() {}

    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(150, 255, 150));
        Graphics2D g2 = (Graphics2D) g;

        MyPolygon firstTri = new MyPolygon();
        firstTri.drawTriangle(g, 200, 100, 300, 100, 300, 200);

        MyPolygon secondTri = new MyPolygon();
        secondTri.drawTriangle(g, 200, 200, 200, 400, 300, 200);

        MyPolygon thirdTri = new MyPolygon();
        thirdTri.drawTriangle(g, 100, 200, 100, 400, 200, 400);

        g.fillArc(0, 0, 200, 200, 270, 90);

        g2.setStroke(new BasicStroke(2));
        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i < POS_LIST1.length; i++) {
            g.drawLine(POS_LIST1[i][0], POS_LIST1[i][1], POS_LIST2[i][0], POS_LIST2[i][1]);
            g.drawArc(0, 0, 200, 200, 270, 90);
        }
        MyPoints.paintPoint(g);
    }
}

