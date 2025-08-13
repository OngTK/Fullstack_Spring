package example.day10_250813;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data                   //  getter·setter·toString 등을 일괄 처리
public class LoginDto {
    private int mno;        // 회원번호
    private String mid;     // ID
    private String mpw;     // PW
}
