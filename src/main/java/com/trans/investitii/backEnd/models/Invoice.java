package com.trans.investitii.backEnd.models;

import java.time.LocalDate;

public class Invoice {
    //nr fac
    private String furnizor;
    private double valoare;
    private double tva;
    private double TotalValoare;
    private LocalDate dataFacturii;
    private int numarProiect;
    private String deviz;
    private String utt;

    public void setFurnizor ( String furnizor ) {
        this.furnizor = furnizor;
    }

    public void setValoare ( double valoare ) {
        this.valoare = valoare;
    }

    public void setTva ( double tva ) {
        this.tva = tva;
    }

    public void setTotalValoare ( double totalValoare ) {
        TotalValoare = totalValoare;
    }

    public void setDataFacturii ( LocalDate dataFacturii ) {
        this.dataFacturii = dataFacturii;
    }

    public void setNumarProiect ( int numarProiect ) {
        this.numarProiect = numarProiect;
    }

    public void setDeviz ( String deviz ) {
        this.deviz = deviz;
    }

    public void setUtt ( String utt ) {
        this.utt = utt;
    }

    public String getFurnizor () {
        return furnizor;
    }

    public double getValoare () {
        return valoare;
    }

    public double getTva () {
        return tva;
    }

    public double getTotalValoare () {
        return TotalValoare;
    }

    public LocalDate getDataFacturii () {
        return dataFacturii;
    }

    public int getNumarProiect () {
        return numarProiect;
    }

    public String getDeviz () {
        return deviz;
    }

    public String getUtt () {
        return utt;
    }

    public Invoice () {
    }


}


