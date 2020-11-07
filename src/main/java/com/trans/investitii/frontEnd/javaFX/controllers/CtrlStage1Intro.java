package com.trans.investitii.frontEnd.javaFX.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CtrlStage1Intro implements Initializable {

    @FXML
    public Text CUI;
    public Text furnizor;
    public Text nrFactura;
    public Text Data;
    public Text valoare;
    public Text TVA;
    public Text totalValoare;
    public Text proiect;
    public Text deviz;
    public Text utt;
    public Text Data1;
    public Text respProj;
    public TextField fieldNrFact;
    public TextField fieldCUI;
    public TextField fieldValFact;
    public TextField fieldTVA;
    public TextField fieldValTotala;
    public DatePicker fieldDataFactura;
    public DatePicker fieldDataGL;
    public ComboBox comBoboxFz;
    public ComboBox comboBoxContract;
    public ComboBox comboBoxCtInv;
    public ComboBox comboBoxRespProj;
    public ComboBox comboBoxDeviz;
    public ComboBox comboBoxOrg;
    public ComboBox cBCtFz;
    public ComboBox cBProjNr;
    public Button buttonBackSt0;
    public Button ButtonNextSt2;
    public Button addFacturaButtonId;



    @Override
    public void initialize ( URL location, ResourceBundle resources ) {

        List<String> myListFz = null;
       // List<String> myListCUI = null;
        List<String> myListContract = null;
        List<String> myListCtInvest = null;
        List<String> myListCtFz = null;
        List<String> myListProj = null;
        List<String> myListDeviz = null;
        List<String> myListOrg = null;
        List<String> myListRespProj = null;

        try {
            myListFz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/fz") ));
           // myListCUI = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/cui") ));///.collect( Collectors.toList());
            myListContract = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/contract") ));
            myListCtInvest = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/ctInv") ));
            myListCtFz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/ctFz") ));
            myListDeviz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/deviz") ));
            myListOrg = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/org") ));
            myListRespProj = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/respproj") ));
             myListProj = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" ) ));

        } catch (IOException e) {
            e.printStackTrace();
        }
        comBoboxFz.setItems( FXCollections.observableArrayList(myListFz));
        comboBoxContract.setItems( FXCollections.observableArrayList(myListContract));
        comboBoxCtInv.setItems( FXCollections.observableArrayList(myListCtInvest));
        cBCtFz.setItems( FXCollections.observableArrayList(myListCtFz));
        comboBoxDeviz.setItems( FXCollections.observableArrayList(myListDeviz));
        comboBoxOrg.setItems( FXCollections.observableArrayList(myListOrg));
        comboBoxRespProj.setItems( FXCollections.observableArrayList(myListRespProj));
        cBProjNr.setItems( FXCollections.observableArrayList(myListProj));



    }

    public void goOnSt0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }


    public void goOnStage2 ( ActionEvent event ) {
    }

    public void addFacturaButton ( ActionEvent event ) {
    }
}
