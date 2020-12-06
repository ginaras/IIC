package com.trans.investitii.backEnd.DBase;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Investitii {
    public   static String USER= "root";
    public   static String PASSWORD= "root";
    public static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS invDB";
    public static final String USE_DATABASE = "USE invDB";
    public static String URL0 = String.format( "jdbc:mysql://localhost:3306/%s?useLegacyDateTimeCode=false&serverTimezone=GMT","sys" );
    public static String URL = String.format( "jdbc:mysql://localhost:3306/%s?useLegacyDateTimeCode=false&serverTimezone=GMT","invDB" );
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS invTBL (nrCrt INT(5) AUTO_INCREMENT PRIMARY KEY, furnizor CHAR(70) not null, nrFactura char(20) , dataFacturii DATE, dataContabilizarii DATE, valoare varCHAR(20), tva varCHAR(20), valTot varCHAR(20), contract CHAR(10), contInv CHAR(20), contFz CHAR(20), nrProiect CHAR(20), deviz CHAR(20), org CHAR(10), respProiect CHAR(50))";
    public static final String USE_TABLE = "USE investitiiTable";

//    public static final String UserWeb = "imobiliz_sorin";
//    public static final String PASSWORDWEB = "PoiuLkjh123";
//    public static final String URLWEB = String.format( "jdbc:mysql://programpersonal.imobilizari.ro:3306/%s?useLegacyDateTimeCode=false&serverTimezone=America/New_York", "imobiliz_cursuri" );
//    public static final String USE_TABLE = "USE personal";

    private int nrCrt;
    private Object furnizor;
    private String nrFactura;
    private String valoare;
    private LocalDate dataFacturii;
    private LocalDate dataContabilizarii;
    private Object contract;
    private Object contInv;
    private Object contFz;
    private Object nrProiect;
    private Object deviz;
    private Object org;
    private Object respProiect;


    public Object getFurnizor () {
        return furnizor;
    }

    public void setFurnizor ( Object furnizor ) {
        this.furnizor = furnizor;
    }

    public String getNrFactura () {
        return nrFactura;
    }

    public void setNrFactura ( String nrFactura ) {
        this.nrFactura = nrFactura;
    }

    public String getValoare () {
        return valoare;
    }

    public void setValoare ( String valoare ) {
        this.valoare = valoare;
    }

    public LocalDate getDataFacturii () {
        return dataFacturii;
    }

    public void setDataFacturii ( LocalDate dataFacturii ) {
        this.dataFacturii = dataFacturii;
    }

    public LocalDate getDataContabilizarii () {
        return dataContabilizarii;
    }

    public void setDataContabilizarii ( LocalDate dataContabilizarii ) {
        this.dataContabilizarii = dataContabilizarii;
    }

    public Object getContract () {
        return contract;
    }

    public void setContract ( Object contract ) {
        this.contract = contract;
    }

    public Object getContInv () {
        return contInv;
    }

    public void setContInv ( Object contInv ) {
        this.contInv = contInv;
    }

    public Object getContFz () {
        return contFz;
    }

    public void setContFz ( Object contFz ) {
        this.contFz = contFz;
    }

    public Object getNrProiect () {
        return nrProiect;
    }

    public void setNrProiect ( Object nrProiect ) {
        this.nrProiect = nrProiect;
    }

    public Object getRespProiect () {
        return respProiect;
    }

    public void setRespProiect ( Object respProiect ) {
        this.respProiect = respProiect;
    }

    public Object getDeviz () {
        return deviz;
    }

    public void setDeviz ( Object deviz ) {
        this.deviz = deviz;
    }

    public Object getOrg () {
        return org;
    }

    public void setOrg ( Object org ) {
        this.org = org;
    }

    //constructor pt ctrl1StageIntro
    public Investitii ( Object furnizor, String nrfactura, String valoare, LocalDate dataFacturii, LocalDate dataContabilizarii, Object contract, Object ctInv, Object ctFz,  Object respProj, Object deviz, Object org, Object  nrProj ) {
//    public Investitii ( Object furnizor, String nrfactura, String valoare, LocalDate dataFacturii, LocalDate dataContabilizarii, Object contract, Object ctInv, Object ctFz,  Object nrProj, Object deviz, Object org, Object respProj ) {
        this.furnizor=furnizor;
        this.nrFactura = nrfactura;
        this.valoare= valoare;
        this.dataFacturii=dataFacturii;
        this.dataContabilizarii = dataContabilizarii;
        this.contract=contract;
        this.contInv=ctInv;
        this.contFz=ctFz;
        this.nrProiect=nrProj;
        this.deviz=deviz;
        this.org=org;
        this.respProiect=respProj;
    }
    // constructor pentru popularea listei definite in CtrlStage1Intro
    public Investitii ( Object furnizor, String nrFactura, String valoare, Object contInv, Object nrProiect, Object respProiect, String dataContabilizarii ) {

        this.furnizor=furnizor;
        this.nrFactura =  nrFactura;
        this.valoare= valoare;
        this.contInv=contInv;
        this.nrProiect= nrProiect;
        this.respProiect =respProiect;
        this.dataContabilizarii= LocalDate.parse(dataContabilizarii);
    }
    // constructor pt rapoarte
//    public Investitii ( String furnizor, String nrFactura, String valoare, String dataContabilizarii, String respProiect, String contract, String contInv, String contFz, String nrProiect ) {
//        this.furnizor=furnizor;
//        this.valoare= valoare;
//        this.nrFactura=nrFactura;
//        this.dataContabilizarii= LocalDate.parse(dataContabilizarii);
//        this.respProiect =respProiect;
//        this.contract = contract;
//        this.contInv=contInv;
//        this.contFz = contFz;
//        this.nrProiect= nrProiect;
//
//
//    }

    public Investitii ( String furnizor, String nrFactura, String valoare, String dataContabilizarii, String respProiect, String contract, String contInv, String contFz, String nrProiect ) {
        this.furnizor=furnizor;
        this.valoare= valoare;
        this.nrFactura=nrFactura;
        this.dataContabilizarii= LocalDate.parse(dataContabilizarii);
        this.respProiect =respProiect;
        this.contract = contract;
        this.contInv=contInv;
        this.contFz = contFz;
        this.nrProiect= nrProiect;

    }


    public Investitii () {
    }
}
