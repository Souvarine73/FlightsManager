package org.ntic.entregable

import java.io._

object FlightsLoader extends App {

  def writeObject(flights: Seq[Flight], outputFilePath: String): Unit = {
    val out = new ObjectOutputStream(new FileOutputStream(outputFilePath))
    out.writeObject(flights)
    out.close()
  }

  val flights = FileUtils.loadFile(FlightsLoaderConfig.filePath) // DONE TODO: carga el fichero de vuelos usando la función loadFile de FileUtils
  for (origin <- FlightsLoaderConfig.filteredOrigin) { // DONE TODO: Itera sobre los orígenes filtrados, filteredOrigin, definidos en FlightsLoaderConfig
    val filteredFligths: Seq[Flight] = flights.filter(flight => flight.origin.code == origin)  // DONE TODO: Filtra los vuelos por origen
    val delayedFlights: Seq[Flight] = filteredFligths.filter(flight => flight.isDelayed).sorted // DONE TODO: Filtra los vuelos retrasados por origen y ordénalos
                                          //  Pista: el atributo isDelayed de org.ntic.entregable.Flight te puede ayudar para realizar el filtrado
                                          //  Pista: usa la función sorted de las colecciones de Scala para ordenar
                                          //  Pista: para que la función sorted funcione, debes implementar el trait Ordered en la clase org.ntic.entregable.Flight
    val notDelayedFlights: Seq[Flight] = filteredFligths.filter(flight => !flight.isDelayed).sorted  // DONE TODO: Filtra los vuelos no retrasados
                                              //  Pista: el atributo isDelayed de org.ntic.entregable.Flight te puede ayudar para realizar el filtrado
                                              //  Pista: usa la función sorted de las colecciones de Scala para ordenar
                                              //  Pista: para que la función sorted funcione, debes implementar el trait Ordered en la clase org.ntic.entregable.Flight

    val flightObjPath: String = FlightsLoaderConfig.outputDir + "/" + origin + ".obj" //DONE TODO: Crea el path del fichero de salida para los vuelos no retrasados
                                    //  Pista: el path debe concatenar el directorio de salida (outputDir) con el origen
                                    //  del vuelo y la extensión .obj
    val delayedFlightsObj: String = FlightsLoaderConfig.outputDir + "/" + origin + "_delayed" + ".obj" //DONE TODO: Crea el path del fichero de salida para los vuelos retrasados
                                        //  Pista: el path debe concatenar el directorio de salida (outputDir) con el origen
                                        //  del vuelo, la cadena "_delayed" y la extensión .obj
    //DONE TODO: Escribe los vuelos no retrasados en el fichero de salida usando la función writeObject y
    //  pasándole como parámetros los vuelos no retrasados y el path del fichero de salida para los vuelos no retrasados.
    writeObject(notDelayedFlights, flightObjPath)
    //DONE TODO: Escribe los vuelos retrasados en el fichero de salida usando la función writeObject y
    //  pasándole como parámetros los vuelos retrasados y el path del fichero de salida para los vuelos retrasados.
    writeObject(delayedFlights, delayedFlightsObj)
  }
}
