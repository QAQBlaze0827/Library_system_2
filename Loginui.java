import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;
public class Loginui extends JFrame {
    private final JPanel panel = new JPanel();
    private Container cp;

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

        // 主面板區域
        panel.setBounds(20, 20, 400, 200);
        panel.setLayout(null);
        // panel.setBackground(new java.awt.Color(161, 196, 253));
        cp.add(panel);
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
        loginButton.setBounds(60, 120, 80, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(200, 120, 80, 25);
        panel.add(registerButton);

        // Admin 帳號驗證
        Admin admin = new Admin();
        String path="user.csv";
        //顯示目前清單
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
    }
    public static void main(String[] args) {
        new Loginui().setVisible(true);
    }
} //有人在搞
