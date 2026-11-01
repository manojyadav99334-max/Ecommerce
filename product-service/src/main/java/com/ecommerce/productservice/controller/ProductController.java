package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto dto)
    {
        return new ResponseEntity<>(productService.createProduct(dto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,@RequestBody ProductDto dto)
    {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.updateProduct(id,dto));
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);;
        return ResponseEntity.status(HttpStatus.OK).body("Product has been deleted :"+id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductById(id));
    }
    //Page<ProductDto> getAllProducts(int page,int size,String sortBy,String sortDir);
    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size,
                                                           @RequestParam(defaultValue = "id") String sortBy,
                                                           @RequestParam(defaultValue = "asc") String sortDir)
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getAllProducts(page,size,sortBy,sortDir));

    }
    //    Page<ProductDto> searchProduct(String keyword,int page,int size);
    @GetMapping("/search")
    public ResponseEntity<Page<ProductDto>> searchProducts(@RequestParam String keyword,@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size)
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.searchProduct(keyword,page,size));
    }
    @GetMapping("/filter")
    public ResponseEntity<Page<ProductDto>> filterProducts(@RequestParam(required = false) Long categoryId,@RequestParam(required = false) double minPrice,@RequestParam(required = false) double maxPrice,
                                                           @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size)
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.filterProducts(categoryId,minPrice,maxPrice,page,size));
    }
    @PostMapping("/{id}/upload-image")
    public ResponseEntity<ProductDto> uploadImage(@PathVariable Long id,
                                                  @RequestParam("file")MultipartFile file) throws IOException{
        return ResponseEntity.ok(productService.uploadImage(id,file));
    }



}
