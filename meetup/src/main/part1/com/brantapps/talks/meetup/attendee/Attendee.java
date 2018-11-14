package com.brantapps.talks.meetup.attendee;

import org.jetbrains.annotations.NotNull;

public class Attendee {
    private final int memberId;
    private final String name;

    public Attendee(final int memberId, @NotNull final String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }
}
