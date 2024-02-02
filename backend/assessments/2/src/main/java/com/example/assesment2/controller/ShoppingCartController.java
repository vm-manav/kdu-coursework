package com.example.assesment2.controller;

import com.example.assesment2.entities.ShoppingCart;
import com.example.assesment2.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/create-shopping-cart")
    public ResponseEntity<String> addShoppingCart(@RequestParam String userId){
        UUID uuid=UUID.fromString(userId);
        shoppingCartService.addShoppingCart(uuid);
        return ResponseEntity.ok("Shopping Cart Created successfully");
    }
    @GetMapping("/get-shopping-cart")
    public ShoppingCart getShoppingCart(@RequestParam String cartId){
        UUID uuid=UUID.fromString(cartId);
        return shoppingCartService.getShoppingCart(uuid);
    }
    @DeleteMapping("/remove-shopping-cart")
    public ResponseEntity<String> deleteShoppingCart(@RequestParam String cartId){
        UUID uuid=UUID.fromString(cartId);
        shoppingCartService.deleteCart(uuid);
        return ResponseEntity.ok("Shopping Cart deleted successfully");
    }
    @PutMapping("/add-to-cart")
    public ResponseEntity<String> addProductToCart(@RequestParam String productId,@RequestParam String cartId){
        UUID productUUID=UUID.fromString(productId);
        UUID cardUUID=UUID.fromString(cartId);
        shoppingCartService.addToCart(productUUID,cardUUID);
        return ResponseEntity.ok("Product added to shopping cart successfully");
    }
    @PutMapping("/remove-from-cart")
    public ResponseEntity<String> deleteFromCart(@RequestParam String productId,@RequestParam String cartId){
        UUID productUUID=UUID.fromString(productId);
        UUID cardUUID=UUID.fromString(cartId);
        shoppingCartService.deleteFromCart(productUUID,cardUUID);
        return ResponseEntity.ok("Product deleted from shopping cart successfully");
    }
}
