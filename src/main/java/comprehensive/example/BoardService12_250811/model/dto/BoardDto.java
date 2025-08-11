package comprehensive.example.BoardService12_250811.model.dto;

import lombok.*;

// [0.2] lombok Annotation
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDto {
    // [0.1] 멤버변수
    private int bno;
    private String bwriter;
    private String bcontent;
} // class end
