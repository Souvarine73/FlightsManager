package org.ntic.entregable
import scala.io.Source

object FileUtils {

  def isInvalid(s: String): Boolean = {
    /**
     * This function is used to check if the line is valid or not
     * @param s: String
     * @return Boolean: true if the line is invalid, false otherwise
     */
    s.isEmpty || s.split(FlightsLoaderConfig.delimiter).length != FlightsLoaderConfig.headersLength // REVIEW
  }

  def loadFile(filePath: String): Seq[Flight] = {
    /**
     * This function is used to load the file
     * @param filePath: String
     * @return Seq[org.ntic.entregable.Flight]
     */
    val linesList: List[String] = Source.fromFile(filePath).getLines().toList
    val headers = linesList.head

    require(FlightsLoaderConfig.headersLength == headers.split(FlightsLoaderConfig.delimiter).length)

    val rows = linesList.tail

    val invalidRows: List[String] = rows.filter(line => isInvalid(line))
    val validRows: List[String] = rows.filter(line => !isInvalid(line))

    val flights: Seq[Flight] = validRows.map(row => Flight.fromString(row))
    flights
  }
}