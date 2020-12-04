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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.cassandra.io.DeletionService;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Double.parseDouble;

public class CtrlStage1Intro implements Initializable {

    @FXML
    public Label CUI;
    public Label furnizor;
    public Label nrFactura;
    public Label Data;
    public Label valoare;
    public Label proiect;
    public Label deviz;
    public Label utt;
    public Label Data1;
    public Label respProj;

    public ComboBox comBoboxFz;
    public TextField fieldNrFact;
//    public TextField fieldCUI;
    public TextField fieldValFact;
    public DatePicker fieldDataFactura;
    public DatePicker fieldDataGL;
    public ComboBox comboBoxContract;
    public ComboBox comboBoxCtInv;
    public ComboBox cBCtFz;
    public ComboBox cBProjNr;
    public ComboBox comboBoxDeviz;
    public ComboBox comboBoxOrg;
    public ComboBox comboBoxRespProj;
    public TableColumn furnizorColumn;
    public TableColumn facturaColumn;
    public TableColumn valoareColumn;
    public TableColumn contInvColumn;
    public TableColumn respProjColumn;
    public TableColumn nrProjColumn;
    public TableColumn dataContabilizarii;
    public TextField nrDeFacturiDeAfisat;
    public Button validFacturaButton;

    @FXML
    TableView <Investitii> tableView;


    public Button buttonBackSt0;
    public Button addFacturaButtonId;

    public CtrlStage1Intro () throws SQLException {
    }



    public ObservableList<Investitii> getInvestitii (){
        ObservableList <Investitii> investitii = FXCollections.observableArrayList() ;
        return investitii;
    }

