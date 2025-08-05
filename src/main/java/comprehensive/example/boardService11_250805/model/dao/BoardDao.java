package comprehensive.example.boardService11_250805.model.dao;


import comprehensive.example.boardService11_250805.model.dto.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {
    // 싱글톤 선언
    private BoardDao(){ connect(); }
    private static final BoardDao instance = new BoardDao();
    public static BoardDao getInstance(){return instance;}


    // DB 연동 
    private String db_url = "jdbc:mysql://localhost:3306/exam11";
    private String db_user = "root";
    private String db_password = "1234";
    private Connection conn;
    
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( db_url , db_user , db_password );
        }catch (Exception e ){ System.out.println(e);   }
    }

    // 메소드

    // (1) 등록 기능 구현
    public boolean boardWrite(BoardDto boardDto){
        // boolean              : 해당 메소드 실행 결과를 true(저장성공)false(저장실패) 반환 하기 위한 타입
        // BoardDto boardDto    : controller 로부터 저장할 값들을 dto로 구성해서 받는 매개변수
        try {
            String sql = "INSERT INTO board ( bcontent, bwriter ) VALUES (?, ?);";  // 1. SQL 작성한다.
            PreparedStatement ps = conn.prepareStatement( sql );  // 2. SQL 기재한다..
            // 3. SQL 매개변수 대입 , 현재 ? 2개
            ps.setString( 1 , boardDto.getBcontent() ); // SQL내 1번 ? 에 매개변수로 받은 boardDto의 bcontent 값 대입
            ps.setString( 2 , boardDto.getBwriter() ); // SQL내 2번 ? 에 매개변수로 받은 boardDto의 bwriter 값 대입
            // 4. SQL 실행  , insert/update/delete 은 .executeUpdate();
            int count = ps.executeUpdate();
            // 5. sql 결과에 따른 로직/리턴/확인
            if( count >= 1 ) return true; // 1개 이상 insert 했으면 저장성공
            return false; // 1개이상 insert 못했으면 저장실패
        } catch (Exception e) { System.out.println(e); } // catch end
        return false; // 예외 발생시 저장실패.
    } // func end

    // (2) 전체조회 기능 구현
    public ArrayList<BoardDto> boardPrint( ) {
        // public : 다른패키지의 Controller 가 접근하기 위해 public 다른패키지 접근 vs private 현재클래스만
        // ArrayList<BoardDto> : *배열* 대신에 다양한 편의성 기능을 제공하는 ArrayList 클래스
        ArrayList<BoardDto> list = new ArrayList<>(); // 조회된 레코드(DTO) 들을 저장할 리스트 선언
        try {
            String sql = "select * from board"; // 1. SQL 작성
            PreparedStatement ps = conn.prepareStatement(sql);  // 2. SQL 기재
            // 3. SQL 매개변수 대입 , SQL문법에 ?(매개변수)가 없어서 생략
            ResultSet rs = ps.executeQuery();  // 4. SQL 실행   , select = executeQuery()
            // 5. SQL 결과에 따른 로직/리턴/확인
            // 1) select 조회 결과를 레코드/행/가로단위 하나씩 조회
            while ( rs.next() ) { // rs.next() : ResultSet 인터페이스가 갖는 (조회)결과테이블에서 다음레코드 이동 뜻
                // 2) 현재 조회중인 레코드의 속성값 호출해서 dto 만들기
                int bno = rs.getInt( "bno" ); // rs.get타입("가져올속성명or번호")
                String bcontent = rs.getString("bcontent");
                String bwriter = rs.getString("bwriter");
                BoardDto boardDto = new BoardDto(  bno , bcontent , bwriter ); // 레코드1개(열3개) --> DTO(멤버변수3개) 1개
                // 3) 생성된 dto를 리스트에 담아주기
                list.add( boardDto );
            } // while 반복문 종료
        } catch (Exception e) { System.out.println(e); }
        return list;
    } // func end

    // (3) 삭제 기능 구현
    public boolean boardDelete( int bno ){ // int bno : 매개변수이면서 삭제할 게시물의 식별(pk)번호
        try {
            String sql = "delete from board where bno = ?";// 1. SQL 작성
            PreparedStatement ps = conn.prepareStatement(sql);// 2. SQL 기재
            ps.setInt(1, bno);// SQL문법내 첫번째 ? 의 INT타입으로 bno 값 대입 // 3. SQL 매개변수 대입
            // 4. SQL 실행 , insert/update/delete 문법 실행 결과는 처리된 레코드수 반환
            int count = ps.executeUpdate();
            // 5. SQL 결과에 따른 로직/리턴/확인
            if (count == 1) {  return true; } // <---sql 결과 1이면 성공
            else {   return false; } // <--- sql 결과 1 아니면 실패
        } catch (Exception e) {  System.out.println(e);  }
        return false; // <--- 예외발생시 삭제실패
    }

    // (4) 수정 기능 구현
    public boolean boardUpdate( BoardDto boardDto ){
        try { // * try ~ catch
            String sql = "update board set bcontent = ? where bno = ? "; // 1. SQL 작성
            PreparedStatement ps = conn.prepareStatement(sql);// 2. SQL 기재
            // 3. SQL 매개변수 대입 , SQL문법내 ? 개수만큼 대입
            ps.setString( 1 , boardDto.getBcontent() ); // .setString() 사용한이유 : bcontent가 문자열 이라서
            ps.setInt( 2 , boardDto.getBno() ); // 2 작성한이유 : SQL문법내 두번째 ? 자리 에 bno 값 대입
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과에 따른 로직/리턴/확인
            if( count == 1 ) return true; // 수정 sql 결과가 1개 이면 수정성공
            return false; // 수정 sql 결과가 1이 아니면 수정실패
        } catch (Exception e) { System.out.println(e);  }
        return false; // 예외발생시 수정실패
    }

} // class end
