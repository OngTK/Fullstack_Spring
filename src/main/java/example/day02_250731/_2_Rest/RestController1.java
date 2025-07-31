package example.day02_250731._2_Rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller // 컨트롤러 어노테이션 삽입
// @Component : Spring 컨테이너(메모리)에 bean을 등록·생성 ☆★싱글톤 대체☆★
// Spring Controller : 기본적으로 HTTP 통신을 지원
public class RestController1 {

    // 싱글톤 생략 : @Component로 인해서 자동으로 싱글톤처럼 하나의 객체를 만들어 줌

    // [1] REST : CRUD 기능을 HTTP로 제공하는 아키텍처
    // Spring REST : @PostMapping / @GetMapping / @PutMapping / @DeleteMapping 등의 웹 CRUD 어노테이션 제공

    // 1) 등록 : Create   >> @PostMapping
    // Talend API : Method [ Post ] / Scheme : http://localhost:8080
    @PostMapping
    public void submit() {
        System.out.println("RestController1.submit");
    }

    // 2) 조회 : Read     >> @GetMapping
    // Talend API : Method [ Get ]
    @GetMapping
    public void view() {
        System.out.println("RestController1.view");
    }

    // 3) 수정 : Update   >> @PutMapping
    // Talend API : Method [ Put ]
    @PutMapping
    public void update() {
        System.out.println("RestController1.update");
    }

    // 4) 삭제 : Delete   >> @DeleteMapping
    // Talend API : Method [ Delete ]
    @DeleteMapping
    public void delete() {
        System.out.println("RestController1.delete");
    }

    // [2] REST test 방법
    // chrome 확장 프로그램 >> [ Talend API Tester - Free Edition ]

} //class end
