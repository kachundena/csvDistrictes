/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kachundena.csvdistrictes.webservice;

import com.kachundena.csvdistrictes.modelo.*;
import com.kachundena.csvdistrictes.util.*;
import static com.kachundena.csvdistrictes.controller.DistrictesController.*;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;

/**
 * REST Web Service
 *
 * @author alex
 */
@Path("/ws")
@Api(value = "/ws", description = "Operaciones con CSV PUNTS_WIFI")
@Produces(MediaType.APPLICATION_JSON)
public class wsDistricte {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of wsPuntWifi
     */
    public wsDistricte() {
    }

    /**
     * Retrieves representation of an instance of com.kachundena.csvpuntswifi.webservice.wsPuntWifi
     * @return an instance of java.lang.String
     */

    
    @GET
    @Path("/lista")
    @ApiOperation(
            value = "Todos los districtos",
            notes = "Devuelve todos los distritos"
    )
    @Produces(MediaType.APPLICATION_JSON)
    public List<Districte> getListaDistricte() throws IOException {
        List<Districte> retorno = new ArrayList<Districte>();
        try {
            // Importar valores de CSV a lista
            Districtes Lista = importCSV(Constantes.FILENAME_CSV_URL,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            // Retornar todos los valores de la lista
            retorno = Lista.getAllDistrictes();
        }
        catch (Exception e) {
            System.console().printf(e.toString());
        }
        return retorno;
    }    
    
    @GET
    @Path("/puntwifi/{linea}")
    @ApiOperation(
            value = "retornar distrito",
            notes = "Devuelve un distrito concreto de una linea"
    )
    @Produces(MediaType.APPLICATION_JSON)
    public Districte getDistricte(
            @ApiParam(value = "Linea Distrito", allowableValues = "range[1," + Integer.MAX_VALUE + "]", required = true)
            @PathParam("linea") int linea) {
        Districte retorno = new Districte();
        try {
            // Importar valores de CSV a lista
            Districtes Lista = importCSV(Constantes.FILENAME_CSV_URL,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            // retornar la linea seleccionada
            retorno = Lista.getDistricte(linea);           
        }
        catch (Exception e) {
            System.console().printf(e.toString());
        }
        return retorno;
  
    }
    
    @POST
    @Path("/add")
    @ApiOperation(
            value = "añadir distrito",
            notes = "Añade un distrito al final de fichero"
    )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean addDistricte(Districte districte, 
                            @HeaderParam("authorization") String keyString) {
         try {
            if(!Utilities.isCorrectKey(keyString)) {
                return false;
            }
            // Importar valores de CSV a lista
            Districtes Lista = importCSV(Constantes.FILENAME_CSV_URL,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            // Añadir el nuevo distrito a la lista. Al final de esta.
            Lista.addDistricte(districte);
            // Exportar la lista al CSV
            exportCSV(Lista,Constantes.FILENAME_CSV,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            return true;
        }
        catch (Exception e) {
            System.console().printf(e.toString());
            return false;
        }
       
    }

    @PUT
    @Path("/update")
    @ApiOperation(
            value = "actualiza un distrito",
            notes = "Actualiza un distrito concreto"
    )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean updateDistricte(Districte districte, 
                            @HeaderParam("authorization") String keyString) {
        try {
            if(!Utilities.isCorrectKey(keyString)) {
                return false;
            }
            // Importar valores de CSV a lista
            Districtes Lista = importCSV(Constantes.FILENAME_CSV_URL,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            // Editar el distrito de la linea. Para ello cogemos la linea del punto WIFI del parámetro
            Lista.editDistricte(districte.getLinea(), districte);
            // Exportar la lista al CSV
            exportCSV(Lista,Constantes.FILENAME_CSV,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            return true;
        }
        catch (Exception e) {
            System.console().printf(e.toString());
            return false;
        }
        
    }

    @DELETE
    @Path("/delete/{linea}")
    @ApiOperation(
            value = "borrar un distrito",
            notes = "Borra un distrito de la linea especificada"
    )
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteDistricte(
            @ApiParam(value = "Linea Distrito", allowableValues = "range[1," + Integer.MAX_VALUE + "]", required = true)
            @PathParam("linea") int Linea, 
            @HeaderParam("authorization") String keyString) {
        try {
            if(!Utilities.isCorrectKey(keyString)) {
                return false;
            }
            // Importar valores de CSV a lista
            Districtes Lista = importCSV(Constantes.FILENAME_CSV_URL,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            // Borrar la entrada de la lista a partir de la linea indicada en el parámetro
            Lista.deleteDistricte(Linea);
            // Exportar al CSV el resto de la lista
            exportCSV(Lista,Constantes.FILENAME_CSV,Constantes.SEPARATOR_COL,Constantes.CODEPAGE);
            return true;
        }
        catch (Exception e) {
            System.console().printf(e.toString());
            return false;
        }
    }
    
}
