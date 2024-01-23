package org.example.managers;
import org.example.utils.Speaker;
import org.springframework.stereotype.Component;

@Component
public class SpeakerService {
    public Speaker getSpeaker(String speakerName) {
        if(speakerName.equalsIgnoreCase("Bose")) {
            return new Speaker(speakerName,20000.00);
        } else if (speakerName.equalsIgnoreCase("Sony")) {
            return new Speaker(speakerName,12000.00);
        } else {
            return null;
        }
    }
}
