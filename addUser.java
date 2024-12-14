import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class addUser {
    public void addUsertolist(String username, String password) {
        String path = "user.csv";

        // 新增用戶資料
        List<User> allUsers = new ArrayList<>();
        allUsers.add(new User(2, username, password));

        // 檢查檔案是否已存在
        boolean fileExists = new File(path).exists();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            // 如果檔案不存在或為空，寫入標題
            if (!fileExists || isFileEmpty(path)) {
                bw.write("ID,Name,Password");
                bw.newLine();
            }

            // 寫入用戶資料
            for (User user : allUsers) {
                bw.write(user.toCsvRow());
                bw.newLine();
            }

            System.out.println("寫入成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 檢查檔案是否為空
    private static boolean isFileEmpty(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine() == null; // 如果第一行為 null，表示檔案為空
        }
    }
}
