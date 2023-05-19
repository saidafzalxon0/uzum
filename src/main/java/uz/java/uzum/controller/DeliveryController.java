package uz.java.uzum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.java.uzum.dto.DeliveryDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.service.Impl.DeliveryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryServiceImpl deliveryService;

    @GetMapping
    public ResponseDto<List<DeliveryDto>> getAll(){
        return deliveryService.getAll();
    }

    @GetMapping("by-id")
    public ResponseDto<DeliveryDto> getById(@RequestParam Integer id){
        return deliveryService.getById(id);
    }

    @PostMapping
    public ResponseDto<DeliveryDto> add(@RequestBody DeliveryDto deliveryDto){
        return deliveryService.add(deliveryDto);
    }

    @PatchMapping
    public ResponseDto<DeliveryDto> update(@RequestBody DeliveryDto deliveryDto){
        return deliveryService.update(deliveryDto);
    }

    @DeleteMapping
    public ResponseDto<DeliveryDto> delete(@RequestParam Integer id){
        return deliveryService.delete(id);
    }
}
