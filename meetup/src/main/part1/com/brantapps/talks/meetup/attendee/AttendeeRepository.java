package com.brantapps.talks.meetup.attendee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AttendeeRepository {
    private final AttendeeService service;
    private final AttendeeDao dao;

    public AttendeeRepository(final AttendeeService service,
                              final AttendeeDao dao) {

        this.service = service;
        this.dao = dao;
    }


    public List<Attendee> fetchAttendees(final UUID meetUpId) {
        try {
            return service.fetch(meetUpId);
        } catch (Exception e) {
            return dao.fetch(meetUpId);
        }
    }


    /** Attendee's API **/
    public static class AttendeeService {
        public List<Attendee> fetch(final UUID meetUpId) {
            return new ArrayList<>();
        }
    }

    /** Offline database store **/
    public static class AttendeeDao {
        public List<Attendee> fetch(final UUID meetUpId) {
            return new ArrayList<>();
        }
    }
}
