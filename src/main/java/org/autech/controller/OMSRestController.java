package org.autech.controller;

import lombok.extern.slf4j.Slf4j;
import org.autech.model.CreateNewOrderRequest;
import org.autech.model.GenericOrder;
import org.autech.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController("/oms")
public class OMSRestController {

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/getOrder")
    public ResponseEntity<GenericOrder> getOrderById(@RequestParam(name = "orderId") String orderId, @RequestAttribute(name = "userId") String userId){
        log.info("Get order called for id - {}", orderId);
        GenericOrder genericOrder = orderService.getOrder(orderId, userId);
        if(Objects.nonNull(genericOrder))
            return ResponseEntity.ok(genericOrder);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/getAllOrders")
    public ResponseEntity<List<GenericOrder>> getAllOrders(@RequestAttribute(name = "userId") String userId){
        log.info("Get all orders called");
        return ResponseEntity.ok(orderService.getAllOrders(userId));
    }

    @PostMapping(value = "/createOrder",
            consumes = "application/json",
            produces = "application/json",
            headers = "X-API-KEY")
    public ResponseEntity<GenericOrder> createOrder(@RequestBody CreateNewOrderRequest createNewOrderRequest, @RequestAttribute(name = "userId") Integer userId){
        log.info("Create order called");
        return ResponseEntity.ok(orderService.createNewOrder(createNewOrderRequest.getOrderType(), Integer.toString(userId)));
    }
}
