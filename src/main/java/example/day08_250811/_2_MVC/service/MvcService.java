package example.day08_250811._2_MVC.service;

import example.day08_250811._2_MVC.model.dao.MvcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// [1] Service bean 등록
@Service
public class MvcService {

    // [2] Repository - Dao의 bean 불러오기
    @Autowired
    private MvcDao mvcDao;

    // [3] 기능처리
    public void method(){
        System.out.println("MvcService.method");
        mvcDao.method();                // repository의 메소드 실행
    }
}
