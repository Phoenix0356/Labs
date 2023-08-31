package Program.Lab_1.Lab_2;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static Program.Lab_1.Lab_2.Data.*;


public class MyComponent extends JList {
    int x, y, w, h;
    DefaultListModel listModel = new DefaultListModel();
    public MyComponent(int x1, int x2, int y1, int y2) {
        this.x = x1;
        this.y = x2;
        this.w = y1;
        this.h = y2;
        getValue(this);

    }
    public JList<Integer> setList(JList<Integer> list, int[] data) {
        list.setBounds(x, y, w, h);
        list.setModel(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        for (int i = 0; i < data.length; i++) {
            listModel.addElement(data[i]);

        }
        return list;
    }
    public void getValue(JList list) {
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Data.xValue =(int)list.getSelectedValue()+200+X_CORRECTION;
            }
        });
    }
    public static JLabel addComp(JLabel label,int x,int y,int width,int height) {
        label.setBounds(x, y, width, height);
        return label;
    }
    public static JButton addComp(JButton button,int x,int y,int width,int height) {
        button.setBounds(x, y, width, height);
        return button;
    }
}
