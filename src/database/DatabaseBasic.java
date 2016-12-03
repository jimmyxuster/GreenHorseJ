package database;


import java.sql.*;

/**
 * Created by jimmyhsu on 2016/11/29.
 * executeSql()返回的ResultSet需要手动调用close()关闭
 */
public class DatabaseBasic {

    private Connection mConnection;
    public DatabaseBasic() {
        mConnection = start();
    }


        private Connection start(){
            String dbname = Constants.MYSQLNAME;
            String host = "localhost";
            String port = "3306";
            String user = Constants.DBUSER;
            String pwd = Constants.DBPASSWORD;
            System.out.println("connecting...");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
                return DriverManager.getConnection(url, user, pwd);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("error when connecting...");
            return null;
        }

        public ResultSet executeSql(String sql){
        if (mConnection == null) {
            return null;
        }
        ResultSet result = null;
        Statement statement = null;
        try {
            statement = mConnection.createStatement();
            System.out.println("sql: " + sql);
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//                if (statement != null) {
//                    try {
//                        statement.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
        }
        return result;
    }

    public boolean executeSqlUpdate(String sql){
        if (mConnection == null) {
            return false;
        }
        boolean result = false;
        Statement statement = null;
        try {
            statement = mConnection.createStatement();
            System.out.println("sql: " + sql);
            result = statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//                if (statement != null) {
//                    try {
//                        statement.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
        }
        return result;
    }

        public void recycle() {
            if (mConnection != null) {
                try {
                    mConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
}
