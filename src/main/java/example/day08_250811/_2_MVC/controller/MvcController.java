package example.day08_250811._2_MVC.controller;

import example.day08_250811._2_MVC.service.MvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// [1] Controller bean 등록
@RestController
public class MvcController {

    // [2] Service의 bean 불러오기
    @Autowired
    private MvcService mvcService;

    // [3] 기능처리
    @GetMapping("/day08/mvc")
    public void method(){
        System.out.println("MvcController.method");
        mvcService.method(); // Service의 메소드 호출
    }
}
