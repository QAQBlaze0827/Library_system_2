import java.awt.Container;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
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
        String path = "user.csv";
        
        // 登入邏輯
        loginButton.addActionListener((ActionEvent e) -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            List<User> allUsers = loadUserFromCsv(path);
            boolean isAuthenticated = false;

            for (User user : allUsers) {
                if (user.getUname().equals(username) && user.getUpassword().equals(password)) {
                    isAuthenticated = true;
                    if (user.getUid() == 0) {
                        System.out.println("這是管理員");
                        MainSystemuiAdmin mainSystemuiAdmin = new MainSystemuiAdmin();
                        mainSystemuiAdmin.setVisible(true);
                        this.setVisible(false);
                        break;
                    } else {
                        System.out.println("這是一班使用者");
                        MainSystemuiUser mainSystemuiUser = new MainSystemuiUser();
                        mainSystemuiUser.setVisible(true);
                        this.setVisible(false);
                        break;
                    }
                    
                }
            }

            if (!isAuthenticated) {
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
    // 從 CSV 文件載入使用者
    private List<User> loadUserFromCsv(String path) {
        List<User> allUsers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean isFirstLine = true; // 跳過標題行
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                allUsers.add(User.fromCsvRow(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allUsers;
    }
    public static void main(String[] args) {
        new Loginui().setVisible(true);
    }
} //有人在搞
