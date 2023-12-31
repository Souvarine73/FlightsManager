package org.ntic.entregable

import com.sun.media.sound.InvalidFormatException
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FlightDateTest extends AnyFlatSpec with Matchers {
  "A org.ntic.entregable.FlightDate" should "be correctly initialized from string" in {
    val dateStr = "7/1/2023 12:00:00 AM"
    val expected = FlightDate(month = 7, day = 1, year = 2023)
    val result = FlightDate.fromString(dateStr)
    result shouldEqual expected
  }

  "A org.ntic.entregable.FlightDate" should "raise an Exception because of wrong string format" in {
    val dateStr = "7/1/2023/3 12:00:00 AM"
    an [InvalidFormatException] should be thrownBy FlightDate.fromString(dateStr)
  }
}
