package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        // [1.1] Service에 메소드 실행
        int result = memberService.signup(memberDto);

        // [1.2] mno를 반환
        return result;
    }
}
