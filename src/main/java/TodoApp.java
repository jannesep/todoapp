import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TodoApp extends JFrame {

    private JPanel mainPanel;
    private JList<TodoItem> itemList;

    private TodoApp() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new MigLayout("fill"));
        mainPanel.add(new JLabel("Hello, world!"));
        pack();
        add(mainPanel);
    }

    public static void main(String... args) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:tododb.db3";
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        new TodoApp().setVisible(true);
    }
}
