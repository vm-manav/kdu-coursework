package com.example.demo.repository;

import com.example.demo.module.Vehicle;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class VehicleRepository {
    private ArrayList<Vehicle> vehiclesList;
    @PostConstruct
    public void initilize(){
        vehiclesList=new ArrayList<>();
    }

    Vehicle vehicle=new Vehicle();
    public void addVehicleToDatabase(Vehicle vehicle) {
        vehiclesList.add(vehicle);
    }
    public void updateVehicleToDatabase(Vehicle newVehicle) {
        for(Vehicle currentVehicle:vehiclesList) {
            if(currentVehicle.getVehicleName().equalsIgnoreCase(newVehicle.getVehicleName())) {
                currentVehicle.setPrice(newVehicle.getPrice());
                currentVehicle.setSpeaker(newVehicle.getSpeaker());
                currentVehicle.setTyre(newVehicle.getTyre());
                return;
            }
        }
    }
    public void deleteVehicleFromDatabase(String vehicleName) {
        for(Vehicle currentVehicle:vehiclesList) {
            if(currentVehicle.getVehicleName().equalsIgnoreCase(vehicleName)) {
                vehiclesList.remove(currentVehicle);
                return;
            }
        }
    }
    public Vehicle getVehicleFromDatabase(String vehicleName) {
        for(Vehicle currentVehicle:vehiclesList) {
            if(currentVehicle.getVehicleName().equalsIgnoreCase(vehicleName)) {
                return currentVehicle;
            }
        }
        return null;
    }
}
