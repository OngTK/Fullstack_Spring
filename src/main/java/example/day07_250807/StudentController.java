package example.day07_250807;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @ Component : 스프링 컨테이너(메모리)에 Bean·객체를 생성, 싱글톤과 유사 역할
@RequestMapping("/student")     // 클라스 내 모든 Mapping 어노테이션 메소드의 공통 URL
public class StudentController {

    // dao 싱글톤 호출
    private StudentDao studentDao = StudentDao.getInstance();

    // 1. 저장
    @PostMapping("/save")
    // @PostMapping : HTTP 요청 method 중 POST 일 경우 Java 메소드 연결
    public boolean save(@RequestBody StudentDto studentDto){
        System.out.println("StudentController.save");
        System.out.println("studentDto = " + studentDto);
        
        // [1.1] dao에 매개변수 전달, 메소드 실행
        boolean result = studentDao.save(studentDto);
        
        // [1.2] 결과 반환
        return result;
    } //func end

    // 2. 전체조회
    @GetMapping("find")
    // @GetMapping : HTTP 요청 method 중 Get 일 경우 Java 메소드 연결
    public List<StudentDto> find(){
        System.out.println("StudentController.find");
        
        // [2.1] dao의 메소드 실행
        List<StudentDto> list = studentDao.find();
        // [2.2] 결과 반환
        return list;
    } //func end

} // class end
