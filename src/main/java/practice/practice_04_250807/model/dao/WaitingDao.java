package practice.practice_04_250807.model.dao;

import lombok.Getter;
import practice.practice_04_250807.model.dto.WaitingDto;

import java.sql.*;
import java.util.ArrayList;

public class WaitingDao {

    // 싱글톤
    private WaitingDao(){ connect(); }
    @Getter             // lombok 메소드
    private static final WaitingDao instance = new WaitingDao();

    // DB 연동에 필요한 정보 ===================================
    private String db_url = "jdbc:mysql://localhost:3306/waiting4";
    private String db_user = "root";
    private String db_pw = "1234";
    private Connection conn;

    // DB 연동 함수 ==========================================
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_pw);
        } catch (ClassNotFoundException e) {
            System.out.println("[경고] MySQL 드라이버 로드 실패 "+e);
        } catch (SQLException e) {
            System.out.println("[경고] DB 연동 실패 " + e);
        }
    }

    // 메소드 ====================================================

    // [1] 대기 등록
    public boolean waitingRegi(WaitingDto waitingDto){
        try {
            // [1.1] SQL 작성
            String sql = "insert into waiting(phone, count) values(?,?)";
            // [1.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [1.3] SQL 매개변수 삽입
            ps.setString(1,waitingDto.getPhone());
            ps.setInt(2,waitingDto.getCount());
            // [1.4] SQL 실행
            int count = ps.executeUpdate();
            // [1.5] SQL 결과
            if( count == 1 ) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("WaitingDao.waitingRegi" + e );
        }
        return false;
    } // func end

    // [2] 대기 현황 조회
    public ArrayList<WaitingDto> waitingPrint(){
        // [2.5.3] dto 저장을 위한 list 선언
        ArrayList<WaitingDto> list = new ArrayList<>();
        try {
            // [2.1] SQL 작성
            String sql = "select * from waiting";
            // [2.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [2.3] SQL 매개변수 삽입 / 생략
            // [2.4] SQL 실행
            ResultSet rs = ps.executeQuery();
            // [2.5] SQL 결과
            // [2.5.1] 반복문
            while (rs.next()){
                // [2.5.2] dto 객체 생성
                WaitingDto waitingDto = new WaitingDto(
                        rs.getInt("wno"),
                        rs.getString("phone"),
                        rs.getInt("count")
                );

                // [2.5.4] 리스트에 객체 add
                list.add(waitingDto);
            }
        } catch (Exception e) {
            System.out.println("WaitingDao.waitingPrint" + e);
        }
        // [2.6] 결과 반환
        return list;
    } // func end

} // class end
