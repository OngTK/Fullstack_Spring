package practice.practice_03_250805.model.dao;

import practice.practice_03_250805.model.dto.WaitingDto;

import java.sql.*;
import java.util.ArrayList;

public class WaitingDao {

    //싱글톤
    private WaitingDao(){ connect(); }
private static final WaitingDao instance = new WaitingDao();
    public static WaitingDao getInstance(){ return instance; }

    // DB 연동
    private String db_url = "jdbc:mysql://localhost:3306/waiting";
    private String db_user = "root";
    private String db_pw = "1234";
    private Connection conn;

    // DB연동 메소드
    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_pw);
        } catch (ClassNotFoundException e) {
            System.out.println("[경고] MySQL 드라이버 로드 실패 " + e);
        } catch (SQLException e) {
            System.out.println("[경고] DB 연동 실패 " + e);
        }
    }

    // 메소드 ==================================


    // [1] 대기 등록 / waitingRegi()
    // 매개변수 : WaitingDto waitingDto
    // 반환 : boolean
    public boolean waitingRegi(WaitingDto waitingDto) {
        try {
            //[1.1] SQL 작성
            String sql = "insert into waiting_list( phone , count ) values(?,?)";
            //[1.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            //[1.3] SQL 매개변수 대입
            ps.setString(1, waitingDto.getPhone());
            ps.setInt(2, waitingDto.getCount());
            //[1.4] SQL 실행
            int count = ps.executeUpdate();
            //[1.5] SQL 실행 결과
            if (count >= 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("[예외발생] dao-1 " + e);
        }
        // [1.6] 결과 반환
        return false;
    }// func end

    // [2] 대기 전체 조회 / waitingPrint()
    // 매개변수 : -
    // 반환 : ArrayList<WaitingDto>
    public ArrayList<WaitingDto> waitingPrint() {
        // [2.5.1] ArrayList<WaitingDto> 배열 선언
        ArrayList<WaitingDto> waitingList = new ArrayList<>();
        try {
            //[2.1] SQL 작성
            String sql = "select * from waiting_list";
            //[2.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            //[2.3] SQL 매개변수 대입 (생략)
            //[2.4] SQL 실행
            ResultSet result = ps.executeQuery();
            //[2.5] SQL 실행 결과
            // 반복문 : 1 레코드를 1개의 dto 생성 >> dto를 ArrayList에 저장
            while (result.next()) {
                // [2.5.2] Dto 객체 생성
                WaitingDto waitingDto = new WaitingDto(result.getInt("wno"), result.getString("phone"), result.getInt("count"));
                // [2.5.3] dto 객체를 arraylist에 add
                waitingList.add(waitingDto);
            }
        } catch (Exception e) {
            System.out.println("[예외발생] dao-2" + e);
        }
        // [2.6] 결과 반환
        return waitingList;
    }//func end

    // [3] 대기 취소 / waitingDelete()
    // 매개변수 : int wno
    // 반환 : boolean
    public boolean waitingDelete(int wno) {
        try {
            //[3.1] SQL 작성
            String sql = "delete from waiting_list where wno=?";
            //[3.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            //[3.3] SQL 매개변수 대입
            ps.setInt(1, wno);
            //[3.4] SQL 실행
            int count = ps.executeUpdate();
            //[3.5] SQL 실행 결과
            if (count == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("[예외발생] dao-3");
        }
        // [3.6] 결과 반환
        return false;
    }//func end

    // [4] 대기 정보 수정 / waitingUpdate()
    // 매개변수 : WaitingDto waitingDto
    // 반환 : boolean
    public boolean waitingUpdate(WaitingDto waitingDto) {
        try {
            //[4.1] SQL 작성
            String sql = "update waiting_list set count=? where wno=?";
            //[4.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            //[4.3] SQL 매개변수 대입
            ps.setInt(1,waitingDto.getCount());
            ps.setInt(2,waitingDto.getWno());
            //[4.4] SQL 실행
            int count = ps.executeUpdate();
            //[4.5] SQL 실행 결과
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("[예외발생] dao-4 " + e);
        }
        // [4.6] 결과 반환
        return false;
    } //func end

}// class end
