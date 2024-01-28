package com.kdu.caching.repository;

import com.kdu.caching.model.GeoCodeCommonData;
import com.kdu.caching.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;


/**
 * Repository class responsible for caching and fetching geocoding data using the AppService.
 */
@Slf4j
@Repository
public class GeoRepository {
    private AppService appService;
    @Autowired
    public GeoRepository(AppService appService) {
        this.appService=appService;
    }

    /**
     * Retrieves forward geocoding data for the given address, with caching support.
     * @param address The address to be geocoded.
     * @return A GeoCodeCommonData object containing the geocoding information.
     * also ignores if the address contains term goa
     */
    @Cacheable(value = "geocoding", cacheManager = "cacheManager",condition = "!#address.equals('goa')")
    public GeoCodeCommonData getForwardGeoCode(String address) {
        log.info("coordinates for provided address fetched");
        return appService.getGeoCode(address);
    }

    /**
     * Retrieves reverse geocoding data for the given latitude and longitude, with caching support.
     * @param latitude  The latitude for reverse geocoding.
     * @param longitude The longitude for reverse geocoding.
     * @return A GeoCodeCommonData object containing the reverse geocoding information.
     */
    @Cacheable(value = "reverse-geocoding", cacheManager = "cacheManager")
    public GeoCodeCommonData getReverseGeoCode(String latitude ,String longitude) {
        log.info("Address for provided coordinates fetched");
        return appService.getReverseGeoCode(latitude,longitude);
    }
}
