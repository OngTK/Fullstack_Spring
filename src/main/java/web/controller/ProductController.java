package web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.ProductDto;
import web.service.FileService;
import web.service.ProductService;

@RestController
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

    }

    // [2] 제품 전체조회

    // [3] 제품 개별조회

} // class end
