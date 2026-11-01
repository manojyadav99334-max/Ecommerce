package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.mapper.ProductMapper;
import com.ecommerce.productservice.repository.ProductRepository;
import entity.Category;
import entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService̛Impl implements ProductService{
    private final ProductRepository productRepo;
    private final ProductMapper productMapper;
    private String uploadDir=System.getProperty("user.dir")+"/uploads/products/";


    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Category category=null;
        if(productDto.getCategoryId()!=null)
        {
            category=new Category();
            category.setId(productDto.getId());

        }
        Product product=productMapper.toEntity(productDto,category);
        Product saved=productRepo.save(product);
        return productMapper.toDto(saved);

    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto dto) {
       Product product= productRepo.findById(id)
                .orElseThrow(()->new RuntimeException("product Id not found"));
       product.setName(dto.getName());
       product.setBrand(dto.getBrand());
       product.setImageUrl(dto.getImageUrl());
       product.setDescription(dto.getDescription());
       product.setPrice(dto.getPrice());
       product.setDiscountPrice(dto.getDiscountedPrice());
       if(dto.getCategoryId()!=null)
       {
           Category category=new Category();
           category.setId(dto.getCategoryId());
           product.setCategory(category);
       }
       return productMapper.toDto(productRepo.save(product));

    }

    @Override
    public void deleteProduct(Long productId) {
            productRepo.deleteById(productId);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product=productRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Product Id not found"));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto uploadImage(Long productId, MultipartFile file) throws IOException {
        Product product=productRepo.findById(productId)
                .orElseThrow(()->new RuntimeException("Product id not found"));
        if(file.isEmpty())
        {
            throw new RuntimeException("Image file is empty");
        }
        long maxSize=1024*1024*2;
        if(file.getSize()>maxSize)
        {
            throw new RuntimeException("File size should not be more than 2MB");
        }
        List<String> allowedtype=List.of("image/jpeg","image/png","image/jpg");
        if(!allowedtype.contains(file.getContentType()))
        {
            throw new RuntimeException("Only jpeg,jpg,png are allowed");
        }
        String originalName=file.getOriginalFilename();
        if(originalName==null || !originalName.contains("."))
        {
            throw new RuntimeException("Invalid file Name");
        }
        String ext=originalName.substring(originalName.lastIndexOf(".")+1).toLowerCase();
        List<String> allowedExtension=List.of("jpg","jpeg","png");
        if(!allowedExtension.contains(ext))
        {
            throw new RuntimeException("Invalid Image Extension");
        }
        File folder=new File(uploadDir);
        if(!folder.exists())
        {
            folder.mkdirs();
        }
        String fileName= UUID.randomUUID().toString()+"."+ext;
       Path filePath= Paths.get(uploadDir+fileName);

        Files.write(filePath,file.getBytes());
        String imageUrl="/products/image/"+fileName;
        product.setImageUrl(imageUrl);
       Product savedProduct= productRepo.save(product);
       return productMapper.toDto(savedProduct);


    }

    @Override
    public Page<ProductDto> getAllProducts(int page, int size, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<Product> productPage= productRepo.findAll(pageable);
        return productPage.map(productMapper::toDto);
    }

    @Override
    public Page<ProductDto> searchProduct(String keyword, int page, int size) {
        Pageable pageable=PageRequest.of(page,size);
        Page<Product> productPage=productRepo.searchProducts(keyword,pageable);
        return productPage.map(productMapper::toDto);
    }

    @Override
    public Page<ProductDto> filterProducts(Long categoryId, Double minPrice, Double maxPrice, int page, int size) {
        Pageable pageable=PageRequest.of(page,size);
        Page<Product> productPage=productRepo.advanceFilter(null,categoryId,minPrice,maxPrice,pageable);
        return productPage.map(productMapper::toDto);
    }

    @Override
    public Page<ProductDto> advanceFilter(String keyword, Long categoryId, Double minPirce, double maxPrice, int page, int size, String sortBy, String sortDir) {
        return null;
    }
}
