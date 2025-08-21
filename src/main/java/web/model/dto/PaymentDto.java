package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {
    // 멤버변수
    private int pno;
    private int mno;
    private int pamount;
    private String pdate;
}
