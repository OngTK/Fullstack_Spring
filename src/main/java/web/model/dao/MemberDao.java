package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class MemberDao extends Dao {

    // [1] 회원가입
    public int signup(MemberDto memberDto) {

        try {
            // [1.1] SQL 작업
            String sql = "insert into member(mid,mpwd,mname,mphone) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // [1.1.1] ☆★ RETURN_GENERATED_KEYS : PK의 auto increment 값을 반환 ☆★☆★☆★☆★☆★☆★☆★☆★☆★

            ps.setString(1, memberDto.getMid());
            ps.setString(2, memberDto.getMpwd());
            ps.setString(3, memberDto.getMname());
            ps.setString(4, memberDto.getMphone());

            // [1.2] SQL 결과
            int count = ps.executeUpdate();
            if (count == 1) {
                // [1.2.1] ☆★ RETURN_GENERATED_KEYS의 결과값 = PK 값 가져오기 ☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int mno = rs.getInt(1);
                    // [1.3] 결과 반환 - 성공
                    return mno; // 회원가입 성공 시, 해당 회원의 mno를 반환
                }
            }
        } catch (Exception e) {
            System.out.println("MemberDao.signup" + e);
        }
        // [1.3] 결과 반환 - 실패
        return 0;
    } // func end


} // class end
