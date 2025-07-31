package practice.practice_01_250731;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BoardController {

    //        Spring 실습1 : 게시판 RestController 만들기
//        1. AppStart 클래스 생성
//        2. BoardController 클래스 생성
//        3. 각 URL 매핑 만들기
//
//        1. 글쓰기           POST       "/exam1/board"
//        요청자료 : x , 응답자료 : true/false
    @PostMapping("/exam1/board")
    @ResponseBody
    public boolean methodPost() {
        System.out.println("BoardController.method");
        return false;
    }//func end

//        2. 전체 글 조회      GET        "/exam1/board"
//        요청자료 : x , 응답자료 : 임의의 List 타입 ,  [ {bno:'',btitle:''} ,  {bno:'',btitle:''}  ] ,
//        아래 참고
    @GetMapping("/exam1/board")
    @ResponseBody
    public ArrayList<BoardDto> methodGet(){
        System.out.println("BoardController.methodGet");
        ArrayList<BoardDto> list = new ArrayList<>();
        list.add(new BoardDto());
        list.add(new BoardDto());
        return list;
    } // func end

//        3. 개별 글 조회      GET        "/exam1/board/view"
//        요청자료 : x , 응답자료 : 임의의 MAP  타입  ,  {bno:'',btitle:''} , 아래 참고
    @GetMapping("/exam1/board/view")
    @ResponseBody
    public Map<Integer, String> methodGet2(){
        System.out.println("BoardController.methodGet2");
        Map<Integer, String> map = new HashMap<>();
        map.put(1,"test1");
        map.put(2,"test2");
        return map;
    }

//        4. 개글 글 수정      PUT        "/exam1/board"
//        요청자료 : x , 응답자료 : true 또는 false
    @PutMapping("/exam1/board")
    @ResponseBody
    public boolean methodPut(){
        System.out.println("BoardController.methodPut");
        return true;
    }

//        5. 개별 글 삭제      DELETE     "/exam1/board"
//        요청자료 : x , 응답자료 : 임의의 삭제한 번호 , 3
    @DeleteMapping("/exam1/board")
    @ResponseBody
    public int methodDelete(){
        System.out.println("BoardController.methodDelete");
        int deleteNo = 3;
        return deleteNo;
    }


//        제출 :
//        1. 카카오톡방에 ip 제출 ( 강사가 체크 할때 까지 서버 켜주세요
//        http://192.168.40.205:8080
//        2. itdanja.com에 과제 코드 git 링크 제출

} // class end
