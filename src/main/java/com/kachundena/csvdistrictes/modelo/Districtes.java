package com.kachundena.csvdistrictes.modelo;


import java.util.ArrayList;
import java.util.List;




public class Districtes {

    private List<Districte> districtes;

    public Districtes() {
        districtes = new ArrayList<Districte>();
    }

    /**
     * 
     * @param districte
     */
    public void addDistricte(Districte districte) {
        districtes.add(districte);
    }

    /**
     * 
     * @param linea
     * @param districte
     */
    public void editDistricte(int linea, Districte districte) {
        districtes.set(linea, districte);
    }

    /**
     * 
     * @param linea
     */
    public void deleteDistricte(int linea) {
        districtes.remove(linea);
    }


    /**
     * 
     * @param linea
     */
    public Districte getDistricte(int linea) {
        return districtes.get(linea);
    }

    public int getNumItems() {
        return districtes.size();
    }

    public List<Districte> getAllDistrictes() {
        return districtes;
    }
}