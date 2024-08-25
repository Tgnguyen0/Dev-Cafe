package app.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import app.AppFunctions.CafeFunction;
import app.InitFont.CustomFont;
import app.Object.*;
import app.SaveToFile.ReadSaveFromFile;

public class ProductPage extends JPanel {
    private DefaultTableModel tableModel;
    private JTextField nameInput;
    private JTextField idInput;
    private JRadioButton servedHotRadio;
    private JTextField priceInput;
    private String id;
    private String name;
    private Boolean servedHot;
    private double price;
    CafeFunction menu = new CafeFunction();
    CustomFont customFont = new CustomFont();

    public ProductPage() {
        setPreferredSize(new Dimension(1100, 500));
        setLayout(new BorderLayout());
        //setBackground(new Color(225, 203, 177));
        setOpaque(false);

        JPanel empty = new JPanel();
        empty.setPreferredSize(new Dimension(1100, 140));
        empty.setOpaque(false);
        add(empty, BorderLayout.NORTH);

        createEmpTextBox();
        createEmpTablePanel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        String imagePath = "dev_cafe/asset/background.png"; // Path to your GIF image file
        File imageFile = new File(imagePath);

        //Chèn ảnh vào Option menu
        try {
            // Đọc ảnh từ file
            Image image = ImageIO.read(imageFile);

            // Tạo icon cho ảnh
            int newWidth = getWidth(); // Get the width of the panel
            int newHeight = getHeight(); // Get the height of the panel
            Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createEmpTextBox() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(1100, 300));
        panel.setOpaque(false);
        //panel.setBackground(new Color(225, 203, 177));

        JPanel emptyW = new JPanel();
        emptyW.setPreferredSize(new Dimension(15, 300));
        emptyW.setOpaque(false);
        panel.add(emptyW, BorderLayout.WEST);

        JPanel emptyN = new JPanel();
        emptyN.setPreferredSize(new Dimension(1100, 20));
        emptyN.setOpaque(false);
        panel.add(emptyN, BorderLayout.NORTH);

        JPanel emptyE = new JPanel();
        emptyE.setPreferredSize(new Dimension(15, 300));
        emptyE.setOpaque(false);
        panel.add(emptyE, BorderLayout.EAST);

        JPanel emptyS = new JPanel();
        emptyS.setPreferredSize(new Dimension(1100, 15));
        emptyS.setOpaque(false);
        panel.add(emptyS, BorderLayout.SOUTH);

        JPanel tbiPanel = new JPanel();
        tbiPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tbiPanel.setPreferredSize(new Dimension(1100, 300));
        tbiPanel.setOpaque(false);

        JPanel tbPanel = new JPanel();
        tbPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tbPanel.setPreferredSize(new Dimension(1100, 300));
        tbPanel.setOpaque(false);

        JPanel left = new JPanel();
        left.setLayout(new FlowLayout(FlowLayout.LEFT));
        left.setOpaque(false);
        left.setPreferredSize(new Dimension(330, 300));

        JLabel idLabel = new JLabel("Id: ");
        idLabel.setFont(customFont.getFernandoFont(13));
        idLabel.setForeground(new Color(255, 213, 146));
        idLabel.setPreferredSize(new Dimension(120, 25));
        left.add(idLabel);

        idInput = new JTextField();
        idInput.setPreferredSize(new Dimension(140, 25));
        idInput.setBackground(new Color(255, 213, 146));
        idInput.setFont(customFont.getFernandoFont(9));
        idInput.setForeground(new Color(79, 92, 133));
        idInput.setBorder(BorderFactory.createLineBorder(new Color(21, 24, 48)));
        left.add(idInput);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(customFont.getFernandoFont(13));
        nameLabel.setForeground(new Color(255, 213, 146));
        nameLabel.setPreferredSize(new Dimension(120, 25));
        left.add(nameLabel);

        nameInput = new JTextField();
        nameInput.setPreferredSize(new Dimension(140, 25));
        nameInput.setBackground(new Color(255, 213, 146));
        nameInput.setFont(customFont.getFernandoFont(9));
        nameInput.setForeground(new Color(79, 92, 133));
        nameInput.setBorder(BorderFactory.createLineBorder(new Color(21, 24, 48)));
        left.add(nameInput);

        tbPanel.add(left);

        JPanel right = new JPanel();
        right.setLayout(new FlowLayout(FlowLayout.LEFT));
        right.setOpaque(false);
        right.setPreferredSize(new Dimension(330, 300));

        JLabel priceLabel = new JLabel("Price: ");
        priceLabel.setFont(customFont.getFernandoFont(13));
        priceLabel.setForeground(new Color(255, 213, 146));
        priceLabel.setPreferredSize(new Dimension(120, 25));
        right.add(priceLabel);

        priceInput = new JTextField();
        priceInput.setPreferredSize(new Dimension(140, 25));
        priceInput.setBackground(new Color(255, 213, 146));
        priceInput.setFont(customFont.getFernandoFont(9));
        priceInput.setForeground(new Color(79, 92, 133));
        priceInput.setBorder(BorderFactory.createLineBorder(new Color(21, 24, 48)));
        right.add(priceInput);

        JLabel hotLabel = new JLabel("Cold: ");
        hotLabel.setFont(customFont.getFernandoFont(13));
        hotLabel.setForeground(new Color(255, 213, 146));
        hotLabel.setPreferredSize(new Dimension(120, 25));
        right.add(hotLabel);

        servedHotRadio = new JRadioButton();
        servedHotRadio.setPreferredSize(new Dimension(140, 25));
        servedHotRadio.setBackground(new Color(255, 213, 146));
        servedHotRadio.setFocusPainted(false);
        servedHotRadio.setOpaque(false);
        servedHotRadio.setBorder(BorderFactory.createLineBorder(new Color(21, 24, 48)));
        right.add(servedHotRadio);

        tbPanel.add(right);

        tbiPanel.add(tbPanel);

        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new BorderLayout());
        imgPanel.setPreferredSize(new Dimension(250, 300));
        //Border lineBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255, 213, 146));
        //imgPanel.setBorder(lineBorder);
        imgPanel.setOpaque(false);

