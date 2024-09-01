package app.GUI;

import app.InitFont.CustomFont;
import app.SaveToFile.ReadSaveFromFile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

// @important
// old Color: 161, 103, 37
// old OnClick Color: 196, 125, 44

public class DevCafeGUI extends JFrame implements MouseListener, ActionListener {
    private JLabel timeLabel;
    private JPanel right;
    private JPanel pageContainer;
    private HomePage homePage; 
    private SellPage sellPage;
    private ReceiptPage receiptPage; 
    private ProductPage productPage; 
    private PromotionPage promotionPage; 
    private StatisticPage statisticPage; 
    private EmployeePage employeePage; 
    private JButton homeButton;
    private JButton sellButton;
    private JButton receiptButton;
    private JButton productButton;
    private JButton promotionRateButton;
    private JButton statisticsButton;
    private JButton employeeButton;
    private CustomFont customFont = new CustomFont();
    private ReadSaveFromFile s = new ReadSaveFromFile();

    //Function tạo GUI chính
    public DevCafeGUI() {
        ImageIcon icon = new ImageIcon("dev_cafe/asset/icon.png"); // For vscode
        // ImageIcon icon = new ImageIcon("asset/icon.png"); // for eclipse, Intelj
        setTitle("Dev Cafe");
        setSize(new Dimension(750, 500));

        setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setBackground(new Color(96, 69, 113));
        setResizable(false);
        getContentPane().setBackground(new Color(225, 203, 177));
        setLayout(new BorderLayout());

        createGUIUserRelatedBar();
        createGUIOptionMenu(); 
        updateTime(); // Cập nhật tg
        startTimer(); // Khởi động bộ đếm thời gian để cập nhật liên tục
    }

    public void createGUIUserRelatedBar() {
        this.right = new JPanel();
        this.right.setPreferredSize(new Dimension(660, 400));
        this.right.setLayout(new BorderLayout());

        JPanel infoBar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                /*super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(79, 92, 133), 0, h, new Color(104, 101, 133));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);*/

                String imagePath = "dev_cafe/asset/infoBar-background.png"; // Path to your GIF image file
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
        };
        infoBar.setPreferredSize(new Dimension(670, 35));
        //infoBar.setBackground(new Color(51, 62, 116));
        infoBar.setLayout(new BorderLayout());
        //banner.setBorder(new LineBorder(Color.red, 3));

        JPanel userRelatedBar = new JPanel();
        userRelatedBar.setOpaque(false);
        userRelatedBar.setPreferredSize(new Dimension(385, 35));
        //userRelatedBar.setBackground(new Color(51, 62, 116));

        JButton changePassButton = new JButton("Đổi mật khẩu");
        changePassButton.setPreferredSize(new Dimension(110, 25));
        changePassButton.setFont(customFont.getFernandoFont(9));
        changePassButton.setForeground(new Color(255, 213, 146));
        changePassButton.setBackground(new Color(51, 62, 116));
        userRelatedBar.add(changePassButton);

        JButton signOutButton = new JButton("Đăng xuất");
        signOutButton.setPreferredSize(new Dimension(110, 25));
        signOutButton.setFont(customFont.getFernandoFont(9));
        signOutButton.setForeground(new Color(255, 213, 146));
        signOutButton.setBackground(new Color(51, 62, 116));
        userRelatedBar.add(signOutButton);

        JLabel accountNameLabel = new JLabel("Nguyễn Nhật Tấn - Dev");
        accountNameLabel.setPreferredSize(new Dimension(140, 25));
        accountNameLabel.setFont(customFont.getFernandoFont(9));
        accountNameLabel.setForeground(new Color(255, 213, 146));
        userRelatedBar.add(accountNameLabel);
        infoBar.add(Box.createHorizontalStrut(170));

        // Tạo thanh ngày giờ
        timeLabel = new JLabel();
        timeLabel.setPreferredSize(new Dimension(140, 25));
        timeLabel.setFont(customFont.getFernandoFont(9));
        timeLabel.setForeground(new Color(255, 213, 146));
        infoBar.add(timeLabel, BorderLayout.EAST);
        infoBar.add(userRelatedBar, BorderLayout.WEST);

        // Khởi tạo trang chứa
        this.pageContainer = new JPanel();
        this.pageContainer.setPreferredSize(new Dimension(200, 200));
        //this.pageContainer.setBackground(new Color(225, 203, 177));
        this.pageContainer.setLayout(new CardLayout());

