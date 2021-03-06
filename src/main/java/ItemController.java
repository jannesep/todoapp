import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class ItemController {

    private Connection conn;
    private SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ItemController() {
        try {
            // Switch these around if running from an IDE
//            String url = "jdbc:sqlite:src/main/resources/tododb.db3";
            String url = "jdbc:sqlite::resource:tododb.db3";

            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Tietokantayhteys epäonnistui, ohjelma lopetetaan",
                    "Virhe tietokantayhteydessä", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Vector<TodoItem> getAllItems() {
        Vector<TodoItem> result = new Vector<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT rowid, duedate, description, is_done FROM items ORDER BY is_done, duedate");
            while (rs.next()) {
                result.add(new TodoItem(rs.getInt(1), sqlFormat.parse(rs.getString(2)), rs.getString(3), rs.getBoolean(4)));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addNewItem(TodoItem item) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO items (duedate, description, is_done) VALUES (?, ?, ?)");
            ps.setString(1, sqlFormat.format(item.getDueDate()));
            ps.setString(2, item.getDescription());
            ps.setBoolean(3, item.isDone());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateItem(TodoItem item) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE items SET duedate = ?, description = ?, is_done = ? WHERE rowid = ?");
            ps.setString(1, sqlFormat.format(item.getDueDate()));
            ps.setString(2, item.getDescription());
            ps.setBoolean(3, item.isDone());
            ps.setInt(4, item.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
