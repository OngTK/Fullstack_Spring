package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.ProductDao;
import web.model.dto.ProductDto;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    // [1] 제품 등록
    public int createProduct(ProductDto productDto) {
        int result = productDao.createProduct(productDto);
        return result;
    }

    // [2] 제품 이미지 등록
    public boolean createProductImages(int pno, String fileName) {
        boolean result = productDao.createProductImages(pno,fileName);
        return result;
    }

    // [2] 제품 전체조회

    // [3] 제품 개별조회


} // class end

