import java.awt.Container;
import javax.swing.*;
public class Registerui extends JFrame {
    private final JPanel panel = new JPanel();
    private Container cp;
    public Registerui(){
        init();
    }
    private void init(){
        this.setTitle("Register Library System");
        this.setSize(400, 200);
        this.setLocation(1000, 250);
        // this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 處理版面
        cp = this.getContentPane();
        cp.setLayout(null);
        panel.setBounds(20, 20, 400, 400);
        panel.setLayout(null);
        cp.add(panel);
        //改變使用者登入
        JLabel userLabel = new JLabel("User:"); 
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 200, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 200, 25);
        panel.add(passwordText);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(150, 80, 100, 25);
        panel.add(registerButton);

        registerButton.addActionListener(e -> {
            String uname = userText.getText();
            String upassword = new String(passwordText.getPassword());
            addUser addUser = new addUser();
            addUser.addUsertolist(uname, upassword);
            JOptionPane.showMessageDialog(null, "註冊成功");
        });
    }
}
