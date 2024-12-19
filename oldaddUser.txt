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
        List<User> allUsers = loadUserFromFile(path);
        // allUsers.add(new User(username, password));
        int maxUid = allUsers.stream().mapToInt(User::getUid).max().orElse(0);
        User.setNextUid(maxUid + 1); // 更新靜態變數 nextUid

        User newUser = new User(username, password);
        allUsers.add(newUser);

        // 檢查檔案是否已存在
        boolean fileExists = new File(path).exists();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            // 如果檔案不存在或為空，寫入標題
            if (!fileExists || isFileEmpty(path)) {
                bw.write("ID,Name,Password,borrowedBooks");
                bw.newLine();
            }

            // 寫入用戶資料
            for (User user : allUsers) {
                bw.write(user.toCsvRow());
                bw.newLine();
            }

            System.out.println("寫入成功！ 之後記得要把她註解掉");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<User> loadUserFromFile(String path) {
        List<User> allUsers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            // boolean isFirstLine = true; // 跳過標題行
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("ID")) {
                    User user = User.fromCsvRow(line);
                    if (user != null) {
                        allUsers.add(user);
                    }
                }
                // allUsers.add(User.fromCsvRow(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int maxUid = allUsers.stream().mapToInt(User::getUid).max().orElse(0);
        User.setNextUid(maxUid + 1); // 更新靜態變數 nextUid
        return allUsers;
    }
    // 檢查檔案是否為空
    private static boolean isFileEmpty(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine() == null; // 如果第一行為 null，表示檔案為空
        }
    }
}
