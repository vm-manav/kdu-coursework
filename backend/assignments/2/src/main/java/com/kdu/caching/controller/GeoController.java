package com.kdu.caching.controller;

import com.kdu.caching.dto.GeoCodeDTO;
import com.kdu.caching.service.GeoService;
import com.kdu.caching.validationcheck.LatitudeLongitudeCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class for handling geocoding and reverse geocoding requests.
 */
@RestController
public class GeoController {

    private final GeoService geoService;
    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService=geoService;
    }

    /**
     * Handles forward geocoding requests.
     * @param address The address to be geocoded.
     * @return A GeoCodeDTO containing the geocoding information.
     */
    @GetMapping("/geocoding")
    public GeoCodeDTO getForwardGeoCode(@RequestParam String address) {
        return geoService.forwardGeoCode(address);
    }

    /**
     * Handles reverse geocoding requests.
     *
     * @param latitude  The latitude for reverse geocoding.
     * @param longitude The longitude for reverse geocoding.
     * @return A String representing the reverse geocoding information.
     */
    @GetMapping("/reverse-geocoding")
    public String getReverseGeoCode(@RequestParam String latitude,@RequestParam String longitude) {
        LatitudeLongitudeCheck latitudeLongitudeCheck=new LatitudeLongitudeCheck();
        latitudeLongitudeCheck.checkLocationPoints(latitude,longitude);
        return geoService.backwardGeoCode(latitude,longitude);
    }

}
