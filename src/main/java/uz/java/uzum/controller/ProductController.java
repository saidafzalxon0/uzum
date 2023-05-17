package uz.java.uzum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.java.uzum.dto.ProductDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.service.ProductService;


@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseDto<ProductDto> addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @PatchMapping
    public ResponseDto<ProductDto> updateProduct(@RequestBody ProductDto productDto){
        return productService.updateProduct(productDto);
    }

    @GetMapping
    public ResponseDto<Page<ProductDto>> getAllProducts(@RequestParam(defaultValue = "10") Integer size,
                                                                     @RequestParam(defaultValue = "0") Integer page){
        return productService.getAllProducts(page, size);
    }

    @GetMapping("by-id")
    public ResponseDto<ProductDto> getProductById(@RequestParam Integer id){
        return productService.getProductById(id);
    }
}
