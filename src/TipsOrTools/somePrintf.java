package TipsOrTools;

import java.util.Date;

public class somePrintf {
    public static void main(String[] args) {
        System.out.printf("%s\n","输出字符串");
        //输出字符串
        System.out.printf("%s:%d\n","输出十进制数字",10);
        //输出十进制数字:10
        System.out.printf("%s:%b\n","输出布尔类型",true);
        //输出布尔类型:true
        System.out.printf("%s:%f\n","输出浮点类型数字",99.99);
        //输出浮点类型数字:99.990000
        Date date = new Date();
        //c的使用
        System.out.printf("全部日期和时间信息:%tc%n",date);
        //全部日期和时间信息:星期二 三月 09 03:20:34 CST 2021
        //f的用法
        System.out.printf("年-月-日格式:%tF%n",date);
        //年-月-日格式:2021-03-09
        //d的用法
        System.out.printf("月/日/年格式:%tD%n",date);
        //月/日/年格式:03/09/21
        //r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);
        //HH:MM:SS PM格式（12时制）：03:20:34 上午
        //t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);
        //HH:MM:SS格式（24时制）：03:20:34
        //R的使用
        System.out.printf("HH:MM格式（24时制）：%tR",date);
        //HH:MM格式（24时制）：03:20

        //7位小数
        int c=99;
        System.out.printf("%.7f", c);
    }
}
