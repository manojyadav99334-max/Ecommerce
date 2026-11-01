package com.ecommerce.productservice.service;


import com.ecommerce.productservice.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Long id,ProductDto dto);
    void deleteProduct(Long productId);
    ProductDto getProductById(Long id);
    ProductDto uploadImage(Long productId, MultipartFile file) throws IOException;
    Page<ProductDto> getAllProducts(int page,int size,String sortBy,String sortDir);
    Page<ProductDto> searchProduct(String keyword,int page,int size);
    Page<ProductDto> filterProducts(Long categoryId,Double minPrice,Double maxPrice,int page,int size);
    Page<ProductDto> advanceFilter(String keyword,Long categoryId,Double minPirce,double maxPrice,int page,int size,String sortBy,String sortDir);


}
