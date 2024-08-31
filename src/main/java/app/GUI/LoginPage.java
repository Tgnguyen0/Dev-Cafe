package app.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import app.InitFont.CustomFont;

public class LoginPage extends JFrame {
    private CustomFont customFont = new CustomFont();
    private JTextField nameField;
    private JTextField password;

    public LoginPage() {
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

        createLoginPanel();
    }

    public void createLoginPanel() {
        JPanel center = new JPanel();
        center.setBackground(new Color(51, 62, 116));
        center.setPreferredSize(new Dimension(750, 500));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel programLabel = new JLabel("Dev Cafe", SwingConstants.CENTER);
        programLabel.setFont(customFont.getFernandoFont(30));
        programLabel.setForeground(new Color(255, 213, 146));
        programLabel.setPreferredSize(new Dimension(750, 50));
        center.add(programLabel);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(customFont.getFernandoFont(15));
        nameLabel.setForeground(new Color(255, 213, 146));
        nameLabel.setPreferredSize(new Dimension(750, 50));
        center.add(nameLabel);

        nameField = new JTextField();
        nameField.setForeground(new Color(79, 92, 133));
        nameField.setBackground(new Color(255, 213, 146));
        nameField.setBorder(null);
        nameField.setFont(customFont.getFernandoFont(15));
        nameField.setPreferredSize(new Dimension(750, 50));
        center.add(nameField);

        JLabel passLabel = new JLabel("Password: ");
        passLabel.setFont(customFont.getFernandoFont(15));
        passLabel.setForeground(new Color(255, 213, 146));
        passLabel.setPreferredSize(new Dimension(750, 50));
        center.add(passLabel);

        password = new JTextField();
        password.setForeground(new Color(79, 92, 133));
        password.setBackground(new Color(255, 213, 146));
        password.setBorder(null);
        password.setFont(customFont.getFernandoFont(15));
        password.setPreferredSize(new Dimension(750, 50));
        center.add(password);

        DevCafeGUI panel = new DevCafeGUI();

        add(center, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
