import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddBook {

    public void addBookToList(String bookName, int bookId, boolean isBorrowed) {
        String path = "allBook.csv";

        // 加載現有書籍資料
        List<Book> allBooks = Book.loadBooksFromFile(path);

        // 新增書籍
        Book newBook = new Book(bookName, bookId, isBorrowed);
        allBooks.add(newBook);

        // 檢查檔案是否已存在
        boolean fileExists = new File(path).exists();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            // 如果檔案不存在或為空，寫入標題
            if (!fileExists || isFileEmpty(path)) {
                bw.write("BookName,BookId,IsBorrowed,BorrowedByUid");
                bw.newLine();
            }

            // 寫入書籍資料
            for (Book book : allBooks) {
                bw.write(book.toCsvRow());
                bw.newLine();
            }

            System.out.println("書籍資料寫入成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // // 從文件中加載書籍資料
    // private List<Book> loadBooksFromFile(String path) {
    //     List<Book> allBooks = new ArrayList<>();
    //     File file = new File(path);
    
    //     if (!file.exists()) {
    //         return allBooks; // 如果檔案不存在，返回空列表
    //     }
    
    //     try (BufferedReader br = new BufferedReader(new FileReader(file))) {
    //         String line;
    //         while ((line = br.readLine()) != null) {
    //             // 跳過標題行和無效行
    //             if (!line.startsWith("BookName")) {
    //                 Book book = Book.fromCsvRow(line);
    //                 if (book != null) {
    //                     allBooks.add(book);
    //                 }
    //             }
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    
    //     return allBooks;
    // }
    
    // 檢查檔案是否為空
    private static boolean isFileEmpty(String path) throws IOException {
        File file = new File(path);
        if (!file.exists() || file.length() == 0) {
            return true; // 檔案不存在或大小為 0 視為空檔案
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.readLine() == null; // 如果第一行為 null，表示檔案為空
        }
    }
}

