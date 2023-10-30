package TipsOrTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @Author: QiHongYu
 * @CreateDate: 2023/2/22
 */
public class RepetitionReferences {

    public static void readTxtFile(String filePath) {
        int num = 0;
        File file = new File(filePath);
        String encoding = "utf-8";
        HashSet<String> hashSet = new HashSet<>();
        try (InputStreamReader read = new InputStreamReader(Files.newInputStream(file.toPath()), encoding);
             BufferedReader bufferedReader = new BufferedReader(read)) {
            //判断文件是否存在
            if (file.isFile() && file.exists()) {
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
//                    System.out.println(lineTxt);
                    num++;
                    hashSet.add(lineTxt);
                }
                System.out.println("总共："+num+" 个\n实际："+hashSet.size()+" 个");
                for (String i:hashSet){
                    System.out.println(i);
                }
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
        }
    }

    public static void main(String[] args) {
       readTxtFile("D:\\test\\repeat.txt");
    }
}
