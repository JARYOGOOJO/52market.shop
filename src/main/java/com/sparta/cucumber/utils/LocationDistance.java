package com.sparta.cucumber.utils;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LocationDistance {

    public double distance(Double lat1, Double lon1, Double lat2, Double lon2, String unit) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (Objects.equals(unit, "kilometer")) {
            dist = dist * 1.609344;
        } else if (Objects.equals(unit, "meter")) {
            dist = dist * 1609.344;
        }
        return dist;
    }

    // This function converts decimal degrees to radians
    public double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    public double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}

// Referenced by https://www.geodatasource.com/resources/tutorials/how-to-calculate-the-distance-between-2-locations-using-java/