package uz.java.uzum.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.java.uzum.dto.OrderDetailDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.service.OrderDetailService;

@RestController
@RequestMapping("order-details")
@RequiredArgsConstructor

public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @PostMapping("add")
    public ResponseDto<OrderDetailDto> add(@RequestBody @Valid OrderDetailDto orderDetailDto){
        return orderDetailService.add(orderDetailDto);
    }

    @GetMapping()
    public ResponseDto<Page<OrderDetailDto>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "10") Integer size){
        return orderDetailService.getAll(page, size);
    }

    @GetMapping("{id}")
    public ResponseDto<OrderDetailDto> getById(@PathVariable Integer id){
        return orderDetailService.getById(id);
    }

    @PatchMapping("update")
    public ResponseDto<OrderDetailDto> update(@RequestBody OrderDetailDto orderDetailDto){
        return orderDetailService.update(orderDetailDto);
    }

    @DeleteMapping()
    public ResponseDto<Void> delete(@RequestParam Integer id){
        return orderDetailService.delete(id);
    }

}
