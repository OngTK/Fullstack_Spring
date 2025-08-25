package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
// 게시물 정보를 갖는 클래스
public class PostDto {
    // 멤버변수
    private int pno;
    private String ptitle;
    private String pcontent;
    private String pdate;
    private int pview;
    private int mno;
    private int cno;

    // 추가 구성
    private String mid;     // 작성자 아이디
    private String cname;   // 카테고리 명
    private boolean host;   // 현재 게시물을 보고있는 클라이언트가 작성한 본인의 글인지 확인
} // class end
