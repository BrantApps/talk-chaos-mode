package com.brantapps.talks.meetup.venue;

public class Address {
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private GeoPoint geoPoint;

    public Address(final String addressLine1,
            final String addressLine2,
            final String city,
            final GeoPoint geoPoint) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.geoPoint = geoPoint;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(final GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }
}
