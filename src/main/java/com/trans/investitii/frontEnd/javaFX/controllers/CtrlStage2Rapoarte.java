package com.trans.investitii.frontEnd.javaFX.controllers;

import com.trans.investitii.backEnd.DBase.Investitii;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CtrlStage2Rapoarte implements Initializable {
    @FXML
    public Button buttonBackSt0;
    @FXML
    public Button buttonSt1Intro;
    @FXML
    public TableView tableViewTotal;
    @FXML
    public TableColumn<Investitii, String> furnizorColumn;
    @FXML
    public TableColumn<Investitii, String> valoareFactColumn;
    @FXML
    public TableColumn<Investitii, String> dataCtbColumn;
    @FXML
    public TableColumn<Investitii, String> respProjColumn;
    @FXML
    public TableColumn<Investitii, String> contractColumn;
    @FXML
    public TableColumn<Investitii, String> contInvColumn;
    @FXML
    public TableColumn<Investitii, String> contFzColumn;
    @FXML
    public TableColumn<Investitii, String> nrProjColumn;
    public Label valoareaTotala;

    public ObservableList<Investitii> tabelFacturi;
    public TableColumn nrFactColumn;
    public ComboBox comboAlegeProj;
    public ComboBox comboAlegeFz;
    public Button alegePerButton;
    public Button refreshButton;
    public DatePicker secondDate;
    public DatePicker firstDate;
    public Button ExportButton;
    public Button RapoarteButton;


    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement stm = connection.createStatement();
    ResultSet rs1 = stm.executeQuery( "SELECT * FROM invtbl " );
    private URL location;
    private ResourceBundle resources;

    public CtrlStage2Rapoarte () throws SQLException {
    }

    public void goOnSt0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }

    public void goToStage1Intro ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage1Intro.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        this.location = location;
        this.resources = resources;
        furnizorColumn.setCellValueFactory( new PropertyValueFactory<>( "furnizor" ) );
        nrFactColumn.setCellValueFactory( new PropertyValueFactory<>( "nrFactura" ) );
        valoareFactColumn.setCellValueFactory( new PropertyValueFactory<>( "valoare" ) );
        dataCtbColumn.setCellValueFactory( new PropertyValueFactory<>( "dataContabilizarii" ) );
        respProjColumn.setCellValueFactory( new PropertyValueFactory<>( "respProiect" ) );
        contractColumn.setCellValueFactory( new PropertyValueFactory<>( "contract" ) );
        contInvColumn.setCellValueFactory( new PropertyValueFactory<>( "contInv" ) );
        contFzColumn.setCellValueFactory( new PropertyValueFactory<>( "contFz" ) );
        nrProjColumn.setCellValueFactory( new PropertyValueFactory<>( "nrProiect" ) );

        tabelFacturi = FXCollections.observableArrayList();
        try {
            int facturi = 0;
            while (rs1.next()) {
                tabelFacturi.addAll( new Investitii(
                        rs1.getString( "furnizor" ),
                        rs1.getString( "nrFactura" ),
                        rs1.getString( "valoare" ),
                        rs1.getString( "dataContabilizarii" ),
                        rs1.getString( "respProiect" ),
                        rs1.getString( "contract" ),
                        rs1.getString( "contInv" ),
                        rs1.getString( "contFz" ),
                        rs1.getString( "nrProiect" ) ) );
                facturi++;
            }
            tableViewTotal.setItems( tabelFacturi );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement stm = connection.createStatement();
            ResultSet total = stm.executeQuery( "SELECT SUM(valoare) AS valoare FROM invtbl  " );
            while (total.next()) {
                double valoarea = total.getDouble( "valoare" );
                double valTotala = ++valoarea;
                valoareaTotala.setText( String.valueOf( valTotala ) );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            comboAlegeProj.setItems( FXCollections.observableArrayList( Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" ) ))));
//            comboAlegeFz.setItems( FXCollections.observableArrayList( Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" ) ))));
//            comboAlegeFz.setItems( FXCollections.observableArrayList( Files.readAllLines( (Paths.get( "C:/Investitii/resurse/respproj" ) ))));
            comboAlegeFz.setItems( FXCollections.observableArrayList( Files.readAllLines( (Paths.get( "C:/Investitii/resurse/fz" ) ))));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void comboSelectProj ( ActionEvent event ) throws SQLException, IOException {

        tabelFacturi = FXCollections.observableArrayList();
        ResultSet rsProj =stm.executeQuery( "SELECT * FROM invtbl WHERE nrProiect = '"+comboAlegeProj.getValue().toString()+"'" );
        try {
            int facturi = 0;
            while (rsProj.next()) {
                tabelFacturi.addAll( new Investitii(
                        rsProj.getString( "furnizor" ),
                        rsProj.getString( "nrFactura" ),
                        rsProj.getString( "valoare" ),
                        rsProj.getString( "dataContabilizarii" ),
                        rsProj.getString( "respProiect" ),
                        rsProj.getString( "contract" ),
                        rsProj.getString( "contInv" ),
                        rsProj.getString( "contFz" ),
                        rsProj.getString( "nrProiect" ) ) );
                facturi++;
            }
            tableViewTotal.setItems( tabelFacturi );

            String val = "SELECT sum(valoare) as 'totalProiect' FROM invTbl WHERE nrProiect = '" + comboAlegeProj.getValue().toString() + "'  ";
            double totalProiect;
            ResultSet projVal = stm.executeQuery( val );

            while (projVal.next()) {
                totalProiect = projVal.getDouble( "totalProiect" );
                valoareaTotala.setText( String.valueOf( totalProiect ));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        rsProj.close();
//        if (firstDate.getValue()!=null) {
//            clearDataPiker();
//        }
    }

    public void comboSelectFZ ( ActionEvent actionEvent ) throws SQLException, IOException {

        tabelFacturi = FXCollections.observableArrayList();
        ResultSet rsProj =stm.executeQuery( "SELECT * FROM invtbl WHERE furnizor = '"+comboAlegeFz.getValue().toString()+"'" );
        try {
            int facturi = 0;
            while (rsProj.next()) {
                tabelFacturi.addAll( new Investitii(
                        rsProj.getString( "furnizor" ),
                        rsProj.getString( "nrFactura" ),
                        rsProj.getString( "valoare" ),
                        rsProj.getString( "dataContabilizarii" ),
                        rsProj.getString( "respProiect" ),
                        rsProj.getString( "contract" ),
                        rsProj.getString( "contInv" ),
                        rsProj.getString( "contFz" ),
                        rsProj.getString( "nrProiect" ) ) );
                facturi++;
            }
            tableViewTotal.setItems( tabelFacturi );

            String val = "SELECT sum(valoare) as 'totalProiect' FROM invTbl WHERE furnizor = '" + comboAlegeFz.getValue().toString() + "'  ";
            double totalProiect;
            ResultSet projVal = stm.executeQuery( val );

            while (projVal.next()) {
                totalProiect = projVal.getDouble( "totalProiect" );
                valoareaTotala.setText( String.valueOf( totalProiect ));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void comboAlegePer ( ActionEvent actionEvent ) throws SQLException, IOException {
        tabelFacturi = FXCollections.observableArrayList();
        ResultSet rsPer =stm.executeQuery( "SELECT * FROM invtbl WHERE dataContabilizarii BETWEEN '"+firstDate.getValue()+"' AND '"+secondDate.getValue()+"' " );
        try {
            int facturi = 0;
            while (rsPer.next()) {
                tabelFacturi.addAll( new Investitii(
                        rsPer.getString( "furnizor" ),
                        rsPer.getString( "nrFactura" ),
                        rsPer.getString( "valoare" ),
                        rsPer.getString( "dataContabilizarii" ),
                        rsPer.getString( "respProiect" ),
                        rsPer.getString( "contract" ),
                        rsPer.getString( "contInv" ),
                        rsPer.getString( "contFz" ),
                        rsPer.getString( "nrProiect" ) ) );
                facturi++;
            }
            tableViewTotal.setItems( tabelFacturi );

            String valData = "SELECT sum(valoare) as 'totalData' FROM invTbl WHERE dataContabilizarii BETWEEN '"+firstDate.getValue()+"' AND '"+secondDate.getValue()+"'" ;
            double totalData;
            ResultSet projData = stm.executeQuery( valData );

            while (projData.next()) {
                totalData = projData.getDouble( "totalData" );
                valoareaTotala.setText( String.valueOf( totalData ));
                System.out.println(totalData);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void refresh ( ActionEvent actionEvent ) throws SQLException, IOException {
        ResultSet rs1 = stm.executeQuery( "SELECT * FROM invtbl " );

        tabelFacturi = FXCollections.observableArrayList();
        try {
            int facturi = 0;
            while (rs1.next()) {
                tabelFacturi.addAll( new Investitii(
                        rs1.getString( "furnizor" ),
                        rs1.getString( "nrFactura" ),
                        rs1.getString( "valoare" ),
                        rs1.getString( "dataContabilizarii" ),
                        rs1.getString( "respProiect" ),
                        rs1.getString( "contract" ),
                        rs1.getString( "contInv" ),
                        rs1.getString( "contFz" ),
                        rs1.getString( "nrProiect" ) ) );
                facturi++;
            }
            tableViewTotal.setItems( tabelFacturi );
//            comboAlegeProj.setItems( FXCollections.observableArrayList( Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" ) ))));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Statement stm = connection.createStatement();
            ResultSet total = stm.executeQuery( "SELECT SUM(valoare) AS valoare FROM invtbl  " );
            while (total.next()) {
                double valoarea = total.getDouble( "valoare" );
                double valTotala = ++valoarea ;
                valoareaTotala.setText( String.valueOf( valTotala ) );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (firstDate.getValue()!=null) {
            clearDataPiker();
        }
//        comboAlegeFz.setItems( FXCollections.observableArrayList( Files.readAllLines( (Paths.get( "C:/Investitii/resurse/fz" ) ))));


    }
    public void clearDataPiker () throws IOException {
        firstDate.getEditor().clear();
        secondDate.getEditor().clear();
//        comboAlegeProj.setItems( FXCollections.observableArrayList( Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" ) ))));
//        comboAlegeFz.setItems( FXCollections.observableArrayList( Files.readAllLines( (Paths.get( "C:/Investitii/resurse/fz" ) ))));
    }

    public void ExportXlsButton ( ActionEvent actionEvent ) {
            try {
                Connection connection = DriverManager.getConnection(Investitii.URL, Investitii.USER, Investitii.PASSWORD );
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery( "SELECT * FROM invtbl " );

//Print - Crearea si prima linie a fisierilui de raport
                LocalDateTime date0 = LocalDateTime.now();
                DateTimeFormatter date2 = DateTimeFormatter.ofPattern( "yyyy-MM-dd 'ora' hh.mm" );
                String replaceNume2 = date0.format( date2 );

                BufferedWriter writer0 = new BufferedWriter( new FileWriter( "C:\\Investitii\\rapoarte\\detaliu_investitii-" + replaceNume2 + ".csv", false ) );
                writer0.append( "nrCrt; furnizor; nrFactura; dataFacturii; dataContabilizarii; valoare; tva; valTot; contract; contInv; contFz; nrProiect; deviz; org; respProiect" );
                writer0.close();
//Parcurgerea BD si extragerea datelor iterate through the java resultset
                while (rs.next()) {
                    Integer nrCrtPrint = rs.getInt( "nrCrt" );
                    String furnizorPrint = rs.getString( "furnizor" );
                    String nrFacturaPrint = rs.getString( "nrFactura" );
                    Date dataFacturiiPrint = rs.getDate( "dataFacturii" );
                    Date dataContabilizariiPrint = rs.getDate( "dataContabilizarii" );
                    String valoarePrint = rs.getString( "valoare" );
                    String tvaPrint = rs.getString( "tva" );
                    String valTotPrint = rs.getString( "valTot" );
                    String contractPrint = rs.getString( "contract" );
                    String contInvPrint = rs.getString( "contInv" );
                    String contFzPrint = rs.getString( "contFz" );
                    String nrProiectPrint = rs.getString( "nrProiect" );
                    String devizPrint = rs.getString( "deviz" );
                    String orgPrint = rs.getString( "org" );
                    String respProiectPrint = rs.getString( "respProiect" );

//print - adaugarea datelor in fisier
                    String datele = (Integer) nrCrtPrint + ";" + (String) furnizorPrint + ";" + (String) nrFacturaPrint + ";" +dataFacturiiPrint+";"+ dataContabilizariiPrint+";"+ (String) valoarePrint + ";"+(String) tvaPrint + ";"  + (String) valTotPrint + ";" + (String) contractPrint + ";" + (String) contInvPrint + ";" + (String) contFzPrint
                            + ";" +(String) nrProiectPrint  + ";" +(String) devizPrint + ";" +(String)orgPrint  + ";" +(String)respProiectPrint;
                    BufferedWriter writer = new BufferedWriter( new FileWriter( "C:\\Investitii\\rapoarte\\detaliu_investitii-" + replaceNume2 + ".csv", true ) );
                    writer.append( " \n" );
                    writer.append( datele );
                    writer.close();
                }
                Desktop desktop = null;
                desktop.getDesktop().open( new File( "c:\\Investitii\\rapoarte\\detaliu_investitii-" + replaceNume2 + ".csv" ) );

            } catch (IOException | SQLException ioException) {
                ioException.printStackTrace();
            }
    }

    public void RapoarteOpenButton ( ActionEvent actionEvent ) {
        Desktop desktop = null;
        try {
            desktop.getDesktop().open( new File( "c:\\Investitii\\rapoarte" ) );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


