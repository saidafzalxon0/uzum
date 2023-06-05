package uz.java.uzum.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.OverridesAttribute;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.BrandDto;
import uz.java.uzum.dto.ProductDto;
import uz.java.uzum.dto.ProductVariantDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.Brand;
import uz.java.uzum.entity.Product;
import uz.java.uzum.entity.ProductVariant;
import uz.java.uzum.projections.ProductProjection;
import uz.java.uzum.repository.ProductRepository;
import uz.java.uzum.repository.ProductRepositoryImpl;
import uz.java.uzum.repository.ProductVariantRepository;
import uz.java.uzum.service.BrandServices;
import uz.java.uzum.service.ProductService;
import uz.java.uzum.service.mapper.CategoryMapper;
import uz.java.uzum.service.mapper.ProductMapper;
import uz.java.uzum.service.mapper.ProductVariantMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static uz.java.uzum.service.appStatus.AppStatusCodes.*;
import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final BrandServiceImpl brandServices;
    private final ProductVariantMapper productVariantMapper;
    private final ProductVariantRepository productVariantRepository;

    @Override
    public ResponseDto<ProductDto> addProduct(ProductDto productDto) {
        Brand brand = brandServices.addBrand(productDto.getBrand().getName());
        Product product = productMapper.toEntity(productDto);
        product.setBrand(brand);
        try {
            Product save = productRepository.save(product);

            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .data(productMapper.toDto(save))
                    .message("OK")
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + e.getMessage())
                    .build();
        }

    }

    @Override
    public ResponseDto<ProductDto> updateProduct(ProductDto productDto) {
        if (productDto.getId() == null) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product ID is null")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        Optional<Product> optional = productRepository.findById(productDto.getId());

        if (optional.isEmpty()) {
            return ResponseDto.<ProductDto>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }

        Product product = optional.get();

        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }

        if (productDto.getAmount() != null && productDto.getAmount() > 0) {
            product.setIsAvailable(true);
            product.setAmount(productDto.getAmount());
        }
        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }
        if (productDto.getCategory() != null) {
            product.setCategory(categoryMapper.toEntity(productDto.getCategory()));
        }

        try {
            productRepository.save(product);

            return ResponseDto.<ProductDto>builder()
                    .message(OK)
                    .data(productMapper.toDto(product))
                    .success(true)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .message(DATABASE_ERROR + ": " + e.getMessage())
                    .code(DATABASE_ERROR_CODE)
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<ProductDto>> getAllProducts(Integer page, Integer size) {
        Long count = productRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size);

        Page<ProductDto> products = productRepository.findAll(pageRequest)
                .map(productMapper::toDto);

        return ResponseDto.<Page<ProductDto>>builder()
                .message(OK)
                .success(true)
                .data(products)
                .build();
    }

    @Override
    @Transactional
    public ResponseDto<ProductDto> getProductById(Integer id) {
        Integer userId = 1;
        try {
            Optional<Product> byId = productRepository.findById(id);
            if (!byId.isEmpty()) {
                productRepository.insertViewedProduct(userId, id);
                return ResponseDto.<ProductDto>builder()
                        .data(productMapper.toDto(byId.get()))
                        .success(true)
                        .code(OK_CODE)
                        .message(OK)
                        .build();
            }
            return ResponseDto.<ProductDto>builder()
                    .message(NOT_FOUND)
                    .code(NOT_FOUND_ERROR_CODE)
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .message(DATABASE_ERROR)
                    .code(DATABASE_ERROR_CODE)
                    .build();
        }


    }

    @Override
    public ResponseDto<List<ProductProjection>> getProducts(Integer userId) {
        try {
            List<ProductProjection> products = productRepository.getProducts(userId, null, null);
            return ResponseDto.<List<ProductProjection>>builder()
                    .success(true)
                    .code(OK_CODE)
                    .message(OK)
                    .data(products)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<ProductProjection>>builder()
                    .success(false)
                    .code(OK_CODE)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<ProductProjection>> getViewedProduct(Integer userId) {
        try {
            List<ProductProjection> productViewed = productRepository.getProductViewed(userId);
            return ResponseDto.<List<ProductProjection>>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .data(productViewed)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<ProductProjection>>builder()
                    .message(DATABASE_ERROR + ":" + e.getMessage())
                    .success(true)
                    .code(DATABASE_ERROR_CODE)
                    .build();
        }
    }

    public ResponseDto<Page<ProductDto>> universalSearch(String query, String sorting, String ordering, Integer size, Integer currentPage) {
        Page<Product> products = productRepository.universalSearch(query, sorting, ordering, size, currentPage);
        if (products.isEmpty()) {
            return ResponseDto.<Page<ProductDto>>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .success(false)
                    .message(NOT_FOUND)
                    .build();
        }
        return ResponseDto.<Page<ProductDto>>builder()
                .code(OK_CODE)
                .message(OK)
                .success(true)
                .data(products.map(productMapper::toDto))
                .build();
    }
}