package com.trans.investitii.frontEnd.javaFX.controllers;

import com.trans.investitii.backEnd.DBase.Investitii;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class CtrlStage3RapoarteInv implements Initializable {


    public Label labelProjEstimatInit;
    public Label labelAditionalProj;
    public Label lababelTotalEstimatProj;
    public Label labelTotalRealizatProj;
    public Label labelDiferentaProj;

    public Label labelEstimatInitFz;
    public Label labelAditionalFz;
    public Label lababelTotalEstimatFz;
    public Label labelTotalRealizatFz;
    public Label labelDiferentaFz;

    public Label labelEstimatInitContract;
    public Label labelAditionalContract;
    public Label lababelTotalEstimatContract;
    public Label labelTotalRealizatContract;
    public Label labelDiferentaContract;

    public Label labelEstimatInitOrg;
    public Label labelAditionalOrg;
    public Label labetTotalEstimatOrg;
    public Label labelTotalRealizatOrg;
    public Label labelDiferentaOrg;

    public Label labelCMProj;
    public Label labelEchipamenteProj;
    public Label labelMagazieProj;
    public Label labelAlteInvProj;

    public Label labelCMFz;
    public Label labelEchipamenteFz;
    public Label labemMagazieFz;
    public Label labelAlteInvFz;

    public Label labelCMContract;
    public Label labelEchipamenteContract;
    public Label labelMagazieContract;
    public Label labelAlteInvContract;

    public Label labelCMOrg;
    public Label labelEchipamenteOrg;
    public Label labemMagazieOrg;
    public Label labelAlteInvOrg;
    public DatePicker dataFinala;
    public Button buttonBackSt0;
    public Button goToStage2Rapoarte;
    public ComboBox comboBoxButtonFz;
    public ComboBox comboBoxButtonContract;
    public ComboBox comboBoxButtonOrg;
    public ComboBox comboBoxButtonProj;
    public Button resetButton;
    public Button selectButton;

    Connection connection = DriverManager.getConnection( Investitii.URL, Investitii.USER, Investitii.PASSWORD );
    Statement stm = connection.createStatement();

    public CtrlStage3RapoarteInv () throws SQLException {
    }


    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        List<String> myListFz = null;
        List<String> myListContract = null;
        List<String> myListProj = null;
        List<String> myListOrg = null;

        try {
            myListFz = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/fz") ));
            myListContract = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/contract") ));
            myListOrg = Files.readAllLines( ( Paths.get("C:/Investitii/resurse/org") ));
            myListProj = Files.readAllLines( (Paths.get( "C:/Investitii/resurse/newproj" ) ));


        } catch (IOException e) {
            e.printStackTrace();
        }
        comboBoxButtonFz.setItems( FXCollections.observableArrayList(myListFz));
        comboBoxButtonContract.setItems( FXCollections.observableArrayList(myListContract));
        comboBoxButtonOrg.setItems( FXCollections.observableArrayList(myListOrg));
        comboBoxButtonProj.setItems( FXCollections.observableArrayList(myListProj));


    }


    public void goOnSt0 ( ActionEvent event ) throws IOException {
        Parent tableView = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tabeleViewScene = new Scene( tableView );
        //This line gets the stage inforation
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene( tabeleViewScene );
        window.show();

    }

    public void goToStage2Rapoarte ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage2Rapoarte.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }
    public void comboBoxProj ( ActionEvent actionEvent ) {

    }

    public void comboBoxFzAct ( ActionEvent actionEvent ) {
    }

    public void comboBoxActContract ( ActionEvent actionEvent ) {
    }

    public void comboBoxActOrg ( ActionEvent actionEvent ) {
    }

    public void comboBoxAct ( ActionEvent actionEvent ) {
    }

    public void resetAct ( ActionEvent actionEvent ) {
        comboBoxButtonFz.getSelectionModel().select( null);
        comboBoxButtonProj.getSelectionModel().select( null);
        comboBoxButtonOrg.getSelectionModel().select( null);
        comboBoxButtonContract.getSelectionModel().select( null);
    }


    public void apliFiter ( ActionEvent actionEvent ) throws SQLException {
        String query2 = "SELECT round(SUM(valoare), 2) as 'totalProiect' FROM invTBL WHERE ";

        Object valueProj = comboBoxButtonProj.getValue();
        Object valueFz = comboBoxButtonFz.getValue();
        Object valueOrg = comboBoxButtonOrg.getValue();
        Object valueContract = comboBoxButtonContract.getValue();

        if(valueProj ==null && valueFz==null && valueOrg==null && valueContract==null)    {
            Alert alert = new Alert( Alert.AlertType.INFORMATION );
            alert.setHeaderText( "Da-mi un criteriu sa-ti pot arata ceva" );
            alert.showAndWait();
            return;
        }

        if(valueProj !=null && valueFz==null && valueOrg==null && valueContract==null)    {
//            query2 += "nrProiect='"+valueProj.toString()+"'";
//
            Statement stm2=connection.createStatement();
            ResultSet rsProj2 = stm2.executeQuery( query2 );
            double totalProiect=0.00;
            try {
                while (rsProj2.next()) {
                    totalProiect = rsProj2.getDouble( "totalProiect" );
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            labelTotalRealizatProj.setText( String.valueOf( totalProiect ) );

        }


        if(valueFz !=null && valueOrg==null && valueContract==null) {
            if (valueProj == null) {
                query2 += " furnizor='"+valueFz.toString()+"' ";
            } else {
                query2 += "nrProiect='"+valueProj.toString()+"' AND furnizor='"+valueFz.toString()+ "' ";
            }
        }

        if(valueOrg !=null && valueContract!=null) {
            if (valueProj != null && valueFz != null) {
                query2 += "nrProiect='" + valueProj.toString() + "' AND furnizor='" + valueFz.toString() + "' AND dataContabilizarii BETWEEN '" +valueOrg.toString()+ "' AND '" +valueContract.toString()+ "' ";
            }
            if (valueProj == null && valueFz !=null){
                query2 += "furnizor='" + valueFz.toString() + "' AND dataContabilizarii BETWEEN '" +valueOrg.toString()+ "' AND '" +valueContract.toString()+ "' ";

            }
//            if (valueProj ==null && valueFz ==null){
//                query += "dataContabilizarii BETWEEN '" + valueOrg.toString() + "' AND '"+ valueContract.toString()+ "' ";
//                query2 += "dataContabilizarii BETWEEN '" +valueOrg.toString() + "' AND '" +valueContract.toString()+ "' ";
//            }
//        }
//        if ((valueOrg ==null && valueContract!=null) || (valueOrg !=null && valueContract==null)){
//            Alert alert = new Alert( Alert.AlertType.INFORMATION );
//            alert.setHeaderText( "Trebuie completate ambele campuri de date!" );
//            alert.showAndWait();
//            return;
        }


    }
}
