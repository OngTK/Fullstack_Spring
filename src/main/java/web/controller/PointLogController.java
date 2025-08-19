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
