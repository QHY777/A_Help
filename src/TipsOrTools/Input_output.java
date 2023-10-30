package TipsOrTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Input_output {

	public static void main(String[] args) throws IOException {
		//比Scanner快
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line=br.readLine();
		int n = Integer.parseInt(line);
		
		Path path = Paths.get("D:\\javaqhy\\input2.txt");
	    byte[] data;
		data = Files.readAllBytes(path);
		String str = new String(data , "utf-8");  //读取字符串
		String[] str1=str.split("[\\t \\r\\n]+");     //按空格、换行拆分
		
		
		//写入文件
		 File file=new File("D:\\javaqhy\\output2.txt");
		 FileOutputStream out = new FileOutputStream(file);
		 Object min = null;
		 //write后参数为字符串
		 out.write((min + "\r\n").getBytes());
		 out.close();
		 System.out.println("写入完成!");
	}
}
