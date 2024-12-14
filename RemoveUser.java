import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveUser{
    public static void main(String[] args){
        String path="user.csv";
        List<User> LoadAllUsers=loadUserFromCsv(path);
        //顯示目前清單
        System.out.println("目前書本清單");
        for(User user:LoadAllUsers){
            System.out.println(user.getUid()+","+user.getUname()+","+user.getUpassword());

        }
        int uidToDelete=2; //要刪除的資料
        boolean uidIsDeleted=deleteUid(LoadAllUsers,uidToDelete);
        if(uidIsDeleted){
            System.out.println("使用者成功刪除!");
            saveUserToCsv(LoadAllUsers,path); //更新文件

        }else{
            System.out.println("未找到使用者");

        }

        

    }
    //從CSV檔載入資料
    public static List<User> loadUserFromCsv(String path){
        List<User> allUsers=new ArrayList<>();
        try (BufferedReader br=new BufferedReader(new FileReader(path))){
            String line;
            boolean isFirstLine=true; //跳過標題行;
            while((line=br.readLine())!=null){
                if(isFirstLine){
                    isFirstLine=false;
                    continue;
                }
                allUsers.add(User.fromCsvRow(line));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return allUsers;
    }

    //刪除使用者
    public static boolean deleteUid(List<User> allUsers,int uid){
        return allUsers.removeIf(user ->user.getUid()==uid);
    }
    //儲存刪除後的資料
    public static void saveUserToCsv(List<User> allUsers,String path){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(path))){
            bw.write("ID,Uname,upassword");
            bw.newLine();
            for(User user:allUsers){
                bw.write(user.toCsvRow());
                bw.newLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}