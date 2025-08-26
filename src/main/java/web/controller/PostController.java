package web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.model.dto.PageDto;
import web.model.dto.PostDto;
import web.service.PostService;

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

} // class end
