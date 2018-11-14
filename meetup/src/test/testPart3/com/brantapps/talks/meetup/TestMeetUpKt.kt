package com.brantapps.talks.meetup

import com.brantapps.talks.meetup.venue.GeoPoint
import fr.xgouchet.elmyr.junit.JUnitForger
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

open class TestMeetUpKt {

    @Test
    fun `waitlist length calculated correctly`() {
        val waitlistLength = meetUp {
            venue {
                capacity = 65
            }
            attendees {
                repeat(50) {
                    person { }
                }
            }
        }.calculateWaitlistLength()

        assertThat(waitlistLength).isEqualTo(15)
    }


    @Test
    fun `did I DSL right`() {
        val meetUp = meetUp {
            id = UUID.fromString("db3953f0-e6d5-11e8-b568-0800200c9a66")
            name = "November Talk Night"
            time = ZonedDateTime.parse("2018-11-13T18:30:00+00:00")

            venue {
                name = "Old Office"
                capacity = 65
                address {
                    addressLine1 = "JUST EAT"
                    addressLine2 = "Level 2, Broad Quay House"
                    city = "Bristol"
                    latLong = GeoPoint(51.451744, -2.596873)
                }
            }
            attendees {
                repeat(52) {
                    person { }
                }
                person {
                    memberId = "219269164"
                    name = "OceanLife Development"
                }
            }
        }

        // Then.
        assertThat(meetUp).hasNoNullFieldsOrProperties()
        assertThat(meetUp.id).isEqualTo(UUID.fromString("db3953f0-e6d5-11e8-b568-0800200c9a66"))
        assertThat(meetUp.name).isEqualTo("November Talk Night")
        assertThat(meetUp.time).isEqualTo(ZonedDateTime.of(2018, 11, 13, 18, 30, 0, 0, ZoneId.of("Europe/London")))
        assertThat(meetUp.venue?.name).isEqualTo("Old Office")
        assertThat(meetUp.venue?.capacity).isEqualTo(65)
        assertThat(meetUp.venue?.address?.addressLine1).isEqualTo("JUST EAT")
        assertThat(meetUp.venue?.address?.addressLine2).isEqualTo("Level 2, Broad Quay House")
        assertThat(meetUp.venue?.address?.city).isEqualTo("Bristol")
        assertThat(meetUp.venue?.address?.geoPoint?.latitude).isEqualTo(51.451744)
        assertThat(meetUp.venue?.address?.geoPoint?.longitude).isEqualTo(-2.596873)
        assertThat(meetUp.attendees).hasSize(53)
        assertThat(meetUp.attendees).contains(Attendee("219269164", "OceanLife Development"))
    }
}