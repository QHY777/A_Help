package test;


import java.io.File;
import java.util.*;

/**
 * @Author: QiHongYu
 * @CreateDate: 2022/10/2
 * @Version: 1.0
 */
public class Test {


	/*
	 * public static void main(String[] args) { Scanner scanner = new
	 * Scanner(System.in); double a = scanner.nextDouble(); System.out.println(a%1);
	 * System.out.println(1.9%1);
	 *
	 *
	 * scanner.close(); }
	 */
	/*
	 * public static void main(String[] args) { float a = 34.12f; float b = 34f;
	 * float c = 0.12f; System.out.println(a - b); System.out.println(a-c);
	 * System.out.println(c); }
	 */
	private static void sortFileArrayByNum(File[] fList){
		assert fList != null;
		List fileList = Arrays.asList(fList);
		Collections.sort(fileList, (Comparator<File>) (o1, o2) -> {
			if (o1.isDirectory() && o2.isFile())
				return -1;
			if (o1.isFile() && o2.isDirectory())
				return 1;
			String t1 = o1.getName().split("[\\s[.][《][【]+]")[0];
			String t2 = o2.getName().split("[\\s[.][《][【]+]")[0];
			if(t1.length() > t2.length()){
				return 1;
			}else if(t1.length() < t2.length()){
				return -1;
			}
			return t1.compareTo(t2);
		});
	}
	public static void main(String[] args) {
		String path = "G:\\歌曲";
		int x = 150,y = 1;
		String[][] result = new String[x][y];
		int m = 0 ;
		File f = new File(path);
		if (f.isDirectory()) {
			File[] fList = f.listFiles();
//			for (int i = 0;i < fList.length;i++){
//				System.out.println(fList[i]);
//			}

			sortFileArrayByNum(fList);
			System.out.println(fList);
//			for (int j = 0; j < Objects.requireNonNull(fList).length; j++) {
//				//子目录
//				if (fList[j].isDirectory()){
//					result[m][0] = fList[j].getName();
//					File[] fList2 = fList[j].listFiles();
//					assert fList2 != null;
//					for (File ff : fList2){
//						result[m][1] = ff.getName();
//						m++;
//					}
//					//分割线
//					result[m][1] = "----------------------------------------------------------------------------------";
//
//				}else {
//					result[m][0] = fList[j].getName();
//				}
//				m++;
//			}
		}
//		for (int i = 0; i < result.length; i++) {
//			for (int j = 0; j < result[i].length;j++){
//				System.out.print(result[i][j]+"           ");
//			}
//			System.out.println();
//		}
	}

}
