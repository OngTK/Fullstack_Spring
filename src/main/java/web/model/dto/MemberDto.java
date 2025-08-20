package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {
    // 멤버변수
    private int mno;
    private String mid;
    private String mpwd;
    private String mname;
    private String mphone;
    private String mdate;

    // 부가정보
    // [1] 20250820 추가 회원 이미지 저장용 정보
    private MultipartFile upload;      // 업로드 파일용
    private int mimgno;         // 회원 이미지 번호 PK
    private String mimgname;    // 파일명
}
