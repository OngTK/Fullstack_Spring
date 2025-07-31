package example.day02_250731._2_Rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller // @Component 내장
public class RestController2 {
    // 싱글톤 대신한 @Component 이용한 인스턴스 자동화

    // [ 반환타입이 있는 REST ]

    // [1] 100을 반환하는 메소드
    @GetMapping("/day02/task") // URL주소정의 >> http://localhsot:8080/day02/task
    @ResponseBody
    // HTTP는 int 타입 X >
    // @ResponseBody : Java가 사용하는 타입을 HTTP 언어의 타입을 자동으로 변환해주는 어노테이션
    public int method1(){
        System.out.println("RestController2.method1");
        return 100;
    }

    // [2] 문자열을 반환하는 메소드
    @GetMapping("/day02/task2") // 서버 내 동일한 주소 불가!
    @ResponseBody
    public String method2(){
        System.out.println("RestController2.method2");
        return "Spring으로부터의 메세지 입니다.";
    };
    
    // [3] MAP 타입 반환
    @PostMapping("/day02/task") //서버 내 동일한 주소가 원칙적으로 불가하지만, REST가 다르면 가능
    @ResponseBody
    public Map<String, Integer> emthod3(){
        System.out.println("RestController2.emthod3");
        Map<String, Integer> map = new HashMap<>();
        map.put("배두훈", 1986);
        map.put("강형호", 1988);
        return map;
    }

    // [4] Boolean 타입 반환
    @PutMapping("/day02/task")
    @ResponseBody
    public boolean method4(){
        System.out.println("RestController2.method4");
        return false;
    }

    // [5] DTO 타입 반환
    @DeleteMapping("/day02/task")
    @ResponseBody
    public TaskDto method5(){
        System.out.println("RestController2.method5");

        TaskDto taskDto = new TaskDto();
        return taskDto;
    }

    // [6] ArrayList 타입 반환
    @GetMapping("/day02/task3")
    @ResponseBody
    public ArrayList<TaskDto> method6(){
        System.out.println("RestController2.method6");
        ArrayList<TaskDto> arrayList = new ArrayList<>();
        arrayList.add(new TaskDto("조민규", 35));
        arrayList.add(new TaskDto("고우림", 30));
        return arrayList;
    };
} // class end
