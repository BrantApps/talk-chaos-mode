package com.brantapps.talks.meetup.fixture;

import com.brantapps.talks.meetup.MeetUp;
import com.brantapps.talks.meetup.attendee.Attendee;
import com.brantapps.talks.meetup.attendee.AttendeeRepository;
import com.brantapps.talks.meetup.venue.Place;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MeetUpFixture extends AbstractFixture<MeetUp> {
    private List<Attendee> attendees;
    private AttendeeFixture attendeeFixture;
    private DataGenerator dataGenerator;
    private VenueFixture venueFixture;
    private UUID uuid;
    private ZonedDateTime time;
    private String name;
    private Boolean hasVenue;
    private Place venue;

    private MeetUpFixture(
            final DataGenerator dataGenerator,
            final AttendeeFixture attendeeFixture,
            final VenueFixture venueFixture
    ) {
        this.attendeeFixture = attendeeFixture;
        this.dataGenerator = dataGenerator;
        this.venueFixture = venueFixture;
    }

    public static MeetUpFixture getInstance() {
        final DataGenerator dataGenerator = new DataGenerator();
        return new MeetUpFixture(
                dataGenerator,
                new AttendeeFixture(dataGenerator),
                new VenueFixture(dataGenerator)
        );
    }


    public MeetUpFixture withId(final UUID uuid) {
        this.uuid = uuid;
        return this;
    }


    public MeetUpFixture withName(final String name) {
        this.name = name;
        return this;
    }

    public MeetUpFixture atTime(final ZonedDateTime time) {
        this.time = time;
        return this;
    }

    public MeetUpFixture hasVenue() {
        this.hasVenue = true;
        return this;
    }

    public MeetUpFixture noVenue() {
        this.hasVenue = false;
        return this;
    }

    public MeetUpFixture atVenue(final Place place) {
        hasVenue();
        this.venue = place;
        return this;
    }

    public MeetUpFixture withAttendeeCount(final int numberOfAttendees) {
        attendees = new ArrayList<>();
        for (int i = 0; i < numberOfAttendees; i++) {
            attendees.add(attendeeFixture.build());
        }

        return this;
    }

    @Override
    public MeetUp build() {
        final UUID fixtureUuid;
        final String fixtureName;
        final ZonedDateTime fixtureTime;
        final AttendeeRepository mockAttendeeRepository = mock(AttendeeRepository.class);

        if (uuid == null) {
            fixtureUuid = UUID.randomUUID();
        } else {
            fixtureUuid = uuid;
        }

        if (name == null) {
            fixtureName = dataGenerator.nextString(30);
        } else {
            fixtureName = name;
        }


        if (time == null) {
            fixtureTime = dataGenerator.nextFutureTimeNotToday();
        } else {
            fixtureTime = time;
        }

        if (attendees != null) {
            when(mockAttendeeRepository
                    .fetchAttendees(eq(fixtureUuid)))
                    .thenReturn(attendees);
        }

        final MeetUp meetUp = new MeetUp(
                fixtureUuid,
                fixtureName,
                fixtureTime,
                mockAttendeeRepository
        );

        if (hasVenue == null && dataGenerator.nextBoolean()) {
            // Add a venue sometimes
            meetUp.setPlace(venueFixture.build());
        } else if (hasVenue) {
            if (venue == null) {
                // Make a venue up.
                meetUp.setPlace(venueFixture.build());
            } else {
                // Add the specified venue
                meetUp.setPlace(venue);
            }
        }


        return meetUp;
    }
}
