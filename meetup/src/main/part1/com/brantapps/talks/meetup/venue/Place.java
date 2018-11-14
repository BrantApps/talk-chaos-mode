package com.brantapps.talks.meetup.venue;

public class Place {
    private final Address address;
    private final int capacity;
    private String name;

    public Place(Address address, int capacity) {
        this.address = address;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }
}
