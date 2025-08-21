package web.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PointLogDto {
    // 멤버변수
    private int plno;
    private int mno;
    private int plpoint;
    private String plcomment;
    private String pldate;

} // class end