        this.homePage = new HomePage(); // Khởi tạo trang Trang chủ
        this.sellPage = new SellPage(); // Khởi tạo trang Bán hàng
        this.receiptPage = new ReceiptPage(); // Khởi tạo trang Hóa đơn
        this.productPage = new ProductPage(); // Khởi tạo trang Sản phẩm
        this.promotionPage = new PromotionPage(); // Khởi tạo trang Giảm giá
        this.statisticPage = new StatisticPage(); // Khởi tạo trang Thống kê
        this.employeePage = new EmployeePage(); // Khởi tạo trang Nhân viên

        sellPage.setOrderButtonListener(() -> {
            // Khi nút orderButton được nhấn:
            receiptButton.setEnabled(true); // Kích hoạt receiptButton

            // Kiểm tra xem ReceiptPage đã được thêm vào chưa
            if (receiptPage == null) {
                receiptPage = new ReceiptPage(); // Tạo trang ReceiptPage mới
                pageContainer.add(receiptPage, "Receipt Page"); // Thêm vào pageContainer
            }

            try {
                Object o = s.ReadFile("dev_cafe/data/bill_details_data.txt");
                
                if (o instanceof ArrayList<?>) {   
                    receiptPage = new ReceiptPage();
                    pageContainer.add(receiptPage, "Receipt Page");
                }

                System.out.println("Import successfully!");
            } catch (Exception ee) {
                ee.printStackTrace();
            }

            // Chuyển đến trang Hóa đơn ngay lập tức nếu cần
            CardLayout cardLayout = (CardLayout) pageContainer.getLayout();
            cardLayout.show(pageContainer, "Receipt Page");
        });

        pageContainer.add(homePage, "Home Page");
        pageContainer.add(sellPage, "Sell Page");
        pageContainer.add(receiptPage, "Receipt Page");
        pageContainer.add(productPage, "Product Page");
        pageContainer.add(promotionPage, "Promotion Page");
        pageContainer.add(statisticPage, "Statistic Page");
        pageContainer.add(employeePage, "Employee Page");

