package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.PaymentDao;
import web.model.dto.PaymentDto;

@Service
public class PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    // [1] 결제 정보 등록하기
    // 결제 성공시
    public int addPaylog(PaymentDto paymentDto){
        int result = paymentDao.addPaylog(paymentDto);
        return result;
    } // func end

}
