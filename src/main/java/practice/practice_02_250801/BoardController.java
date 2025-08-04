package practice.practice_02_250801;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController                 //RestController annotation 선언
public class BoardController {

    ArrayList<BoardDto> list = new ArrayList<>();

    // [ 1 ] Post - boardWrite

    @PostMapping("/board")
    public boolean boardWrite(@RequestBody BoardDto boardDto) {
        System.out.println("BoardController.boardWrite");
        System.out.println("boardDto = " + boardDto);

        boolean result = false;
        int bno = 0;
        if(list.size() == 0 ){
            bno = 1;
        } else {
            bno = list.get(list.size()-1).getBno() +1  ; ;
        }
        boardDto.setBno(bno);

        list.add(boardDto);
        result = true;

        return result;
        // 수정 필요
    } //func end

    // [ 2 ] Get - boardPrint
    @GetMapping("/board")
    public ArrayList<BoardDto> boardPrint() {
        System.out.println("BoardController.boardPrint");

        return list;
    }//func end

    // [ 3 ] get - boardDetail
    @GetMapping("/board/detail")
    public BoardDto boardDetail(int bno) {
        System.out.println("BoardController.boardDetail");
        System.out.println("bno = " + bno);

        BoardDto result = null;
        for(BoardDto boardDto : list){
            if( boardDto.getBno() == bno ){
                result = boardDto;
            }
        }
        return result;
    } //func end

    // [ 4 ] delete - boardDelete
    @DeleteMapping("/board")
    public boolean boardDelete(int bno){
        System.out.println("BoardController.boardDelete");
        System.out.println("bno = " + bno);

        boolean result = false;

        for(BoardDto boardDto : list){
            if( boardDto.getBno() == bno ){
                list.remove(bno-1);
                result = true;
                break;
            }
        }
        return result;
    }

    // [ 5 ] put - boardUpdate
    @PutMapping("/board")
    public boolean boardUpdate(@RequestBody BoardDto boardDto){
        System.out.println("BoardController.boardUpdate");
        System.out.println("boardDto = " + boardDto);

        boolean result = false;

        for(BoardDto boardDto0 : list){
            if( boardDto0.getBno() == boardDto.getBno() ){
                boardDto0.setBcontent(boardDto.getBcontent());
                boardDto0.setBwriter(boardDto0.getBcontent());
                result = true;
                break;
            }
        }
        return result;
    }//func end


} // class end
