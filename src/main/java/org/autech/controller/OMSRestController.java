package org.autech.controller;

import lombok.extern.slf4j.Slf4j;
import org.autech.model.CreateNewOrderRequest;
import org.autech.model.GenericOrder;
import org.autech.model.Order;
import org.autech.repository.OrderRepository;
import org.autech.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import java.util.List;

@Slf4j
@RestController("/oms")
public class OMSRestController {

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/getOrder")
    public ResponseEntity<GenericOrder> getOrderById(@RequestParam(name = "orderId") String orderId){
        log.info("Get order called for id - {}", orderId);
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @GetMapping(value = "/getAllOrders")
    public ResponseEntity<List<GenericOrder>> getAllOrders(){
        log.info("Get all orders called");
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping(value = "/createOrder",
            consumes = "application/json",
            produces = "application/json",
            headers = "X-API-KEY")
    public ResponseEntity<GenericOrder> createOrder(@RequestBody CreateNewOrderRequest createNewOrderRequest){
        log.info("Create order called");
        return ResponseEntity.ok(orderService.createNewOrder(createNewOrderRequest.getOrderType()));
    }
}
