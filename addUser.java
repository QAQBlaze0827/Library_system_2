import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class addUser {

    public void addUsertolist(String username, String password) {
        String path = "user.csv";

        // 載入所有使用者
        List<User> allUsers = loadUserFromFile(path);

        // 新增用戶
        User newUser = new User(username, password);
        allUsers.add(newUser);

        // 寫入更新後的用戶列表
        writeUsersToFile(path, allUsers);
        System.out.println("用戶新增成功！");
    }

    private List<User> loadUserFromFile(String path) {
        List<User> allUsers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 跳過標題行
                if (!line.startsWith("ID")) {
                    User user = User.fromCsvRow(line);
                    if (user != null) {
                        allUsers.add(user);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("載入用戶檔案時發生錯誤：" + e.getMessage());
        }

        // 更新 nextUid
        int maxUid = allUsers.stream().mapToInt(User::getUid).max().orElse(0);
        // User.setNextUid(maxUid + 1);

        return allUsers;
    }

    private void writeUsersToFile(String path, List<User> allUsers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {
            // 寫入標題行
            bw.write("ID,Name,Password,borrowedBooks");
            bw.newLine();

            // 寫入用戶資料
            for (User user : allUsers) {
                bw.write(user.toCsvRow());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("寫入用戶檔案時發生錯誤：" + e.getMessage());
        }
    }

    // 檢查檔案是否為空
    private static boolean isFileEmpty(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine() == null; // 如果第一行為 null，表示檔案為空
        }
    }
}
