package web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.model.dto.MemberDto;
import web.service.FileService;
import web.service.MemberService;
import web.service.PointLogService;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private PointLogService pointLogService;
    @Autowired
    private FileService fileService;

    // [1] 회원가입
    @PostMapping("/signup")
    public int signup(@ModelAttribute MemberDto memberDto) {
        System.out.println("MemberController.signup");
        System.out.println("memberDto = " + memberDto);
        // [1.1] Service에 메소드 실행
        int result = memberService.signup(memberDto);

        // [ 250820 수정 1 ] 회원가입 시, 1000point 부여 기능 수정
        if( result > 0 ) pointLogService.pointAssignment(result,1);

        // [ 250820 추가 2 ] 업로드 이미지 여부 확인 후 File 등록 처리
        // [1-(2)-1] 업로드 이미지 여부 확인 후 FileService 작업
        if( result > 0 && !memberDto.getUpload().isEmpty() ){
            // [mno가 정상적으로 발급 + getUpload가 존재]
            // [1-(2)-2] fileServer에 업로드 요청
            MultipartFile multipartFile = memberDto.getUpload();
            String fileName = fileService.fileUpload(multipartFile);

            // [1-(2)-3] file 관련 정보를 memberimg 에 저장
            memberDto.setMno(result);
            memberDto.setMimgname(fileName);
            boolean result2 = memberService.postMimg(memberDto);
        }
        // [1.2] mno를 반환
        return result;
    } // func end

    // [2] 로그인
    // [2.1] HttpServletRequest 매개변수 기재
    @PostMapping("/login")
    public int login(@RequestBody MemberDto memberDto, HttpServletRequest request) {
        System.out.println("memberDto = " + memberDto + ", request = " + request);
        System.out.println("MemberController.login");

        // [2.2] Session 정보 가져오기
        HttpSession session = request.getSession();

        // [2.3] service의 메소드 실행
        int result = memberService.login(memberDto);

        // [2.4] 로그인 성공 => result = mno 가 1 이상인 경우
        if (result >= 1) {
            // [2.4.1] Session 정보에 속성 추가
            session.setAttribute("loginMno", result);

            // [ 250820 수정 ] 로그인 시, 10point 부여 기능 수정
            pointLogService.pointAssignment(result,2);
        }
        // [2.5] 결과 반환
        return result;
    } // func end

    // [3] 로그아웃
    // [3.1] HttpServletRequest 매개변수 선언
    @GetMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        System.out.println("MemberController.logout");
        System.out.println("request = " + request);

        // [3.2] Servlet에서 session 객체 가져오기
        HttpSession session = request.getSession();

        // [3.3] 유효성 검사
        if (session == null || session.getAttribute("loginMno") == null) {
            return false; // 비로그인 상태 - 로그아웃 실패
        }
        // [3.4] session 객체 내의 mno 속성 값 초기화
        session.removeAttribute("loginMno");
        return true; // 로그인 성공
    } // func end

    // [4] 내 정보 조회
    // [4.1] HttpServletRequest 매개변수 선언
    @GetMapping("/info")
    public MemberDto info(HttpServletRequest request) {
        System.out.println("MemberController.info");
        System.out.println("request = " + request);

        // [4.2] servlet에서 session 객체 가져오기
        HttpSession session = request.getSession();

        // [4.3] 유효성 검사 - 비로그인상태 null
        if (session == null || session.getAttribute("loginMno") == null) {
            return null;
        }

        // [4.4] session 객체 내의 mno 속성 값 가져오기
        Object obj = session.getAttribute("loginMno");
        // [4.4.1] 타입변환
        int mno = (int) obj;

        // [4.5] service 메소드 실행
        MemberDto memberDto = memberService.info(mno);

        // [4-(1)-1] 최신 사진 이미지 fileName 불러오기 (※ 250820 추가)
        String fileName = memberService.getFileName(mno);
        memberDto.setMimgname(fileName);

        // [4.6] 결과 반환
        return memberDto;
    } // func end

    // [5] 중복검사
    @GetMapping("/check")
    public boolean check(@RequestParam String type, @RequestParam String data) {
        // [5.1] service 메소드 실행
        boolean result = memberService.check(type, data);
        // [5.2] 결과 반환
        return result;
    } // func end

    // [6] 회원 정보 수정
    @PutMapping("/update")
    public boolean update(@ModelAttribute MemberDto memberDto, HttpServletRequest request) {
        System.out.println("MemberController.update");
        System.out.println("memberDto = " + memberDto + ", request = " + request);

        // [6.1] Servlet 내의 session 가져오기
        HttpSession session = request.getSession();
        // [6.2] 유효성 검사
        if (session == null || session.getAttribute("loginMno") == null) {
            return false; //비로그인 상태
        }
        // [6.3] session의 mno 가져오기
        Object obj = session.getAttribute("loginMno");
        int mno = (int) obj;

        // [6.4] memberDto에 mno 삽입
        memberDto.setMno(mno);

        // [6.5] service 메소드 실행
        boolean result = memberService.update(memberDto);

        // [ 250820 추가 1 ] 업로드 이미지 여부 확인 후 File 등록 처리
        // [6-(1)-1] 업로드 이미지 여부 확인 후 FileService 작업
        if( result == true && !memberDto.getUpload().isEmpty() ){
            // [mno가 정상적으로 발급 + getUpload가 존재]
            // [6-(1)-2] fileServer에 업로드 요청
            MultipartFile multipartFile = memberDto.getUpload();
            String fileName = fileService.fileUpload(multipartFile);

            // [6-(1)-3] file 관련 정보를 memberimg 에 저장
            memberDto.setMno(mno);
            memberDto.setMimgname(fileName);
            boolean result2 = memberService.postMimg(memberDto);
        }

        // [6.6] 결과 반환
        return result;

    } //func end

    // [7] 비밀번호 수정
    @PutMapping("/update/password")
    public boolean updatePassword(@RequestBody Map<String, String> map, HttpServletRequest request) {
        System.out.println("MemberController.updatePassword");
        System.out.println("map = " + map + ", request = " + request);
        // [7.1] servlet 에서 session 추출
        HttpSession session = request.getSession();
        // [7.2] 유효성 검사
        if (session == null || session.getAttribute("loginMno") == null) {
            return false; // 비로그인 상태
        }
        // [7.3] session에서 mno 추출
        Object obj = session.getAttribute("loginMno");
        int mno = (int) obj;
        // [7.4] service의 메소드 실행
        boolean result = memberService.updatePassword(mno, map);
        // [7.5] 성공시 session 객체 내의 mno 속성 값 초기화
        if (result == true) session.removeAttribute("loginMno");
        // [7.6] 결과 반환
        return result;
    } // func end

    // [8] 회원 탈퇴
    @DeleteMapping("/delete")
    public boolean delete(String mpwd, HttpServletRequest request) {
        System.out.println("mpwd = " + mpwd + ", request = " + request);
        System.out.println("MemberController.delete");

        // [8.1] servlet에서 session 추출
        HttpSession session = request.getSession();
        // [8.2] 유효성 검사
        if (session == null || session.getAttribute("loginMno") == null) {
            return false;
        }
        // [8.3] session에서 mno 추출
        Object obj = session.getAttribute("loginMno");
        int mno = (int) obj;
        // [8.4] service의 메소드 실행
        boolean result = memberService.delete(mno, mpwd);
        // [8.5] 성공시 session 객체 내의 mno 속성 값 초기화
        if (result == true) session.removeAttribute("loginMno");
        // [8.6] 결과 반환
        return result;
    } // func end

    // [9] 아이디 찾기
    // 입력: 이름, 연락처
    // 처리: 이름+연락처 일치 시 아이디 반환
    // 불일치 시 "회원정보 없음" 메시지
    @GetMapping("/findID")
    public String findMid(@RequestParam String mname, @RequestParam String mphone) {
        System.out.println("MemberController.findMid");
        System.out.println("mname = " + mname + ", mphone = " + mphone);
        String mid = memberService.findMid(mname, mphone);
        return mid;
    }

    // [10] 비밀번호 찾기
    // 입력: 아이디, 연락처
    // 처리: 아이디+연락처 일치 시 새로운 난수 비밀번호 생성 후 반환
    // 생성된 비밀번호를 DB에 업데이트(임시 비밀번호로 사용)
    @GetMapping("/findPwd")
    public String findMpwd(@RequestParam String mid, @RequestParam String mphone) {
        System.out.println("MemberController.findMpwd");
        System.out.println("mid = " + mid + ", mphone = " + mphone);

        // [10.1] 난수 발생, 6자리 난수 발생
        String ranStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String randPwd = "";
        for (int i = 1; i <= 6; i++) {
            Random random = new Random();
            int strlen= ranStr.length();
            int ranIndex = random.nextInt( strlen );
            char c = ranStr.charAt(ranIndex);
            randPwd += c;
        }

        // [10.2] service의 메소드 실행
        String result = memberService.findMpwd(mid, mphone, randPwd);
        return result;
    }//func end


    

} // class end
