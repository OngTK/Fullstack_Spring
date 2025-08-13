package web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // [1] 회원가입
    @PostMapping("/signup")
    public int signup(@RequestBody MemberDto memberDto){
        System.out.println("MemberController.signup");
        System.out.println("memberDto = " + memberDto);
        // [1.1] Service에 메소드 실행
        int result = memberService.signup(memberDto);

        // [1.2] mno를 반환
        return result;
    } // func end

    // [2] 로그인
    // [2.1] HttpServletRequest 매개변수 기재
    @PostMapping("/login")
    public int login(@RequestBody MemberDto memberDto, HttpServletRequest request){
        System.out.println("memberDto = " + memberDto + ", request = " + request);
        System.out.println("MemberController.login");

        // [2.2] Session 정보 가져오기
        HttpSession session = request.getSession();

        // [2.3] service의 메소드 실행
        int result = memberService.login(memberDto);

        // [2.4] 로그인 성공 => result = mno 가 1 이상인 경우
        if( result >= 1 ){
            // [2.4.1] Session 정보에 속성 추가
            session.setAttribute("loginMno",result);
        }
        // [2.5] 결과 반환
        return result;
    } // func end

    // [3] 로그아웃
    // [3.1] HttpServletRequest 매개변수 선언
    @GetMapping("/logout")
    public boolean logout(HttpServletRequest request){
        // [3.2] Servlet에서 session 객체 가져오기
        HttpSession session = request.getSession();

        // [3.3] 유효성 검사
        if (session == null || session.getAttribute("loginMno") == null){
            return false; // 비로그인 상태 - 로그아웃 실패
        }
        // [3.4] session 객체 내의 mno 속성 값 초기화
        session.removeAttribute("loginMno");
        return true; // 로그인 성공
    } // func end

} // class end
