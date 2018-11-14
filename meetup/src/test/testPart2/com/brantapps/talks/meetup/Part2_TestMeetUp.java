package com.brantapps.talks.meetup;

import com.brantapps.talks.meetup.fixture.MeetUpFixture;
import com.brantapps.talks.meetup.fixture.VenueFixture;
import org.junit.Test;

import static com.brantapps.talks.meetup.matcher.MeetUpMatcher.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class Part2_TestMeetUp {

    @Test
    public void calculatesWaitlistLengthCorrectly() {
        // Given
        final MeetUp meet = MeetUpFixture.getInstance()
                .atVenue(VenueFixture.getInstance()
                        .withCapacity(35)
                        .build())
                .withAttendeeCount(50)
                .build();

        // Then
        assertThat(meet).hasWaitlistLength(15);
    }


    @Test
    public void waitlistWithoutVenue() {
        // Given
        final MeetUp meet = MeetUpFixture.getInstance()
                .noVenue()
                .build();

        // Then
        assertThat(meet.calculateWaitlistLength()).isEqualTo(0);
    }


    @Test
    public void nameAssignment() {
        // Given
        final String stubName = "November Talk Night";
        final MeetUp meet = MeetUpFixture.getInstance()
                .withName(stubName)
                .build();

        // Then
        assertThat(meet.getEventName()).isEqualTo(stubName);
    }
}