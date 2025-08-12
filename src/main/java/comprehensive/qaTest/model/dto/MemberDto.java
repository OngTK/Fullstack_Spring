package comprehensive.qaTest.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MemberDto {
    private int custNo;
    private String custName;
    private String phone;
    private String address;
    private String joinDate;
    private String grade;
    private String city;
}
