package com.example.assesment2.service;

import com.example.assesment2.dao.AddressDao;
import com.example.assesment2.dao.UserDAO;
import com.example.assesment2.entities.Address;
import com.example.assesment2.entities.Users;
import com.example.assesment2.exceptions.customexceptions.NoEntityFoundWithGivenIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private UserDAO userDAO;

    public void addAddress(Address address,String userId){
        UUID userUUID=UUID.fromString(userId);
        Optional<Users> users=userDAO.findById(userUUID);
        if (users.isPresent()) {
            Users currentUser = users.get();
            currentUser.addAddress(address);
        } else {
            throw new NoEntityFoundWithGivenIdException("No shift user exist with given ID");
        }
        addressDao.save(address);
    }
    public void deleteAddress(UUID addressId){
        addressDao.deleteById(addressId);
    }
}
