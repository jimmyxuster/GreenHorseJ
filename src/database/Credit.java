package database;

/**
 * Created by jimmyhsu on 2016/12/7.
 */
public class Credit {
    private String userId;
    private int credit;

    public Credit(String userId, int credit) {
        this.userId = userId;
        this.credit = credit;
    }

    public Credit() {
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
