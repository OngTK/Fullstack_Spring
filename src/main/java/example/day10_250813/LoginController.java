package example.day10_250813;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/day10")
public class LoginController {
    
    // [1] 로그인
    // 1) id와 pw가 일치하면, 로그인 성공 > 세션에 회원번호를 저장

    @PostMapping("/login")          // 로그인은 보안적인 중요정보를 다루기에 QueryString 보다는 Body로 통신
    public boolean login(@RequestBody LoginDto loginDto , HttpServletRequest request){
        // [1.1] HttpServletRequest
        //      HTTP 요청 정보를 갖는 객체
        
        // 관련 메소드
        System.out.println(request.getRemoteAddr());                            // 요청 클라이언트의 IP를 반환
        System.out.println(request.getRemotePort());                            // 요청 클라이언트의 PORT를 반환
        System.out.println(request.getHeader("User-Agent"));              // 요청 클라이언트의 브라우저 정보(HTTP Header·통신 부가정보)를 반환
                                                                                // talend 에서는 null로 뜸
        // [1.2] ☆★☆★ Session 정보 가져오기
        // Session은 key-Value의 Map 컬렉션 사용
        HttpSession session = request.getSession();
        System.out.println(session.getId());                                    // session 식별 번호 : 브라우저·PC마다 다르게 할당_사용자 별 개인 저장소 = 공유 X
        System.out.println(session.getCreationTime());                          // 세션 생성시간 (단위 milli-sec)
        System.out.println(session.getLastAccessedTime());                      // 세션의 마지막 접근 시간 (단위 milli-sec)
        System.out.println(session.getMaxInactiveInterval());                   // 세션의 최대 유효시간 (단위 sec)

        // [1.3] ☆★☆★ Session 속성 저장하기
        session.setAttribute("loginMno",3);                         // .setAttribute("속성명",속성값); : Map 형태

        return true;
    } // func end

    // [2] login 정보 가져오기
    // session의 저장된 회원정보 확인
    @GetMapping("/info")
    public boolean info(HttpServletRequest request){
        // [2.1] 서블릿에서 세션정보 꺼내오기
        HttpSession session = request.getSession();

        // [2.2] 세션정보 내 원하는 속성값 꺼내기,
        // 무조건 Object 타입
        Object obj = session.getAttribute("loginMno");
        if(obj == null) {
            System.out.println("비로그인 상태");
            return false;
        }; // 비로그인 상태

        // [2.3] 타입변환
        int loginMno = (int) obj;
        System.out.println(loginMno);

        return true;
    }

    // [3] 로그아웃
    // 세션 정보 내 속성 제거하기
    @GetMapping("/logout")
    public boolean logout(HttpServletRequest request){
        // [3.1] 세션 정보 가져오기
        HttpSession session = request.getSession();

        // [3.2] 특정 속성 제거하기
        session.removeAttribute("loginMno");

        // [3.2] 전체 속성 제거하기
        // session.invalidate();

        return true;
    }


} // class end
