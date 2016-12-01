/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kachundena.csvdistrictes.controller;

import com.kachundena.csvdistrictes.modelo.*;
import com.kachundena.csvdistrictes.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author alex
 */
public class DistrictesController {
    
    public static Districtes importCSV(String szFileCSV, String szSeparator, String szCodePage) throws FileNotFoundException, IOException {
        Districtes retorno = new Districtes();
        URL inFile = new URL(szFileCSV);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inFile.openStream(),szCodePage))) {
            String strLine;
            String[] regLine;
            // hace una primera lectura para omitir la cabecera.
            strLine = br.readLine();
            int i = 0;
            // recorre todas las lineas del CSV (a partir de la 2ª linea)
            while ((strLine = br.readLine()) != null)   {
                // divide el string en una cadena de string a partir del separador indicado.
                regLine = strLine.split(szSeparator);
                // si la longitud el fichero es diferente a cero
                if (regLine.length > 0) {
                    // Crea el objeto y le asigna todas las propiedades.
                    Districte districte = new Districte();
                    districte.setLinea(i);
                    districte.setCodigo(Integer.parseInt(regLine[0]));
                    districte.setNombre(regLine[1]);
                    // añade el objeto a la lista
                    retorno.addDistricte(districte);
                } 
                i++;
            }
            br.close();
        }
        catch (Exception e) {
            System.out.println("Error");
        }
        return retorno;     
    }

    public static void exportCSV(Districtes Lista, String szFileCSV, String szSeparator, String szCodePage) throws FileNotFoundException, IOException {
        try (BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(szFileCSV), szCodePage))){
            String szHeader = "CODIGO;NOMBRE\r\n";
            br.write(szHeader);
            for(int i=0;i < Lista.getNumItems();i++) {
                Districte districte = Lista.getDistricte(i);
                String szLine = districte.getCodigo() + szSeparator +
                        districte.getNombre() + "\r\n";
                br.write(szLine);                     
            }            
            br.close();
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }
}
