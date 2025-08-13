package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {
    private int mno;
    private String mid;
    private String mpwd;
    private String mname;
    private String mphone;
    private String mdate;
}
