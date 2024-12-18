import java.awt.*;
import javax.swing.*;


public class MainSystemuiUser extends JFrame {
    private Container cp;
    private CardLayout cardLayout;
    private final JPanel userPanel= new JPanel();
    private final JPanel functionPanel = new JPanel();
    private JPanel contentPanel;


    public MainSystemuiUser() {
        init();
    }

    private void init() {
        //初始化視窗
        this.setTitle("Library System");
        this.setSize(1000, 750); // 設定初始大小
        this.setLocation(500, 200); // 設定視窗初始位置
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp = this.getContentPane(); 
        cp.setLayout(null); 

        //使用者 panel
        //用來做歡迎小介面的
        userPanel.setBounds(0, 0, 200, 50);
        userPanel.setBackground(new Color(161,196,253)); 
        cp.add(userPanel); 
        // User user = new User();
        
        JLabel label = new JLabel("Welcome " );
        label.setBounds(0, 0, 200, 30); 
        userPanel.setLayout(null);
        userPanel.add(label);

        //-----------------功能 panel
        //這邊應該可以在優化

        functionPanel.setBounds(0, 0, 200, 700);
        functionPanel.setBackground(Color.lightGray);
        functionPanel.setLayout(null);
        cp.add(functionPanel); 
        //功能panel 中的查詢書籍
        JButton searchBookButton = new JButton("Search Book");
        searchBookButton.setBounds(0, 50, 200, 50); 
        searchBookButton.setAlignmentX(LEFT_ALIGNMENT);
        functionPanel.add(searchBookButton);
        //功能panel 中的借閱書籍(僅讀者可使用)
        JButton borrowBookButton = new JButton("Borrow Book"); 
        borrowBookButton.setBounds(0, 100, 200, 50); 
        borrowBookButton.setAlignmentX(LEFT_ALIGNMENT); 
        functionPanel.add(borrowBookButton); 
        //功能panel 中的還書(僅讀者可使用)
        JButton returnBookButton = new JButton("Return Book");
        returnBookButton.setBounds(0, 150, 200, 50);
        returnBookButton.setAlignmentX(LEFT_ALIGNMENT);
        functionPanel.add(returnBookButton); 
        //功能panel 中的讀者借書紀錄(僅讀者可使用)
        JButton borrowRecordButton = new JButton("Borrow Record"); 
        borrowRecordButton.setBounds(0, 200, 200, 50);
        borrowRecordButton.setAlignmentX(LEFT_ALIGNMENT); 
        functionPanel.add(borrowRecordButton);
        //-----------------功能 panel end

        //------------------右邊的panel
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBounds(200, 0, 800, 750);
        cp.add(contentPanel);
        //------------------
        //查詢書籍的panel
        JPanel searchBookPanel = new JPanel();
        searchBookPanel.setBounds(200, 0, 800, 750);
        searchBookPanel.setBackground(Color.white);
        cp.add(searchBookPanel);
        searchBookPanel.setLayout(null);
        searchBookPanel.setVisible(true); // 一開始不顯示
        contentPanel.add(searchBookPanel, "SearchBook");
        //查詢書籍的panel中的元件
        JLabel searchTitle = new JLabel("Search Book System");
        searchTitle.setBounds(10, 10, 200, 25);
        searchTitle.setAlignmentX(CENTER_ALIGNMENT);
        searchBookPanel.add(searchTitle);
        JLabel searchBookID = new JLabel("Book ID");
        searchBookID.setBounds(10, 50, 80, 25);
        searchBookPanel.add(searchBookID);
        JTextField searchBookIDText = new JTextField(20);
        searchBookIDText.setBounds(100, 50, 200, 25);
        searchBookPanel.add(searchBookIDText);
        JButton searchBook = new JButton("Search Book");
        searchBook.setBounds(10, 80, 200, 25);
        searchBook.setAlignmentX(LEFT_ALIGNMENT);
        searchBookPanel.add(searchBook);
        //查詢書籍的panel中的元件 end
        //------------------

        //------------------右邊的panel end

        
        //監控 控制右邊panel 的按鈕
        searchBookButton.addActionListener((e) -> {cardLayout.show(contentPanel, "SearchBook");});
        //監控 控制右邊panel 的按鈕 end
        //監控新增的按鈕
        searchBook.addActionListener((e) ->{
            try {
                // int book_ID = Integer.parseInt(searchBookIDText.getText());
        
                // 呼叫 Library 的 deleteBook 方法
                // library.displayBooks();
        
                // 清空輸入框
                searchBookIDText.setText("");
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Book ID. Please enter a numeric value.");
            }
        });
        //底下這不知道幹嘛的
        this.setResizable(true); // 確保視窗大小可以調整
    }
    public static void main(String[] args) {
        new MainSystemuiUser().setVisible(true);
    }
}
