package example.day06_250806;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FetchController {

    // 비동기 fetch vs 동기 fetch
    // [1] GET
    @GetMapping("/day06/exam1")
    public boolean method1(){
        return true; //임의의 반환값
    }

}
