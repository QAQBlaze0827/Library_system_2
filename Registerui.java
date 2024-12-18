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
        userLabel.setBounds(10, 20, 120, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(140, 20, 200, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 120, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(140, 50, 200, 25);
        panel.add(passwordText);

        JLabel confirmpasswordLabel = new JLabel("Confirm Password:");
        confirmpasswordLabel.setBounds(10, 80, 120, 25);
        panel.add(confirmpasswordLabel);

        JPasswordField confirmpasswordText = new JPasswordField(20);
        confirmpasswordText.setBounds(140, 80, 200, 25);
        panel.add(confirmpasswordText);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(120, 110, 100, 25);
        panel.add(registerButton);

        registerButton.addActionListener(e -> {
            String uname = userText.getText();
            String upassword = new String(passwordText.getPassword());
            String uconfirmpassword = new String(confirmpasswordText.getPassword());
            addUser addUser = new addUser();
            if((uname.equals("")) || (upassword.equals("")) || (uconfirmpassword.equals(""))){
                JOptionPane.showMessageDialog(null, "Please enter user and password");
                return;
            }
            else{
                if(upassword.equals(uconfirmpassword) == true){
                    addUser.addUsertolist(uname, upassword);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Password and Confirm Password are not the same");
                    return;
                }
            }
            
            JOptionPane.showMessageDialog(null, "register success");
            this.dispose();
        });
    }
}
