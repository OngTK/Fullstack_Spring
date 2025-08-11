package example.day08_250811._2_MVC.model.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 여러 개의 Dao를 연동·중복 코드를 관리하는 슈퍼 클래스

public class Dao {

    public Dao(){connect();}

    // DB 연동에 필요한 정보 ===================================
    private String db_url = "jdbc:mysql://localhost:3306/Spring08";
    private String db_user = "root";
    private String db_password = "1234";

    // DB 연동 멤버변수 ======================================
    public Connection conn;
    // 하위 클래스에서 사용할 수 있도록 public

    // DB 연동 함수 ==========================================
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Dao.connect");
            conn = DriverManager.getConnection(db_url, db_user, db_password);
        } catch (ClassNotFoundException e) {
            System.out.println("[경고] MySQL 드라이버 로드 실패 "+e);
        } catch (SQLException e) {
            System.out.println("[경고] DB 연동 실패 " + e);
        }
    }




}
