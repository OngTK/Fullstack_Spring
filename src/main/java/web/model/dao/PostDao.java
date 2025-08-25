package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.PostDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class PostDao extends Dao {

    // 250825
    // [1] 게시물 등록
    // 성공시 pno/게시물 PK no를 반환
    public int writePost(PostDto postDto) {
        try {
            String sql = "insert into post(ptitle, pcontent, mno, cno) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, postDto.getPtitle());
            ps.setString(2, postDto.getPcontent());
            ps.setInt(3, postDto.getMno());
            ps.setInt(4, postDto.getCno());
            int count = ps.executeUpdate();
            if (count == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) return rs.getInt(1); // 성공
            }
        } catch (Exception e) {
            System.out.println("PostDao.postBoard " + e);
        }
        return 0; // 실패
    } // func end

    // [2] 게시물 출력
    public PostDto findAllPost() {
        try {
            String sql = "select * from post";
        } catch (Exception e) {
            System.out.println("PostDao.printPost " + e);
        }

    } // func end

} // class end
