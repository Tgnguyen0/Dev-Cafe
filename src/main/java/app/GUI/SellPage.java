package app.GUI;

import app.AppFunctions.CafeFunction;
import app.InitFont.CustomFont;
import app.Object.BillDetail;
import app.Object.MenuItem;
import app.SaveToFile.ReadSaveFromFile;
import app.AppFunctions.BillDetailsManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class SellPage extends JPanel {
    private CustomFont customFont = new CustomFont();
    private DefaultTableModel receiptTableModel;
    private DefaultTableModel productTableModel;
    private CafeFunction menu = new CafeFunction();
    private BillDetailsManagement bdl = new BillDetailsManagement();
    private JTable productTable;
    private JTable table;
    private OrderButtonListener orderButtonListener;
    private boolean created = false;

    public SellPage() {
        setPreferredSize(new Dimension(1100, 500));
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel emptyL = new JPanel();
        emptyL.setPreferredSize(new Dimension(16, 500));
        emptyL.setOpaque(false);
        add(emptyL, BorderLayout.WEST);

        JPanel emptyR = new JPanel();
        emptyR.setPreferredSize(new Dimension(16, 500));
        emptyR.setOpaque(false);
        add(emptyR, BorderLayout.EAST);

        add(createSearchPane(), BorderLayout.NORTH);
        add(createProductPanel(), BorderLayout.CENTER);
        add(createReceiptTable(), BorderLayout.SOUTH);
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

    public JPanel createSearchPane() {
        JPanel north = new JPanel();
        north.setPreferredSize(new Dimension(800, 55));
        north.setOpaque(false);
        north.setVisible(true);
        north.setLayout(new BorderLayout());

        JPanel northN = new JPanel();
        northN.setPreferredSize(new Dimension(800, 25));
        northN.setOpaque(false);
        northN.setVisible(true);
        northN.setLayout(new FlowLayout(FlowLayout.LEFT));
        north.add(northN, BorderLayout.CENTER);

        JPanel emptyN = new JPanel();
        emptyN.setPreferredSize(new Dimension(800, 25));
        emptyN.setOpaque(false);
        north.add(emptyN, BorderLayout.NORTH);

        JPanel emptyL = new JPanel();
        emptyL.setPreferredSize(new Dimension(12, 25));
        emptyL.setOpaque(false);
        north.add(emptyL, BorderLayout.WEST);

        JLabel searchLabel = new JLabel("Tìm kiếm:");
        searchLabel.setFont(customFont.getFernandoFont(9));
        searchLabel.setForeground(new Color(255, 213, 146));
        searchLabel.setPreferredSize(new Dimension(60, 25)); // Thay đổi kích thước cho phù hợp
        northN.add(searchLabel);

        JTextField searchBar = new JTextField();
        searchBar.setForeground(new Color(79, 92, 133));
        searchBar.setBackground(new Color(255, 213, 146));
        searchBar.setBorder(null);
        searchBar.setFont(customFont.getFernandoFont(9));
        searchBar.setPreferredSize(new Dimension(170, 25)); // Thay đổi kích thước cho phù hợp và vị trí
        northN.add(searchBar);

        JButton findProduct = new JButton("Tìm");
        findProduct.setFont(customFont.getFernandoFont(9));
        findProduct.setForeground(new Color(79, 92, 133));
        findProduct.setBackground(new Color(255, 213, 146));
        findProduct.setPreferredSize(new Dimension(60, 25));
        northN.add(findProduct);

        JLabel chooseLabel = new JLabel("Loại sản phẩm:");
        chooseLabel.setFont(customFont.getFernandoFont(9));
        chooseLabel.setForeground(new Color(255, 213, 146));
        chooseLabel.setPreferredSize(new Dimension(80, 25));
        northN.add(chooseLabel);

        JComboBox<String> productCategory = new JComboBox<>();
        productCategory.setForeground(new Color(79, 92, 133));
        productCategory.setBackground(new Color(255, 213, 146));
        productCategory.setFont(customFont.getFernandoFont(9));
        productCategory.addItem("Tất cả");
        productCategory.addItem("Cà phê");
        productCategory.addItem("Soda");
        productCategory.addItem("Kem");
        productCategory.setPreferredSize(new Dimension(65, 25));
        northN.add(productCategory);

        return north;
    }

    public JPanel createProductPanel() {
        JPanel ptPanel = new JPanel();
        ptPanel.setOpaque(false);
        ptPanel.setPreferredSize(new Dimension(800, 300));
        ptPanel.setLayout(new BorderLayout());

        JPanel emptyN = new JPanel();
        emptyN.setPreferredSize(new Dimension(800, 10));
        emptyN.setOpaque(false);
        ptPanel.add(emptyN, BorderLayout.NORTH);

        productTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make "Points" column (index 0,1,2) uneditable
                return column != 0 && column != 1 && column != 3;
            }
        };
        productTableModel.addColumn("N0");
        productTableModel.addColumn("Name");
        productTableModel.addColumn("Served");
        productTableModel.addColumn("Price");

        ReadSaveFromFile s = new ReadSaveFromFile();
        Boolean isServed = false;

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
                    rowData.add(isServed ? "Cold" : "Hot");
                    rowData.add(String.valueOf(item.getPrice()));
                    productTableModel.addRow(rowData);
                }
            }

            System.out.println("Import successfully!");
        } catch (Exception ee) {
            ee.printStackTrace();
        }

        productTable = new JTable(productTableModel);
        productTable.setFont(customFont.getFernandoFont(9));
        productTable.setForeground(new Color(79, 92, 133));
        productTable.setBackground(new Color(255, 213, 146));

        JComboBox<String> servedComboBox = new JComboBox<>();
        servedComboBox.setForeground(new Color(79, 92, 133));
        servedComboBox.setBackground(new Color(255, 213, 146));
        servedComboBox.setFont(customFont.getFernandoFont(9));
        servedComboBox.addItem("Hot");
        servedComboBox.addItem("Cold");

        TableCellEditor comboBoxEditor = new DefaultCellEditor(servedComboBox);

        // Set the cell editor for the "Served" column (index 2)
        TableColumn servedColumn = productTable.getColumnModel().getColumn(2);
        servedColumn.setCellEditor(comboBoxEditor);

        JTableHeader tableHeader = productTable.getTableHeader();
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

        for (int i = 0; i < productTable.getColumnCount(); i++) {
            productTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        scrollPane.getViewport().setBackground(new Color(255, 213, 146));
        scrollPane.setBackground(new Color(255, 213, 146));
        scrollPane.setForeground(new Color(79, 92, 133));
        ptPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel imgPanel = new JPanel();
        imgPanel.setPreferredSize(new Dimension(250, 400));
        imgPanel.setOpaque(false);
        imgPanel.setLayout(new BorderLayout());
        ptPanel.add(imgPanel, BorderLayout.EAST);

        JPanel emptyN1 = new JPanel();
        emptyN1.setOpaque(false);
        emptyN1.setPreferredSize(new Dimension(250, 70));
        imgPanel.add(emptyN1, BorderLayout.NORTH); 

        JPanel itemPanel = new JPanel();
        itemPanel.setPreferredSize(new Dimension(240, 200));
        itemPanel.setOpaque(false);
        itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        imgPanel.add(itemPanel, BorderLayout.CENTER);

        String imagePath = "dev_cafe/asset/dev_cafe.png";
        //File imageFile = new File(imagePath);

        // Tạo icon cho ảnh
        ImageIcon imageIcon = new ImageIcon(imagePath); // image_width: 2560, 1440

        JLabel imgLabel = new JLabel(imageIcon);
        imgLabel.setPreferredSize(new Dimension(250, 106));
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        imgLabel.setVerticalAlignment(JLabel.CENTER);
        imgLabel.setBackground(Color.CYAN);
        itemPanel.add(imgLabel);

        JLabel nameLabel = new JLabel("Capuchino");
        nameLabel.setPreferredSize(new Dimension(250, 25));
        nameLabel.setFont(customFont.getFernandoFont(15));
        nameLabel.setForeground(new Color(255, 213, 146));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        itemPanel.add(nameLabel);

        JLabel priceLabel = new JLabel("Price: $10.2");
        priceLabel.setPreferredSize(new Dimension(250, 25));
        priceLabel.setFont(customFont.getFernandoFont(15));
        priceLabel.setForeground(new Color(255, 213, 146));
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        priceLabel.setVerticalAlignment(JLabel.CENTER);
        itemPanel.add(priceLabel);

        JPanel emptySI = new JPanel();
        emptySI.setOpaque(false);
        emptySI.setPreferredSize(new Dimension(240, 70));
        imgPanel.add(emptySI, BorderLayout.SOUTH);

        JPanel emptyS = new JPanel();
        emptyS.setOpaque(false);
        emptyS.setPreferredSize(new Dimension(400, 20));
        ptPanel.add(emptyS, BorderLayout.SOUTH);

        return ptPanel;
    }

    public JPanel createReceiptTable() {
        JLabel recieptTableLabel = new JLabel("Đồ ăn thức uống đã chọn:");
        recieptTableLabel.setFont(customFont.getFernandoFont(9));
        recieptTableLabel.setForeground(new Color(255, 213, 146));
        recieptTableLabel.setOpaque(false);

        // Initialize the receiptTableModel with columns
        receiptTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make "Points" column (index 0,1,4) uneditable
                return column != 0 && column != 1 && column != 4;
            }
        };
        receiptTableModel.addColumn("N0");
        receiptTableModel.addColumn("Name");
        receiptTableModel.addColumn("Served");
        receiptTableModel.addColumn("Quantity");
        receiptTableModel.addColumn("Total price");
    
        table = new JTable(receiptTableModel);
        table.setFont(customFont.getFernandoFont(9));
        table.setForeground(new Color(79, 92, 133));
        table.setBackground(new Color(255, 213, 146));

        JComboBox<String> servedComboBox = new JComboBox<>();
        servedComboBox.setForeground(new Color(79, 92, 133));
        servedComboBox.setBackground(new Color(255, 213, 146));
        servedComboBox.setFont(customFont.getFernandoFont(9));
        servedComboBox.addItem("Hot");
        servedComboBox.addItem("Cold");

        TableCellEditor comboBoxEditor = new DefaultCellEditor(servedComboBox);

        // Set the cell editor for the "Served" column (index 2)
        TableColumn servedColumn = table.getColumnModel().getColumn(2);
        servedColumn.setCellEditor(comboBoxEditor);

        // Add ListSelectionListener to handle row selection
        // Xử lý sự kiện khi người dùng chọn hàng
        productTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy giá trị từ hàng được chọn trong productTable
                    String id = (String) productTable.getValueAt(selectedRow, 0);
                    String name = (String) productTable.getValueAt(selectedRow, 1);
                    Boolean isServed = ((String) productTable.getValueAt(selectedRow, 2)).equals("Hot");
                    Double price = Double.parseDouble((String) productTable.getValueAt(selectedRow, 3)); // Correct column index for price
        
                    MenuItem item = new MenuItem(id, name, isServed, price);
                    int quantity = 1; // Số lượng ban đầu
        
                    // Tạo BillDetail và thêm vào danh sách
                    BillDetail bd = new BillDetail(item, quantity);
                    bdl.addBillDetail(bd);  // Cập nhật số lượng mới nếu sản phẩm đã tồn tại
        
                    Boolean found = false;
                    for (int i = 0; i < table.getRowCount(); i++) {
                        if (id.equals((String) table.getValueAt(i, 0)) && isServed == ((String) table.getValueAt(i, 2)).equals("Hot")) {
                            quantity = bdl.getList().get(i).getQuantity() + 1;
                            table.setValueAt(quantity, i, 3); // Update quantity
                            bdl.getList().get(i).setQuantity(quantity);
                            double newTotalPrice = quantity * price;
                            table.setValueAt(newTotalPrice, i, 4); // Update total price
                            found = true;
                            break;
                        }
                    }
        
                    if (!found) {
                        // Nếu sản phẩm chưa tồn tại, thêm hàng mới vào receiptTableModel
                        Vector<String> rowData = new Vector<>();
                        rowData.add(bd.getItem().getId());
                        rowData.add(bd.getItem().getName());
                        rowData.add(isServed ? "Hot" : "Cold");
                        rowData.add(String.valueOf(bd.getQuantity()));
                        rowData.add(String.valueOf(bd.getTotal_price()));
        
                        // Thêm hàng vào receiptTableModel
                        receiptTableModel.addRow(rowData);
                    }
                }
            }
        });        

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
        scrollPane.setPreferredSize(new Dimension(300, 200));
        scrollPane.getViewport().setBackground(new Color(255, 213, 146));
        scrollPane.setForeground(new Color(79, 92, 133));

        JPanel tablePanel = new JPanel();
        //tablePanel.setBackground(new Color(96, 69, 113));
        tablePanel.setOpaque(false);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setPreferredSize(new Dimension(395, 200));
        tablePanel.add(recieptTableLabel, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel editPanel = new JPanel();
        editPanel.setOpaque(false);
        editPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        editPanel.setPreferredSize(new Dimension(200, 200));

        JLabel totalPriceLabel = new JLabel("Total: $");
        totalPriceLabel.setForeground(new Color(255, 213, 146));
        totalPriceLabel.setFont(customFont.getFernandoFont(9));
        totalPriceLabel.setPreferredSize(new Dimension(120, 25));
        editPanel.add(totalPriceLabel);

        JButton deleteButton = new JButton("Xóa");
        deleteButton.setFont(customFont.getFernandoFont(9));
        deleteButton.setPreferredSize(new Dimension(120, 25));
        deleteButton.setForeground(new Color(79, 92, 133));
        deleteButton.setBackground(new Color(255, 213, 146));

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    receiptTableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete", "Delete Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        editPanel.add(deleteButton);

        JButton resetButton = new JButton("Đặt lại");
        resetButton.setFont(customFont.getFernandoFont(9));
        resetButton.setPreferredSize(new Dimension(120, 25));
        resetButton.setForeground(new Color(79, 92, 133));
        resetButton.setBackground(new Color(255, 213, 146));
        editPanel.add(resetButton);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = receiptTableModel.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    receiptTableModel.removeRow(i);
                }
            }
        });

        JButton orderButton = new JButton("Tạo đơn");
        orderButton.setFont(customFont.getFernandoFont(9));
        orderButton.setPreferredSize(new Dimension(120, 25));
        orderButton.setForeground(new Color(79, 92, 133));
        orderButton.setBackground(new Color(255, 213, 146));

        orderButton.addActionListener(e -> {
            ReadSaveFromFile s = new ReadSaveFromFile();

            try {
                s.SaveFile(bdl.getList(), "dev_cafe/data/bill_details_data.txt");
                created = true;
                System.out.println("Save successfully!");
            } catch (Exception ee) {
                ee.printStackTrace();
            }

            // Khi orderButton được nhấn, gọi sự kiện nếu có listener
            if (orderButtonListener != null) {
                orderButtonListener.onOrderPlaced();
            }
        });
        
        editPanel.add(orderButton);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(400, 200));
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        contentPanel.add(editPanel, BorderLayout.EAST);
    
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        center.setOpaque(false);
        center.setPreferredSize(new Dimension(600, 200));

        JPanel empty1 = new JPanel();
        empty1.setPreferredSize(new Dimension(15, 500));
        empty1.setBackground(new Color(96, 69, 113));
        empty1.setOpaque(false);
        center.add(empty1, BorderLayout.WEST);

        JPanel empty2 = new JPanel();
        empty2.setPreferredSize(new Dimension(180, 500));
        empty2.setOpaque(false);
        center.add(empty2, BorderLayout.EAST);

        center.add(contentPanel, BorderLayout.CENTER);

        JPanel emptyS = new JPanel();
        emptyS.setPreferredSize(new Dimension(800, 15));
        emptyS.setOpaque(false);
        center.add(emptyS, BorderLayout.SOUTH);

        return center;
    }

    public void rightPanel() {
        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(480, 601)); // Thay đổi kích thước cho phù hợp
        right.setBackground(new Color(225, 203, 177));
        right.setLayout(new BorderLayout());
        right.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        add(right);
    }

    public boolean isImportedSuccessfully() {
        return created; 
    }

    public interface OrderButtonListener {
        void onOrderPlaced();
    }

    public void setOrderButtonListener(OrderButtonListener listener) {
        this.orderButtonListener = listener;
    }
}