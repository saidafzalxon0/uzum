package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.BrandDto;
import uz.java.uzum.entity.Brand;
import uz.java.uzum.repository.BrandRepository;
import uz.java.uzum.service.BrandService;
import uz.java.uzum.service.mapper.BrandMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandMapper brandMapper;
    private final BrandRepository brandRepository;

    @Override
    public Brand addBrand(String name) {
        Brand brand = new Brand();
        brand.setName(name);
        return brandRepository.save(brand);
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return brandRepository.findAllBrands().stream().map(brandMapper::toDto).toList();
    }
}
