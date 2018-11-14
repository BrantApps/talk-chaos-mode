package com.brantapps.talks.meetup;

import com.brantapps.talks.meetup.attendee.AttendeeRepository;
import com.brantapps.talks.meetup.venue.Address;
import com.brantapps.talks.meetup.venue.GeoPoint;
import com.brantapps.talks.meetup.venue.Place;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class Part1_TestMeetUp {
    @Test
    public void calculatesWaitlistLengthCorrectly() {
        // Given
        UUID stubUuid = UUID.randomUUID();
        AttendeeRepository mockAttendeeRepo =
                mock(AttendeeRepository.class);
        List mockAttendeeList = mock(List.class);
        MeetUp meet = new MeetUp(
                stubUuid,
                "test",
                ZonedDateTime.now(),
                mockAttendeeRepo
        );
        meet.setPlace(new Place(
                new Address("foo", "bar", "testville", new GeoPoint(0, 0)),
                50
        ));

        // When
        when(mockAttendeeList.size()).thenReturn(65);
        when(mockAttendeeRepo.fetchAttendees(any(UUID.class)))
                .thenReturn(mockAttendeeList);
        int waitlistLength = meet.calculateWaitlistLength();

        // Then
        assertEquals(15, waitlistLength);
        verify(mockAttendeeRepo).fetchAttendees(eq(stubUuid));
    }
}