        this.right.add(pageContainer);
        this.right.add(infoBar, BorderLayout.NORTH);
        add(this.right, BorderLayout.CENTER);
    }

    //Tạo down menu GUI cho dev cafe
    public void createGUIOptionMenu() {
        JPanel optionBar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                String imagePath = "dev_cafe/asset/menu-banner.png"; // Path to your GIF image file
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
        };
        optionBar.setPreferredSize(new Dimension(200, 500));
        //optionBar.setBackground(new Color(51, 62, 116));
        Border lineBorder = BorderFactory.createMatteBorder(0, 0, 0, 5, new Color(44, 31, 93)); // Tạo MatteBorder với màu cụ thể
        optionBar.setBorder(lineBorder);

        JPanel empty = new JPanel();
        empty.setPreferredSize(new Dimension(110, 30));
        empty.setOpaque(false);
        optionBar.add(empty);

        String imagePath = "dev_cafe/asset/dev_cafe_icon.gif"; // for vs code
        // String imagePath = "asset/dev_cafe.png"; // for eclipse, intelj
        //File imageFile = new File(imagePath);

        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Tạo imageLabel cho ảnh
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setPreferredSize(new Dimension(110, 77));

        optionBar.add(imageLabel, BorderLayout.WEST);

        //Chèn ảnh vào Option menu
        /*try {
            // Đọc ảnh từ file
            Image image = ImageIO.read(imageFile);

            // Tạo icon cho ảnh
            ImageIcon imageIcon = new ImageIcon(imagePath);

            // Tạo imageLabel cho ảnh
            JLabel imageLabel = new JLabel(imageIcon);

            // Thêm imageLabel vào optionMenu
            optionBar.add(imageLabel, BorderLayout.WEST);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // Tạo tên logo
        JLabel logoNameLabel = new JLabel("Dev Café");
        logoNameLabel.setPreferredSize(new Dimension(110, 50));
        logoNameLabel.setForeground(new Color(255, 213, 146));
        logoNameLabel.setFont(customFont.getFernandoFont(9).deriveFont(Font.PLAIN, 18));
        optionBar.add(logoNameLabel);

        lineBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(158, 188, 208));

        // Tạo Nút dẫn đến trang chủ
        homeButton = new JButton("Trang Chủ");
        homeButton.setPreferredSize(new Dimension(195, 60));
        homeButton.setFont(customFont.getFernandoFont(9));
        homeButton.setForeground(new Color(255, 213, 146));
        homeButton.setBackground(new Color(51, 62, 116));
        //homeButton.setBorder(lineBorder);
        homeButton.addMouseListener(this);
        homeButton.addActionListener(this);
        optionBar.add(homeButton);

        // Tạo Nút đến trang bán hàng
        sellButton = new JButton("Bán Hàng");
        sellButton.setPreferredSize(new Dimension(195, 60));
        sellButton.setFont(customFont.getFernandoFont(9));
        sellButton.setForeground(new Color(255, 213, 146));
        sellButton.setBackground(new Color(51, 62, 116));
        //sellButton.setBorder(lineBorder);
        sellButton.addMouseListener(this);
        sellButton.addActionListener(this);
        optionBar.add(sellButton);

        // Tạo Nút đến trang hóa đơn
        receiptButton = new JButton("Hóa Đơn");
        receiptButton.setPreferredSize(new Dimension(195, 60));
        receiptButton.setFont(customFont.getFernandoFont(9));
        receiptButton.setForeground(new Color(255, 213, 146));
        receiptButton.setBackground(new Color(51, 62, 116));
        //receiptButton.setBorder(lineBorder);
        receiptButton.setEnabled(false);
        receiptButton.addMouseListener(this);

        /*if (this.sellPage.isImportedSuccessfully()) {
            receiptButton.setEnabled(true);

            try {
                Object o = s.ReadFile("dev_cafe/data/bill_details_data.txt");
                
                if (o instanceof ArrayList<?>) {   
                    receiptPage = new ReceiptPage();
                    pageContainer.add(receiptPage, "Receipt Page");
                }

                System.out.println("Import successfully!");
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }*/

        optionBar.add(receiptButton);

        // Tạo Nút đến trang sản phẩm
        productButton = new JButton("Sản Phẩm");
        productButton.setPreferredSize(new Dimension(195, 60));
        productButton.setFont(customFont.getFernandoFont(9));
        productButton.setForeground(new Color(255, 213, 146));
        productButton.setBackground(new Color(51, 62, 116));
        //productButton.setBorder(lineBorder);
        productButton.addMouseListener(this);
        productButton.addActionListener(this);
        optionBar.add(productButton);

        // Tạo Nút đến trang giảm giá
        promotionRateButton = new JButton("Khuyến Mại");
        promotionRateButton.setPreferredSize(new Dimension(195, 60));
        promotionRateButton.setFont(customFont.getFernandoFont(9));
        promotionRateButton.setForeground(new Color(255, 213, 146));
        promotionRateButton.setBackground(new Color(51, 62, 116));
        //promotionRateButton.setBorder(lineBorder);
        promotionRateButton.addMouseListener(this);
        promotionRateButton.addActionListener(this);
        optionBar.add(promotionRateButton);

        // Tạo Nút đến trang thống kê
        statisticsButton = new JButton("Thống Kê");
        statisticsButton.setPreferredSize(new Dimension(195, 60));
        statisticsButton.setFont(customFont.getFernandoFont(9));
        statisticsButton.setForeground(new Color(255, 213, 146));
        statisticsButton.setBackground(new Color(51, 62, 116));
        //statisticsButton.setBorder(lineBorder);
        statisticsButton.addMouseListener(this);
        statisticsButton.addActionListener(this);
        optionBar.add(statisticsButton);

        // Tạo Nút đến trang nhân viên
        employeeButton = new JButton("Nhân Viên");
        employeeButton.setPreferredSize(new Dimension(195, 60));
        employeeButton.setFont(customFont.getFernandoFont(9));
        employeeButton.setForeground(new Color(255, 213, 146));
        employeeButton.setBackground(new Color(51, 62, 116));
        //employeeButton.setBorder(lineBorder);
        employeeButton.addMouseListener(this);
        employeeButton.addActionListener(this);
        optionBar.add(employeeButton);
        
        add(optionBar, BorderLayout.WEST);
    }

    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String currentTime = sdf.format(new Date());
        timeLabel.setText(currentTime);
    }

    private void startTimer() {
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton enteredButton = (JButton) e.getComponent();
        enteredButton.setBackground(new Color(79, 92, 133)); // Thay đổi màu khi hover
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton exitedButton = (JButton) e.getComponent();
        exitedButton.setBackground(new Color(51, 62, 116)); // Khôi phục màu ban đầu khi di chuột ra khỏi nút
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout) pageContainer.getLayout();
        String command = e.getActionCommand();
        
        switch (command) {
            case "Trang Chủ":
                cardLayout.show(pageContainer, "Home Page");
                break;
            case "Bán Hàng":
                cardLayout.show(pageContainer, "Sell Page");
                break;
            case "Hóa Đơn":
                cardLayout.show(pageContainer, "Receipt Page");
                break;
            case "Sản Phẩm":
                cardLayout.show(pageContainer, "Product Page");
                break;
            case "Khuyến Mại":
                cardLayout.show(pageContainer, "Promotion Page");
                break;
            case "Thống Kê":
                cardLayout.show(pageContainer, "Statistic Page");
                break;
            case "Nhân Viên":
                cardLayout.show(pageContainer, "Employee Page");
                break;
        }
    }
}