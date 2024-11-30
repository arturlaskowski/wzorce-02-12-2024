package wzorce.cqrs.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wzorce.cqrs.application.command.OrderCommandService;
import wzorce.cqrs.application.command.dto.ApproveOrderCommand;
import wzorce.cqrs.application.command.dto.PayOrderCommand;
import wzorce.cqrs.application.query.OrderQueryService;
import wzorce.cqrs.domain.OrderId;
import wzorce.cqrs.domain.OrderStatus;
import wzorce.cqrs.web.dto.CreateOrderRequest;
import wzorce.cqrs.web.dto.GetOrderByIdQuery;
import wzorce.cqrs.web.dto.OrderPageQuery;
import wzorce.cqrs.web.dto.TrackingOrderQuery;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
class OrderController {

    private final OrderCommandService orderCommandService;
    private final OrderQueryService orderQueryService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        var createOrderCommand = OrderApiMapper.mapToCreateOrderCommand(createOrderRequest);
        var orderId = orderCommandService.createOrder(createOrderCommand);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderId.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<Void> payOrder(@PathVariable UUID id) {
        var payOrderCommand = new PayOrderCommand(new OrderId(id));
        orderCommandService.pay(payOrderCommand);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approveOrder(@PathVariable UUID id) {
        var approveOrderCommand = new ApproveOrderCommand(new OrderId(id));
        orderCommandService.approve(approveOrderCommand);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public GetOrderByIdQuery getOrderById(@PathVariable UUID id) {
        return orderQueryService.getOrderById(id);
    }

    @GetMapping("/{id}/track")
    public TrackingOrderQuery trackOrder(@PathVariable UUID id) {
        return orderQueryService.trackOrder(id);
    }

    @GetMapping
    public ResponseEntity<Page<OrderPageQuery>> getAllOrders(
            @SortDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<OrderPageQuery> orders = orderQueryService.findAllOrders(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/pending")
    public ResponseEntity<Page<OrderPageQuery>> getPendingOrders(
            @SortDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<OrderPageQuery> pendingOrders = orderQueryService.findOrdersByStatus(OrderStatus.PENDING, pageable);
        return ResponseEntity.ok(pendingOrders);
    }
}
