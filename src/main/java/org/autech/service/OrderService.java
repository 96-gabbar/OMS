package org.autech.service;

import org.autech.model.GenericOrder;
import org.autech.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public GenericOrder getOrder(String orderId){
        return orderRepository.getReferenceById(orderId);
    }

    public List<GenericOrder> getAllOrders(){
        return orderRepository.findAll();
    }

    public GenericOrder createNewOrder(String orderType){
        GenericOrder order = GenericOrder.builder()
                .createdBy("ashu")
                .updatedBy("ashu")
                .orderType(orderType)
                .build();
        return orderRepository.save(order);
    }
}
