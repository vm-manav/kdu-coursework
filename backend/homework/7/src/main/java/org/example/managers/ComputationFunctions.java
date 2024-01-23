package org.example.managers;

import org.example.utils.Vehicle;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


public class ComputationFunctions {

    public Vehicle findVehicleWithTopPrice(List<Vehicle> vehicleList1, List<Vehicle> vehicleList2) {
        return Stream.concat(vehicleList1.stream(), vehicleList2.stream())
                .max(Comparator.comparingDouble(Vehicle::getPrice)).orElse(null);
    }
    public Vehicle findVehicleWithLowestPrice(List<Vehicle> vehicleList1, List<Vehicle> vehicleList2) {
        return Stream.concat(vehicleList1.stream(), vehicleList2.stream())
                .min(Comparator.comparingDouble(Vehicle::getPrice)).orElse(null);
    }
}
