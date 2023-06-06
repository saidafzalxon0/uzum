package uz.java.uzum.service;

import uz.java.uzum.dto.BrandDto;
import uz.java.uzum.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand addBrand(String name);

    List<BrandDto> getAllBrands();
}
