package org.example.utils;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private ArrayList<Vehicle> vehiclesList;

    public void setVehiclesList(List<Vehicle> vehiclesList) {
        this.vehiclesList = (ArrayList<Vehicle>) vehiclesList;
    }

    public List<Vehicle> getVehiclesList() {
        return vehiclesList;
    }
    public void addVehicle(Vehicle vehicle) {
        this.vehiclesList.add(vehicle);
    }
}
