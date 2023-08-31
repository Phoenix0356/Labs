package Program.Lab_1.Lab_2;

import javax.swing.*;

import static Program.Lab_1.Lab_2.Data.*;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PanelOfGraphics panelOfGraphics = new PanelOfGraphics();
                frame.add(panelOfGraphics);
                PanelOfComponents PanelOfComponents = new PanelOfComponents();
                frame.add(PanelOfComponents);
                System.out.println(frame.getInsets());
                frame.setVisible(true);
            }
        });
    }
}
