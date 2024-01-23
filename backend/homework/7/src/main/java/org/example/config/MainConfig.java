package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ InventoryService.class, SpeakerConfig.class, TyreConfig.class })
@ComponentScan(basePackages = "org.example.factorymanager")
@ComponentScan(basePackages = "org.example.managers")
public class MainConfig {
}
