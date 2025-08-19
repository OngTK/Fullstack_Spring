package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.ProductDao;
import web.model.dto.ProductDto;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    // [1-1] 제품 등록
    public int createProduct(ProductDto productDto) {
        int result = productDao.createProduct(productDto);
        return result;
    }

    // [1-2] 제품 이미지 등록
    public boolean createProductImages(int pno, String fileName) {
        boolean result = productDao.createProductImages(pno,fileName);
        return result;
    }

    // [2] 제품 전체조회
    public List<ProductDto> getAllProduct(){
        // [2.1] 모든 제품의 정보 조회
        List<ProductDto> productDtoList = productDao.getAllProduct();
        // [2.2]] 모든 제품의 이미지 조회
        for( ProductDto productDto : productDtoList ){
            // pno를 매개변수로 첨부파일의 파일명을 반환하는 method를 실행하고 반환값을 list 타입으로 받아옴
            List<String> images = productDao.getProductImages( productDto.getPno() ); 
            productDto.setImages( images ); // 조회한 모든 이미지명을 특정한 제품의 dto에 삽입
        }
        // 3. 반환
        return productDtoList;
    }

    // [3] 제품 개별조회


} // class end

