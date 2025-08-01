package example.day03_250801;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller     // 콘트롤러 Annotation
@RestController // @ResponseBody를 내장하고 있는 새로운 어노테이션!!!!!!
public class RestController1 {
    
    // 싱글톤 생략
    // @Controller 내의 @Component가 존재하며, Component가 bean을 생성함

    @GetMapping("/day03")
        // HTTP를 지원하며, Http Get Method 실행시 연결됨을 주석
        // URL정의 : http://localhost:8080/day03
    @ResponseBody
        // Http 요청 > Java method 실행 및 결과 반환 > 결과를 JSON 타입으로 변환
    public String method1(){
        System.out.println("method1 실행");
        return "Hello world!";
    }// func end


} //class end
