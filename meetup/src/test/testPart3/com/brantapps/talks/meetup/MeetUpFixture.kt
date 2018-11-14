package com.brantapps.talks.meetup

import com.brantapps.talks.meetup.venue.Address
import com.brantapps.talks.meetup.venue.GeoPoint
import fr.xgouchet.elmyr.junit.JUnitForger
import org.junit.Rule
import java.time.ZonedDateTime
import java.util.*

//Part 4
@get:Rule
val forger = JUnitForger()

@DslMarker
annotation class MeetUpDsl

@MeetUpDsl
class MeetUpBuilder(var id: UUID = UUID.randomUUID(),
                    var name: String = forger.aString(),
                    var time: ZonedDateTime = ZonedDateTime.now(),
                    private var venue: Venue? = null,
                    private var attendees: MutableList<Attendee> = mutableListOf()) {
    fun venue(init: VenueBuilder.() -> Unit) {
        venue = VenueBuilder().apply(init).build()
    }
    fun attendees(init: ATTENDEES.() -> Unit) = attendees.addAll(ATTENDEES().apply(init))
    fun build() = MeetUpKt(id, name, time, attendees, venue)
}

@MeetUpDsl
class VenueBuilder(var name: String = forger.aSentence(),
                   var capacity: Int = 0,
                   private var address: Address = Address("", "", "", GeoPoint(0.0, 0.0))) {
    fun address(init: BUILDADDRESS.() -> Unit) {
        address = BUILDADDRESS().apply(init).build()
    }
    fun build() = Venue(name, capacity, address)
}

@MeetUpDsl
class ATTENDEES : ArrayList<Attendee>() {
    fun person(init: BUILDATTENDEE.() -> Unit) = add(BUILDATTENDEE().apply(init).build())
}

@MeetUpDsl
class BUILDATTENDEE(var memberId: String = "",
                    var name: String = "") {
    fun build() = Attendee(memberId, name)
}

@MeetUpDsl
class BUILDADDRESS(var addressLine1: String = "",
                   var addressLine2: String = "",
                   var city: String = "",
                   var latLong: GeoPoint = GeoPoint(0.0, 0.0)) {
    fun build() = Address(addressLine1, addressLine2, city, latLong)
}

fun meetUp(init: MeetUpBuilder.() -> Unit): MeetUpKt = MeetUpBuilder().apply(init).build()