package comprehensive.qaTest.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MoneyDto {
    private int custno;
    private int salenol;
    private int pcost;
    private int abount;
    private int price;
    private String pcode;
    private String sdate;
}
