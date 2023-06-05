package uz.java.uzum.service;

import uz.java.uzum.dto.BrandDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.Brand;

import java.util.List;

public interface BrandServices {

    Brand addBrand(String name);

    List<BrandDto> getAllBrands();
}