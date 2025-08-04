package example.day04_250804;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication      // 스프링 실행 어노테이션
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);  // AppStart class를 매개변수로 하여 스프링 실행
    }
}
