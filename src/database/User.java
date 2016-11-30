package database;

/**
 * Created by jimmyhsu on 2016/11/29.
 */
public class User {
    private String id;
    private String name;
    private String sex;
    private String institute;
    private String branch;
    private String grade;
    private String type;
    private String dormitory;
    private String permission;
    private String tel;
    private String email;
    private String createTime;
    private String password;
    private int credit;
    private String userInClass;

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getUserInClass() {
        return userInClass;
    }

    public void setUserInClass(String userInClass) {
        this.userInClass = userInClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String id, String name, String sex, String institute, String branch
            , String grade, String type, String dormitory, String permission, String tel
            , String email, String createTime, String password, String userInClass, int credit) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.institute = institute;
        this.branch = branch;
        this.grade = grade;
        this.type = type;
        this.dormitory = dormitory;
        this.permission = permission;
        this.tel = tel;
        this.email = email;
        this.createTime = createTime;
        this.password = password;
        this.userInClass = userInClass;
        this.credit = credit;
    }

    public User(){
//        $userDB = new UserDB();
//        $result = $userDB->selectUserById($id);
//        $line = mysql_fetch_array($result);
//        if(!$line){
//            return;
//        }
//        this.id = $line['id'];
//        $this->name = $line['name'];
//        $this->sex = $line['sex'];
//        $this->institute = $line['institude'];
//        $this->branch = $line['branch'];
//        $this->grade = $line['grade'];
//        $this->type = $line['type'];
//        $this->dormitory = $line['dormitory'];
//        $this->permission = $line['permission'];
//        $this->tel = $line['tel'];
//        $this->email = $line['email'];
//        $this->createTime = $line['createTime'];
//        $this->password = $line['password'];
    }
}