        String imagePath = "dev_cafe/asset/food.png";
        Image scaledImage;

        try {
            // Read the image from the file
            Image image = ImageIO.read(new File(imagePath));

            // Scale the image
            int newWidth = image.getWidth(null) / 4; // Desired width
            int newHeight = image.getHeight(null) / 4; // Desired height
            scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Create an ImageIcon from the scaled image
            ImageIcon imageIcon = new ImageIcon(scaledImage);

            // Create a JLabel and set the icon
            JLabel imgLabel = new JLabel(imageIcon);
            imgLabel.setPreferredSize(new Dimension(250, 300));
            imgLabel.setHorizontalAlignment(JLabel.CENTER);
            imgLabel.setVerticalAlignment(JLabel.NORTH);
            imgLabel.setBackground(Color.CYAN);
            imgPanel.add(imgLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tbPanel.add(imgPanel);
        
        //tbiPanel.add(imgPanel, BorderLayout.SOUTH);
        panel.add(tbiPanel, BorderLayout.CENTER);

        add(panel, BorderLayout.NORTH);
    }

    private void createEmpTablePanel() {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tablePanel.setPreferredSize(new Dimension(1100, 500));
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setOpaque(false);

        JPanel emptyW = new JPanel();
        emptyW.setPreferredSize(new Dimension(20, 500));
        emptyW.setOpaque(false);
        tablePanel.add(emptyW, BorderLayout.WEST);

        JPanel emptyE = new JPanel();
        emptyE.setPreferredSize(new Dimension(20, 500));
        emptyE.setOpaque(false);
        tablePanel.add(emptyE, BorderLayout.EAST);

        //panel.setBackground(new Color(225, 203, 177));

        this.tableModel = new DefaultTableModel();
        tableModel.addColumn("N0");
        tableModel.addColumn("Name");
        tableModel.addColumn("Price");

        ReadSaveFromFile s = new ReadSaveFromFile();

        try {
            Object o = s.ReadFile("dev_cafe/data/menu_items_data.txt");
            
            if (o instanceof ArrayList<?>) {
                @SuppressWarnings("unchecked")
                ArrayList<MenuItem> listOfItem= (ArrayList<MenuItem>) o;
                menu.addAllItem(listOfItem);

                for (MenuItem item : menu.getListOfItem()) {
                    Vector<String> rowData = new Vector<>();
                    rowData.add(item.getId());
                    rowData.add(item.getName());
                    rowData.add(String.valueOf(item.getPrice()));
                    tableModel.addRow(rowData);
                }
            }

            System.out.println("Import successfully!");
        } catch (Exception ee) {
            ee.printStackTrace();
        }

        JTable table = new JTable(tableModel);
        table.setFont(customFont.getFernandoFont(9));
        table.setForeground(new Color(79, 92, 133));
        table.setBackground(new Color(255, 213, 146));

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setForeground(new Color(79, 92, 133));
        tableHeader.setBackground(new Color(255, 213, 146));
        tableHeader.setFont(customFont.getFernandoFont(9));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Call the superclass method to get the default renderer
                JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
                // Customize the cell's appearance based on your requirements
                cell.setForeground(new Color(255, 213, 146)); // Set text color
                cell.setBackground(new Color(79, 92, 133)); // Set background color
                cell.setFont(customFont.getFernandoFont(9));
        
                return cell;
            }
        };
        
