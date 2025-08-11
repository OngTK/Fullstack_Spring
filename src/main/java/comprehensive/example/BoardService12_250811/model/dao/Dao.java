package comprehensive.example.BoardService12_250811.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Dao 상위 클래스 - SQL 연결과 관련된 공통 부분에 대한 선언
public class Dao {

    public Dao(){connect();}

    private String db_url = "jdbc:mysql://localhost:3306/exam12";
    private String db_user = "root";
    private String db_password = "1234";

    public Connection conn;

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
    } // func end

} // class end