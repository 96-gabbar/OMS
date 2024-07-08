package org.autech.service;

import org.autech.Exception.OMSException;
import org.autech.model.GenericOrder;
import org.autech.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public GenericOrder getOrder(String orderId, String userId){
        if(authenticationService.checkDataReadAccess(userId))
            return orderRepository.findById(orderId).orElse(null);
        throw new OMSException(String.format("User: %s not privileged to read data", userId));
    }

    public List<GenericOrder> getAllOrders(String userId){
        if(authenticationService.checkDataReadAccess(userId))
            return orderRepository.findAll();
        throw new OMSException(String.format("User: %s not privileged to read data", userId));
    }

    public GenericOrder createNewOrder(String orderType, String userId){
        if(authenticationService.checkWriteAccess(userId)) {
            GenericOrder order = GenericOrder.builder()
                    .createdBy(userId)
                    .updatedBy(userId)
                    .orderType(orderType)
                    .build();
            return orderRepository.save(order);
        }
        throw new OMSException(String.format("User: %s not authorized to create orders", userId));
    }
}
