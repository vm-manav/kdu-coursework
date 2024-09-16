package org.example.config;

import jakarta.annotation.PostConstruct;
import org.example.beans.Speaker;
import org.example.beans.Tyre;
import org.example.beans.Vehicle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;

@Component
public class VehicleService {
    private ArrayList<Vehicle> arrayVehicles;

    @PostConstruct
    public void initialize(){
        arrayVehicles= new ArrayList<>();
    }

    public void addVehicle(Speaker speaker, Tyre tyre) {
        Vehicle vehicle=new Vehicle(speaker,tyre);
        arrayVehicles.add(vehicle);
    }

    public Vehicle findVehicleWithTopPrice() {
        return arrayVehicles.stream()
                .max(Comparator.comparingDouble(Vehicle::getPrice)).orElse(null);
    }
}
