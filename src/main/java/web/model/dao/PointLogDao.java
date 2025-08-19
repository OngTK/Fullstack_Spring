package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.PointLogDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PointLogDao extends Dao {

    // [1] 포인트 지급
    // 회원가입(1) 시, 1000 point 지급
    // 로그인(2) 시, 10 point
    public boolean pointAssignment(int mno, int reason) {
        try {
            String sql = "insert into pointlog(mno, plpoint, plcomment) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,mno);
            if( reason == 1 ){ // 회원가입
                ps.setInt(2,1000);
                ps.setString(3, "회원가입");
            }  else if (reason == 2 ){ // 로그인
                ps.setInt(2,10);
                ps.setString(3, "로그인");
            }
            int result = ps.executeUpdate();
            if(result == 1) return true;
        } catch (Exception e) {
            System.out.println("PointLogDao.singupPoint " + e);
        }
        return false;
    } // func end


    // [2] mno 별 포인트 이력 출력
    public List<PointLogDto> mnoPointLog(int mno) {
        List<PointLogDto> list = new ArrayList<>();
        try {
            String sql = "select * from pointlog where mno = ? order by pldate desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PointLogDto pointLogDto = new PointLogDto();
                pointLogDto.setPlno(rs.getInt("plno"));
                pointLogDto.setMno(rs.getInt("mno"));
                pointLogDto.setPlpoint(rs.getInt("plpoint"));
                pointLogDto.setPlcomment(rs.getString("plcomment"));
                pointLogDto.setPldate(rs.getString("pldate"));

                list.add(pointLogDto);
            }
        } catch (Exception e) {
            System.out.println("PointLogDao.mnoPointLog " + e);
        }
        return list;
    } //func end

    // [3] mno 별 포인트 합산
    public int mnoPoint(int mno) {
        try {
            String sql = "select sum(plpoint) as totalPoint from pointlog where mno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("totalPoint");
            }
        } catch (Exception e) {
            System.out.println("PointLogDao.mnoPoint " + e);
        }
        return 0;
    } // func end

} // class end
