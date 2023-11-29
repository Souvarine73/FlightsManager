package org.ntic.entregable
import scala.io.Source

object FileUtils {

  def isInvalid(s: String): Boolean = {
    /**
     * This function is used to check if the line is valid or not
     * @param s: String
     * @return Boolean: true if the line is invalid, false otherwise
     */
    // DONE TODO: Implementar esta función
    //  asegúrate de que el número de campos es el correcto, `s` representa una línea del csv, para ser inválido:
    //    - debe ser vacío,
    //    - tras hacer un split por el delimitador (ver FlightsLoaderConfig) el número de campos debe ser distinto al
    //      número de headers (ver FlightsLoaderConfig)
    s.isEmpty || s.split(FlightsLoaderConfig.delimiter).length != FlightsLoaderConfig.headersLength // REVIEW
  }

  def loadFile(filePath: String): Seq[Flight] = {
    /**
     * This function is used to load the file
     * @param filePath: String
     * @return Seq[org.ntic.entregable.Flight]
     */
    val linesList: List[String] = Source.fromFile(filePath).getLines().toList // DONE TODO: Lee el fichero con Source.fromFile y obtén una lista de líneas
    val headers = linesList.head // DONE TODO: Obtén los headers del fichero csv
                      //  Pista: existen funciones de la clase List que te pueden ayudar

    require(FlightsLoaderConfig.headersLength == headers.split(FlightsLoaderConfig.delimiter).length)  // DONE TODO: Comprueba que el número de headers es el correcto comparándolo con headersLength
                  //  (ver FlightsLoaderConfig)

    val rows = linesList.tail  // DONE TODO: Obtén las filas del fichero csv (sin los headers)
                    //  Pista: existen funciones de la clase List que te pueden ayudar

    val invalidRows: List[String] = rows.filter(line => isInvalid(line))  // DONE TODO: Obtén las filas inválidas.
                                        //  Pista: usa la función isInvalid para filtrar
    val validRows: List[String] = rows.filter(line => !isInvalid(line))  // DONE TODO: Obtén las filas válidas.
                                      //  Pista: usa la función isInvalid para filtrar
    val flights: Seq[Flight] = validRows.map(row => Flight.fromString(row))  //DONE TODO: Convierte las filas válidas en objetos de tipo org.ntic.entregable.Flight y devuélvelos en una lista
                                    //  Pista: usa la función fromString de org.ntic.entregable.Flight
    flights
  }
}