    public void goOnSt0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();
    }
    public void goToStage2Rapoarte ( ActionEvent event ) throws SQLException, IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage2Rapoarte.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

    }
    public ObservableList <Investitii> invest;
    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement statement = connection.createStatement();

    ResultSet rs1 =statement.executeQuery( "SELECT * FROM invTBL");// WHERE dataContablizarii > LOCALDATE " );


    public void addFacturaButton ( ActionEvent event ) throws IOException, InterruptedException {
//        validareCampuri();
        addFactToSQL( connection );
        clearData();

    }
    public void addFactToSQL (Connection connection){
        String addSql = "INSERT INTO invTBL  (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(addSql) ){

            Investitii newInvestitii = new Investitii(
                    comBoboxFz.getValue(),
                    fieldNrFact.getText(),
                    fieldValFact.getText(),
                    fieldDataFactura.getValue(),
                    fieldDataGL.getValue(),
                    comboBoxContract.getValue(),
                    comboBoxCtInv.getValue(),
                    cBCtFz.getValue(),
                    comboBoxRespProj.getValue(),
                    comboBoxDeviz.getValue(),
                    comboBoxOrg.getValue(),
                    cBProjNr.getValue());

           if(!fieldValFact.getText().isEmpty() || !(fieldDataGL.getValue() ==null) || !(fieldDataFactura.getValue()==null))
            {
               double val= parseDouble( fieldValFact.getText());
                    val = Math.round( val*100);
                    val = val/100;
               double tva0 = val * 0.19;
               tva0 = Math.round( tva0 * 100 );
               double tva = tva0 / 100;
               double valTot = val + tva;
               valTot = Math.round( valTot * 100 );
               valTot = valTot / 100;

////            statement.executeUpdate( new StringBuilder().append( "INSERT INTO investitiitable (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect) VALUES(' " ).append( comBoboxFz.getValue() ).append( " ',' " ).append( fieldNrFact.getText() ).append( "','" ).append( fieldDataFactura.getValue() ).append( " ',' " ).append( fieldDataGL.getValue() ).append( "','" ).append( val ).append( " ',' " ).append( tva ).append( " ' , '" ).append( valTot ).append( " ',' " ).append( comboBoxContract.getValue() ).append( "','" ).append( comboBoxCtInv.getValue() ).append( "','" ).append( cBCtFz.getValue() ).append( "','" ).append( cBProjNr.getValue() ).append( "','" ).append( comboBoxDeviz.getValue() ).append( "','" ).append( comboBoxOrg.getValue() ).append( "','" ).append( comboBoxRespProj.getValue() ).append( "')" ).toString() );
               statement.executeUpdate( "INSERT INTO invtbl (furnizor, nrFactura, dataFacturii, dataContabilizarii, valoare, tva, valTot, contract, contInv, contFz, nrProiect, deviz, org, respProiect) VALUES(' " + comBoboxFz.getValue() + " ',' " + fieldNrFact.getText() + "','" + fieldDataFactura.getValue() + " ',' " + fieldDataGL.getValue() + "','" + val + " ',' " + tva + " ' , '" + valTot + " ',' " +
                       comboBoxContract.getValue() + "','" + comboBoxCtInv.getValue() + "','" + cBCtFz.getValue() + "','" + cBProjNr.getValue() + "','" + comboBoxDeviz.getValue() + "','" + comboBoxOrg.getValue() + "','" + comboBoxRespProj.getValue() + "')" );

               Alert confirm = new Alert( Alert.AlertType.INFORMATION );
               confirm.setHeaderText( "Factura a fost adaugata" );
               confirm.setContentText( "TVA:" + tva + "  Valoare Totala: " + valTot );
               confirm.show();

               tableView.getItems().addAll( newInvestitii ); // adauga campuri in tabel)
           }  } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        addFacturaButtonId.setDisable( true );

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
//            myListProj = Files.AllLines( (Paths.get( "C:/Investitii/resurse/newproj" ) ));

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
//pt tabel in scena
        furnizorColumn.setCellValueFactory( new PropertyValueFactory<>( "furnizor" ) );
        facturaColumn.setCellValueFactory( new PropertyValueFactory<>( "nrFactura" ) );
        valoareColumn.setCellValueFactory(  new PropertyValueFactory<>( "valoare" ) );
        contInvColumn.setCellValueFactory( new PropertyValueFactory<>( "contInv" ) );
        nrProjColumn.setCellValueFactory( new PropertyValueFactory<>( "nrProiect" ) );
        respProjColumn.setCellValueFactory( new PropertyValueFactory<>( "respProiect" ) );
        dataContabilizarii.setCellValueFactory( new PropertyValueFactory<>( "dataContabilizarii" ) );
        tableView.setItems(getInvestitii()  );

        invest = FXCollections.observableArrayList();

        try {

//            int factura=0;
            while (rs1.next()) {
                if(rs1.isLast()){invest.addAll( new Investitii(
                        rs1.getObject( "furnizor" ),
                        rs1.getString( "nrFactura" ),
                        rs1.getString( "valoare" ),
                        rs1.getObject( "contInv" ),
                        rs1.getObject( "nrProiect" ),
                        rs1.getObject( "respProiect" ),
                        rs1.getString("dataContabilizarii")
                ));}}

            tableView.setItems( invest);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void clearData() throws IOException {
        comBoboxFz.setItems( FXCollections.observableArrayList(Files.readAllLines( ( Paths.get("C:/Investitii/resurse/fz") ))));
        fieldNrFact.setText( null );
        fieldValFact.setText( null );
        fieldDataFactura.getEditor().clear();
        fieldDataGL.getEditor().clear();
        comboBoxRespProj.setItems( FXCollections.observableArrayList(Files.readAllLines( ( Paths.get("C:/Investitii/resurse/respproj") ))));
        comboBoxContract.setItems( FXCollections.observableArrayList(Files.readAllLines( ( Paths.get("C:/Investitii/resurse/contract") ))));
        comboBoxCtInv.setItems( FXCollections.observableArrayList(Files.readAllLines( ( Paths.get("C:/Investitii/resurse/ctInv") ))));
        cBCtFz.setItems( FXCollections.observableArrayList(Files.readAllLines( ( Paths.get("C:/Investitii/resurse/ctFz") ))));
        cBProjNr.setItems( FXCollections.observableArrayList(Files.readAllLines( ( Paths.get("C:/Investitii/resurse/newproj") ))));
        comboBoxDeviz.setItems( FXCollections.observableArrayList(Files.readAllLines( ( Paths.get("C:/Investitii/resurse/deviz") ))));
        comboBoxOrg.setItems( FXCollections.observableArrayList(Files.readAllLines( ( Paths.get("C:/Investitii/resurse/org") ))));
    }

    public void validareCampuri() throws InterruptedException {
        if (comBoboxFz.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege Furnizorul" );
            alert.showAndWait();
        }

        if (fieldNrFact.getText().isEmpty()) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Adauga numarul de factura!" );
            alert.showAndWait();
        }
        if (fieldValFact.getText().isEmpty()) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Adauga VALOAREA facturii fara TVA!" );
            alert.showAndWait();
        }

        try {
            double s = Double.parseDouble( fieldValFact.getText() );
            String f = fieldValFact.getText();

            if (f.matches( "\\d+(\\.\\d\\d)" )) {
//                double round =Math.round(s*100);
//                double roundAt2= round/100;
//                fieldValFact.setText( String.valueOf( roundAt2 ) );
//
//            System.out.println(roundAt2);
            }

        } catch (NumberFormatException e) {
            Alert alert1 = new Alert( Alert.AlertType.INFORMATION );
            alert1.setHeaderText( "Adauga VALOAREA ca numar! Atentie la virgula!" );
            alert1.showAndWait();
        }

        if (fieldDataFactura.getValue() == null || fieldDataGL.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Adauga data!" );
            alert.showAndWait();
        }
        if (comboBoxContract.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege numarul de contract" );
            alert.showAndWait();
        }
        if (comboBoxCtInv.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege contul de investitii" );
            alert.showAndWait();
        }
        if (cBCtFz.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege contul de furnizori" );
            alert.showAndWait();
        }
        if (cBProjNr.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege numarul de proiect" );
            alert.showAndWait();
        }
        if (comboBoxRespProj.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege responsabilul de proiect" );
            alert.showAndWait();
        }
        if (comboBoxDeviz.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege numarul de deviz" );
            alert.showAndWait();
        }
        if (comboBoxOrg.getValue() == null) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Alege organizatia" );
            alert.showAndWait();
        }

        if (   comBoboxFz.getValue() != null && !fieldNrFact.getText().isEmpty() && !fieldValFact.getText().isEmpty()
                && fieldDataFactura.getValue() != null && fieldDataGL.getValue() != null
                && !(comboBoxContract.getValue() == null) && !(comboBoxCtInv.getValue() == null) && !(cBCtFz.getValue() == null)
                && !(cBProjNr.getValue() == null) && !(comboBoxDeviz.getValue() == null)
                && !(comboBoxOrg.getValue() == null) && !(comboBoxRespProj.getValue() == null))
        {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Toate campurile au fost completate!" );
            alert.showAndWait();
            addFacturaButtonId.setDisable( false );
        }
    }

}
