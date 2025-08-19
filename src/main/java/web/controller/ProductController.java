package web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import web.model.dto.ProductDto;
import web.service.FileService;
import web.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;
    // 파일 업로드를 위한 service

    // [1] 제품 등록
    @PostMapping("/create")
    public int createProduct(@ModelAttribute ProductDto productDto, HttpSession session) {
        // [1.1] 로그인 상태 확인, 비로그인 시 0을 반환
        if (session.getAttribute("loginMno") == null) {
            return 0;
        }

        int loginMno = (int)session.getAttribute("loginMno");
        productDto.setMno(loginMno);

        // [1.2] 제품정보 등록 // pno를 반환
        int result = productService.createProduct(productDto);

        // [1.3] 업로드 이미지 여부 확인 후 FileService 작업 수행
        if( result > 0 && !productDto.getUploads().isEmpty() && !productDto.getUploads().get(0).isEmpty() ){
            // [제품등록 성공] And [DTO의 upload(MultipartFile List객체)가 비어있지 않음] And [ Dto 첫번째 첨부파일이 비어있지 않음]

            // [1.3.1] 반복문을 통해 List 배열 내의 Upload 파일을 업로드
            for(MultipartFile multipartFile : productDto.getUploads() ){
                // [1.3.1.1] fileService 에서 파일 업로드 및 fileName을 반환 받음
                String fileName = fileService.fileUpload(multipartFile);

                // 업로드 실패
                if( fileName == null ) return 0;

                // [1.3.1.2] 업로드 성공 시, 업로드 파일명을 DB에 저장
                boolean result2 = productService.createProductImages(result,fileName);
                if(result2 == false) return 0; // DB저장 실패
            }

        }
        // [1.4] 결과 반환
        return result;
    }

    // [2] 제품 전체조회

    // [3] 제품 개별조회

} // class end
