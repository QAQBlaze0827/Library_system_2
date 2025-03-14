import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MainSystemuiAdmin extends JFrame {
    private Container cp;
    private CardLayout cardLayout;
    private final JPanel userPanel= new JPanel();
    private final JPanel functionPanel = new JPanel();
    private JPanel contentPanel;


    public MainSystemuiAdmin() {
        init();
    }

    private void init() {
        //初始化視窗
        this.setTitle("Library System");
        this.setSize(1015, 750); // 設定初始大小
        this.setLocation(500, 200); // 設定視窗初始位置
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp = this.getContentPane(); 
        cp.setLayout(null); 

        //使用者 panel
        //用來做歡迎小介面的
        userPanel.setBounds(0, 0, 200, 50);
        userPanel.setBackground(new Color(161,196,253)); 
        cp.add(userPanel); 
        Admin admin = new Admin();
        JLabel label = new JLabel("Welcome " + admin.getAdminName());
        label.setBounds(0, 0, 200, 30); 
        userPanel.setLayout(null);
        userPanel.add(label);

        //-----------------功能 panel
        //這邊應該可以在優化

        functionPanel.setBounds(0, 50, 200, 700);
        functionPanel.setBackground(Color.lightGray);
        functionPanel.setLayout(null);
        cp.add(functionPanel); 

        //功能panel 中的新增書籍(僅管理員可使用)
        JButton addBookButton = new JButton("Add Book"); 
        addBookButton.setBounds(0, 0, 200, 50);
        addBookButton.setAlignmentX(LEFT_ALIGNMENT); 
        functionPanel.add(addBookButton); 
        //功能panel 中的刪除書籍(僅管理員可使用)
        JButton deleteBookButton = new JButton("Delete Book");
        deleteBookButton.setBounds(0, 50, 200, 50);
        deleteBookButton.setAlignmentX(LEFT_ALIGNMENT);
        functionPanel.add(deleteBookButton); 
        //功能panel 中的查詢書籍
        JButton searchBookButton = new JButton("Search Book");
        searchBookButton.setBounds(0, 100, 200, 50); 
        searchBookButton.setAlignmentX(LEFT_ALIGNMENT);
        functionPanel.add(searchBookButton);
        //功能panel 中的登出
        JButton logoutButton = new JButton("Log out");
        logoutButton.setBackground(Color.red);
        logoutButton.setBounds(0, 600, 200, 70); 
        logoutButton.setAlignmentX(LEFT_ALIGNMENT);
        functionPanel.add(logoutButton);
        // //功能panel 中的借閱書籍(僅讀者可使用)
        // JButton borrowBookButton = new JButton("Borrow Book"); 
        // borrowBookButton.setBounds(0, 150, 200, 50); 
        // borrowBookButton.setAlignmentX(LEFT_ALIGNMENT); 
        // functionPanel.add(borrowBookButton); 
        // //功能panel 中的還書(僅讀者可使用)
        // JButton returnBookButton = new JButton("Return Book");
        // returnBookButton.setBounds(0, 200, 200, 50);
        // returnBookButton.setAlignmentX(LEFT_ALIGNMENT);
        // functionPanel.add(returnBookButton); 
        // //功能panel 中的讀者借書紀錄(僅讀者可使用)
        // JButton borrowRecordButton = new JButton("Borrow Record"); 
        // borrowRecordButton.setBounds(0, 250, 200, 50);
        // borrowRecordButton.setAlignmentX(LEFT_ALIGNMENT); 
        // functionPanel.add(borrowRecordButton);
        //-----------------功能 panel end

        //------------------右邊的panel
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBounds(200, 0, 800, 750);
        cp.add(contentPanel);

        //新增書籍的panel
        JPanel addBookPanel = new JPanel();
        addBookPanel.setBounds(200, 0, 800, 750);
        addBookPanel.setBackground(Color.white);
        cp.add(addBookPanel);
        addBookPanel.setLayout(null);
        addBookPanel.setVisible(true); 
        contentPanel.add(addBookPanel, "AddBook");
            //書籍的panel中的元件
        JLabel title = new JLabel("Add Book System");
        title.setBounds(10, 10, 200, 25);
        title.setAlignmentX(CENTER_ALIGNMENT);
        addBookPanel.add(title);
        JLabel bookNameLabel = new JLabel("Book Name:");
        bookNameLabel.setBounds(10, 50, 80, 25);
        addBookPanel.add(bookNameLabel);
        JTextField bookNameText = new JTextField(20); 
        bookNameText.setBounds(100, 50, 200, 25); 
        addBookPanel.add(bookNameText); 

        JLabel bookID = new JLabel("Book ID"); 
        bookID.setBounds(10, 80, 80, 25); 
        addBookPanel.add(bookID);
        JTextField bookIDText = new JTextField(20);
        bookIDText.setBounds(100, 80, 200, 25); 
        addBookPanel.add(bookIDText);

        JButton addBook = new JButton("Add Book"); 
        addBook.setBounds(10, 110, 200, 25); 
        addBook.setAlignmentX(LEFT_ALIGNMENT); 
        addBookPanel.add(addBook); 
        //書籍的panel中的元件 end
        
        //刪除書籍的paenl
        JPanel deleteBookPanel = new JPanel();
        deleteBookPanel.setBounds(200, 0, 800, 750);
        deleteBookPanel.setBackground(Color.white);
        cp.add(deleteBookPanel);
        deleteBookPanel.setLayout(null);
        deleteBookPanel.setVisible(true); // 一開始不顯示
        contentPanel.add(deleteBookPanel, "DeleteBook");
        //刪除書籍的panel中的元件
        JLabel deleteTitle = new JLabel("Delete Book System");
        deleteTitle.setBounds(10, 10, 200, 25);
        deleteTitle.setAlignmentX(CENTER_ALIGNMENT);
        deleteBookPanel.add(deleteTitle);
        JLabel deleteBookID = new JLabel("Book ID");
        deleteBookID.setBounds(10, 50, 80, 25);
        deleteBookPanel.add(deleteBookID);
        JTextField deleteBookIDText = new JTextField(20);
        deleteBookIDText.setBounds(100, 50, 200, 25);
        deleteBookPanel.add(deleteBookIDText);
        JButton deleteBook = new JButton("Delete Book");
        deleteBook.setBounds(10, 80, 200, 25);
        deleteBook.setAlignmentX(LEFT_ALIGNMENT);
        deleteBookPanel.add(deleteBook);
        String[] columnNames = {"Book ID", "Book Name", "Is Borrowed"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        // 创建表格并添加到滚动面板
        JTable bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        deleteBookPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBounds(10, 120, 780, 500);
        // 加载书籍数据并填充表格

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
        //刪除書籍的panel中的元件 end
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
        JLabel searchBookID = new JLabel("Book Name");
        searchBookID.setBounds(10, 50, 80, 25);
        searchBookPanel.add(searchBookID);
        JTextField searchBookIDText = new JTextField(20);
        searchBookIDText.setBounds(100, 50, 200, 25);
        searchBookPanel.add(searchBookIDText);
        JButton searchBook = new JButton("Search Book");
        searchBook.setBounds(10, 80, 200, 25);
        searchBook.setAlignmentX(LEFT_ALIGNMENT);
        searchBookPanel.add(searchBook);
        // String[] columnNames = {"Book ID", "Book Name", "Is Borrowed"};
        DefaultTableModel tableModel_search = new DefaultTableModel(columnNames, 0);
        // 创建表格并添加到滚动面板
        JTable bookTable_search = new JTable(tableModel_search);
        JScrollPane scrollPane_search = new JScrollPane(bookTable_search);
        searchBookPanel.add(scrollPane_search, BorderLayout.CENTER);
        scrollPane_search.setBounds(10, 120, 780, 500);
        // 加载书籍数据并填充表格
        //這邊我設想是 只顯示未被借走的書
        try {
            String path = "allBook.csv";
            List<Book> allBooks = loadBooksFromFile(path);
            for (Book book : allBooks) {
                if(searchBookIDText.getText().equals(book.getBookID())){
                    tableModel_search.addRow(new Object[]{
                        book.getBookID(),
                        book.getBookName(),
                        book.getIsBorrowed() ? "Yes" : "No"
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading books: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        //查詢書籍的panel中的元件 end

        //------------------

        //------------------右邊的panel end

        
        //監控 控制右邊panel 的按鈕
        addBookButton.addActionListener((e) -> cardLayout.show(contentPanel, "AddBook"));
        deleteBookButton.addActionListener((e) -> {cardLayout.show(contentPanel, "DeleteBook");});
        searchBookButton.addActionListener((e) -> {cardLayout.show(contentPanel, "SearchBook");});
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
        addBook.addActionListener((e) -> {
            try {
                String bookName = bookNameText.getText();
                int book_ID = Integer.parseInt(bookIDText.getText());
                //這邊我覺得怪怪的
                AddBook addBookInstance = new AddBook();
                addBookInstance.addBookToList(bookName, book_ID, false);
                //到這邊
                //底下我也覺得怪怪的
                // Book newBook = new Book(bookName, book_ID, false);
                // Library.addBook(newBook);
                //到這邊
                System.out.println("Book added: Name = " + bookName + ", ID = " + book_ID);
                refreshTable(tableModel, "allBook.csv");
        
                // 清空輸入框
                bookNameText.setText("");
                bookIDText.setText("");
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Book ID. Please enter a numeric value.");
            }
        });
        //監控刪除的按鈕
        deleteBook.addActionListener((e) -> {
            try {
                int book_ID = Integer.parseInt(deleteBookIDText.getText());
                RemoveBook removeBook = new RemoveBook();
                removeBook.removefromlist(book_ID); // 假設此方法已成功刪除書籍
        
                // 清空輸入框
                deleteBookIDText.setText("");
        
                // 立即更新表格
                refreshTable(tableModel, "allBook.csv");
        
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Book ID. Please enter a numeric value.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        searchBook.addActionListener((e) -> {
            String keyword = searchBookIDText.getText().trim(); // 使用者輸入的書名
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a book name or part of it.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        
            try {
                String path = "allBook.csv";
                List<Book> allBooks = loadBooksFromFile(path); // 加載所有書籍
                List<Book> filteredBooks = searchBooksByName(keyword, allBooks); // 搜索相關書籍
        
                tableModel_search.setRowCount(0); // 清空表格內容
                for (Book book : filteredBooks) {
                    tableModel_search.addRow(new Object[]{
                        book.getBookID(),
                        book.getBookName(),
                        book.getIsBorrowed() ? "Yes" : "No"
                    });
                }
        
                if (filteredBooks.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No books found matching your search criteria.", "No Results", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error occurred while searching: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        //底下這不知道幹嘛的
        this.setResizable(false); // 確保視窗大小可以調整
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
    private List<Book> searchBooksByName(String keyword, List<Book> allBooks) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getBookName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }
    //刷新表格
    private void refreshTable(DefaultTableModel tableModel, String filePath) {
        tableModel.setRowCount(0); // 清空表格
        try {
            List<Book> allBooks = loadBooksFromFile(filePath); // 重新加載數據
            for (Book book : allBooks) {
                tableModel.addRow(new Object[]{
                    book.getBookID(),
                    book.getBookName(),
                    book.getIsBorrowed() ? "Yes" : "No"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading books: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        new MainSystemuiAdmin().setVisible(true);
    }
}
