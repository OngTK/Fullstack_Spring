package comprehensive.qaTest.controller;

import comprehensive.qaTest.model.dto.MemberDto;
import comprehensive.qaTest.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // [1] 회원 정보 등록 memberCreate()
    @PostMapping
    public boolean memberCreate(@RequestBody MemberDto memberDto) {
        boolean result = memberService.memberCreate(memberDto);
        return result;
    }//func end

    // [2] 회원 정보 전체조회 memberRead()
    @GetMapping
    public ArrayList<MemberDto> memberRead(){
        ArrayList<MemberDto> list = memberService.memberRead();
        return list;
    } // func end

    // [3] 회원 정보 개별조회 memberView()
    @GetMapping("/view")
    public MemberDto memberView(int custNo){
        MemberDto memberDto = memberService.memberView(custNo);
        return memberDto;
    } //func end

    // [4] 회원 정보 수정 memberUpdate()
    @PutMapping
    public boolean memberUpdate(@RequestBody MemberDto memberDto){
        boolean result = memberService.memberUpdate(memberDto);
        return result;
    } // func end

    // [5] 회원 정보 삭제 memberDelete()
    @DeleteMapping
    public boolean memberDelete(int custNo){
        boolean result = memberService.memberDelete(custNo);
        return result;
    } // func end

    // [6] max custNO 추출
    @GetMapping("/custNo")
    public int maxCustNo(){
        int result = memberService.maxCustNo();
        return result;
    }//func end

} // class end
