package com.brantapps.talks.meetup;

import com.brantapps.talks.meetup.attendee.AttendeeRepository;
import com.brantapps.talks.meetup.venue.Place;

import java.time.ZonedDateTime;
import java.util.UUID;

public class MeetUp {
    private UUID id;
    private final String eventName;
    private final ZonedDateTime eventTime;
    private final AttendeeRepository attendees;
    private Place place;

    public MeetUp(final UUID id,
                  final String name,
                  final ZonedDateTime dateTime,
                  final AttendeeRepository attendees) {
        this.id = id;
        this.eventName = name;
        this.eventTime = dateTime;
        this.attendees = attendees;
    }

    public String getEventName() {
        return eventName;
    }

    public Place getPlace() {
        return place;
    }

    public ZonedDateTime getEventTime() {
        return eventTime;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public int calculateWaitlistLength() {
        return Math.abs(place.getCapacity() - attendees.fetchAttendees(id).size());
    }
}
