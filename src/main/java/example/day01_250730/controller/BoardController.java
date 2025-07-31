package example.day01_250730.controller;

import example.day01_250730.model.dao.BoardDao;
import example.day01_250730.model.dto.BoardDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController //컨트롤러 선언
public class BoardController {
    // 싱글톤 =======================================
    private BoardController() {
    }

    ;
    private static final BoardController instance = new BoardController();

    public static BoardController getInstance() {
        return instance;
    }

    // Dao 싱글톤 호출  =======================================
    private BoardDao boardDao = BoardDao.getInstance();

    // 메소드 =======================================

    //[1] 등록 기능 구현
    @PostMapping
    public boolean boardWrite(String bcontent, String bwriter) {
        //[1.1] 유효성 검사 (생략)

        //[1.2] 매개변수로 객체 만들기
        BoardDto boardDto = new BoardDto(0, bcontent, bwriter); //bno에 0을 넣어도 DB 등록 시, bno를 사용하지 않으므로 문제 없음!

        //[1.3] Dto를 Dao에게 전달
        boolean result = boardDao.boardWrite(boardDto);

        //[1.4] view에 결과 반환
        if (result) {
            return true;
        }
        return false;
    } // func end

    //[3] 전체조회 기능 구현
    @GetMapping
    public ArrayList<BoardDto> boardPrint() {
        //[3.1] dao의 전체조회 기능 실행
        ArrayList<BoardDto> list = boardDao.boardPrint();
        //[3.2] view에 결과 반환
        return list;
    } //func end

} // class end
