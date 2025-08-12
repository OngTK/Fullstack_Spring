package practice.practice_05_250812.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import practice.practice_05_250812.model.dto.WaitingDto;
import practice.practice_05_250812.service.WaitingService;

import java.util.ArrayList;

@RestController                 // [0.1] RestController Annotation 선언
@RequestMapping("/waiting")     // [0.3] 공통 Mapping URL 정의
public class WaitingController {

    // [0.2] Service 불러오기
    @Autowired
    private WaitingService waitingService;

    // [1] 대기 현황 등록 기능 , /waiting/write.jsp
    // 조건 : 대기번호는 자동부여 되며 연락처 와 인원수만 입력받아 저장합니다.
    @PostMapping
    public boolean waitingWrite(@RequestBody WaitingDto waitingDto) {
        System.out.println("WaitingController.waitiongWrite");
        System.out.println("waitingDto = " + waitingDto);

        // [1.1] service의 메소드 실행
        boolean result = waitingService.waitingWrite(waitingDto);
        // [1.2] 결과 반환
        return result;
    } //func end

    // [2] 대기 현황 전체 조회 기능 , /waiting/list.jsp
    //조건 : 모든 대기 현황의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
    @GetMapping
    public ArrayList<WaitingDto> waitingPrint() {
        System.out.println("WaitingController.waitingPrint");

        // [2.1] service 메소드 실행
        ArrayList<WaitingDto> list = waitingService.waitingPrint();
        // [2.2] 결과 반환
        return list;
    } // func end

    // [3] 특정 대기 현황 조회 기능 ,  /waiting/view.jsp
    //조건 : 선택한 대기번호의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
    @GetMapping("/view")
    public WaitingDto waitingView(int wno) {
        System.out.println("WaitingController.waitingView");
        System.out.println("wno = " + wno);

        // [3.1] service 메소드 실행
        WaitingDto waitingDto = waitingService.waitingView(wno);
        // [3.2] 결과 반환
        return waitingDto;
    } // func end

    // [4] 특정 대기 현황 삭제 기능  , /waiting/view.jsp포함됩니다.
    //조건 : 선택한 대기번호의 정보를 삭제합니다.
    @DeleteMapping
    public boolean waitingDelete(int wno) {
        System.out.println("WaitingController.waitingDelete");
        System.out.println("wno = " + wno);

        // [4.1] service 실행
        boolean result = waitingService.waitingDelete(wno);
        // [4.2] 결과 반환
        return result;
    } // func end

    // [5] 특정 대기 현황 수정 기능 , /waiting/update.jsp
    //조건1 : 선택한 대기번호의 연락처 와 인원수 를 출력합니다.
    //조건2 : 새로운 연락처 와 인원수 입력받아 선택한 대기번호 정보를 수정합니다.
    @PutMapping
    public boolean waitingUpdate(@RequestBody WaitingDto waitingDto) {
        System.out.println("WaitingController.waitingUpdate");
        System.out.println("waitingDto = " + waitingDto);
        
        // [5.1] service 메소드 실행
        boolean result = waitingService.waitingUpdate(waitingDto);
        // [5.2] 결과 반환
        return result;
    }// func end


} // class end


