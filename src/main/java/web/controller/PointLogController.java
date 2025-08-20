package web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.PointLogDto;
import web.service.PointLogService;

import java.util.List;


@RestController
@RequestMapping("/point")
public class PointLogController {


    @Autowired
    private PointLogService pointLogService;

    // [1] 회원가입, 로그인 시 포인트 부여
    // membercontroller에서 회원가입/로그인 처리 후 마지막에
    // 매개변수를 회원가입은 (mno, 1), 로그인은 (mno, 2)를 반환
    public boolean pointAssignment(int mno, int reason) {
        System.out.println("PointLogController.pointAssignment");
        System.out.println("mno = " + mno + ", reason = " + reason);

        String plcomment = "";
        int plpoint = 0;
        if(reason == 1 ){           // reason 1 : 회원가입
            plcomment = "회원가입";
            plpoint = 1000;
        } else if (reason == 2) {   // reason 2 : 로그인
            plcomment = "로그인";
            plpoint = 10;
        }
        PointLogDto pointLogDto = new PointLogDto();
        pointLogDto.setMno(mno);
        pointLogDto.setPlpoint(plpoint);
        pointLogDto.setPlcomment(plcomment);

        boolean result = pointLogService.pointAssignment(pointLogDto);
        return result;
    } // func end

    // [2] mno 별 포인트 이력 출력
    @GetMapping("/pointlog")
    public List<PointLogDto> mnoPointLog(HttpSession session) {
        System.out.println("PointLogController.mnoPointLog");
        System.out.println("session = " + session);

        // [2.1] 로그인 여부 검사
        if( session.getAttribute("loginMno")==null) return null;

        // [2.2] session 에서 loginMno 가져오기
        int mno = (int)session.getAttribute("loginMno");

        // [2.3] controller 의 메소드 실행
        List<PointLogDto> list = pointLogService.mnoPointLog(mno);
        return list;
    } // func end

        // [3] mno 별 포인트 합산
    @GetMapping("/totalpoint")
    public int mnoPoint(HttpSession session){
        System.out.println("PointLogController.mnoPoint");
        System.out.println("session = " + session);

        // [3.1] 로그인 여부 검사
        if( session.getAttribute("loginMno")==null) return 0;

        // [3.2] session 에서 loginMno 가져오기
        int mno = (int)session.getAttribute("loginMno");

        // [3.3] controller 의 메소드 실행
        int result = pointLogService.mnoPoint(mno);
        return result;
    } // func end

} // class end
