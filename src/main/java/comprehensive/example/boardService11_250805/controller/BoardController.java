package comprehensive.example.boardService11_250805.controller;

import comprehensive.example.boardService11_250805.model.dao.BoardDao;
import comprehensive.example.boardService11_250805.model.dto.BoardDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        // TO-BE : @RequestBody 로 HTTP Body를 통해 객체 생성 단계 생략! ☆★☆★☆★

        // 3. 객체화 된 dto를 dao에게 전달후 결과를 받는다.
        boolean result = boardDao.boardWrite( boardDto );
        // 4. 결과를 view에게 리턴한다.
        return result;
    }

} // class end
