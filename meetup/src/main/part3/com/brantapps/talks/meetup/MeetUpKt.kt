package com.brantapps.talks.meetup

import com.brantapps.talks.meetup.venue.Address
import java.time.ZonedDateTime
import java.util.*

data class Attendee(val memberId: String, val name: String)

data class MeetUpKt(val id: UUID,
                    val name: String,
                    val time: ZonedDateTime,
                    val attendees: List<Attendee>,
                    val venue: Venue?)

fun MeetUpKt.calculateWaitlistLength(): Int {
    return Math.abs(venue!!.capacity - attendees.size)
}

data class Venue(val name: String, val capacity: Int, val address: Address?)
