import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddBook{ //將class改掉
    public void addBooktolist( String bookname , int bookid , boolean isborrowed) { //後須刪除
        String path="allBook.csv";
        List<Book> allBooks=new ArrayList<>();
        allBooks.add(new Book(bookname , bookid , isborrowed));

        // 檢查檔案是否已存在
        boolean fileExists=new File(path).exists();

        try (BufferedWriter bw=new BufferedWriter(new FileWriter(path,true /*true為敘寫*/))){
            if(!fileExists || isFileEmpty(path)){
                //標題
                bw.write("BookName,BookId,IsBorrowed");
                bw.newLine();
            }
           

            //寫資料
            for(Book book:allBooks){
                bw.write(book.toCsvRow());
                bw.newLine();
            }
            System.out.println("寫入成功");
        

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    private static boolean isFileEmpty(String path) throws IOException{
        try(BufferedReader br=new BufferedReader(new FileReader(path))){
            return br.readLine()==null; 
        }
    }
}//abc
//靠