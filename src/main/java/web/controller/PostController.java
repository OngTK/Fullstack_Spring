package web.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.PostDto;
import web.service.PostService;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor // final 변수에 대하여 자동 생성자 주입
public class PostController {

    private final PostService postService;

    // [1] 게시물 등록
    @PostMapping
    public int writePost(@RequestBody PostDto postDto, HttpSession session) {
        System.out.println("PostController.writePost");
        System.out.println("postDto = " + postDto + ", session = " + session);

        // [1.1] 로그인 상태 확인
        Object login = session.getAttribute("loginMno");
        if(login == null ) return 0 ;
        // [1.2] 비로그인 시 등록 실패 처리
        int mno = (int)login;
        // [1.3] 로그인 시, loginMno를 dto에 삽입
        postDto.setMno(mno);
        // [1.4] service 호출·결과 반환
        return postService.writePost(postDto);
    } // func end


} // class end
