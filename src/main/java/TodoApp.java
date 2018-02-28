import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TodoApp extends JFrame {

    private JPanel mainPanel;
    private JList<TodoItem> itemList;
    private ItemController controller;
    private JButton addNewButton;
    private JButton closeButton;
    private JButton saveButton;
    private JTextArea descriptionArea;
    private JCheckBox isDoneBox;
    private JTextField datePicker;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private TodoApp() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new MigLayout("fill"));

        mainPanel = new JPanel(new MigLayout("fill"));

        controller = new ItemController();

        itemList = new JList<>(controller.getAllItems());
        itemList.addListSelectionListener(e -> {
            if (itemList.getSelectedIndex() != -1) {
                datePicker.setText(dateFormat.format(itemList.getSelectedValue().getDueDate()));
                descriptionArea.setText(itemList.getSelectedValue().getDescription());
                isDoneBox.setSelected(itemList.getSelectedValue().isDone());
            }
        });
        addNewButton = new JButton("Uusi tehtävä");
        addNewButton.addActionListener(e -> {
            itemList.clearSelection();
            datePicker.setText(null);
            descriptionArea.setText(null);
            isDoneBox.setSelected(false);
        });

        mainPanel.add(itemList, "grow, wrap");
        mainPanel.add(addNewButton);

        JPanel itemDetailsPanel = new JPanel(new MigLayout("fill, wrap 1"));
        datePicker = new JTextField();
        descriptionArea = new JTextArea(3, 10);
        isDoneBox = new JCheckBox();
        saveButton = new JButton("Tallenna");
        saveButton.addActionListener(e -> {
            try {
                if (itemList.getSelectedIndex() == -1) {
                    controller.addNewItem(new TodoItem(dateFormat.parse(datePicker.getText()), descriptionArea.getText(), isDoneBox.isSelected()));
                    itemList.setListData(controller.getAllItems());
                } else {
                    TodoItem oldItem = itemList.getSelectedValue();
                    oldItem.setDueDate(dateFormat.parse(datePicker.getText()));
                    oldItem.setDescription(descriptionArea.getText());
                    oldItem.setDone(isDoneBox.isSelected());
                    controller.updateItem(oldItem);
                    itemList.setListData(controller.getAllItems());
                    itemList.setSelectedValue(oldItem, true);
                }
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
        });

        itemDetailsPanel.add(new JLabel("Päivämäärä"));
        itemDetailsPanel.add(datePicker, "grow");
        itemDetailsPanel.add(new JLabel("Kuvaus"));
        itemDetailsPanel.add(descriptionArea, "grow");
        itemDetailsPanel.add(new JLabel("Tehty"));
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
