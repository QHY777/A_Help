package TipsOrTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FilesRename {

	private static String firstPath;  //原目录 路径
	private static String resultPath;  //结果目录 路径

	public static void renameNum(String firstDir,String resultDir,int beginIndex) {
		File file = new File(firstDir);
		File[] list = file.listFiles();
        // 如果目录下文件存在
        if (file.exists() && file.isDirectory()){
            for (int i = 0; i < list.length; i++){
                //取文件名子存入name中
                String name = list[i].getName();
                // 截取.之前的字符串出来
                int index = name.indexOf(".");
//                System.out.println(index);
                String trueName=name.substring(0, index);
                // 截取类似.JPG后缀出来
                String houZhui = name.substring(index);
                // 拼接字符串
                String newName = i+beginIndex + houZhui;
                //重命名
                File dest = new File(resultDir + "/" + newName);
                list[i].renameTo(dest);
                System.out.println(name+"   ==>    "+dest.getName());
            }
        }
	}

	//将多个目录下文件从某个数字开始按序命名到另外一个文件夹
	public static void name3(String[] dirs,int beginIndex) {
		int n=beginIndex;//记录当前数字
		for(int i=0;i<dirs.length;i++) {
			File file = new File(dirs[i]);
			File[] list = file.listFiles();
			renameNum(dirs[i], resultPath, n);
			n+=list.length;
		}

	}

	//将目录下文件从1开始按序命名
	public static void renameFromNum(int beginIndex) {
		renameNum(firstPath, firstPath, beginIndex);
	}

	//将指定目录下文件从某个数字开始按序命名到另外一个文件夹
	public static void renameFromNumToOtherDir(int beginIndex) {
		renameNum(firstPath, resultPath, beginIndex);
	}

	//批量替换同一个文件夹下文件名中某字符串
	public static void replaceName(String firstDir,String target,String replacement) {
		File file = new File(firstDir);
		File[] list = file.listFiles();
        // 如果目录下文件存在
        if (file.exists() && file.isDirectory()){
            for (int i = 0; i < list.length; i++){
                //取文件名子存入name中
                String name = list[i].getName();

                if(name.contains(target)){
					String newName = name.replace(target,replacement);
					//重命名
					File dest = new File(firstDir + "/" + newName);
					list[i].renameTo(dest);
					System.out.println(list[i]+"===>>>"+dest.getName());
				}
            }
        }

	}

	//替换指定文件夹下所有文件
	public static void replaceAll(String dirName,String target,String replacement) {
		File dir = new File(dirName);
		File[] files = dir.listFiles();

		for(int i=0;i<files.length;i++) {
			if(files[i].isFile()) {
				//操作文件
				//判断包含指定字符串则替换
				String oldName=files[i].getName();
				if(oldName.contains(target)){
					String newName = oldName.replace(target,replacement);
					//重命名
					File dest = new File(dirName + "/" + newName);

					System.out.println(oldName+"===>"+dest.getName());
					files[i].renameTo(dest);

				}

			}else if(files[i].isDirectory()) {
				//System.out.println(files[i].getPath());
				replaceAll(files[i].getPath(),target,replacement);
			}
		}
	}

	//删除指定文件夹下为指定内容文件名的所有文件
	public static void deleteAllEqualFile(String dirName, String[] target) {
		File dir = new File(dirName);
		File[] files = dir.listFiles();

		assert files != null;
		for (File file : files) {
			if (file.isFile()) {
				//操作文件
				//判断指定字符串则删除
				//oldName为带后缀全称
				String oldName = file.getName();
//				System.out.println(oldName);
				//遍历关键词
				for (int j = 0; j < target.length; j++) {
//					System.out.println("现在是"+files[i]+"     遍历关键词为："+target[j]);
					if (oldName.equals(target[j])) {
						if (file.delete()) {
							System.out.println("删除文件   " + file + "成功！");
						} else {
							System.out.println("删除文件   " + file + "失败！");
						}

					}
				}
			} else if (file.isDirectory()) {
				//System.out.println(files[i].getPath());
				deleteAllEqualFile(file.getPath(), target);
			}
		}
	}
	//删除指定文件夹下为指定内容文件名的所有文件夹
	public static void deleteAllEqualDir(String dirName, String[] target) {
		File dir = new File(dirName);
		File[] files = dir.listFiles();


		int flag = 0;
		assert files != null;
		for (File file : files) {
			if (file.isDirectory()) {
//				System.out.println("now is dir:  "+file);
				//操作文件
				//判断指定字符串则删除
				//oldName为带后缀全称
				String oldName = file.getName();
//				System.out.println(oldName);
				//遍历关键词
				for (String s : target) {
//					System.out.println("现在是"+files[i]+"     遍历关键词为："+target[j]);
					if (oldName.equals(s)) {
						if (file.delete()) {
							System.out.println("删除文件夹" + file + "成功！");
						} else {
							System.out.println("删除文件夹" + file + "失败！");
						}
						break;
					}else {
						deleteAllEqualDir(file.getPath(),target);
					}
				}
			}
		}
	}
	//删除指定文件夹下包含指定内容文件名的所有文件
	public static void deleteAllContain(String dirName,String[] target) {
		File dir = new File(dirName);
		File[] files = dir.listFiles();

		for(int i=0;i<files.length;i++) {
			if(files[i].isFile() && files[i].exists()) {
				//操作文件
				//判断指定字符串则删除
				//oldName为带后缀全称
				String oldName=files[i].getName();
//				System.out.println(oldName);
				//遍历关键词
				for (String s : target) {
//					System.out.println("现在是"+files[i]+"     遍历关键词为："+target[j]);
					if (oldName.contains(s)) {
						if (files[i].delete()) {
							System.out.println("删除文件" + files[i] + "成功！");
						} else {
							System.out.println("删除文件" + files[i] + "失败！");
						}

					}
				}
			}else if(files[i].isDirectory()) {
				//System.out.println(files[i].getPath());
				deleteAllContain(files[i].getPath(),target);
			}
		}
	}

	public static void main(String[] args)
    {
		Scanner sc=new Scanner(System.in);
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("--------------菜单--------------");
        System.out.println("1.将指定目录下一级文件从某个数字开始按序命名");
        System.out.println("2.将  指定  目录下下一级文件从某个数字开始按序命名到另外一个文件夹");
        System.out.println("3.将  多个  目录下下一级文件从某个数字开始按序命名到另外一个文件夹");
        System.out.println("4.替换指定文件夹下  所有  文件的名称");
		System.out.println("5.替换指定文件夹下  一级  文件的名称");
		System.out.println("6.删除指定文件夹下所有   为 指定名称（可多个）文件和文件夹");
		System.out.println("7.删除指定文件夹下所有 含有 指定名称（可多个）文件");



		System.out.println("other.结束操作");



        int command=0;
        boolean flag=true;
        while(flag) {
        	System.out.println("输入指令：");
        	command=sc.nextInt();
            if (command == 1){
				System.out.println("请输入操作目录的路径!");
				try {
					firstPath=input.readLine();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				System.out.println("输入第一个文件名字开始数字");
				int beginIndex=sc.nextInt();
				renameFromNum(beginIndex);

			} else if (command == 2){
				System.out.println("请输入操作目录的路径!");
				try {
					firstPath=input.readLine();
					System.out.println("请输入目标目录的路径!");
					resultPath=input.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("输入第一个文件名字开始数字");
				int beginIndex2=sc.nextInt();
				renameFromNumToOtherDir(beginIndex2);

			} else if (command == 3) {
				System.out.println("请输入操作目录的个数");
				int num=sc.nextInt();
				String[] dirStrings=new String[num];
				for(int i=0;i<num;i++) {
					System.out.println("请输入第"+(i+1)+"个操作目录的路径!");
					try {
						dirStrings[i]=input.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					System.out.println("请输入目标目录的路径!");
					resultPath=input.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("输入第一个文件名字开始数字");
				int beginIndex3=sc.nextInt();
				name3(dirStrings, beginIndex3);

			} else if (command == 4) {
				System.out.println("请输入操作目录的路径!");
				firstPath=sc.next();
				System.out.println("输入需要替换字符串：");
				String target=sc.next();
//				String target="副本";
				System.out.println("--------------------------------------------\n替换结果如下：\n--------------------------------");
				replaceAll(firstPath,target,"");

			} else if (command == 5) {
				System.out.println("请输入操作目录的路径!");
				firstPath=sc.next();
				System.out.println("输入需要替换字符串：");
//				String targetS=sc.next();
				String targetS="[3D]";
				System.out.println("--------------------------------------------\n替换结果如下：\n--------------------------------");
				replaceName(firstPath,targetS,"");
//				replaceName(firstPath,targetS,"7z");

			} else if (command == 6) {
				System.out.println("请输入操作目录的路径!");
				firstPath=sc.next();
				System.out.println("path:  "+firstPath);
				String[] targetArray={"XunACG-地址发布站.url","XunACG-更多免费资源下载.url","更多免费资源下载.txt",
						"【重要必须看】-更多优质资源.txt","地址发布页（务必收藏）.url",
						"更多优质资源（务必查看）","【重要必须看】-更多优质资源","XunACG更多免费资源"

						,"永久发布页fxcy.vip","重要说明必须看！.txt","获取本站最新地址请看我.txt","点我前往繁星次元.url","fxcy.vip繁星次元.jpg",
						"本站最新地址发布页.txt","繁星次元回家的路.jpg","数千部漫画、动画、游戏、福利姬精品资源等你白嫖！.txt","找回域名的重要说明.txt"
				};
				System.out.println("--------------------------------------------\n删除结果如下：\n--------------------------------");
				deleteAllEqualFile(firstPath,targetArray);
				deleteAllEqualDir(firstPath,targetArray);

			} else if (command == 7) {
				System.out.println("请输入操作目录的路径!");
				firstPath=sc.next();
				String[] targetArray2={"_"};
				System.out.println("--------------------------------------------\n删除结果如下：\n--------------------------------");
				deleteAllContain(firstPath,targetArray2);

			} else {
				flag=false;
				sc.close();
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("操作结束！");
			}
        }




    }

	/*
	 * String类可能使用的方法

	substring(int beginIndex)               截取从beginIndex到末尾的字符串并返回

	substring(int beginIndex, int endIndex) 截取从beginIndex到endIndex的字符串并返回

	concat(String str) 				将指定字符串str连接到此字符串的结尾

	indexOf(int ch)					返回指定字符在此字符串中第一次出现处的索引

	indexOf(String str) 			返回指定子字符串在此字符串中第一次出现处的索引

	lastIndexOf(int ch) 			返回指定字符在此字符串中最后一次出现处的索引

	lastIndexOf(int ch, int fromIndex) 返回指定字符在此字符串中最后一次出现处的索引，从指定的索引处开始进行反向搜索

	replace(CharSequence target, CharSequence replacement)
	          使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串

	replaceAll(String regex, String replacement)
	          使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串

	 */

}
