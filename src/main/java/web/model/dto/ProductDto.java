package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

    // 멤버변수
    private int pno;
    private String pname;
    private int pprice;
    private String pcomment;
    private String pdate;
    private double plat;
    private double plng;
    private int mno;

    // 커스텀 정보

    // [1] 업로드에 사용할 multipartFile 객체를 List 타입으로 여러 개의 첨부파일 받아오기
    private List< MultipartFile > uploads;

    // [2] 업로드된 파일명을 문자열로 조회
    private List< String > images;

    // [3] 판매자 아이디
    private int mid;
    
} // class end
