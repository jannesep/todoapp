import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class TodoApp extends JFrame {

    private JPanel mainPanel;

    private TodoApp() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new MigLayout("fill"));
        mainPanel.add(new JLabel("Hello, world!"));
        pack();
        add(mainPanel);
    }

    public static void main(String... args) {
        new TodoApp().setVisible(true);
    }
}
