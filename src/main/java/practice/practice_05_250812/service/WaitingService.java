package practice.practice_05_250812.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.practice_05_250812.model.dao.WaitingDao;
import practice.practice_05_250812.model.dto.WaitingDto;

import java.util.ArrayList;

@Service                    // [0.1] Service Annotation 선언
public class WaitingService {

    // [0.2] dao Repository 불러오기
    @Autowired
    private WaitingDao waitingDao;

    // [1] 대기 현황 등록 기능 , /waiting/write.jsp
    // 조건 : 대기번호는 자동부여 되며 연락처 와 인원수만 입력받아 저장합니다.
    public boolean waitingWrite(WaitingDto waitingDto) {
        System.out.println("WaitingService.waitingWrite");
        System.out.println("waitingDto = " + waitingDto);

        // [1.1] dao의 func 실행
        boolean result = waitingDao.waitingWrite(waitingDto);
        // [1.2] 결과 반환
        return result;
    } // func end

    // [2] 대기 현황 전체 조회 기능 , /waiting/list.jsp
    //조건 : 모든 대기 현황의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
    public ArrayList<WaitingDto> waitingPrint() {
        System.out.println("WaitingService.waitingPrint");

        // [2.1] dao의 func 실행
        ArrayList<WaitingDto> list = waitingDao.waitingPrint();
        // [2.2] 결과 반환
        return list;
    } // func end

    // [3] 특정 대기 현황 조회 기능 ,  /waiting/view.jsp
    //조건 : 선택한 대기번호의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
    public WaitingDto waitingView(int wno) {
        System.out.println("WaitingService.waitingView");
        System.out.println("wno = " + wno);

        // [3.1] dao의 func 실행
        WaitingDto waitingDto = waitingDao.waitingView(wno);
        // [3.2] 결과 반환
        return waitingDto;
    } // func end

    // [4] 특정 대기 현황 삭제 기능  , /waiting/view.jsp포함됩니다.
    //조건 : 선택한 대기번호의 정보를 삭제합니다.
    public boolean waitingDelete(int wno) {
        System.out.println("WaitingService.waitingDelete");
        System.out.println("wno = " + wno);

        // [4.1] dao 실행
        boolean result = waitingDao.waitingDelete(wno);
        // [4.2] 결과 반환
        return result;
    } // func end

    // [5] 특정 대기 현황 수정 기능 , /waiting/update.jsp
    //조건1 : 선택한 대기번호의 연락처 와 인원수 를 출력합니다.
    //조건2 : 새로운 연락처 와 인원수 입력받아 선택한 대기번호 정보를 수정합니다.
    public boolean waitingUpdate(WaitingDto waitingDto) {
        System.out.println("waitingDto = " + waitingDto);
        System.out.println("WaitingService.waitingUpdate");

        // [5.1] dao 실행
        boolean result = waitingDao.waitingUpdate(waitingDto);
        // [5.2] 결과 반환
        return result;
    } // func end

} // class end
