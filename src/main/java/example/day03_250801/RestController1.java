package example.day03_250801;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// @Controller     // 콘트롤러 Annotation
@RestController // @ResponseBody를 내장하고 있는 새로운 어노테이션!!!!!!
public class    RestController1 {
    
    // 싱글톤 생략
    // @Controller 내의 @Component가 존재하며, Component가 bean을 생성함

    @GetMapping("/day03")
        // HTTP를 지원하며, Http Get Method 실행시 연결됨을 주석
        // URL정의 : http://localhost:8080/day03
    // @ResponseBody
        // Http 요청 > Java method 실행 및 결과 반환 > 결과를 JSON 타입으로 변환
        // @RestController를 선언하였으므로, ResponseBody 생략 가능!!
    public String method1(){
        System.out.println("method1 실행");
        return "Hello world!";
    }// func end
    
    
    // 매개변수 ==========================
    // 매소드 사용을 위해 필요한 인자 값을 저장하는 변수
    // HTTP 요청 시, 매개변수를 HTTP에서 JAVA로 매개변수를 전달할 필요가 있음

    // HTTP QueryString을 사용
    // URL?para1=value1&para2=value2&para3=value3
    // 주의 value는 모두 String으로 인식

    // @ModelAttribute
    //
    // @RequestParam()
    // 요청매개변수 매핑 어노테이션, 생략 가능
    //        (1) name = "URL매개변수명"
    //            - URL상 ? 뒤에 매핑할 매개변수명
    //            - Java의 매개변수명과 QueryString의 매개변수명이 동일할 경우, 생략 가능
    //        (2) defaultValue = "초기값"
    //            - 매개변수 생략 시 초기값
    //        (3) required = true or false
    //            - 매개변수가 없을 경우, 예외 발생
    //            - true : 예외 발생 >> Error code : 400 : 경로가 잘못 되었음
    //            - false : 예외 발생 X

    @GetMapping("/day03/param1")
    public boolean method2( @RequestParam(name = "name", defaultValue = "홍길동", required = false) String name ){
        System.out.println("RestController1.method2");  // soutm
        System.out.println("name = " + name);           // soutp
            // QueryString을 이용한 매개변수 전달
            // http://localhost:8080//day03/param1?name=""
            // 실행시 name이 콘솔로 들어오는 것을 알 수 있음
        return true;
    } //func end

    @GetMapping("/day03/param2")
    public int method3( String name, int age ) {
        System.out.println("RestController1.method3");
        System.out.println("name = " + name + ", age = " + age);
        return 3;
    }

    @DeleteMapping("/day03/param3")
    public String method4( @RequestParam Map<String, String> map){
        // Map
        //      key와 value를 한 entry로 하며, 여러 entry를 저장하는 구조
        //☆★☆★일반적으로 정해진 규칙이 아닌, 비규칙 구조인 Map을 사용할 경우, @RequestParam 필수!!
        System.out.println("RestController1.method4");
        System.out.println("map = " + map);
        return "안녕";
    } //func end

    @DeleteMapping("day03/param4")
    public int method5( TaskDto taskDto ){
        System.out.println("RestController1.method5");
        System.out.println("taskDto = " + taskDto);
        System.out.println(taskDto.toString());
        return 3;
    }
    // QueryString의 param 명과 DTO 멤버면수 명이 일치해야하고 반드시 존재해야지만 자동 매핑됨
    // Dto에 생성자 / getter·setter / toString을 모두 만들어 놓을 것!!!

    @PostMapping("/day03/param5")
    public boolean method6( @RequestBody TaskDto taskDto ){
        System.out.println("taskDto = " + taskDto);
        System.out.println("RestController1.method6");

        return true;
    }

    @PutMapping("/day03/param6")
    public int method7(@RequestBody Map<String, String> map){
        System.out.println("RestController1.method7");
        System.out.println("map = " + map);
        return 3;
    }


} //class end
