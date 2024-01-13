package org.q2;

import org.Logger;

public class Main {
    public static void main(String[] args) {
        TicketReservation ticketReservation=new TicketReservation();
        boolean var1=ticketReservation.bookFlight("Manav","Verma",21,"Male","Buisness","C8");
        if(var1){
            Logger.infoMessage("BOOKED ");
        }
        else {
            Logger.infoMessage("NOT BOOKED");
        }

        boolean var2=ticketReservation.cancel("C8");
        if(var2){
            Logger.infoMessage("Cancelled");
        }
        else {
            Logger.infoMessage("NOT Cancelled");
        }

    }
}
