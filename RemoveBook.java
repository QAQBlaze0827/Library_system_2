import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveBook {
    public static void main(String[] args) {
        String path = "allBook.csv";
        List<Book> loadAllBooks = loadBookFromCsv(path);

        // 顯示目前清單
        System.out.println("目前書本清單：");
        for (Book book : loadAllBooks) {
            System.out.println(book.getBookName() + "," + book.getBookID() + "," + book.getIsBorrowed());
        }

        int bookIdToDelete = 1234; // 要刪除的書本ID
        boolean bookIdIsDeleted = deleteBook(loadAllBooks, bookIdToDelete);

        if (bookIdIsDeleted) {
            System.out.println("書本成功刪除!");
            saveBookToCsv(loadAllBooks, path); // 更新文件
        } else {
            System.out.println("未找到書本！");
        }
    }

    // 從 CSV 檔載入資料
    public static List<Book> loadBookFromCsv(String path) {
        List<Book> allBooks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean isFirstLine = true; // 跳過標題行
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                allBooks.add(Book.fromCsvRow(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allBooks;
    }

    // 刪除書本
    public static boolean deleteBook(List<Book> allBooks, int bookID) {
        return allBooks.removeIf(book -> book.getBookID() == bookID);
    }

    // 儲存刪除後的資料
    public static void saveBookToCsv(List<Book> allBooks, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            // 使用正確的 CSV 標題
            bw.write("BookName,BookId,IsBorrowed");
            bw.newLine();
            for (Book book : allBooks) {
                bw.write(book.toCsvRow());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
