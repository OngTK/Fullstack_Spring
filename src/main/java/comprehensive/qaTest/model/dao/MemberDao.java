package comprehensive.qaTest.model.dao;

import comprehensive.qaTest.model.dto.MemberDto;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class MemberDao extends Dao {

    // [1] 회원 정보 등록 memberCreate()
    public boolean memberCreate(MemberDto memberDto) {
        boolean result = false;
        try {
            // [1.1] SQL 작업
            String sql = "insert into member(custname, phone, address, joindate, grade, city) values (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getCustName());
            ps.setString(2, memberDto.getPhone());
            ps.setString(3, memberDto.getAddress());
            ps.setString(4, memberDto.getJoinDate());
            ps.setString(5, memberDto.getGrade());
            ps.setString(6, memberDto.getCity());
            int count = ps.executeUpdate();

            // [1.2] SQL 결과
            if (count == 1) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println("MemberDao.memberCreate" + e);
        }
        // [1.3] 결과 반환
        return result;
    } // func end

    // [2] 회원 정보 전체조회 memberRead()
    public ArrayList<MemberDto> memberRead() {
        ArrayList<MemberDto> list = new ArrayList<>();
        try {
            String sql = "select * from member";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MemberDto memberDto = new MemberDto(
                        rs.getInt("custNo"),
                        rs.getString("custName"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("joinDate"),
                        rs.getString("grade"),
                        rs.getString("city")
                );
                list.add(memberDto);
            }
        } catch (Exception e) {
            System.out.println("MemberDao.memberRead" + e);
        }
        return list;
    } // func end

    // [3] 회원 정보 개별조회 memberView()
    public MemberDto memberView(int custNo) {
        MemberDto memberDto = new MemberDto();
        try {
            String sql = "select * from member where custNo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,custNo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            memberDto.setCustNo(rs.getInt("custNo"));
            memberDto.setCustName(rs.getString("custName"));
            memberDto.setPhone(rs.getString("phone"));
            memberDto.setAddress(rs.getString("address"));
            memberDto.setJoinDate(rs.getString("joinDate"));
            memberDto.setGrade(rs.getString("grade"));
            memberDto.setCity(rs.getString("city"));
        } catch (Exception e) {
            System.out.println("MemberDao.memberView" + e);
        }
        return memberDto;
    } //func end

    // [4] 회원 정보 수정 memberUpdate()
    public boolean memberUpdate(MemberDto memberDto){
        boolean result = false;
        try{
            String sql = "update member set custname=?, phone=?, address=?, joindate=?, grade=?, city=? where custNo= ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,memberDto.getCustName());
            ps.setString(2,memberDto.getPhone());
            ps.setString(3,memberDto.getAddress());
            ps.setString(4,memberDto.getJoinDate());
            ps.setString(5,memberDto.getGrade());
            ps.setString(6,memberDto.getCity());
            ps.setInt(7,memberDto.getCustNo());
            int count = ps.executeUpdate();
            if( count == 1 ){
                result = true;
            }
        } catch (Exception e) {
            System.out.println("MemberDao.memberUpdate"+e);
        }
        return result;
    } // func end

    // [5] 회원 정보 삭제 memberDelete()
    public boolean memberDelete(int custNo){
        boolean result = false;
        try{
            String sql = "delete from member where custNo = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,custNo);
            int count = ps.executeUpdate();

            if(count == 1){
                result = true;
            }
        }catch (Exception e) {
            System.out.println("MemberDao.memberDelete"+e);
        }
        return result;
    }

    // [6] max custNO 추출
    public int maxCustNo(){
        int result = 0;
        try{
            String sql = "select max(custno) from member";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            result = rs.getInt("max(custno)") + 1;
        } catch (Exception e) {
            System.out.println("MemberDao.maxCustNo"+e);
        }
        return result;
    }

} // class end
