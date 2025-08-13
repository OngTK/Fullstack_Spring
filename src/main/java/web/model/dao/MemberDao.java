package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

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

    // [2] 로그인
    public int login(MemberDto memberDto) {
        try {
            // [2.1] SQL 작성
            String sql = "select * from member where mid = ? and mpwd = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getMid());
            ps.setString(2, memberDto.getMpwd());
            ResultSet rs = ps.executeQuery();
            // [2.2] SQL 결과
            if (rs.next()) {
                int mno = rs.getInt("mno");
                // [2.3] 결과반환 - 성공 시 mno 반환
                return mno;
            }
        } catch (Exception e) {
            System.out.println("MemberDao.login" + e);
        }
        // [2.3] 결과 반환 - 실패 시 0 반환
        return 0;
    } // func end

    // [3] 로그아웃 - Dao X

    // [4] 내 정보 조회
    public MemberDto info(int mno) {
        try {
            // [4.1] SQL 작성
            String sql = "select * from member where mno=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mno);
            ResultSet rs = ps.executeQuery();
            // [4.2] SQL 결과
            if (rs.next()) {
                // mpw는 정보보호를 위해 Dto에 담지 않음
                MemberDto memberDto = new MemberDto();
                memberDto.setMno(rs.getInt("mno"));
                memberDto.setMid(rs.getString("mid"));
                memberDto.setMname(rs.getString("mname"));
                memberDto.setMphone(rs.getString("mphone"));
                memberDto.setMdate(rs.getString("mdate"));
                return memberDto;
            }
        } catch (Exception e) {
            System.out.println("MemberDao.info" + e);
        }
        // [4.3] 결과 반환
        return null;
    } // func end

    // [5] 중복검사
    public boolean check(String type, String data) {
        try {
            // [5.1] SQL 작성
            String sql = "select * from member where " + type + " = ? "; // ? 는 param 값으로 사용은 가능하나, 속성명으로는 불가
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, data);
            ResultSet rs = ps.executeQuery();

            // [5.2] SQL 결과
            if (rs.next()) {
                // [5.3] 결과반환 - 중복
                return true;
            }
        } catch (Exception e) {
            System.out.println("MemberDao.check" + e);
        }
        // [5.3] 결과반환 - 중복X
        return false;
    } // func end

    // [6] 회원 정보 수정
    public boolean update(MemberDto memberDto) {
        try {
            // [6.1] SQL 작성
            String sql = "update member set mname=?,mphone=? where mno=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getMname());
            ps.setString(2, memberDto.getMphone());
            ps.setInt(3, memberDto.getMno());
            int count = ps.executeUpdate();
            // [6.2] SQL 결과
            if (count == 1) {
                // [6.3] 결과 반환 - 성공
                return true;
            }
        } catch (Exception e) {
            System.out.println("MemberDao.update" + e);
        }
        // [6.3] 결과 반환 - 성공
        return false;
    } // func end

    // [7] 비밀번호 수정
    public boolean updatePassword(int mno, Map<String, String> map) {
        try {
            // [7.1] SQL 작성
            String sql = "update member set mpwd=? where mno = ? and mpwd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, map.get("newPwd"));
            ps.setInt(2, mno);
            ps.setString(3, map.get("oldPwd"));
            int count = ps.executeUpdate();
            // [7.2] SQL 결과
            if (count == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("MemberDao.updatePassword" + e);
        }
        // [7.3]
        return false;
    } // func end

    // [8] 회원 탈퇴
    public boolean delete(int mno, String mpwd) {
        try {
            // [8.1] SQL 작성
            String sql = "delete from member where mno=? and mpwd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mno);
            ps.setString(2,mpwd);
            int count = ps.executeUpdate();
            // [8.2] 결과
            if (count == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("MemberDao.delete" + e);
        }
        return false;
    } // func end

} // class end
