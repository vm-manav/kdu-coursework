package com.kdu.caching.validationcheck;

import com.kdu.caching.exceptions.customexceptions.InvalidLocationPointException;

public class LatitudeLongitudeCheck {
    public void checkLocationPoints(String latitude,String longitude) {
        if(!isLocationValid(latitude) || !isLocationValid(longitude)) {
            throw new InvalidLocationPointException("The location coordinates are not valid");
        }
    }

    /**
     * Checks if the provided location point is a valid numeric coordinate.
     * @param locationPoint The location point to be validated.
     * @return true if the location point is a valid numeric coordinate, false otherwise.
     */
    public boolean isLocationValid(String locationPoint) {
        int dotCount = 0;
        int minusCount = 0;
        for (char c : locationPoint.toCharArray()) {
            if (c == '.' && dotCount == 0) {
                dotCount++;
            } else if (c == '-' && minusCount == 0) {
                minusCount++;
            } else if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