        // Apply the custom renderer to each column
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 500));
        scrollPane.setForeground(new Color(79, 92, 133));
        scrollPane.getViewport().setBackground(new Color(255, 213, 146));

        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setPreferredSize(new Dimension(800, 50));
        panel.setOpaque(false);

        JPanel left = new JPanel();
        left.setLayout(new FlowLayout(FlowLayout.LEFT));
        left.setPreferredSize(new Dimension(360, 35));
        Border lineBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(255, 213, 146));
        left.setBorder(lineBorder);
        left.setOpaque(false);

        JLabel searchLabel = new JLabel("Tìm kiếm:");
        searchLabel.setFont(customFont.getFernandoFont(9));
        searchLabel.setForeground(new Color(255, 213, 146));
        searchLabel.setPreferredSize(new Dimension(60, 25)); // Thay đổi kích thước cho phù hợp
        left.add(searchLabel);

        JTextField searchBar = new JTextField();
        searchBar.setForeground(new Color(79, 92, 133));
        searchBar.setBackground(new Color(255, 213, 146));
        searchBar.setBorder(null);
        searchBar.setFont(customFont.getFernandoFont(9));
        searchBar.setPreferredSize(new Dimension(170, 25)); // Thay đổi kích thước cho phù hợp và vị trí
        left.add(searchBar);

        JButton findProduct = new JButton("Tìm");
        findProduct.setFont(customFont.getFernandoFont(10));
        findProduct.setForeground(new Color(79, 92, 133));
        findProduct.setBackground(new Color(255, 213, 146));
        findProduct.setPreferredSize(new Dimension(100, 25));
        left.add(findProduct);

        panel.add(left);

        JPanel right = new JPanel();
        right.setLayout(new FlowLayout(FlowLayout.LEFT));
        right.setPreferredSize(new Dimension(490, 35));
        right.setOpaque(false);

        JButton addButton = new JButton("Thêm");
        addButton.setFont(customFont.getFernandoFont(10));
        addButton.setPreferredSize(new Dimension(100, 25));
        addButton.setForeground(new Color(79, 92, 133));
        addButton.setBackground(new Color(255, 213, 146));
        right.add(addButton);

        JButton deleteButton = new JButton("Xóa");
        deleteButton.setFont(customFont.getFernandoFont(10));
        deleteButton.setPreferredSize(new Dimension(100, 25));
        deleteButton.setForeground(new Color(79, 92, 133));
        deleteButton.setBackground(new Color(255, 213, 146));
        right.add(deleteButton);

        JButton cancelChangeButton = new JButton("Hủy thay đổi");
        cancelChangeButton.setFont(customFont.getFernandoFont(10));
        cancelChangeButton.setPreferredSize(new Dimension(120, 25));
        cancelChangeButton.setForeground(new Color(79, 92, 133));
        cancelChangeButton.setBackground(new Color(255, 213, 146));
        right.add(cancelChangeButton);

        JButton saveButton = new JButton("Lưu");
        saveButton.setFont(customFont.getFernandoFont(9));
        saveButton.setPreferredSize(new Dimension(100, 25));
        saveButton.setForeground(new Color(79, 92, 133));
        saveButton.setBackground(new Color(255, 213, 146));
        right.add(saveButton);

        panel.add(right);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = idInput.getText();
                name = nameInput.getText();
                servedHot = servedHotRadio.isSelected();
                price = Double.parseDouble(priceInput.getText());

                MenuItem item = new MenuItem(id, name, servedHot, price);
                menu.addItem(item);

                idInput.setText("");
                nameInput.setText("");
                servedHotRadio.setSelected(false);
                priceInput.setText("");

                Vector<String> rowData = new Vector<>();
                rowData.add(id);
                rowData.add(name);
                rowData.add(String.valueOf(price));
                tableModel.addRow(rowData);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    idInput.setText((String) tableModel.getValueAt(selectedRow, 0));
                    nameInput.setText((String) tableModel.getValueAt(selectedRow, 1));
                    priceInput.setText((String) tableModel.getValueAt(selectedRow, 2));

                    // Delete employee
                    menu.deleteItem((String) tableModel.getValueAt(selectedRow, 0));

                    // Delete the selected row
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete", "Delete Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReadSaveFromFile s = new ReadSaveFromFile();

                try {
                    s.SaveFile(menu.getListOfItem(), "dev_cafe/data/menu_items_data.txt");
                    System.out.println("Save successfully!");
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        idInput.setText((String) tableModel.getValueAt(selectedRow, 0));
                        nameInput.setText((String) tableModel.getValueAt(selectedRow, 1));
                        priceInput.setText((String) tableModel.getValueAt(selectedRow, 2));
                    }
                }
            }
        });

        tablePanel.add(panel, BorderLayout.SOUTH);

        add(tablePanel, BorderLayout.CENTER);
    }
}
