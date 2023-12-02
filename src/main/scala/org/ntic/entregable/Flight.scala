package org.ntic.entregable

case class Flight(flDate: String,
                  origin: AirportInfo,
                  dest: AirportInfo,
                  scheduledDepTime: Time,
                  scheduledArrTime: Time,
                  depDelay: Double,
                  arrDelay: Double) extends Ordered[Flight] {

  lazy val flightDate: FlightDate = FlightDate.fromString(flDate)

  lazy val actualDepTime: Time = Time.fromMinutes(scheduledDepTime.asMinutes + depDelay.toInt)

  lazy val actualArrTime: Time = Time.fromMinutes(scheduledArrTime.asMinutes + arrDelay.toInt)

  val isDelayed: Boolean = depDelay != 0 || arrDelay != 0

  def compare(that: Flight): Int = this.actualArrTime - that.actualArrTime // Review
}

object Flight {


  def fromString(flightInfoRow: String): Flight = {
    val columns: Array[String] = flightInfoRow.split(FlightsLoaderConfig.delimiter)
    def getColValue(colName: String): String = {
      /**
       * This function is used to get the value of a column from the array of String generated from the row of the csv
       * and stored in the variable `columns`.
       * @param colName: String name of the column
       * @return String value of the column
       */
       columns.apply(FlightsLoaderConfig.columnIndexMap(colName))
    }
    val oriAirport = AirportInfo(
      airportId = getColValue("ORIGIN_AIRPORT_ID").toLong,
      code = getColValue("ORIGIN"),
      cityName = getColValue("ORIGIN_CITY_NAME"),
      stateAbr = getColValue("ORIGIN_STATE_ABR"))
    val destAirport = AirportInfo(
      airportId = getColValue("DEST_AIRPORT_ID").toLong,
      code = getColValue("DEST"),
      cityName = getColValue("DEST_CITY_NAME"),
      stateAbr = getColValue("DEST_STATE_ABR"))
    Flight(
      flDate = getColValue("FL_DATE"),
      origin = oriAirport,
      dest = destAirport,
      scheduledDepTime = Time.fromString(getColValue("DEP_TIME")),
      scheduledArrTime = Time.fromString(getColValue("ARR_TIME")),
      depDelay = getColValue("DEP_DELAY").toDouble,
      arrDelay = getColValue("ARR_DELAY").toDouble
    )
  }
}
