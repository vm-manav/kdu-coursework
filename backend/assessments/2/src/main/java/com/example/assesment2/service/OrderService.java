package com.example.assesment2.service;

import com.example.assesment2.dao.AddressDao;
import com.example.assesment2.dao.OrderDAO;
import com.example.assesment2.dao.ShoppingCartDAO;
import com.example.assesment2.dao.UserDAO;
import com.example.assesment2.entities.Address;
import com.example.assesment2.entities.Orders;
import com.example.assesment2.entities.ShoppingCart;
import com.example.assesment2.entities.Users;
import com.example.assesment2.exceptions.customexceptions.NoEntityFoundWithGivenIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    @Autowired
    private AddressDao addressDao;

    public void addOrder(UUID userId,UUID shoppingCartId,UUID addressId){
        Orders order=new Orders();

        Optional<Users> users=userDAO.findById(userId);
        if (users.isPresent()) {
            Users currentUser = users.get();
            order.setUsers(currentUser);
        } else {
            throw new NoEntityFoundWithGivenIdException("No user exist with given ID");
        }

        Optional<ShoppingCart> shoppingCart=shoppingCartDAO.findById(shoppingCartId);
        if (shoppingCart.isPresent()) {
            ShoppingCart shoppingCart1 = shoppingCart.get();
            order.setShoppingCart(shoppingCart1);
        } else {
            throw new NoEntityFoundWithGivenIdException("No Shopping cart exist with given ID");
        }

        Optional<Address> address=addressDao.findById(addressId);
        if (address.isPresent()) {
            Address address1 = address.get();
            order.setAddress(address1);
        } else {
            throw new NoEntityFoundWithGivenIdException("No address cart exist with given ID");
        }
    }

    public void deleteOrder(UUID orderId){
        orderDAO.deleteById(orderId);
    }

    public Orders getOrder(UUID orderId) {
        Optional<Orders> orders=orderDAO.findById(orderId);
        if (orders.isPresent()) {
            Orders orders1 = orders.get();
            return orders1;
        } else {
            throw new NoEntityFoundWithGivenIdException("No order exist with given ID");
        }
    }
}
