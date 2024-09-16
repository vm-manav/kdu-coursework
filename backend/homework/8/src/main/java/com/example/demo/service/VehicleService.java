package com.example.demo.service;

import com.example.demo.dto.VehicleDTO;
import com.example.demo.module.Speaker;
import com.example.demo.module.Tyre;
import com.example.demo.module.Vehicle;
import com.example.demo.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;
    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository=vehicleRepository;
    }
    /*
    * method to search for a vehicle with vehicle name and return vehicle dto from it
    */
    public VehicleDTO getVehicle(String vehicleName) {
        Vehicle vehicle=vehicleRepository.getVehicleFromDatabase(vehicleName);
        return convertEntityToDTO(vehicle);
    }
    /*
     * method to search for a vehicle with vehicle name and delete it
     */
    public void deleteVehicle(String vehicleName) {
        vehicleRepository.deleteVehicleFromDatabase(vehicleName);
    }
    /*
     * method to insert a vehicle
     */
    public void insertVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle=convertDTOToEntity(vehicleDTO);
        vehicleRepository.addVehicleToDatabase(vehicle);
    }
    /*
     * method to search for a vehicle with vehicle name update all the values
     */
    public void updateVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle=convertDTOToEntity(vehicleDTO);
        vehicleRepository.updateVehicleToDatabase(vehicle);
    }

    /*
     * method to covert data from vehicle type to vehicleDTO type
     */
    public VehicleDTO convertEntityToDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO=new VehicleDTO();
        vehicleDTO.setVehicleName(vehicle.getVehicleName());
        vehicleDTO.setPrice(vehicle.getPrice());
        vehicleDTO.setSpeakerName(vehicle.getSpeaker().getSpeakerName());
        vehicleDTO.setTyreName(vehicle.getTyre().getTyreName());
        return vehicleDTO;
    }
    /*
     * method to covert data from vehicleDTO type to vehicle type
     */
    public Vehicle convertDTOToEntity(VehicleDTO vehicleDTO) {
        Vehicle vehicle=new Vehicle();
        Speaker speaker=new Speaker();
        speaker.setSpeakerName(vehicleDTO.getSpeakerName());
        Tyre tyre=new Tyre();
        tyre.setTyreName(vehicleDTO.getTyreName());
        vehicle.setVehicleName(vehicleDTO.getVehicleName());
        vehicle.setPrice(vehicleDTO.getPrice());
        vehicle.setSpeaker(speaker);
        vehicle.setTyre(tyre);
        return vehicle;
    }
}
