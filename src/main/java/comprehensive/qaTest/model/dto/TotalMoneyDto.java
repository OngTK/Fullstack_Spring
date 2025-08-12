package comprehensive.qaTest.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class TotalMoneyDto {
    private int custNo;
    private String custName;
    private String grade;
    private int totalPrice;

}
