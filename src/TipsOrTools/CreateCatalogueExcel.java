package TipsOrTools;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CreateCatalogueExcel {

    //fList是1 10 2的顺序，改为按照1,2,3...10
    private static void sortFileArrayByNum(File[] fList){
        assert fList != null;
        List fileList = Arrays.asList(fList);
        Collections.sort(fileList, (Comparator<File>) (o1, o2) -> {
            if (o1.isDirectory() && o2.isFile()) {
                return -1;
            }
            if (o1.isFile() && o2.isDirectory()) {
                return 1;
            }
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
    private static void createExcelOnlyDir(String path,String sign) throws IOException, WriteException {
        //创建Excel文件
        File file = new File(path+File.separator+"目录.xls");
        //创建文件
        file.createNewFile();
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        //创建sheet
        WritableSheet sheet = workbook.createSheet("sheet1", 0);
        //添加数据
        String[] title = {"名称", "标识"};
        Label label = null;
        for (int i = 0; i < title.length; i++) {
            label = new Label(i, 0, title[i]);
            sheet.addCell(label);
        }
        ArrayList<String> dirs=getDir(path);
        //追加数据
        for (int i = 0; i < dirs.size(); i++) {
            label = new Label(0, i+1, dirs.get(i));
            sheet.addCell(label);
//            label = new Label(1, i+1, sign);
//            sheet.addCell(label);
        }
        workbook.write();
        System.out.println("success！！！");
        workbook.close();
    }
    public static ArrayList<String> getDir(String strPath) {
        ArrayList<String> result=new ArrayList<String>();
        File f = new File(strPath);
        if (f.isDirectory()) {
            File[] fList = f.listFiles();
            sortFileArrayByNum(fList);
            for (int j = 0; j < fList.length; j++) {
                if (fList[j].isDirectory()) {
                    result.add(fList[j].getName());
                }
//                System.out.println(fList[j].getName());
            }
        }
        return result;
    }
    //将矩阵写入excel
    public static void createExcelByMatrix(String strPath, String[][] matrix) throws IOException, WriteException {

        //创建Excel文件
        File excel = new File(strPath+File.separator+"目录.xls");
        //创建文件
        excel.createNewFile();
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(excel);
        //创建sheet
        WritableSheet sheet = workbook.createSheet("sheet1", 0);
        //添加数据
        String[] title = {"文件夹名称", "文件名"};
        Label label = null;
        for (int i = 0; i < title.length; i++) {
            label = new Label(i, 0, title[i]);
            sheet.addCell(label);
        }

        //追加数据
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length;j++){
                label = new Label(j, i+1, matrix[i][j] );
                sheet.addCell(label);
            }
        }
        workbook.write();
        workbook.close();

    }
    //输入绝对路径，统计此文件夹下两级文件文件名,存在x*y矩阵里
    public static String[][] getDirFile(String path,int x,int y){
        String[][] result = new String[x][y];
        int m = 0 ;
        File f = new File(path);
        if (f.isDirectory()) {
            File[] fList = f.listFiles();
            sortFileArrayByNum(fList);
            for (int j = 0; j < Objects.requireNonNull(fList).length; j++) {
                //子目录
                if (fList[j].isDirectory()){
                    result[m][0] = fList[j].getName();
                    File[] fList2 = fList[j].listFiles();
                    sortFileArrayByNum(fList2);
                    assert fList2 != null;
                    for (File ff : fList2){
                        result[m][1] = ff.getName();
                        m++;
                    }
                    //分割线
                    result[m][1] = "----------------------------------------------------------------------------------";

                }else {
                    result[m][0] = fList[j].getName();
                }
                m++;
            }
        }
        return result;
    }
    public static void method2(String path, int x, int y) throws WriteException, IOException {
        createExcelByMatrix(path,getDirFile(path,x,y));
        System.out.println("生成目录！！！Successfully！！！");
    }
    public static void main(String[] args) throws WriteException, IOException {
        Scanner scanner = new Scanner(System.in);
//        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("--------------菜单--------------");

        System.out.println("1.指定路径下一级文件夹目录");
        System.out.println("2.指定路径下两级文件夹和文件目录");
        System.out.println("other.结束");


        while(true){
            System.out.println("请输入指令：");
            int command = scanner.nextInt();
            if (command == 1 ){
                scanner.nextLine();
                System.out.println("请输入目录：");
                String path = scanner.nextLine();
                System.out.println("目录："+path);
                System.out.println("请输入标识：");
//            scanner.nextLine();
                String sign = scanner.next();
                try {
                    createExcelOnlyDir(path,sign);
                } catch (IOException | WriteException e) {
                    e.printStackTrace();
                }
            } else if (command==2) {
                System.out.println("请输入目录：");
                scanner.nextLine();
                String path = scanner.nextLine();
                method2(path,140,2);
            } else {
                break;
            }
        }


    }
}
