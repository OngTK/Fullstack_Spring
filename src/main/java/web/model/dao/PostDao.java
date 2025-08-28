package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.PageDto;
import web.model.dto.PostDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // [2-1] 카테고리별 data의 수 (검색 X)
    public int getTotalCount(int cno) {
        try {
            String sql = "select count(*) from post where cno = ? ";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setInt(1, cno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("PostDao.getTotalCount " + e);
        }
        return 0;
    } // func end

    // [2-2] 조건에 해당하는 게시물 조회  (검색 X)
    // startRow : 페이지당 조회 시작 인덱스
    public List<PostDto> findAll(int cno, int startRow, int count) {
        List<PostDto> postList = new ArrayList<>();
        try {
            String sql = "select * from post p inner join member m on p.mno = m.mno where p.cno=? order by p.pno desc limit ?,?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cno);
            ps.setInt(2, startRow);
            ps.setInt(3, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PostDto postDto = new PostDto();
                postDto.setMno(rs.getInt("mno"));
                postDto.setCno(rs.getInt("cno"));
                postDto.setPcontent(rs.getString("pcontent"));
                postDto.setPno(rs.getInt("pno"));
                postDto.setPtitle(rs.getString("ptitle"));
                postDto.setPdate(rs.getString("pdate"));
                postDto.setPview(rs.getInt("pview"));
                postDto.setMid(rs.getString("mid"));
                postList.add(postDto);
            }

        } catch (Exception e) {
            System.out.println("PostDao.findAll " + e);
        }
        return postList;
    } // func end

    // [2-3] 카테고리별 data의 수 (검색O)
    public int getTotalCountSearch(int cno, String key, String keyword) {
        try {
            String sql = "select count(*) from post where cno = ? ";
            // 동적 SQL
            if (key.equals("ptitle")) {
                sql += " and ptitle like ? ";
            } else if (key.equals("pcontent")) {
                sql += "and pcontent like ? ";
            } // 그외 검색 속성이 존재하면 추가
            System.out.println("PostDao.getTotalCountSearch " + sql);

            PreparedStatement ps = conn.prepareCall(sql);
            ps.setInt(1, cno);
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("PostDao.getTotalCount " + e);
        }
        return 0;
    } // func end

    // [2-4] 조건에 해당하는 게시물 조회 (검색O)
    public List<PostDto> findAllSearch(int cno, int startRow, int count, String key, String keyword) {
        List<PostDto> postList = new ArrayList<>();
        try {
            String sql = "select * from post p inner join member m on p.mno = m.mno where p.cno=? ";

            // 동적 SQL
            if (key.equals("ptitle")) {
                sql += " and ptitle like ? ";
            } else if (key.equals("pcontent")) {
                sql += "and pcontent like ? ";
            } // 그외 검색 속성이 존재하면 추가
            sql += "order by p.pno desc limit ?,? ";

            System.out.println("PostDao.findAllSearch " + sql);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cno);
            ps.setString(2, "%" + keyword + "%");
            ps.setInt(3, startRow);
            ps.setInt(4, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PostDto postDto = new PostDto();
                postDto.setMno(rs.getInt("mno"));
                postDto.setCno(rs.getInt("cno"));
                postDto.setPcontent(rs.getString("pcontent"));
                postDto.setPno(rs.getInt("pno"));
                postDto.setPtitle(rs.getString("ptitle"));
                postDto.setPdate(rs.getString("pdate"));
                postDto.setPview(rs.getInt("pview"));
                postDto.setMid(rs.getString("mid"));

                postList.add(postDto);

            }
        } catch (Exception e) {
            System.out.println("PostDao.getTotalCount " + e);
        }
        return postList;
    } // func end

    // 250827 추가
    // [3-1] 개별 조회
    public PostDto getPost(int pno) {
        try {
            String sql = "select * from post p inner join member m on p.mno = m.mno where p.pno=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pno);
            ResultSet rs = ps.executeQuery();
            PostDto postDto = new PostDto();
            if (rs.next()) {
                postDto.setPno(rs.getInt("pno"));
                postDto.setPtitle(rs.getString("ptitle"));
                postDto.setPcontent(rs.getString("pcontent"));
                postDto.setPdate(rs.getString("pdate"));
                postDto.setPview(rs.getInt("pview"));
                postDto.setMno(rs.getInt("mno"));
                postDto.setCno(rs.getInt("cno"));
                postDto.setMid(rs.getString("mid"));
            }
            return postDto;
        } catch (Exception e) {
            System.out.println("PostDao.getPost " + e);
        }
        return null;
    } // func end

    // [3-2] 조회수 증가
    public void incrementView(int pno) {
        try {
            String sql = "update post set pview = pview+1 where pno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pno);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("PostDao.incrementView " + e);
        }
    } // func end

    // [4] 개별 삭제
    public boolean deletePost(int pno) {
        try {
            String sql = "delete from post where pno = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pno);
            int result = ps.executeUpdate();
            if (result == 1) return true;
        } catch (Exception e) {
            System.out.println("PostDao.deletePost " + e);
        }
        return false;
    } // func end

    // [5] 개별 수정
    public int updatePost(PostDto postDto, int loginMno) {
        try {
            String sql = "update post set ptitle=? , pcontent =?, cno=? where pno = ? and mno=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, postDto.getPtitle());
            ps.setString(2, postDto.getPcontent());
            ps.setInt(3, postDto.getCno());
            ps.setInt(4, postDto.getPno());
            ps.setInt(5, loginMno);
            int result = ps.executeUpdate();
            if (result == 1) return postDto.getPno();
        } catch (Exception e) {
            System.out.println("PostDao.updatePost " + e);
        }
        return 0;
    } // func end

    // 250828 추가
    // [6] 댓글 등록
    public int writeReply(Map<String, String> reply) {
        try {
            String sql = "insert into reply(rcontent, mno, pno) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,reply.get("rcontent"));
            ps.setString(2,reply.get("mno"));
            ps.setString(3,reply.get("pno"));
            int count = ps.executeUpdate();
            if ( count >0 ){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()) return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("PostDao.writeReply " + e);
        }
        return 0;
    } // func end

    // [7] 댓글 조회
    // 조회중인 게시물(pno)의 댓글을 전체 조회
    public List<Map<String, String>> findAllReply(int pno){
        try{
            String sql = "select * from reply where pno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, pno);
            ResultSet rs = ps.executeQuery();
            List replies = new ArrayList();
            while(rs.next()){
                Map<String, String> reply = new HashMap<>();
                reply.put("rcontent", rs.getString("rcontent"));
                reply.put("rdate", rs.getString("rdate"));
                reply.put("rno", rs.getString("rno"));
                reply.put("mno", rs.getString("mno"));
                replies.add(reply);
            }
            return replies;
        } catch (Exception e) {
            System.out.println("PostDao.findAllReply " + e);
        }
        return null;
    } // func end

} // class end
