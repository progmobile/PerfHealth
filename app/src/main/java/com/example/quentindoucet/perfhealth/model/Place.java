package com.example.quentindoucet.perfhealth.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by alexis on 02/11/2017.
 */

public class Place implements Serializable {

    private String name;
    private String action;
    private Position location;

    public Place() {
        this("Lieu ?", "Action ?", new LatLng(0, 0));

    }

    public Place(String name, String action, LatLng location) {
        this.name = name;
        this.action = action;
        if (location == null) location = new LatLng(0, 0);
        this.location = new Position(location.latitude, location.longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (getName() != null ? !getName().equals(place.getName()) : place.getName() != null)
            return false;
        if (getAction() != null ? !getAction().equals(place.getAction()) : place.getAction() != null)
            return false;
        return getLocation() != null ? getLocation().equals(place.getLocation()) : place.getLocation() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getAction() != null ? getAction().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LatLng getLocation() {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    public void setLocation(Position location) {
        this.location = location;
    }

    public static class Position implements Serializable {
        private double latitude;
        private double longitude;

        public Position() {

        }

        public Position(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }

}
