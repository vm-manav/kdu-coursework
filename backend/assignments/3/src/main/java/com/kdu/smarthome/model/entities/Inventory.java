    package com.kdu.smarthome.model.entities;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.sql.Timestamp;

    /**
     * Entity class representing an inventory item in the Smart Home application.
     */

    @Entity
    @Data
    @Table(name = "inventory")
    public class Inventory {

        @Id
        @Column(name = "kickston_id")
        private String kickstonId;

        @Column(name = "device_username")
        private String deviceUserName;

        @Column(name = "device_password")
        private String devicePassword;

        @Column(name = "manufactore_date_time")
        private Timestamp manufactureDateTime;

        @Column(name = "manufactore_factory_place")
        private String manufacturePlace;

        private Boolean registered;
    }
