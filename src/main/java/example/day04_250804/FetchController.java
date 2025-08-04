package example.day04_250804;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
// @Controller + @ResponseBody
// HTTP·클라이언트의 요청·응답 처리
public class FetchController {

    // 복습 - 매개변수 X, 반환값 X =============================================================

    // [1] Method : Get / Url : /day04/exam
    @GetMapping("/day04/exam")
    public void method11() {
        System.out.println("FetchController.method11");
    } // func end

    // [2] Method : Post / Url : /day04/exam2
    @PostMapping("/day04/exam2")    // Post
    public void method2() {
        System.out.println("FetchController.method2");
    }

    // [3] Method : Put / Url : /day04/exam3
    @PutMapping("/day04/exam3")
    public void method3() {
        System.out.println("FetchController.method3");
    }

    // [4] Method : Delete / Url : /day04/exam4
    @DeleteMapping("/day04/exam4")
    public void method4() {
        System.out.println("FetchController.method4");
    }

    // 복습 - 매개변수 O, 반환값 O =============================================================
    // 일부러 매개변수 쿼리스트링·Body 와 반환 내용 임의값을 서로 다르게 하였음.
    // 실제 이용 시, 매개변수가 들어와서 저장되도록 잘 연결할 것!

    // [5] 매개변수 : 쿼리스트링 / 반환타입 : Json
    // Get / day04/exam5?name=배두훈&age=39
    @GetMapping("day04/exam5")
    public boolean method5(@RequestParam String name, @RequestParam int age) {
        System.out.println("FetchController.method5");
        System.out.println("name = " + name + ", age = " + age);
        boolean result = true; //임의값
        System.out.println("result = " + result);
        return result;
    }

    // [6] 매개변수 : Body / 반환타입 : Json
    //     Body ` {"name":"배두훈","age":39} `
    @PostMapping("day04/exam6")
    public int method6(@RequestBody Map<String, String> map) {
        System.out.println("FetchController.method6");
        System.out.println("map = " + map);
        int result = 20; //임의값
        return result;
    }

    // [7] 매개변수 : Body / 반환타입 : Json
    //     Body ` {"name":"배두훈","age":39} `
    @PutMapping("day04/exam7")
    public TaskDto method7(@RequestBody TaskDto taskDto) {
        System.out.println("FetchController.instance initializer");
        System.out.println("taskDto = " + taskDto);
        TaskDto result = new TaskDto("배두훈", 40);
        System.out.println("taskDto = " + taskDto);
        return result;
    }

    // [8] 매개변수 : 쿼리스트링 / 반환타입 : Json
    //      Delete / day04/exam8?name=배두훈&age=39
    @DeleteMapping("day04/exam8")
    public List<TaskDto> method8(@RequestParam String name, int age){
        System.out.println("FetchController.method8");
        System.out.println("name = " + name + ", age = " + age);
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto("강형호",37));
        return taskDtoList;
    }


} // class end
