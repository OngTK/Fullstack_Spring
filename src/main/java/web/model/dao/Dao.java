package web.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
    // 생성자에 DB 연결 메소드 실행
    public Dao(){connect();}

    // DB 연결 기초작업
    private String db_url = "jdbc:mysql://localhost:3306/springweb";
    private String db_user = "root";
    private String db_password = "1234";

    public Connection conn;

    // DB 연결 메소드
    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Dao.connect");
            conn = DriverManager.getConnection(db_url, db_user, db_password);
        } catch (Exception e) {
            System.out.println("Dao.connect"+e);
        }
    }

} // func end
