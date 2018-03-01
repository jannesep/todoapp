import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TodoApp extends JFrame {

    private JList<TodoItem> itemList;
    private ItemController controller;
    private JTextArea descriptionArea;
    private JCheckBox isDoneBox;
    private JXDatePicker datePicker;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private TodoApp() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new MigLayout("fill"));

        JPanel mainPanel = new JPanel(new MigLayout("fill"));
        mainPanel.setPreferredSize(new Dimension(400, 300));

        controller = new ItemController();

        itemList = new JList<>(controller.getAllItems());
        itemList.setCellRenderer(new ItemListRenderer());
        itemList.addListSelectionListener(e -> {
            if (itemList.getSelectedIndex() != -1) {
                datePicker.setDate(itemList.getSelectedValue().getDueDate());
                descriptionArea.setText(itemList.getSelectedValue().getDescription());
                isDoneBox.setSelected(itemList.getSelectedValue().isDone());
            }
        });

        JButton addNewButton = new JButton("Uusi tehtävä");
        addNewButton.addActionListener(e -> {
            itemList.clearSelection();
            datePicker.setDate(null);
            descriptionArea.setText(null);
            isDoneBox.setSelected(false);
        });

        mainPanel.add(new JScrollPane(itemList), "grow, wrap");
        mainPanel.add(addNewButton);

        JPanel itemDetailsPanel = new JPanel(new MigLayout("fill, wrap 1"));
        itemDetailsPanel.setPreferredSize(new Dimension(300, 300));
        datePicker = new JXDatePicker();
        descriptionArea = new JTextArea(3, 10);
        isDoneBox = new JCheckBox("Tehty");

        JButton saveButton = new JButton("Tallenna");
        saveButton.addActionListener(e -> {
            if (itemList.getSelectedIndex() == -1) {
                controller.addNewItem(new TodoItem(datePicker.getDate(), descriptionArea.getText(), isDoneBox.isSelected()));
                itemList.setListData(controller.getAllItems());
            } else {
                TodoItem oldItem = itemList.getSelectedValue();
                oldItem.setDueDate(datePicker.getDate());
                oldItem.setDescription(descriptionArea.getText());
                oldItem.setDone(isDoneBox.isSelected());
                controller.updateItem(oldItem);
                itemList.setListData(controller.getAllItems());
                itemList.setSelectedValue(oldItem, true);
            }
        });

        itemDetailsPanel.add(new JLabel("Päivämäärä"));
        itemDetailsPanel.add(datePicker, "grow");
        itemDetailsPanel.add(new JLabel("Kuvaus"));
        itemDetailsPanel.add(descriptionArea, "grow");
        itemDetailsPanel.add(isDoneBox);
        itemDetailsPanel.add(saveButton);

        add(mainPanel);
        add(itemDetailsPanel);
        pack();
    }

    public static void main(String... args) {
        new TodoApp().setVisible(true);
    }
}
