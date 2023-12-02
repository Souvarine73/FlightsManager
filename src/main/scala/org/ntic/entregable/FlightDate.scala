package org.ntic.entregable

import com.sun.media.sound.InvalidFormatException

case class FlightDate(day: Int,
                      month: Int,
                      year: Int) {

  override lazy val toString: String = f"$day%02d/$month%02d/$year%02d"
}

object FlightDate {
  def fromString(date: String): FlightDate = {
    /**
     * This function is used to convert a string to a org.ntic.entregable.FlightDate
     * @param date: String
     * @return org.ntic.entregable.FlightDate
     */

    date.split(" ").head.split("/").map(x => x.toInt).toList match {
      case month :: day :: year :: Nil => // Cambio el formato dado que el formato fecha en csv es MM/DD/YY
        assert(day >= 1, s"El día $day es invalido")
        assert(month >= 1 && month <= 12, s"El mes $month es invalido")
        assert(year >= 1987, s"El año $year es invalido")
        FlightDate(day, month, year)
      case _ => throw new InvalidFormatException(s"$date tiene un formato inválido")
    }
  }
}
