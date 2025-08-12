package practice.practice_05_250812.model.dao;

import org.springframework.stereotype.Repository;
import practice.practice_05_250812.model.dto.WaitingDto;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository                 // [0] Repository Annotation 전언
public class WaitingDao extends Dao {

    // [1] 대기 현황 등록 기능 , /waiting/write.jsp
    // 조건 : 대기번호는 자동부여 되며 연락처 와 인원수만 입력받아 저장합니다.
    public boolean waitingWrite(WaitingDto waitingDto) {
        System.out.println("WaitingDao.waitingWrite");
        System.out.println("waitingDto = " + waitingDto);

        boolean result = false;
        try {
            // [1.1] SQL 작업
            String sql = "insert into waitingList(phone, count) values(?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, waitingDto.getPhone());
            ps.setInt(2, waitingDto.getCount());
            int count = ps.executeUpdate();

            // [1.2] SQL 결과
            if (count == 1) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("WaitingDao.waitingWrite" + e);
        }
        // [1.3] 결과 반환
        return result;
    } // func end

    // [2] 대기 현황 전체 조회 기능 , /waiting/list.jsp
    //조건 : 모든 대기 현황의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
    public ArrayList<WaitingDto> waitingPrint() {
        System.out.println("WaitingDao.waitingPrint");

        ArrayList<WaitingDto> list = new ArrayList<>();
        try {
            // [2.1] SQL 작성
            String sql = "select * from waitingList";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // [2.2] 반복문
            while (rs.next()) {
                WaitingDto waitingDto = new WaitingDto(
                        rs.getInt("wno"),
                        rs.getString("phone"),
                        rs.getInt("count"),
                        rs.getString("wdate")
                );

                list.add(waitingDto);
            }
        } catch (Exception e) {
            System.out.println("WaitingDao.waitingPrint" + e);
        }
        // [2.3] 결과 반환
        return list;
    }

    // [3] 특정 대기 현황 조회 기능 ,  /waiting/view.jsp
    //조건 : 선택한 대기번호의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
    public WaitingDto waitingView(int wno) {
        System.out.println("WaitingDao.waitingView");
        System.out.println("wno = " + wno);

        WaitingDto waitingDto = new WaitingDto();
        try {
            // [3.1] SQL 작성
            String sql = "select * from waitingList where wno = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, wno);
            ResultSet rs = ps.executeQuery();

            // [3.2] SQL 결과
            rs.next();
            waitingDto.setWno(rs.getInt("wno"));
            waitingDto.setPhone(rs.getString("phone"));
            waitingDto.setCount(rs.getInt("count"));
            waitingDto.setWdate(rs.getString("wdate"));
        } catch (SQLException e) {
            System.out.println("WaitingDao.waitingView" + e);
        }
        // [3.3] 결과 반환
        return waitingDto;
    } // func end

    // [4] 특정 대기 현황 삭제 기능  , /waiting/view.jsp포함됩니다.
    //조건 : 선택한 대기번호의 정보를 삭제합니다.
    public boolean waitingDelete(int wno) {
        System.out.println("WaitingDao.waitingDelete");
        System.out.println("wno = " + wno);
        boolean result = false;
        try {
            // [4.1] SQL 작업
            String sql = "delete from waitingList where wno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, wno);
            int count = ps.executeUpdate();

            // [4.2] SQL 결과
            if (count == 1) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println("WaitingDao.waitingDelete" + e);
        }
        // [4.3] 결과 반환
        return result;
    } // func end

    // [5] 특정 대기 현황 수정 기능 , /waiting/update.jsp
    //조건1 : 선택한 대기번호의 연락처 와 인원수 를 출력합니다.
    //조건2 : 새로운 연락처 와 인원수 입력받아 선택한 대기번호 정보를 수정합니다.
    public boolean waitingUpdate(WaitingDto waitingDto) {
        System.out.println("WaitingDao.waitingUpdate");
        System.out.println("waitingDto = " + waitingDto);
        boolean result = false;
        try {
            // [5.1] SQL 작업
            String sql = "update waitingList set phone = ? , count = ? where wno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, waitingDto.getPhone());
            ps.setInt(2,waitingDto.getCount());
            ps.setInt(3,waitingDto.getWno());
            int count = ps.executeUpdate();

            // [5.2] SQL 결과
            if (count == 1 ){
                result = true;
            }
        } catch (Exception e) {
            System.out.println("WaitingDao.waitingUpdate" + e);
        }
        // [5.3] 결과 반환
        return result;
    } // func end

} // class end
