package com.iutbm.applicationiut.edt;


import android.util.Log;

public class Cours {

    private String jour;
    private int numJour;
    private String mois;
    private String hDebut;
    private String hFin;
    private String libelle;
    private String lieu;

    public Cours(String cours) {

        String[] result = cours.split(" ");

        this.jour = result[0];
        this.numJour = Integer.parseInt(result[1]);
        this.mois = result[2];

        String[] heure = result[3].split("-");

        this.hDebut = heure[0];
        this.hFin = heure[1];

        int i = 4;

        this.libelle = "";
        do {
            this.libelle += result[i] + " ";
            i++;
        }while (!result[i].contains("("));

        this.lieu = "";
        do {
            this.lieu += result[i] + " ";
            i++;
        }while (i!=result.length);

    }

    public int getNumJour() {
        return numJour;
    }

    public String getJour() {
        return jour;
    }

    public String getMois() {
        return mois;
    }

    public String gethDebut() {
        return hDebut;
    }

    public String gethFin() {
        return hFin;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getLieu() {
        return lieu;
    }
}
