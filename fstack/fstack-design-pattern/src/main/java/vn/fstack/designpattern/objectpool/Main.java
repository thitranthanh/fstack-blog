package vn.fstack.designpattern.objectpool;

import java.sql.Connection;

public class Main {
    public static void main(String args[]) {

        JDBCConnectionPool pool = new JDBCConnectionPool(
                "com.mysql.cj.jdbc.Driver", 
                "jdbc:mysql://localhost:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root", "");

        Connection connect1 = pool.getObject(); // create a new one
        Connection connect2 = pool.getObject(); // create a new one
        Connection connect3 = pool.getObject(); // the created object has exceeded the limit
        pool.release(connect1);
        pool.release(connect2);
        Connection connect4 = pool.getObject(); // object reuse
        Connection connect5 = pool.getObject(); // object reuse
        pool.removeAll();
    }
}