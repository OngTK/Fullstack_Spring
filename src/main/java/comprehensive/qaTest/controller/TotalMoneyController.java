package comprehensive.qaTest.controller;

import comprehensive.qaTest.model.dto.MoneyDto;
import comprehensive.qaTest.model.dto.TotalMoneyDto;
import comprehensive.qaTest.service.TotalMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/moneyprint")
public class TotalMoneyController {
    @Autowired
    private TotalMoneyService totalMoneyService;

    // [1] 매출 정보 전체조회 moneyRead()
    @GetMapping
    public ArrayList<TotalMoneyDto> moneyRead(){
        ArrayList<TotalMoneyDto> list = totalMoneyService.moneyRead();
        return list;
    } // func end

} // class end
