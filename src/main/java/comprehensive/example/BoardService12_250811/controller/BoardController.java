package comprehensive.example.BoardService12_250811.controller;

import comprehensive.example.BoardService12_250811.model.dto.BoardDto;
import comprehensive.example.BoardService12_250811.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController                 // [0.1] RestController Annotation
@RequestMapping("/board")       // [0.2] RequestMapping 으로 HTTP URL 선언
public class BoardController {


    @Autowired  // [0.3] Service 호출 Annotation
    private BoardService boardService;

    // [1] boardWrite()
    @PostMapping
    public boolean boardWrite(@RequestBody BoardDto boardDto) {
        System.out.println("BoardController.boardWrite");
        System.out.println("boardDto = " + boardDto);

        boolean result = boardService.boardWrite(boardDto);
        return result;
    } // func end

    // [2] boardPrint()
    @GetMapping
    public ArrayList<BoardDto> boardPrint() {
        System.out.println("BoardController.boardPrint");

        ArrayList<BoardDto> list = boardService.boardPrint();
        return list;
    } // func end

    // [3] boardFind()


    // [4] boardDelete()
    @DeleteMapping
    public boolean boardDelete(int bno) {
        System.out.println("bno = " + bno);
        System.out.println("BoardController.boardDelete");

        boolean result = boardService.boardDelete(bno);
        return result;
    } //func end

    // [5] boardUpdate()
    public boolean boardUpdate(BoardDto boardDto) {
        System.out.println("boardDto = " + boardDto);
        System.out.println("BoardController.boardUpdate");

    } // func end
} // class end
