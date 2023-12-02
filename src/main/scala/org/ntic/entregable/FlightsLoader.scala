package org.ntic.entregable

import java.io._

object FlightsLoader extends App {

  def writeObject(flights: Seq[Flight], outputFilePath: String): Unit = {
    val out = new ObjectOutputStream(new FileOutputStream(outputFilePath))
    out.writeObject(flights)
    out.close()
  }

  val flights = FileUtils.loadFile(FlightsLoaderConfig.filePath)
  for (origin <- FlightsLoaderConfig.filteredOrigin) {
    val filteredFligths: Seq[Flight] = flights.filter(flight => flight.origin.code == origin)
    val delayedFlights: Seq[Flight] = filteredFligths.filter(flight => flight.isDelayed).sorted
    val notDelayedFlights: Seq[Flight] = filteredFligths.filter(flight => !flight.isDelayed).sorted


    val flightObjPath: String = FlightsLoaderConfig.outputDir + "/" + origin + ".obj"
    val delayedFlightsObj: String = FlightsLoaderConfig.outputDir + "/" + origin + "_delayed" + ".obj"

    writeObject(notDelayedFlights, flightObjPath)

    writeObject(delayedFlights, delayedFlightsObj)
  }
}
