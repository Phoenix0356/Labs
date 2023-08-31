package Program.Lab_1.Lab_2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


import static Program.Lab_1.Lab_2.Data.*;

public class PanelOfComponents extends JPanel {
    public PanelOfComponents() {
        this.setPane();
        this.setLayout(new GridLayout(2,3));
    }
    private void setPane(){
        JLabel xLabel=new JLabel("X: ");
        this.add(MyComponent.addComp(xLabel,30,100,20,20));
        xLabel.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                super.componentAdded(e);
                xLabel.setText(String.valueOf(xValue));
            }
        });

        MyComponent xList=new MyComponent(50,100,50,150);
        xList.setList(xList,xSet);


        JLabel yLabel=new JLabel("Y: ");
        this.add(MyComponent.addComp(yLabel,30,100,20,20));
        this.add(MyComponent.addComp(button,250,100,100,50));

        JScrollPane pane=new JScrollPane(xList);
        pane.setBounds(50,100,50,150);
        pane.createHorizontalScrollBar();
        this.add(pane);

        JScrollBar yScrollBar=new JScrollBar(JScrollBar.VERTICAL,0,0,-200,200);
        yScrollBar.setBounds(180,100,50,150);
        this.add(yScrollBar);
        yScrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                yValue=yScrollBar.getValue()+200+Y_CORRECTION;
                System.out.println(-yValue+Y_CORRECTION+200);
            }
        });
    }
}
