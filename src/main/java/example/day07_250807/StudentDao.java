package example.day07_250807;

import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    // 싱글톤
    @Getter // 어노테이션으로 getter 관련 싱글톤 코드 대체
    private static final StudentDao instance = new StudentDao();
    private StudentDao(){connect();};

    // DB 연동에 필요한 정보 ===================================
    private String db_url = "jdbc:mysql://localhost:3306/spring07";
    private String db_user = "root";
    private String db_pw = "1234";

    private Connection conn;    // DB연동 결과를 저장하기 위한 인터페이스

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
    } // DB connect func end
    
    // 메소드
    
    // [1] 등록
    public boolean save(StudentDto studentDto){
        try{
            // [1.1] sql 작성
            String sql = "insert into student(sname,skor,smath) values(?,?,?)";

            // [1.2] sql 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            // [1.3] sql 매개변수 삽입
            ps.setString(1,studentDto.getSname());
            ps.setInt(2,studentDto.getSkor());
            ps.setInt(3,studentDto.getSmath());

            // [1.4] sql 실행
            int count = ps.executeUpdate();

            // [1.5] sql 결과
            if( count == 1) return true ;

        } catch (Exception e) {
            System.out.println("StudentDao.save" + e) ;
        }
        return false;
    } //func end

    // [2] 전체조회
    public List<StudentDto> find(){
        List<StudentDto> list = new ArrayList<>();

        try {
            // [2.1] SQL 작성
            String sql = "select * from student";

            // [2.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            // [2.3] SQL 매개변수 삽입 / 생략

            // [2.4] SQL 실행
            ResultSet rs = ps.executeQuery();

            // [2.5] SQL 결과
            // [2.5.1] 반복문
            while (rs.next()){
                // [2.5.2] DTO 객체 생성
                StudentDto studentDto = new StudentDto(
                        rs.getInt("sno"),
                        rs.getString("sname"),
                        rs.getInt("skor"),
                        rs.getInt("smath"),
                        rs.getString("sdate")
                );
                // [2.5.3] DTO 객체를 list에 삽입
                list.add(studentDto);
            }
        }catch (Exception e){
            System.out.println("StudentDao.find" + e);
        }
        // [2.6] 결과 반환
        return list;
    } //func end

} // class end
