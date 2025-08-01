package example.day03_250801;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// AppStart : 스프링 실행과 동시에 동일한·하위의 패키지 내 모든 컴포넌트를 인식하여 자동으로 bean으로 등록

@SpringBootApplication                          // 스프링부트 Annotation
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);  // AppStart 클래스 매개변수로 해서 스프링을 실행!
    }
}
