package org.example.config;

import org.example.beans.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpeakerService {
    @Bean("bose")
    public Speaker generateSpeakerBose() {
        return new Speaker("Bose",20000.00);
    }

    @Bean("sony")
    public Speaker generateSpeakerSony() {
        return new Speaker("Sony",12000.00);
    }
}
