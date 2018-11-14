package com.brantapps.talks.meetup.fixture;

import com.brantapps.talks.meetup.venue.Address;
import com.brantapps.talks.meetup.venue.GeoPoint;
import com.brantapps.talks.meetup.venue.Place;

public class VenueFixture extends AbstractFixture<Place> {
    private DataGenerator dataGenerator;
    private Integer capacity;
    private Address address;

    VenueFixture(final DataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }

    public static VenueFixture getInstance() {
        final DataGenerator dataGenerator = new DataGenerator();
        return new VenueFixture(dataGenerator);
    }

    public VenueFixture withCapacity(final int capacity) {
        this.capacity = capacity;
        return this;
    }


    public VenueFixture withAddress(final Address address) {
        this.address = address;
        return this;
    }

    @Override
    public Place build() {
        Integer fixtureCapacity;
        Address fixtureAddress;

        if (capacity == null) {
            fixtureCapacity = dataGenerator.nextInt(1, 100);
        } else {
            fixtureCapacity = capacity;
        }

        if (address == null) {
            fixtureAddress = new Address(
                    dataGenerator.nextOneOf(
                            "77, Weston Road",
                            "2nd Floor Broad Quay House",
                            "1-3, Broad Plain",
                            "Framework, 35 Kings Street"),
                    dataGenerator.nextOneOf(
                            "Long Ashton",
                            "Broad Quay House",
                            "Bristol",
                            "South Bristol"),
                    dataGenerator.nextOneOf("Bristol", "London", "Swansea", "Manchester"),
                    new GeoPoint(dataGenerator.nextInt(-90, 90), dataGenerator.nextInt(-180, 180))
            );
        } else {
            fixtureAddress = address;
        }

        return new Place(fixtureAddress, fixtureCapacity);
    }
}
