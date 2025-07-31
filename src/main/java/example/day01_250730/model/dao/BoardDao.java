package example.day01_250730.model.dao;

import example.day01_250730.model.dto.BoardDto;

import java.sql.*;
import java.util.ArrayList;

public class BoardDao {

    // 싱글톤 =====================================================
    private BoardDao() {
        connectDB(); //Dao 객체 생성과 동시에 DB 연동 시작
    }

    ;
    private static final BoardDao instance = new BoardDao();

    public static BoardDao getInstance() {
        return instance;
    }

    // DB 연동에 필요한 정보 ========================================
    private String db_url = "jdbc:mysql://localhost:3306/exam10";
    private String db_user = "root";
    private String db_pw = "1234";

    private Connection conn;    // DB연동 결과를 저장하기 위한 인터페이스

    // DB 연동 함수 ================================================
    public void connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_pw);
        } catch (ClassNotFoundException e) {
            System.out.println("[경고] MySQL 드라이버 로드 실패 " + e);
        } catch (SQLException e) {
            System.out.println("[경고] DB 연동 실패 " + e);
        }
    } //func end

    // [1] 등록 기능 구현
    public boolean boardWrite(BoardDto boardDto) {
        try {
            // [1.1] SQL 작성
            String sql = "insert into board(bcontent, bwriter) values(?,?)";
            // [1.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [1.3] SQL 매개변수 대입
            ps.setString(1, boardDto.getBcontent());
            ps.setString(2, boardDto.getBwriter());
            // [1.4] SQL 실행
            int count = ps.executeUpdate();
            // [1.5] SQL 실행 결과 확인
            if (count >= 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("[예외 발생] " + e);
        }
        return false; // 예외 발생 시 저장 실패
    } // func end

    //[2] 전체조회 기능 구현
    public ArrayList<BoardDto> boardPrint() {
        // ArrayList 선언
        ArrayList<BoardDto> list = new ArrayList<>();

        try {
            //[2.1] SQL 작성
            String sql = "select * from board";
            //[2.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            //[2.3] SQL 매개변수 대입 (생략)
            //[2.4] SQL 실행
            ResultSet rs = ps.executeQuery();
            //[2.5] SQL 실행 결과 확인
            //[2.5.1] select 조회 결과를 한 레코드씩 조회
            while (rs.next()) {
                //[2.5.2] 레코드로 dto 만들기
                BoardDto boardDto = new BoardDto(rs.getInt("bno"), rs.getString("bcontent"), rs.getString("bwriter"));
                //[2.5.3] dto를 ArrayList에 add
                list.add(boardDto);
            }
        } catch (Exception e) {
            System.out.println("[예외발생]" + e);
        }
        return list;
    } // func end

    //[3] 삭제 기능 구현
    public boolean boardDelete(int bno) {
        try {
            //[3.1] SQL 작성
            String sql = "delete from board where bno=?";
            //[3.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            //[3.3] SQL 매개변수 대입
            ps.setInt(1, bno);
            //[3.4] SQL 실행
            int count = ps.executeUpdate();
            //[3.5] SQL 실행 결과 확인
            if (count == 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("[예외발생] " + e);
        }
        return false;
    } //func end

    //[4] 수정 기능 구현
    public boolean boardUpdate(BoardDto boardDto) {
        try {
            //[4.1] SQL 작성
            String sql = "update board set bcontent=? where bno=? ";
            //[4.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            //[4.3] SQL 매개변수 대입
            ps.setString(1,boardDto.getBcontent());
            ps.setInt(2,boardDto.getBno());
            //[4.4] SQL 실행
            int count = ps.executeUpdate();
            //[4.5] SQL 실행 결과 확인
            if( count == 1 ){
                return true;
            }
            return false;
        }catch (SQLException e){
            System.out.println("[예외발생] "+e);
        }
        return false;
    } //func end
}
