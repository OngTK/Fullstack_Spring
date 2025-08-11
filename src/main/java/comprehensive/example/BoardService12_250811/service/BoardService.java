package comprehensive.example.BoardService12_250811.service;

import comprehensive.example.BoardService12_250811.model.dao.BoardDao;
import comprehensive.example.BoardService12_250811.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service                // [0.1] Service Annotation
public class BoardService {

    @Autowired          // [0.2] Repository-Dao 호출 Annotation
    private BoardDao boardDao;

    // [1] boardWrite()
    public boolean boardWrite(BoardDto boardDto) {
        System.out.println("BoardService.boardWrite");
        System.out.println("boardDto = " + boardDto);

        boolean result = boardDao.boardWrite(boardDto);
        return result;
    } // func end

    // [2] boardPrint()
    public ArrayList<BoardDto> boardPrint() {
        System.out.println("BoardService.boardPrint");

        ArrayList<BoardDto> list = boardDao.boardPrint();
        return list;
    }

    // [3] boardFind()


    // [4] boardDelete()
    public boolean boardDelete(int bno) {
        System.out.println("bno = " + bno);
        System.out.println("BoardService.boardDelete");

        boolean result = boardDao.boardDelete(bno);
        return result;
    }

    // [5] boardUpdate()
    public boolean boardUpdate(BoardDto boardDto) {
        System.out.println("BoardService.boardUpdate");
        System.out.println("boardDto = " + boardDto);

    } // func end
} // class end
