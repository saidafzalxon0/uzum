package uz.java.uzum.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.java.uzum.dto.OrderItemDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.service.OrderItemsService;


@RestController
@RequestMapping("order-item")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemsService orderItemsService;

    @PostMapping("add")
    public ResponseDto<OrderItemDto> add(@RequestBody @Valid OrderItemDto orderItemsDto){
        return orderItemsService.add(orderItemsDto);
    }

    @GetMapping()
    public ResponseDto<Page<OrderItemDto>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer size){
        return orderItemsService.getAll(page, size);
    }

    @GetMapping("{id}")
    public ResponseDto<OrderItemDto> getById(@PathVariable Integer id){
        return orderItemsService.getById(id);
    }

    @PatchMapping("update")
    public ResponseDto<OrderItemDto> update(@RequestBody OrderItemDto orderItemsDto){
        return orderItemsService.update(orderItemsDto);
    }

    @DeleteMapping()
    public ResponseDto<Void> delete(@RequestParam Integer id){
        return orderItemsService.delete(id);
    }

}
