package com.kdu.caching.service;

import com.kdu.caching.constants.ConstantVariables;
import com.kdu.caching.model.GeoCodeAllDataList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Service class responsible for making API calls to geocoding and reverse geocoding services.
 */
@Slf4j
@Service
public class ApiService {
    private Environment env;
    @Autowired
    public ApiService( Environment env){
        this.env=env;
    }

    WebClient.Builder builder=WebClient.builder();

    /**
     * Retrieves geocoding data array for the given address using the configured API key.
     * @param urlAddress The address for geocoding.
     * @return A GeoCodeAllDataList object containing the geocoding information.
     */
    public GeoCodeAllDataList getGeoCodeArray(String urlAddress) {
        String url=ConstantVariables.GEOCODING_URL_LINK+env.getProperty("api-key")+"&query="+urlAddress;
        log.debug(url);
        log.info("Third party api called for forward geo coding");
        return builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(GeoCodeAllDataList.class)
                .block();
    }

    /**
     * Retrieves reverse geocoding data for the given latitude and longitude using the configured API key.
     * @param urlLatitude  The latitude for reverse geocoding.
     * @param urlLongitude The longitude for reverse geocoding.
     * @return A GeoCodeAllDataList object containing the reverse geocoding information.
     */

    public GeoCodeAllDataList getReverseGeoCode(String urlLatitude, String urlLongitude) {
        String url= ConstantVariables.REVERSE_GEOCODING_URL_LINK +env.getProperty("api-key")+"&query="+urlLatitude+","+urlLongitude;
        log.debug(url);
        log.info("Third party api called for reverse geo coding");
        return builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(GeoCodeAllDataList.class)
                .block();
    }
}
