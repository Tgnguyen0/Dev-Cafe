package app.GUI;

import app.AppFunctions.CafeFunction;
import app.AppFunctions.MemberManagement;
import app.InitFont.CustomFont;
import app.Object.BillDetail;
import app.Object.Member;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class ReceiptPage extends JPanel {
    private CustomFont customFont = new CustomFont();
    private DefaultTableModel receiptTableModel;
    private DefaultTableModel productTableModel;
    private BillDetailsManagement bdl = new BillDetailsManagement();
    private MemberManagement memberList = new MemberManagement();
    private JTable productTable;
    private JTable table;
    private JLabel nameText;
    private JLabel idText;
    private JLabel dbText;
    private JLabel phoneText;
    private JLabel pointGained;
    private JLabel discountAmount;
    private JTextField phoneInput;
    private JLabel discount;
    private int points;

    public ReceiptPage() {
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
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(1100, 250));
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

        idText = new JLabel("");
        idText.setPreferredSize(new Dimension(140, 25));
        idText.setForeground(new Color(255, 213, 146));
        idText.setFont(customFont.getFernandoFont(13));
        left.add(idText);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(customFont.getFernandoFont(13));
        nameLabel.setForeground(new Color(255, 213, 146));
        nameLabel.setPreferredSize(new Dimension(120, 25));
        left.add(nameLabel);

        nameText = new JLabel("");
        nameText.setPreferredSize(new Dimension(140, 25));
        nameText.setForeground(new Color(255, 213, 146));
        nameText.setFont(customFont.getFernandoFont(13));
        left.add(nameText);

        JLabel dbLabel = new JLabel("Birth Day: ");
        dbLabel.setFont(customFont.getFernandoFont(13));
        dbLabel.setForeground(new Color(255, 213, 146));
        dbLabel.setPreferredSize(new Dimension(120, 25));
        left.add(dbLabel);

        dbText = new JLabel("");
        dbText.setPreferredSize(new Dimension(140, 25));
        dbText.setForeground(new Color(255, 213, 146));
        dbText.setFont(customFont.getFernandoFont(13));
        left.add(dbText);

        tbPanel.add(left);

        JPanel right = new JPanel();
        right.setLayout(new FlowLayout(FlowLayout.LEFT));
        right.setOpaque(false);
        right.setPreferredSize(new Dimension(330, 300));

        JLabel phoneLabel = new JLabel("Phone: ");
        phoneLabel.setFont(customFont.getFernandoFont(13));
        phoneLabel.setForeground(new Color(255, 213, 146));
        phoneLabel.setPreferredSize(new Dimension(120, 25));
        right.add(phoneLabel);

        phoneText = new JLabel("");
        phoneText.setPreferredSize(new Dimension(140, 25));
        phoneText.setForeground(new Color(255, 213, 146));
        phoneText.setFont(customFont.getFernandoFont(13));
        right.add(phoneText);
        
        JLabel pointLabel = new JLabel("Points: ");
        pointLabel.setFont(customFont.getFernandoFont(13));
        pointLabel.setForeground(new Color(255, 213, 146));
        pointLabel.setPreferredSize(new Dimension(120, 25));
        right.add(pointLabel);

        pointGained = new JLabel("0 pts");
        pointGained.setFont(customFont.getFernandoFont(13));
        pointGained.setForeground(new Color(255, 213, 146));
        pointGained.setPreferredSize(new Dimension(140, 25));
        right.add(pointGained);

        JLabel discountLabel = new JLabel("Discount: ");
        discountLabel.setFont(customFont.getFernandoFont(13));
        discountLabel.setForeground(new Color(255, 213, 146));
        discountLabel.setPreferredSize(new Dimension(120, 25));
        right.add(discountLabel);

        discountAmount = new JLabel("");
        discountAmount.setFont(customFont.getFernandoFont(13));
        discountAmount.setForeground(new Color(255, 213, 146));
        discountAmount.setPreferredSize(new Dimension(140, 25));
        right.add(discountAmount);

        tbPanel.add(right);

        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new BorderLayout());
        imgPanel.setPreferredSize(new Dimension(250, 300));
        //Border lineBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255, 213, 146));
        //imgPanel.setBorder(lineBorder);
        imgPanel.setOpaque(false);

        String imagePath = "dev_cafe/asset/user.png";
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
        panel.add(tbPanel, BorderLayout.CENTER);

        return panel;
    }

    public JPanel createProductPanel() {
        JPanel ptPanel = new JPanel();
        ptPanel.setOpaque(false);
        ptPanel.setPreferredSize(new Dimension(800, 350));
        ptPanel.setLayout(new BorderLayout());

        JPanel esPanel = new JPanel();
        esPanel.setOpaque(false);
        esPanel.setLayout(new BorderLayout());
        esPanel.setPreferredSize(new Dimension(800, 300));

        JPanel editPanel = new JPanel();
        editPanel.setOpaque(false);
        editPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        editPanel.setPreferredSize(new Dimension(700, 50));

        JLabel findLabel = new JLabel("Tìm kiếm khách hàng theo sdt: ");
        findLabel.setFont(customFont.getFernandoFont(10));
        findLabel.setForeground(new Color(255, 213, 146));
        findLabel.setPreferredSize(new Dimension(195, 25));
        editPanel.add(findLabel);

        phoneInput = new JTextField();
        phoneInput.setPreferredSize(new Dimension(140, 25));
        phoneInput.setBackground(new Color(255, 213, 146));
        phoneInput.setBorder(BorderFactory.createLineBorder(new Color(79, 92, 133)));
        phoneInput.setForeground(new Color(79, 92, 133));
        phoneInput.setFont(customFont.getFernandoFont(10));
        editPanel.add(phoneInput);

        JButton findButton = new JButton("Tìm Khách");
        findButton.setFont(customFont.getFernandoFont(9));
        findButton.setPreferredSize(new Dimension(120, 25));
        findButton.setForeground(new Color(79, 92, 133));
        findButton.setBackground(new Color(255, 213, 146));

        ReadSaveFromFile s = new ReadSaveFromFile();

        try {
            Object o = s.ReadFile("dev_cafe/data/customers_data.txt");
            
            if (o instanceof ArrayList<?>) {
                @SuppressWarnings("unchecked")
                ArrayList<Member> listOfCust= (ArrayList<Member>) o;
                memberList.addAllMember(listOfCust);
            }

            System.out.println("Import successfully!");
        } catch (Exception ee) {
            ee.printStackTrace();
        }

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneStr = phoneInput.getText();

                if (phoneStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a phone number.");
                    return;
                }

                Member m = memberList.searchMember(phoneStr);

                if (m != null) {
                    idText.setText(m.getId());
                    nameText.setText(m.getName());
                    dbText.setText(m.getDob().toString());
                    phoneText.setText(m.getPhone());
                    pointGained.setText(m.getPoints() + " pts");
        
                    points = m.getPoints();
        
                    if (points >= 1000) {
                        discountAmount.setText("10%");
                    } else if (points >= 500) {
                        discountAmount.setText("5%");
                    } else {
                        discountAmount.setText("No discount");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No member found with the given phone number.");
                }
            }
        });

        editPanel.add(findButton);
        esPanel.add(editPanel, BorderLayout.NORTH);

        productTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make "Points" column (index 0,1,2) uneditable
                return column != 0 && column != 1 && column != 2 && column != 3;
            }
        };
        productTableModel.addColumn("N0");
        productTableModel.addColumn("Name");
        productTableModel.addColumn("Served");
        productTableModel.addColumn("Quantity");
        productTableModel.addColumn("Price");

        try {
            Object o = s.ReadFile("dev_cafe/data/bill_details_data.txt");
            
            if (o instanceof ArrayList<?>) {
                @SuppressWarnings("unchecked")
                ArrayList<BillDetail> bill= (ArrayList<BillDetail>) o;
                bdl.addListBillDetail(bill);

                for (BillDetail bd : bill) {
                    Vector<String> rowData = new Vector<>();
                    rowData.add(bd.getItem().getId());
                    rowData.add(bd.getItem().getName());
                    rowData.add(bd.getItem().getServeHot() ? "Cold" : "Hot");
                    rowData.add(String.valueOf(bd.getQuantity()));
                    rowData.add(String.valueOf(bd.getTotal_price()));
                    productTableModel.addRow(rowData);
                }
            }

            s.SaveFile("", "dev_cafe/data/bill_details_data.txt");

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
        esPanel.add(scrollPane, BorderLayout.CENTER);

        ptPanel.add(esPanel, BorderLayout.CENTER);

        JPanel emptyW = new JPanel();
        emptyW.setPreferredSize(new Dimension(15, 100));
        emptyW.setOpaque(false);
        ptPanel.add(emptyW, BorderLayout.WEST);

        JPanel emptyE = new JPanel();
        emptyE.setPreferredSize(new Dimension(15, 100));
        emptyE.setOpaque(false);
        ptPanel.add(emptyE, BorderLayout.EAST);

        JPanel emptyS = new JPanel();
        emptyS.setOpaque(false);
        emptyS.setPreferredSize(new Dimension(400, 20));
        ptPanel.add(emptyS, BorderLayout.SOUTH);

        return ptPanel;
    }
}