package practice.practice_03_250805.controller;

import org.springframework.web.bind.annotation.*;
import practice.practice_03_250805.model.dao.WaitingDao;
import practice.practice_03_250805.model.dto.WaitingDto;


import java.util.ArrayList;

@RestController
public class WaitingController {

    // dao 싱글톤 호출
    private WaitingDao waitingDao = WaitingDao.getInstance();

    // 메소드 =============================================
    // [1] 대기 등록 / waitingRegi()
    // 매개변수 : WaitingDto waitingDto
    // 반환 : boolean
    @PostMapping("/waiting")
    public boolean waitingRegi(@RequestBody WaitingDto waitingDto) {
        System.out.println("waitingDto = " + waitingDto);
        // [1.1] dto에 waitingRegi 실행
        boolean result = waitingDao.waitingRegi(waitingDto);
        // [1.2] 결과 반환
        return result;
    } //func end

    // [2] 대기 전체 조회 / waitingPrint()
    // 매개변수 : -
    // 반환 : ArrayList<WaitingDto>
    @GetMapping("/waiting")
    public ArrayList<WaitingDto> waitingPrint() {
        // [2.1] dao에 waitingPrint 메소드 실행
        ArrayList<WaitingDto> waitingList = waitingDao.waitingPrint();
        // [2.2] 결과 반환
        return waitingList;
    } //func end

    // [3] 대기 취소 / waitingDelete()
    // 매개변수 : int wno
    // 반환 : boolean
    @DeleteMapping("/waiting")
    public boolean waitingDelete( @RequestParam int wno) {
        // [3.1] dao의 waitingDelete 메소드 실행
        boolean result = waitingDao.waitingDelete(wno);
        // [3.2] 결과 반환
        return result;
    } //func end

    // [4] 대기 정보 수정 / waitingUpdate()
    // 매개변수 : WaitingDto waitingDto
    // 반환 : boolean
    @PutMapping("/waiting")
    public boolean waitingUpdate(@RequestBody WaitingDto waitingDto) {
        System.out.println("waitingDto = " + waitingDto);
        //[4.1] dao의 waitingUpdate 실행
        boolean result = waitingDao.waitingUpdate(waitingDto);
        //[4.2] 결과 반환
        return result;
    } // func end

} // class end