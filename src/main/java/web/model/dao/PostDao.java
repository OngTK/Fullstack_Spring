package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.PageDto;
import web.model.dto.PostDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    // [2-1] 카테고리별 data의 수
    public int getTotalCount(int cno){
        try{
            String sql = "select count(*) from post where cno = ? ";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setInt(1, cno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getInt(1);
        } catch (Exception e){
            System.out.println("PostDao.getTotalCount " + e );
        }
        return 0;
    } // func end

    // [2-2] 조건에 해당하는 게시물 조회
    // startRow : 페이지당 조회 시작 인덱스
    public List<PostDto> findAll (int cno, int startRow, int count){
        List<PostDto> postList = new ArrayList<>();
        try{
            String sql = "select * from post where cno = ? order by pno desc limit ?,?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,cno);
            ps.setInt(2,startRow);
            ps.setInt(3,count);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                PostDto postDto = new PostDto();
                postDto.setMno(rs.getInt("mno"));
                postDto.setCno(rs.getInt("cno"));
                postDto.setPcontent(rs.getString("pcontent"));
                postDto.setPno(rs.getInt("pno"));
                postDto.setPtitle(rs.getString("ptitle"));
                postDto.setPdate(rs.getString("pdate"));
                postDto.setPview(rs.getInt("pview"));

                postList.add(postDto);
            }

        } catch (Exception e) {
            System.out.println("PostDao.findAll " + e );
        }
    } // func end

} // class end
