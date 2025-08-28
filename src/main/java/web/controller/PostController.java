package web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.model.dto.PageDto;
import web.model.dto.PostDto;
import web.service.PostService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor // final 변수에 대하여 자동 생성자 주입
public class PostController {

    private final PostService postService;

    // [1] 게시물 등록
    @PostMapping("")
    public int writePost(@RequestBody PostDto postDto, HttpSession session) {
        System.out.println("PostController.writePost");
        System.out.println("postDto = " + postDto + ", session = " + session);

        // [1.1] 로그인 상태 확인
        Object login = session.getAttribute("loginMno");
        if (login == null) return 0;
        // [1.2] 비로그인 시 등록 실패 처리
        int mno = (int) login;
        // [1.3] 로그인 시, loginMno를 dto에 삽입
        postDto.setMno(mno);
        // [1.4] service 호출·결과 반환
        return postService.writePost(postDto);
    } // func end

    // [2] 카테고리 별 게시물 조회
    // defaultValue : QueryString 입력 값이 없을 경우 대비, 초기 값을 설정
    @GetMapping("")
    public PageDto findAllPost(@RequestParam(defaultValue = "1") int cno, 
                               @RequestParam(defaultValue = "1") int page, 
                               @RequestParam(defaultValue = "5") int count, 
                               // 250826 검색기능 추가를 위해, 하기 2개의 매개변수를 추가
                               @RequestParam(required = false) String key, 
                               @RequestParam(required = false) String keyword) {
        return postService.findAllPost(cno, page, count, key, keyword);
    } // func end

    // 250827 추가
    // [3] 개별 조회
    @GetMapping("/view")
    public PostDto getPost(@RequestParam int pno, HttpSession session) {
        // [3.1] Session 관련
        // [3.1.1] sessiono에서 viewHistory 추출
        // viewHistory : 클라이언트가 조회한 게시물 정보 저장소
        Object object = session.getAttribute("viewHistory");

        // [3.1.2] viewHistory 존재하지 않으면 HashMap 생성 / 존재하면 session에서 가져온 object를 map 으로 타입변환
        Map<Integer, String> viewHistory;
        if(object == null ){
            viewHistory = new HashMap<>();
        } else {
            viewHistory = (Map<Integer, String>) object;
        }

        // [3.1.3] 오늘의 날짜
        String today = LocalDate.now().toString(); // 현재 날짜를 반환
        // [3.1.4] pno와 today를 조합하여, 조회 기록을 체크
        String check = viewHistory.get(pno);
        if( check == null || !check.equals(today) ){
            // 조건 : 24시간 이내에 조회수가 1 만 증가 가능 (사용자 구분없이 1개의 브라우저에 대해 무조건 하루에 1)
            // [3.2] 조회수 증가
            postService.incrementView(pno);

            // [3.1.5] Session에 조회수 기록
            viewHistory.put(pno, today);
            // [3.1.6] session 속성 업데이터
            session.setAttribute("viewHistory",viewHistory);
        }
        
        // [3.3] 요청자가 작성자 본인인지 확인을 위환 session 꺼내오기
        Object object2 = session.getAttribute("loginMno");
        int loginMno = object2==null ? 0 : (int)object2 ;

        // [3.4] postDto 반환 func 실행 + 요청자 본인 확인
        PostDto postDto = postService.getPost(pno);
        if( postDto.getMno() == loginMno) postDto.setHost(true);

        return postDto;

    } // func end

    // [4] 게시물 삭제
    @DeleteMapping()
    public boolean deletePost(@RequestParam int pno, HttpSession session){

        // [4.1] 본인이 작성한 글인지 확인
        // [4.1.1] session 정보 출력
        Object obj = session.getAttribute("loginMno");
        int loginMno = obj==null?0:(int)obj;

        // [4.1.2] 삭제하려는 게시물의 mno 확인
        PostDto postDto = postService.getPost(pno);
        if(postDto.getMno() == loginMno ){
            // [4.1.3] 게시물 작성 mno와 로그인mno가 일치하면 삭제를 실행
            return postService.deletePost(pno);
        } else {
            return false;
        }
    } // func end

    // [5] 게시물 수정
    @PutMapping()
    public int updatePost(@RequestBody PostDto postDto, HttpSession session){
        Object obj = session.getAttribute("loginMno");
        int loginMno = obj==null?0:(int)obj;
        return postService.updatePost(postDto, loginMno);
    } // func end

    // 250828 추가
    // [6] 댓글 등록
    @PostMapping("/reply")
    public int writeReply(@RequestBody Map<String, String> reply, HttpSession session){
        if(session.getAttribute("loginMno") == null )return 0;
        Object obj = session.getAttribute("loginMno");
        String loginMno = obj + "" ;
        reply.put("mno", loginMno);

        return postService.writeReply(reply);
    } // func end

    // [7] 댓글 조회
    // 조회중인 게시물(pno)의 댓글을 전체 조회
    @GetMapping("reply")
    public List<Map<String, String>> findAllReply(@RequestParam int pno){
        return postService.findAllReply(pno);
    } // func end

} // class end
