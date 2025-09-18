package app.GUI;

import app.InitFont.CustomFont;
import app.Listener.ActionListener_CafeLayoutPage;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class CafeLayoutPage extends JFrame {
    private CustomFont customFont = new CustomFont();
    public ActionListener_CafeLayoutPage action;
    public JPanel buildingPanel;
    public JLabel logoNameLabel;
    public JButton groundFloorButton;
    public JButton firstFloorButton;
    public JButton secondFloorButton;

    public CafeLayoutPage() {
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.white);
        setLocationRelativeTo(null);
        setResizable(false);

        action = new ActionListener_CafeLayoutPage(this);

        add(initFloorOption());
        add(initBuildingPanel());
    }

    private JPanel initFloorOption() {
        JPanel optionBar = new JPanel();
        optionBar.setLayout(new BoxLayout(optionBar, BoxLayout.Y_AXIS));
        optionBar.setBackground(Color.white);

        FontIcon batteryIcon = FontIcon.of(Feather.BATTERY_CHARGING, 50, Color.WHITE);
        logoNameLabel = new JLabel("Dev Café", batteryIcon, SwingConstants.CENTER); // Tạo tên logo
        logoNameLabel.setPreferredSize(new Dimension(200, 50));
        logoNameLabel.setIconTextGap(10);
        logoNameLabel.setForeground(Color.WHITE);
        logoNameLabel.setBackground(new Color(164, 56, 32));
        logoNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        logoNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoNameLabel.setFont(customFont.getRobotoFonts().get(0).deriveFont(Font.PLAIN, 20));
        logoNameLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        optionBar.add(logoNameLabel);

        secondFloorButton = new JButton("Tầng hai");
        secondFloorButton.setFont(customFont.getRobotoFonts().get(0).deriveFont(Font.PLAIN, 12));
        secondFloorButton.addActionListener(action);
        optionBar.add(secondFloorButton);

        firstFloorButton = new JButton("Tầng một");
        firstFloorButton.setFont(customFont.getRobotoFonts().get(0).deriveFont(Font.PLAIN, 12));
        firstFloorButton.addActionListener(action);
        optionBar.add(firstFloorButton);

        groundFloorButton = new JButton("Tầng trệt");
        groundFloorButton.setFont(customFont.getRobotoFonts().get(0).deriveFont(Font.PLAIN, 12));
        groundFloorButton.addActionListener(action);
        optionBar.add(groundFloorButton);

        return optionBar;
    }

    private JPanel initBuildingPanel() {
        buildingPanel = new JPanel();
        buildingPanel.setBackground(Color.white);
        buildingPanel.setLayout(new CardLayout());

        buildingPanel.add(initGroundFloor(), "Ground Floor");
        buildingPanel.add(initFirstFloor(), "First Floor");
        buildingPanel.add(initSecondFloor(), "Second Floor");
        return buildingPanel;
    }

    private JPanel initGroundFloor() {
        JPanel groundFloor = new JPanel();
        groundFloor.setBackground(Color.white);

        JLabel label = new JLabel("Tầng trệt");
        groundFloor.add(label);

        return groundFloor;
    }

    private JPanel initFirstFloor() {
        JPanel firstFloor = new JPanel();
        firstFloor.setBackground(Color.white);

        JLabel label = new JLabel("Tầng một");
        firstFloor.add(label);

        return firstFloor;
    }

    private JPanel initSecondFloor() {
        JPanel secondFloor = new JPanel();
        secondFloor.setBackground(Color.white);

        JLabel label = new JLabel("Tầng hai");
        secondFloor.add(label);

        return secondFloor;
    }
}
