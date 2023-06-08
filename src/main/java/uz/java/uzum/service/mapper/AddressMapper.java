package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.AddressDto;
import uz.java.uzum.entity.Address;

@Mapper(componentModel = "spring")
public abstract class AddressMapper implements CommonMapper<AddressDto, Address>{
    @Override
    public abstract AddressDto toDto(Address address);

    @Override
    public abstract Address toEntity(AddressDto addressDto);
}
