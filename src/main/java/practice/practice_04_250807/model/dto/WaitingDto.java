package practice.practice_04_250807.model.dto;

import lombok.*;

@NoArgsConstructor          // 빈생성자
@AllArgsConstructor         // 모든 매개변수 생성자
@Getter                     // getter
@Setter                     // setter
@ToString                   // toString
public class WaitingDto {
    // 멤버변수
    private int wno;
    private String phone;
    private int count;

    public WaitingDto(int wno, String phone, int count) {
    }
}
