package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.AddressDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.Address;
import uz.java.uzum.repository.AddressRepository;
import uz.java.uzum.service.AddressService;
import uz.java.uzum.service.mapper.AddressMapper;

import java.util.List;
import java.util.Optional;

import static uz.java.uzum.service.appStatus.AppStatusCodes.*;
import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    @Override
    public ResponseDto<AddressDto> add(AddressDto addressDto) {
        try{
            addressRepository.save(addressMapper.toEntity(addressDto));
            return ResponseDto.<AddressDto>builder()
                    .data(addressDto)
                    .code(OK_CODE)
                    .message(OK)
                    .success(true)
                    .build();
        } catch (Exception e){
            return ResponseDto.<AddressDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .code(DATABASE_ERROR_CODE)
                    .build();
        }
    }

    @Override
    public ResponseDto<List<AddressDto>> getAll() {
        try{
            List<Address> addresses = addressRepository.findAll();

            return ResponseDto.<List<AddressDto>>builder()
                    .code(OK_CODE)
                    .message(OK)
                    .success(true)
                    .data(addresses.stream().map(addressMapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<AddressDto>>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .code(DATABASE_ERROR_CODE)
                    .build();
        }
    }

    @Override
    public ResponseDto<AddressDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<AddressDto>builder()
                    .message(NULL_VALUE)
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }
        try {
            Optional<Address> addressOptional = addressRepository.findById(id);

            if (addressOptional.isEmpty()) {
                return ResponseDto.<AddressDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            return ResponseDto.<AddressDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(addressMapper.toDto(addressOptional.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<AddressDto>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .code(DATABASE_ERROR_CODE)
                    .build();
        }
    }

    @Override
    public ResponseDto<Void> delete(Integer id) {
        if (id == null){
            return ResponseDto.<Void>builder()
                    .message(NULL_VALUE)
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }
        try {
            Optional<Address> addressOptional = addressRepository.findById(id);

            if (addressOptional.isEmpty()) {
                return ResponseDto.<Void>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            addressRepository.deleteById(id);
            return ResponseDto.<Void>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .build();
        } catch (Exception e){
            return ResponseDto.<Void>builder()
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .code(DATABASE_ERROR_CODE)
                    .build();
        }
    }

    @Override
    public ResponseDto<AddressDto> update(AddressDto addressDto) {
        if (addressDto.getId() == null){
            return ResponseDto.<AddressDto>builder()
                    .message(NULL_VALUE)
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        Optional<Address> addressOptional = addressRepository.findById(addressDto.getId());

        if (addressOptional.isEmpty()){
            return ResponseDto.<AddressDto>builder()
                    .message(NOT_FOUND)
                    .code(NOT_FOUND_ERROR_CODE)
                    .build();
        }
        Address address = addressOptional.get();
        if (addressDto.getCode() != null) {
            address.setCode(addressDto.getCode());
        }if(addressDto.getInfo() != null){
            address.setInfo(addressDto.getInfo());
        }if(addressDto.getPhoneNumber() != null){
            address.setPhoneNumber(addressDto.getPhoneNumber());
        }
        try {
            addressRepository.save(address);
            return ResponseDto.<AddressDto>builder()
                    .data(addressMapper.toDto(address))
                    .code(OK_CODE)
                    .success(true)
                    .message(OK)
                    .build();
        }catch (Exception e){
            return ResponseDto.<AddressDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + e.getMessage())
                    .build();
        }
    }
}
