package example.day02_250731._2_Rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {

    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
        // SpringBootApplication    : Class
        // .run()                   : static · Spring 실행 함수   
        // ( 현클래스명.class )      : 현재 클래스를 run 메소드의 매개변수로 전달하면서, @SpringBootApplication 이 실행
    }
}
