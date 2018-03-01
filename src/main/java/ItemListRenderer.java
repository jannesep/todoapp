import javax.swing.*;
import java.awt.*;

public class ItemListRenderer implements ListCellRenderer<TodoItem> {

    private JLabel label;

    ItemListRenderer() {
        label = new JLabel();
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends TodoItem> list, TodoItem value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            label.setText(value.toString());
            if (value.isDone()) {
                label.setForeground(Color.GREEN.darker());
            } else {
                label.setForeground(null);
            }
        }
        return label;
    }
}
