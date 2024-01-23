package org.example.factorymanager;

import jakarta.annotation.PostConstruct;
import org.example.utils.Inventory;
import org.example.utils.Speaker;
import org.example.managers.SpeakerService;
import org.example.utils.Tyre;
import org.example.managers.TyreService;
import org.example.utils.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class FactoryLocation2 {
    private double percentage=0.1;
    private String location="Location1";

    private TyreService tyreService;
    private SpeakerService speakerService;
    private Inventory inventory;

    @PostConstruct
    public void initialize() {
        generateAllVehicles();
    }

    public String getLocation() {
        return location;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public SpeakerService getSpeakerService() {
        return speakerService;
    }

    public TyreService getTyreService() {
        return tyreService;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setSpeakerService(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    public void setTyreService(TyreService tyreService) {
        this.tyreService = tyreService;
    }

    @Autowired
    public FactoryLocation2(TyreService tyreService, SpeakerService speakerService , Inventory inventory) {
        this.tyreService=tyreService;
        this.speakerService=speakerService;
        this.inventory=inventory;
    }
    public Vehicle createVehicle(Speaker speaker, Tyre tyre) {
        Vehicle vehicle=new Vehicle(speaker,tyre);
        vehicle.setPrice(vehicle.getPrice()+vehicle.getPrice()*percentage);
        return vehicle;
    }

    public void generateAllVehicles() {
        ArrayList<Vehicle> vehiclesList = new ArrayList<>();
        AnnotationConfigApplicationContext contextSpeaker=new AnnotationConfigApplicationContext(SpeakerService.class);
        SpeakerService speakerServiceVar=contextSpeaker.getBean(SpeakerService.class);
        Speaker speaker1 = speakerServiceVar.getSpeaker("Bose");
        Speaker speaker2 =speakerServiceVar.getSpeaker("Sony");

        AnnotationConfigApplicationContext contextTyre=new AnnotationConfigApplicationContext(TyreService.class);
        TyreService tyreServiceVar=contextTyre.getBean(TyreService.class);
        Tyre tyre1 = tyreServiceVar.getTyre("Bridgestone");
        Tyre tyre2 = tyreServiceVar.getTyre("MRF");

        vehiclesList.add(createVehicle(speaker1,tyre1));
        vehiclesList.add(createVehicle(speaker2,tyre1));
        vehiclesList.add(createVehicle(speaker1,tyre2));
        vehiclesList.add(createVehicle(speaker2,tyre2));

        inventory.setVehiclesList(vehiclesList);
    }
}
