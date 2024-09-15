package com.kdu.caching.service;

import com.kdu.caching.exceptions.customexceptions.AddressNotExistException;
import com.kdu.caching.model.GeoCodeCommonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppService {
    private ApiService apiService;
    @Autowired
    public AppService(ApiService apiService) {
        this.apiService=apiService;
    }

    /**
     * Retrieves geocoding data for the given address.
     * @return A GeoCodeCommonData object containing the geocoding information in this case this is the first object found from the array .
     * @throws AddressNotExistException If no coordinates are found for the given address.
     */
    public GeoCodeCommonData getGeoCode(String urlAddress) {
        GeoCodeCommonData[] geoCodeCommonData=apiService.getGeoCodeArray(urlAddress).getDataArray();
        if(geoCodeCommonData.length==0) {
            throw new AddressNotExistException("No coordinates found with respect to the given address");
        }
        else return geoCodeCommonData[0];
    }

    /**
     * Retrieves reverse geocoding data for the given latitude and longitude.
     * @return A GeoCodeCommonData object containing the reverse geocoding information in this case this is the first object found from the array.
     * @throws AddressNotExistException If no coordinates are found for the given latitude and longitude.
     */
    public GeoCodeCommonData getReverseGeoCode(String urlLatitude, String urlLongitude) {
        GeoCodeCommonData[] geoCodeCommonData= apiService.getReverseGeoCode(urlLatitude,urlLongitude).getDataArray();
        if(geoCodeCommonData.length==0) {
            throw new AddressNotExistException("No coordinates found with respect to the given address");
        }
        else return geoCodeCommonData[0];
    }
}
