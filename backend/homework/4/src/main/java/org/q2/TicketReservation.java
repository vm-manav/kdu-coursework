package org.q2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
public class TicketReservation {
    private static final int CONFIRMEDLIST_LIMIT = 10;
    private static final int WAITINGLIST_LIMIT = 3;
    private final List<Passenger> confirmedList = new ArrayList<>();
    private final Deque<Passenger> waitingList = new ArrayDeque<>();
    public List<Passenger> getConfirmedList() {
        return confirmedList;
    }
    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass, String confirmationNumber) {
        Passenger passenger = new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);
        if(confirmedList.size()<CONFIRMEDLIST_LIMIT){
            confirmedList.add(passenger);
            return true;
        } else if (waitingList.size()<WAITINGLIST_LIMIT) {
            waitingList.add(passenger);
            return true;
        }
        return false;
    }
    public boolean cancel(String confirmationNumber) {
        Iterator<Passenger> ConfirmedListIterator=confirmedList.iterator();
        Iterator<Passenger> WaitingListIterator=waitingList.iterator();
        if(removePassenger(ConfirmedListIterator,confirmationNumber)){
            confirmedList.add(waitingList.poll());
            return true;
        } else return removePassenger(WaitingListIterator, confirmationNumber);
    }
    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        while(iterator.hasNext()){
            if (iterator.next().getConfirmationNumber().equals(confirmationNumber)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
