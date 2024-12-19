import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MainSystemuiUser extends JFrame {
    private final String username;
    private Container cp;
    private CardLayout cardLayout;
    private final JPanel userPanel= new JPanel();
    private final JPanel functionPanel = new JPanel();
    private JPanel contentPanel;


    public MainSystemuiUser(String username) {
        this.username = username;
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
        
        JLabel label = new JLabel("Welcome " + username);
        label.setBounds(0, 0, 200, 30); 
        userPanel.setLayout(null);
        userPanel.add(label);

        //-----------------功能 panel
        //這邊應該可以在優化一下
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
        //功能panel 登出
        JButton logoutButton = new JButton("Log out");
        logoutButton.setBackground(Color.red);
        logoutButton.setBounds(0, 600, 200, 70); 
        logoutButton.setAlignmentX(LEFT_ALIGNMENT);
        functionPanel.add(logoutButton);
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
        //借閱書籍的panel
        JPanel borrowBookPanel = new JPanel();
        borrowBookPanel.setBounds(200, 0, 800, 750);
        borrowBookPanel.setBackground(Color.white);
        cp.add(borrowBookPanel);
        borrowBookPanel.setLayout(null);
        borrowBookPanel.setVisible(false); // 一開始不顯示
        contentPanel.add(borrowBookPanel, "BorrowBook");
        //借閱書籍的panel中的元件
        JLabel borrowTitle = new JLabel("Borrow Book System");
        borrowTitle.setBounds(10, 10, 200, 25);
        borrowTitle.setAlignmentX(CENTER_ALIGNMENT);
        borrowBookPanel.add(borrowTitle);
        JLabel borrowBookID = new JLabel("Book ID");
        borrowBookID.setBounds(10, 50, 80, 25);
        borrowBookPanel.add(borrowBookID);
        JTextField borrowBookIDText = new JTextField(20);
        borrowBookIDText.setBounds(100, 50, 200, 25);
        borrowBookPanel.add(borrowBookIDText);
        JButton borrowBook = new JButton("Borrow Book");
        borrowBook.setBounds(10, 80, 200, 25);
        borrowBook.setAlignmentX(LEFT_ALIGNMENT);
        borrowBookPanel.add(borrowBook);
        //借閱書籍的panel中的元件 end
        //------------------
        //還書的panel
        JPanel returnBookPanel = new JPanel();
        returnBookPanel.setBounds(200, 0, 800, 750);
        returnBookPanel.setBackground(Color.white);
        cp.add(returnBookPanel);
        returnBookPanel.setLayout(null);
        returnBookPanel.setVisible(false); // 一開始不顯示
        contentPanel.add(returnBookPanel, "ReturnBook");
        //還書的panel中的元件
        JLabel returnTitle = new JLabel("Return Book System");
        returnTitle.setBounds(10, 10, 200, 25);
        returnTitle.setAlignmentX(CENTER_ALIGNMENT);
        returnBookPanel.add(returnTitle);
        JLabel returnBookID = new JLabel("Book ID");
        returnBookID.setBounds(10, 50, 80, 25);
        returnBookPanel.add(returnBookID);
        JTextField returnBookIDText = new JTextField(20);
        returnBookIDText.setBounds(100, 50, 200, 25);
        returnBookPanel.add(returnBookIDText);
        JButton returnBook = new JButton("Return Book");
        returnBook.setBounds(10, 80, 200, 25);
        returnBook.setAlignmentX(LEFT_ALIGNMENT);
        returnBookPanel.add(returnBook);
        // 創建表格模型
        // 创建表格数据模型
        String[] columnNames = {"Book ID", "Book Name", "Is Borrowed"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        // 创建表格并添加到滚动面板
        JTable bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        returnBookPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBounds(10, 120, 780, 500);
        // 加载书籍数据并填充表格
        //這邊我設想是 只顯示使用者借的書 畢竟是要還書 不可能還自己沒借的書
        try {
            String path = "allBook.csv";
            List<Book> allBooks = loadBooksFromFile(path);
            for (Book book : allBooks) {
                tableModel.addRow(new Object[]{
                    book.getBookID(),
                    book.getBookName(),
                    book.getIsBorrowed() ? "Yes" : "No"
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 添加底部交互部分
        // JPanel bottomPanel = new JPanel(new FlowLayout());
        // returnBookPanel.add(bottomPanel, BorderLayout.SOUTH);

        // JLabel returnBookID = new JLabel("Book ID:");
        // JTextField returnBookIDText = new JTextField(10);
        // JButton returnBookButton = new JButton("Return Book");

        // bottomPanel.add(returnBookID);
        // bottomPanel.add(returnBookIDText);
        // bottomPanel.add(returnBookButton);
        //還書的panel中的元件 end
        //------------------右邊的panel end

        
        //監控 控制右邊panel 的按鈕
        searchBookButton.addActionListener((e) -> {cardLayout.show(contentPanel, "SearchBook");});
        borrowBookButton.addActionListener((e) -> {cardLayout.show(contentPanel, "BorrowBook");});
        returnBookButton.addActionListener((e) -> {cardLayout.show(contentPanel, "ReturnBook");});
        logoutButton.addActionListener((e) -> {
            // 顯示確認對話框
            int result = JOptionPane.showConfirmDialog(
                null, 
                "Are you sure you want to log out?", 
                "Logout Confirmation", 
                JOptionPane.YES_NO_OPTION
            );
        
            // 檢查使用者的選擇
            if (result == JOptionPane.YES_OPTION) {
                // 如果使用者按下 "Yes"，執行登出操作
                JOptionPane.showMessageDialog(null, "You have logged out successfully.");
                 // 創建並顯示 LoginUI 界面
                new Loginui().setVisible(true);
                // 關閉當前視窗
                ((JFrame) SwingUtilities.getWindowAncestor(logoutButton)).dispose();
            } else {
                // // 如果使用者按下 "No"，不執行任何操作
                // System.out.println("Logout cancelled.");
            }
        });
        //監控 控制右邊panel 的按鈕 end
        //監控新增的按鈕
        searchBook.addActionListener((e) ->{
            try {
                //底下是測試用的
                searchBookIDText.setText("");
                String path = "allBook.csv";
                
                List<Book> loadAllBooks = loadBooksFromFile(path);
                for (Book book : loadAllBooks) {
                    System.out.println(book.getBookName() + "," + book.getBookID() + "," + book.getIsBorrowed());
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Book ID. Please enter a numeric value.");
            }
        });
        borrowBook.addActionListener((e) ->{
            try {
                borrowBookIDText.setText("");
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Book ID. Please enter a numeric value.");
            }
        });
        //底下這不知道幹嘛的
        this.setResizable(true); // 確保視窗大小可以調整
    }
    private List<Book> loadBooksFromFile(String path) {
        List<Book> allBooks = new ArrayList<>();
        File file = new File(path);
    
        if (!file.exists()) {
            return allBooks; // 如果檔案不存在，返回空列表
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 跳過標題行和無效行
                if (!line.startsWith("BookName")) {
                    Book book = Book.fromCsvRow(line);
                    if (book != null) {
                        allBooks.add(book);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return allBooks;
    }
    public static void main(String[] args) {

    }
}
