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

        // CSV 路徑
       
        String path = "user.csv";

        // 登入邏輯
        loginButton.addActionListener((ActionEvent e) -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            // 從 CSV 載入所有使用者
            List<User> allUsers = loadUserFromCsv(path);

            // 查找匹配的使用者
            User authenticatedUser = allUsers.stream()
                .filter(user -> user.getUname().equals(username) && user.getUpassword().equals(password))
                .findFirst()
                .orElse(null);

            if (authenticatedUser != null) {
                System.out.println("UID: " + authenticatedUser.getUid()); // 打印正確的 UID

                // 根據 UID 判斷進入哪個界面
                if (authenticatedUser.getUid() == 0) {
                    MainSystemuiAdmin mainSystemuiAdmin = new MainSystemuiAdmin();
                    mainSystemuiAdmin.setVisible(true);
                } else {
                    MainSystemuiUser mainSystemuiUser = new MainSystemuiUser();
                    mainSystemuiUser.setVisible(true);
                }

                this.setVisible(false); // 隱藏登入界面
            } else {
                // 登入失敗，清空相關資料
                JOptionPane.showMessageDialog(
                    this,
                    "Login failed. Please check your username and password.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
                );
                System.out.println("Login failed. No matching user found.");
                userText.setText("");       // 清空輸入框
                passwordText.setText("");
            }
        });
        registerButton.addActionListener((ActionEvent e) -> {
            Registerui registerui = new Registerui();
            registerui.setVisible(true);
            // this.setVisible(false);
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
                try {
                    User user = User.fromCsvRow(line);
                    if (user != null) {
                        allUsers.add(user);
                    }
                } catch (Exception e) {
                    // 如果某行解析失敗，顯示錯誤訊息並跳過
                    System.err.println("Failed to parse line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public static void main(String[] args) {
        new Loginui().setVisible(true);
    }
}
