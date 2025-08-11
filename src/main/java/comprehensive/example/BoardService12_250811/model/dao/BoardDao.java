package comprehensive.example.BoardService12_250811.model.dao;

import comprehensive.example.BoardService12_250811.model.dto.BoardDto;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository                 // [0.1] Repository Annotation 선언
public class BoardDao extends Dao {

    // [1] boardWrite()
    public boolean boardWrite(BoardDto boardDto) {
        System.out.println("BoardDao.boardWrite");
        System.out.println("boardDto = " + boardDto);

        boolean result = false;
        try {
            String sql = "insert into board(bwriter, bcontent) values (?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, boardDto.getBwriter());
            ps.setString(2, boardDto.getBcontent());

            int count = ps.executeUpdate();

            if (count == 1) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println("BoardDao.boardWrite" + e);
        }
        return result;
    } // func end

    // [2] boardPrint()
    public ArrayList<BoardDto> boardPrint() {
        System.out.println("BoardDao.boardPrint");

        ArrayList<BoardDto> list = new ArrayList<>();
        try {
            String sql = "select * from board";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BoardDto boardDto = new BoardDto(
                        rs.getInt("bno"),
                        rs.getString("bwriter"),
                        rs.getString("bcontent"));

                list.add(boardDto);
            }
        } catch (Exception e) {
            System.out.println("BoardDao.boardPrint" + e);
        }
        return list;
    }

    // [3] boardFind()

    // [4] boardDelete()
    public boolean boardDelete(int bno) {
        System.out.println("BoardDao.boardDelete");
        System.out.println("bno = " + bno);

        boolean result = false;
        try {
            String sql = "delete from board where bno = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);

            int count = ps.executeUpdate();

            if (count == 1) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println("BoardDao.boardDelete" + e);
        }
        return result;
    }

    // [5] boardUpdate()
    public boolean boardUpdate(BoardDto boardDto){
        System.out.println("BoardDao.boardUpdate");
        System.out.println("boardDto = " + boardDto);

    }


}
