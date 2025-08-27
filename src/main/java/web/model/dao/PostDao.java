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
            ps.setInt(1,pno);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("PostDao.incrementView " + e);
        }
    } // func end

    // [4] 개별 수정

    // [5] 개별 삭제

} // class end
