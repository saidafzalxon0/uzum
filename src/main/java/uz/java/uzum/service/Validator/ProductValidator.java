package uz.java.uzum.service.Validator;


import org.springframework.stereotype.Component;
import uz.java.uzum.dto.ErrorDto;
import uz.java.uzum.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Component
public class ProductValidator {

    public List<ErrorDto> validateProduct(ProductDto productDto){
        List<ErrorDto> errors = new ArrayList<>();

        if (productDto.getAmount() == null){
            errors.add(new ErrorDto("amount", NULL_VALUE));
        } else if (productDto.getAmount() <= 0) {
            errors.add(new ErrorDto("amount", NEGATIVE_VALUE));
        }
        if (productDto.getPrice() == null){
            errors.add(new ErrorDto("price", NULL_VALUE));
        } else if (productDto.getPrice() <= 0) {
            errors.add(new ErrorDto("price", NEGATIVE_VALUE));
        }
        if (productDto.getDescription() == null){
            errors.add(new ErrorDto("description", NULL_VALUE));
        } else if (productDto.getDescription().trim().length() == 0) {
            errors.add(new ErrorDto("description", EMPTY_STRING));
        }
        if (productDto.getName() == null){
            errors.add(new ErrorDto("name", NULL_VALUE));
        }
        return errors;
    }

}