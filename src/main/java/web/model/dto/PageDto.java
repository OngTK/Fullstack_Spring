package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
// 출력할 정보(게시물/회원/제품 등)들을 paging을 위한 Dto
// DB table 없음
public class PageDto {
    // paging 을 위한 부가 정보 정의
    private int totalCount;     // 총 자료 수
    private int currentPage;    // 현재 페이지
    private int totalPage;      // 전체 페이지 수
    private int startBtn;       // 페이징 버튼 시작 번호
    private int endBtn;         // 페이징 버튼 종료 번호
    private int perCount;       // 페이지 당 자료 수
    private Object data;        // 실제 자료 ☆★

} // class end
