package practice.practice_05_250812.model.dto;

import lombok.*;

// [2] lombok Annotation
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class WaitingDto {
    // [1] 멤버변수 선언
    private int wno;
    private String phone;
    private int count;
    private String wdate;
}
