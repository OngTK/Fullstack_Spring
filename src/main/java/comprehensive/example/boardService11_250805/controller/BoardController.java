package comprehensive.example.boardService11_250805.controller;

import comprehensive.example.boardService11_250805.model.dao.BoardDao;
import comprehensive.example.boardService11_250805.model.dto.BoardDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController // 싱글톤 대체
public class BoardController {

    // boardDao 싱글톤 가져오기
    private BoardDao boardDao = BoardDao.getInstance();

    // (1) 등록 기능 구현
    @PostMapping("/board")  //☆★☆★☆★☆★
    public boolean boardWrite(@RequestBody BoardDto boardDto){

//        System.out.println("BoardController.boardWrite");
//        System.out.println("boardDto = " + boardDto);

        // 1~2.
        // AS-IS : 매개변수로 bcontent, bwriter을 받아 BoardDto 객체 생성
        // TO-BE : @RequestBody 로 HTTP Body를 통해 객체 생성! ☆★☆★☆★

        // 3. 객체화 된 dto를 dao에게 전달후 결과를 받는다.
        boolean result = boardDao.boardWrite( boardDto );
        // 4. 결과를 view에게 리턴한다.
        return result;
    }

    // (2) 전체조회 기능 구현
    @GetMapping("/board")  //☆★☆★☆★☆★
    public ArrayList<BoardDto> boardPrint( ){
        // - 유효성검사 ~ // - 매개변수 ~
        // 3. dao에게 전달후 결과를 받는다.
        ArrayList<BoardDto> result = boardDao.boardPrint( );
        // 4. 결과를 view에게 리턴한다.
        return result;
    }

    // (3) 삭제 기능 구현
    @DeleteMapping("/board")  //☆★☆★☆★☆★
    public boolean boardDelete( @RequestParam int bno ){
        // 1.유효성검사2.객체화
        // 3. dao에게 삭제할번호 전달후 결과를 받는다.
        boolean result = boardDao.boardDelete( bno );
        // 4. 결과를 view에게 리턴한다.
        return result;
    }

    // (4) 수정 기능 구현
    @PutMapping("/board")  //☆★☆★☆★☆★
    public boolean boardUpdate( @RequestBody BoardDto boardDto ){
        // 1.유효성검사
        // 2.객체화<선택, 속성이 2개이상 이면서 하나의 dto 표현 가능하면 권장>
        //        BoardDto boardDto = new BoardDto( bno , bcontent , null );
        // AS-IS : 매개변수로 bno, bcontent 받아 BoardDto 객체 생성
        // TO-BE : @RequestBody 로 HTTP Body를 통해 객체 생성! ☆★☆★☆★

        // 3. dao에게 삭제할번호 전달후 결과를 받는다.
        boolean result = boardDao.boardUpdate( boardDto );
        // 4. 결과를 view에게 리턴한다.
        return result;
    }

} // class end
