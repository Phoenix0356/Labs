package Program.Lab_1.Lab_2;

import java.awt.*;

import static Program.Lab_1.Lab_2.Data.*;
import static Program.Lab_1.Lab_2.Data.Y_CORRECTION;

public class MyPolygon extends Polygon {
    public void drawTriangle(Graphics g,int x1,int y1,int x2,int y2,int x3,int y3) {
        this.addPoint(x1,y1);
        this.addPoint(x2,y2);
        this.addPoint(x3,y3);
        g.fillPolygon(this);
    }
    public static boolean checkPoint(){
        if (xValue>=200+X_CORRECTION&&xValue<=300+X_CORRECTION&&yValue<=xValue-106+Y_CORRECTION&&yValue>=100+Y_CORRECTION){
            return true;
        } else if (xValue>=200+X_CORRECTION&&xValue<=300+X_CORRECTION&&yValue<=(-2)*xValue+812+Y_CORRECTION&&yValue>=200+Y_CORRECTION){
            return true;
        }else if (xValue>=100+X_CORRECTION&&xValue<=200+X_CORRECTION&&yValue<=100+Math.sqrt(10000-Math.pow(xValue-106,2))+Y_CORRECTION&&yValue>=100+Y_CORRECTION){
            return true;
        }else if (xValue>=100+X_CORRECTION&&xValue<=200+X_CORRECTION&&yValue<=400+Y_CORRECTION&&yValue>=(2)*xValue-12+Y_CORRECTION){
            return true;
        }
        return false;
    }
}
