package example.day07_250807;

import lombok.*;

// 롬복 · lombok
//      - 설계에서 자주 사용하는 코드를 자동 생성
//      - 설치
//          1) 인텔리제이 설치
//                  플러그인 : lombok
//          2) Gradle 설치
//                  compileOnly 'org.projectlombok:lombok'
//                  annotationProcessor 'org.projectlombok:lombok'

@NoArgsConstructor  // 컴파일 시, 빈 생성자 자동 생성
@AllArgsConstructor // 컴파일 시, 전체 생성자 자동 생성
@Getter             // getter 자동 생성
@Setter             // setter 자동 생성, 생략시 VO(읽기모드)
@ToString           // toString 자동 생성
public class StudentDto {
    // 멤버변수
    private int sno;
    private String sname;
    private int skor;
    private int smath;
    private String sdate;

    // 생성자

    // getter·setter


    // toString
}
