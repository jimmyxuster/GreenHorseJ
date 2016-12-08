package database;

/**
 * Created by jimmyhsu on 2016/12/7.
 */
public class Credit {
    private String userId;
    private String name;
    private int credit;

    public Credit(String userId, String name, int credit) {
        this.userId = userId;
        this.credit = credit;
        this.name = name;
    }

    public Credit() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
