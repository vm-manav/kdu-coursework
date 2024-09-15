package com.kdu.caching.service;

import com.kdu.caching.dto.GeoCodeDTO;
import com.kdu.caching.dto.ReverseGeoCodeDTO;
import com.kdu.caching.model.GeoCodeCommonData;
import com.kdu.caching.repository.GeoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoService {
    private GeoRepository geoRepository;
    @Autowired
    public GeoService(GeoRepository geoRepository) {
        this.geoRepository=geoRepository;
    }

    public GeoCodeDTO forwardGeoCode(String address) {
        GeoCodeCommonData geoCode= geoRepository.getForwardGeoCode(address);
        return covertToForwardDto(geoCode);
    }

    public String backwardGeoCode(String latitude,String longitude) {
        GeoCodeCommonData reverseGeoCode=geoRepository.getReverseGeoCode(latitude,longitude);
        ReverseGeoCodeDTO reverseGeoCodeDTO=covertToReverseDto(reverseGeoCode);
        return convertDtoToString(reverseGeoCodeDTO);
    }

    /**
     * Converts a GeoCodeCommonData object to a GeoCodeDTO.
     * @param geoCode The GeoCodeCommonData object.
     * @return A GeoCodeDTO containing the latitude and longitude.
     */
    public GeoCodeDTO covertToForwardDto(GeoCodeCommonData geoCode) {
        GeoCodeDTO geoCodeDTO=new GeoCodeDTO();
        geoCodeDTO.setLatitude(geoCode.getLatitude());
        geoCodeDTO.setLongitude(geoCode.getLongitude());
        return geoCodeDTO;
    }

    /**
     * Converts a GeoCodeCommonData object to a ReverseGeoCodeDTO.
     * @param reverseGeoCode The GeoCodeCommonData object for reverse geocoding.
     * @return A ReverseGeoCodeDTO containing detailed location information.
     */
    public ReverseGeoCodeDTO covertToReverseDto(GeoCodeCommonData reverseGeoCode) {
        ReverseGeoCodeDTO reverseGeoCodeDTO=new ReverseGeoCodeDTO();
        reverseGeoCodeDTO.setType(reverseGeoCode.getType());
        reverseGeoCodeDTO.setName(reverseGeoCode.getName());
        reverseGeoCodeDTO.setCountry(reverseGeoCode.getCountry());
        reverseGeoCodeDTO.setStreet(reverseGeoCode.getStreet());
        reverseGeoCodeDTO.setRegion(reverseGeoCode.getRegion());
        reverseGeoCodeDTO.setLocality(reverseGeoCode.getLocality());
        reverseGeoCodeDTO.setNeighbourhood(reverseGeoCode.getNeighbourhood());
        reverseGeoCodeDTO.setContinent(reverseGeoCode.getContinent());
        reverseGeoCodeDTO.setLabel(reverseGeoCode.getLabel());
        return reverseGeoCodeDTO;
    }

    /**
     * Converts a ReverseGeoCodeDTO to a String representation.
     * @param reverseGeoCodeDTO The ReverseGeoCodeDTO object.
     * @return A String representing the reverse geocoding information.
     */
    public String convertDtoToString(ReverseGeoCodeDTO reverseGeoCodeDTO) {
        return reverseGeoCodeDTO.getName()+","+reverseGeoCodeDTO.getCountry();
    }
}
