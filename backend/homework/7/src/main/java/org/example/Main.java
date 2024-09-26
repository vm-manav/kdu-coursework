package org.example;
import org.example.config.MainConfig;
import org.example.factorymanager.FactoryLocation1;
import org.example.factorymanager.FactoryLocation2;
import org.example.managers.ComputationFunctions;
import org.example.utils.Vehicle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Logger logger = Logger.getLogger(String.valueOf(Main.class));
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MainConfig.class);

        FactoryLocation1 factoryLocation1=context.getBean(FactoryLocation1.class);
        FactoryLocation2 factoryLocation2=context.getBean(FactoryLocation2.class);

        ArrayList<Vehicle> arrayList1= (ArrayList<Vehicle>) factoryLocation1.getInventory().getVehiclesList();
        ArrayList<Vehicle> arrayList2= (ArrayList<Vehicle>) factoryLocation2.getInventory().getVehiclesList();

        Vehicle vehicle1=new ComputationFunctions().findVehicleWithTopPrice(arrayList1,arrayList2);
        Vehicle vehicle2=new ComputationFunctions().findVehicleWithLowestPrice(arrayList1,arrayList2);

        logger.info("Vehicle details for MAX price :\n"+"Price - "+vehicle1.getPrice()+"\nTyre - "+vehicle1.getTyre().getName()+"\nSpeaker - "+vehicle1.getSpeaker().getName());
        logger.info("Vehicle details for MIN price :\n"+"Price - "+vehicle2.getPrice()+"\nTyre - "+vehicle2.getTyre().getName()+"\nSpeaker - "+vehicle2.getSpeaker().getName());
    }
}