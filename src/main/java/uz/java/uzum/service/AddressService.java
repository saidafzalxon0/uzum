package uz.java.uzum.service;

import uz.java.uzum.dto.AddressDto;
import uz.java.uzum.dto.ResponseDto;

import java.util.List;

public interface AddressService {
    ResponseDto<AddressDto> add(AddressDto addressDto);
    ResponseDto<List<AddressDto>> getAll();
    ResponseDto<AddressDto> getById(Integer id);
    ResponseDto<Void> delete(Integer id);
    ResponseDto<AddressDto> update(AddressDto addressDto);
}
