package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.ErrorDto;
import uz.java.uzum.dto.ProductDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.Product;
import uz.java.uzum.repository.ProductRepository;
import uz.java.uzum.repository.ProductRepositoryImpl;
import uz.java.uzum.service.ProductService;
import uz.java.uzum.service.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;

import static uz.java.uzum.service.appStatus.AppStatusCodes.*;
import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepositoryImpl productRepository;
    private final ProductMapper productMapper;
    @Override
    public ResponseDto<ProductDto> addProduct(ProductDto productDto) {
//        List<ErrorDto> errors = productValidator.validateProduct(productDto);

//        if (!errors.isEmpty()){
//            return ResponseDto.<ProductDto>builder()
//                    .errors(errors)
//                    .data(productDto)
//                    .message(VALIDATION_ERROR)
//                    .code(VALIDATION_ERROR_CODE)
//                    .success(false)
//                    .build();
//        }

        Product product = productMapper.toEntity(productDto);

        productRepository.save(product);

        return ResponseDto.<ProductDto>builder()
                .success(true)
                .code(0)
                .data(productMapper.toDto(product))
                .message("OK")
                .build();
    }

    @Override
    public ResponseDto<ProductDto> updateProduct(ProductDto productDto) {
        if (productDto.getId() == null) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product ID is null")
                    .code(-2)
                    .build();
        }

        Optional<Product> optional = productRepository.findById(Math.toIntExact(productDto.getId()));

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
        if (productDto.getPrice() != null && productDto.getPrice() > 0) {
            product.setPrice(productDto.getPrice());
        }
        if (productDto.getAmount() != null && productDto.getAmount() > 0) {
            product.setIsAvailable(true);
            product.setAmount(productDto.getAmount());
        }
        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }

        try {

            productRepository.save(product);

            return ResponseDto.<ProductDto>builder()
                    .message(OK)
                    .code(OK_CODE)
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
    public ResponseDto<ProductDto> getById(Integer id) {
        return null;
    }

//    @Override
    public ResponseDto<Page<ProductDto>> getAllProducts(Integer page, Integer size) {
        Long count = productRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size,
                Sort.by("price").descending());

        Page<ProductDto> products = productRepository.findAll(pageRequest)
                .map(productMapper::toDto);

        return ResponseDto.<Page<ProductDto>>builder()
                .message(OK)
                .code(OK_CODE)
                .success(true)
                .data(products)
                .build();
    }

//    @Override
    public ResponseDto<ProductDto> getProductById(Integer id) {
        return productRepository.findById(id)
                .map(products -> ResponseDto.<ProductDto>builder()
                        .data(productMapper.toDto(products))
                        .success(true)
                        .code(OK_CODE)
                        .message(OK)
                        .build())
                .orElse(ResponseDto.<ProductDto>builder()
                        .message(NOT_FOUND)
                        .code(NOT_FOUND_ERROR_CODE)
                        .build()
                );
    }
}
