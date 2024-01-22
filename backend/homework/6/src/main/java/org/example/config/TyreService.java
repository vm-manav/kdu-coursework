package org.example.config;

import org.example.beans.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TyreService {
    @Bean("bridgestone")
    public Tyre generateTyreBridgestone() {
        return new Tyre("Bridgestone",8000.00);
    }

    @Bean("mrf")
    public Tyre generateTyreMRF() {
        return new Tyre("Mrf",6000.00);
    }
}
