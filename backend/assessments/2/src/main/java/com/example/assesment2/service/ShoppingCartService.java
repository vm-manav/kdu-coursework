package com.example.assesment2.service;

import com.example.assesment2.dao.InventoryDAO;
import com.example.assesment2.dao.ShoppingCartDAO;
import com.example.assesment2.dao.UserDAO;
import com.example.assesment2.entities.Inventory;
import com.example.assesment2.entities.ShoppingCart;
import com.example.assesment2.entities.Users;
import com.example.assesment2.exceptions.customexceptions.NoEntityFoundWithGivenIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private InventoryDAO inventoryDAO;

    public void addShoppingCart(UUID userId){
        ShoppingCart shoppingCart=new ShoppingCart();

        Optional<Users> users=userDAO.findById(userId);
        if (users.isPresent()) {
            Users currentUser = users.get();
            shoppingCart.setUsers(currentUser);
        } else {
            throw new NoEntityFoundWithGivenIdException("No user exist with given ID");
        }
    }

    public void addToCart(UUID productId,UUID cartId) {
        Optional<ShoppingCart> shoppingCart=shoppingCartDAO.findById(cartId);
        if (shoppingCart.isPresent()) {
            ShoppingCart currentCart = shoppingCart.get();
            Optional<Inventory> inventory=inventoryDAO.findById(productId);
            if (inventory.isPresent()) {
                Inventory currentProduct = inventory.get();
                currentCart.addToCart(currentProduct);
            } else {
                throw new NoEntityFoundWithGivenIdException("No Product exist with given ID");
            }
        } else {
            throw new NoEntityFoundWithGivenIdException("No Shopping Cart exist with given ID");
        }
    }

    public void deleteFromCart(UUID productId,UUID cartId) {
        Optional<ShoppingCart> shoppingCart=shoppingCartDAO.findById(cartId);
        if (shoppingCart.isPresent()) {
            ShoppingCart currentCart = shoppingCart.get();
            Optional<Inventory> inventory=inventoryDAO.findById(productId);
            if (inventory.isPresent()) {
                Inventory currentProduct = inventory.get();
                currentCart.removeFromCart(currentProduct);
            } else {
                throw new NoEntityFoundWithGivenIdException("No Product exist with given ID");
            }
        } else {
            throw new NoEntityFoundWithGivenIdException("No Shopping Cart exist with given ID");
        }
    }

    public void deleteCart(UUID cartId) {
        shoppingCartDAO.deleteById(cartId);
    }

    public ShoppingCart getShoppingCart(UUID cartId){
        Optional<ShoppingCart> shoppingCart=shoppingCartDAO.findById(cartId);
        if (shoppingCart.isPresent()) {
            ShoppingCart currentCart = shoppingCart.get();
            return currentCart;
        } else {
            throw new NoEntityFoundWithGivenIdException("No Shopping Cart exist with given ID");
        }
    }
}
