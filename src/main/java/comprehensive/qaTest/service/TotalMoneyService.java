package comprehensive.qaTest.service;

import comprehensive.qaTest.model.dao.TotalMoneyDao;
import comprehensive.qaTest.model.dto.TotalMoneyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TotalMoneyService {

    @Autowired
    private TotalMoneyDao totalMoneyDao;

    // [1] 매출 정보 전체조회 moneyRead()
    public ArrayList<TotalMoneyDto> moneyRead(){
        ArrayList<TotalMoneyDto> list = totalMoneyDao.moneyRead();
        return list;
    } // func end


} // class end
