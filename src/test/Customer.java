package test;



/**
 * @Author: QiHongYu
 * @CreateDate: 2023/3/22
 */

public class Customer {
    private int id;
    private String name;
    private String level;
    private long money;

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}
