package org.example;
import org.example.beans.Speaker;
import org.example.beans.Tyre;
import org.example.beans.Vehicle;
import org.example.config.SpeakerService;
import org.example.config.TyreService;
import org.example.config.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Logger logger = Logger.getLogger(String.valueOf(Main.class));
    public static void main(String[] args) {

        AnnotationConfigApplicationContext contextTyre=new AnnotationConfigApplicationContext(TyreService.class);
        AnnotationConfigApplicationContext contextSpeaker=new AnnotationConfigApplicationContext(SpeakerService.class);
        AnnotationConfigApplicationContext contextVehicle=new AnnotationConfigApplicationContext(VehicleService.class);

        Speaker speaker1=contextSpeaker.getBean("bose", Speaker.class);
        Speaker speaker2=contextSpeaker.getBean("sony", Speaker.class);

        Tyre tyre1=contextTyre.getBean("bridgestone", Tyre.class);
        Tyre tyre2=contextTyre.getBean("mrf", Tyre.class);

        VehicleService vehicle=contextVehicle.getBean(VehicleService.class);

        vehicle.addVehicle(speaker1,tyre1);
        vehicle.addVehicle(speaker2,tyre1);
        vehicle.addVehicle(speaker1,tyre2);
        vehicle.addVehicle(speaker2,tyre2);

        Vehicle vehicleWithMaxPrice=vehicle.findVehicleWithTopPrice();
        logger.info("Details For Vehicle With Maximum price\n"+"Price : "
                +vehicleWithMaxPrice.getPrice()+"\nTyre : "+vehicleWithMaxPrice.getTyre().getName()+"\nSpeaker : "
                +vehicleWithMaxPrice.getSpeaker().getName());
    }
}