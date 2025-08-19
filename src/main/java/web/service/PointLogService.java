package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.PointLogDao;
import web.model.dto.PointLogDto;

import java.util.List;

@Service
public class PointLogService {

    @Autowired
    private PointLogDao pointLogDao;

    // [1] 포인트 지급
    // 회원가입(1) 시, 1000 point 지급
    // 로그인(2) 시, 10 point
    public boolean pointAssignment(int mno, int reason) {
        boolean result = pointLogDao.pointAssignment(mno, reason);
        return result;
    } // fund end

    // [2] mno 별 포인트 이력 출력
    public List<PointLogDto> mnoPointLog(int mno) {
        List<PointLogDto> list = pointLogDao.mnoPointLog(mno);
        return list;
    }// func end

    // [3] mno 별 포인트 합산
    public int mnoPoint(int mno){
        int result = pointLogDao.mnoPoint(mno);
        return result;
    } // func end

} // class end
