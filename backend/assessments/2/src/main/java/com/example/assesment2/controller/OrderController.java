package com.example.assesment2.controller;

import com.example.assesment2.entities.Orders;
import com.example.assesment2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestParam String userId,@RequestParam String shoppingCartId,@RequestParam String addressId){
        UUID userUUID=UUID.fromString(userId);
        UUID cartUUID=UUID.fromString(shoppingCartId);
        UUID addressUUID=UUID.fromString(addressId);
        orderService.addOrder(userUUID,cartUUID,addressUUID);
        return ResponseEntity.ok("Order Created successfully");
    }
    @GetMapping("/get-order")
    public Orders getOrder(@RequestParam String orderId){
        UUID uuid=UUID.fromString(orderId);
        return orderService.getOrder(uuid);
    }
    @DeleteMapping("/remove-shopping-cart")
    public ResponseEntity<String> deleteOrder(@RequestParam String orderId){
        UUID uuid=UUID.fromString(orderId);
        orderService.deleteOrder(uuid);
        return ResponseEntity.ok("Order deleted successfully");
    }
}
