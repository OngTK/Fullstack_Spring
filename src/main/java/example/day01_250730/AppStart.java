package example.day01_250730;

import example.day01_250730.controller.BoardController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class AppStart {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        boolean result = BoardController.getInstance().boardWrite("test Content", "test Writer");
        System.out.println(result);

        // Spring 환경 설정
        // SpringApplication.run(현재클래스명.class)
        SpringApplication.run(AppStart.class);
        // console 실행 후 localhost:8080 입력 시 , Whitelabel Error Page 이 출력되면 성공

    } // main end
} // class end
