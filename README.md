csvDistrictes
===========

Proyecto de servicios REST de archivo opendata de Distritos de la ciudad de Barcelona

Parámetros de llamadas a fichero

* FILENAME_CSV_URL: dirección pública URI de fichero CSV
* FILENAME_CSV: dirección privada de fichero CSV
* SEPARATOR_COL; separador entre campos del CSV
* CODEPAGE: código de página utilizado en el fichero

Seguridad

* KEY: Clave de seguridad para servicios REST tipo POST, PUT y DELETE

Servicios 
---------


getListaDistrictes
* Parámetro salida: Lista de Districte

getDistricte
* Parámetro entrada: linea   
* Parámetro salida: Districte

addDistricte
* Parámetro entrada: Districte
* Parámetro header: Autorización

updateDistricte
* Parámetro entrada: Districte
* Parámetro header: Autorización

deleteDistricte
* Parámetro entrada: Linea int32 
* Parámetro header: Autorización

Clase Districte

Districte
 {
linea:	integer (int32)
codigo: integer (int32)
nombre: string
}