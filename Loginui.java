import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

public class Loginui extends JFrame {
    private final JPanel panel = new JPanel();
    private final JPanel switchPanel = new JPanel();
    private Container cp;
    private JLabel titleLabel;

    public Loginui() {
        init();
    }

    private void init() {
        // 視窗基本設定
        this.setTitle("Login Library System");
        this.setSize(400, 300);
        this.setLocation(700, 250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 處理版面
        cp = this.getContentPane();
        cp.setLayout(null);

        // 上方切換按鈕區
        switchPanel.setBounds(20, 0, 360, 50);
        switchPanel.setLayout(null);
        cp.add(switchPanel);

        JButton userModeButton = new JButton("使用者");
        userModeButton.setBounds(50, 10, 100, 30);
        switchPanel.add(userModeButton);

        JButton adminModeButton = new JButton("管理員");
        adminModeButton.setBounds(200, 10, 100, 30);
        switchPanel.add(adminModeButton);

        // 主面板區域
        panel.setBounds(20, 60, 360, 200);
        panel.setLayout(null);
        cp.add(panel);

        // 添加大標題
        titleLabel = new JLabel("使用者登入", SwingConstants.CENTER);
        titleLabel.setBounds(10, 10, 340, 30);
        titleLabel.setFont(titleLabel.getFont().deriveFont(16.0f));
        panel.add(titleLabel);

        // 添加元件
        JLabel userLabel = new JLabel("User:"); 
        userLabel.setBounds(10, 50, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 50, 200, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 80, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 80, 200, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(80, 120, 80, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(200, 120, 80, 25);
        panel.add(registerButton);

        // Admin 帳號驗證
        Admin admin = new Admin();

        loginButton.addActionListener((ActionEvent e) -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword()); // 將 char[] 轉為 String
            if (admin.getAdminUser().equals(username) && admin.getAdminPassword().equals(password)) {
                this.setVisible(false);
                MainSystemui mainSystemui = new MainSystemui();
                mainSystemui.setVisible(true);
                System.out.println("Login Success");
            } else {
                System.out.println("Login Fail");
                userText.setText("");
                passwordText.setText("");
            }
        });
        registerButton.addActionListener((ActionEvent e) -> {
            Registerui registerui = new Registerui();
            registerui.setVisible(true);
        });
        // 切換模式按鈕事件
        userModeButton.addActionListener((ActionEvent e) -> {
            titleLabel.setText("使用者登入");
            userLabel.setText("User:");
            userText.setText("");
            passwordText.setText("");
            registerButton.setVisible(true); // 使用者模式下顯示註冊按鈕
            System.out.println("Switched to User Login");
        });

        adminModeButton.addActionListener((ActionEvent e) -> {
            titleLabel.setText("管理員登入");
            userLabel.setText("Admin:");
            userText.setText("");
            passwordText.setText("");
            registerButton.setVisible(false); // 管理員模式下隱藏註冊按鈕
            System.out.println("Switched to Admin Login");
        });
    }

    public static void main(String[] args) {
        new Loginui().setVisible(true);
    }
} //有人在搞
