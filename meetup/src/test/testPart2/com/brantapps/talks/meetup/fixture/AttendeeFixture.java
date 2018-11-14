package com.brantapps.talks.meetup.fixture;

import com.brantapps.talks.meetup.attendee.Attendee;

public class AttendeeFixture extends AbstractFixture<Attendee> {
    private DataGenerator dataGenerator;
    private Integer memberId;
    private String name;

    AttendeeFixture(final DataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }


    public AttendeeFixture withMemberId(int memberId) {
        this.memberId = memberId;
        return this;
    }


    public AttendeeFixture withName(String name) {
        this.name = name;
        return this;
    }


    @Override
    public Attendee build() {
        int fixtureMemberId;
        String fixtureName;

        if (memberId == null) {
            fixtureMemberId = dataGenerator.nextInt();
        } else {
            fixtureMemberId = memberId;
        }

        if (name == null) {
            fixtureName = dataGenerator.nextOneOf("Scott", "Dave", "Elliot", "Karl", "Daniel");
        } else {
            fixtureName = name;
        }

        return new Attendee(fixtureMemberId, fixtureName);
    }
}
