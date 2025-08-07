package practice.practice_04_250807.controller;

import org.springframework.web.bind.annotation.*;
import practice.practice_04_250807.model.dao.WaitingDao;
import practice.practice_04_250807.model.dto.WaitingDto;

import java.util.ArrayList;

@RestController
@RequestMapping("/waiting")
public class WaitingController {

    // dao 싱글톤 호출
    private WaitingDao waitingDao = WaitingDao.getInstance();

    // [1] 대기번호 등록
    @PostMapping("/regi")
    public boolean waitingRegi(@RequestBody  WaitingDto waitingDto){
        // [1.1] dao에 메소드 실행 및 매개변수 전달
        boolean result = waitingDao.waitingRegi(waitingDto);
        // [1.2] 결과 반환
         return result;
    } // func end

    // [2] 대기번호 조회
    @GetMapping("/print")
    public ArrayList<WaitingDto> waitingPrint(){
        // [2.1] dao에 메소드 실행
        ArrayList<WaitingDto> list = waitingDao.waitingPrint();
        // [2.2] 결과 반환
        return list;
    } // func end

} // class end
