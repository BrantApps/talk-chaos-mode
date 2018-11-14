package com.brantapps.talks.meetup.matcher;

import com.brantapps.talks.meetup.MeetUp;
import org.assertj.core.api.AbstractAssert;

public class MeetUpMatcher extends AbstractAssert<MeetUpMatcher, MeetUp> {

    public MeetUpMatcher(MeetUp meetUp) {
        super(meetUp, MeetUpMatcher.class);
    }

    public static MeetUpMatcher assertThat(MeetUp actual) {
        return new MeetUpMatcher(actual);
    }

    public MeetUpMatcher hasWaitlistLength(int length) {
        isNotNull();

        // check condition
        if (actual.calculateWaitlistLength() != length) {
            failWithMessage("Expected a waitlist with length <%s> but was <%s>", length, actual.calculateWaitlistLength());
        }

        // chaining
        return this;
    }
}
