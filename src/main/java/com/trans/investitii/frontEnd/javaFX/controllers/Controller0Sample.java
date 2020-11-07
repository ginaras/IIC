package com.trans.investitii.frontEnd.javaFX.controllers;

import com.trans.investitii.backEnd.dataBase.configuation.HibernateConfiguration;
import com.trans.investitii.backEnd.services.OrchestratorServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Controller0Sample implements Initializable {

    @FXML
    public Button goToStage1Intro;
    public Button adminFZ;
    public Button adminContracte;
    public Button adminCtInvest;
    public Button adminCtFz;
    public Button adminNrProiect;
    public Button adminRespProiect;
    public Button adminDeviz;
    public Button adminOrg;
    public Button goToStageBDelete;

    public void goToStage1Intro( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/Stage1Intro.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

    }

    public void goOnAdminFz ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminFz.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();

      //  ExportItemsInList.exportFzList();
    }

    public void goOnadminContracte ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminContracte.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnAdminCtInvest ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminContInv.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnAdminCtFz ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminContFz.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnAdminNrProiect ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminNrProiect.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnAdminRespProiect ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminRespProiect.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }


    public void goOnAdminDeviz ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminDeviz.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

    public void goOnAdminOrg ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageAdminOrg.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }
    public void goToStageBDelete ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/admin/StageBAdminDelete.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }

//    public void runHybernate ( ActionEvent actionEvent ) {
//        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory(); //refactorizare- o deschid pt o refolosi pe prcursul rularii
//        OrchestratorServices orchestratorServices = new OrchestratorServices( sessionFactory );//refactorizare
//        orchestratorServices.runApplication();
//        //sessionFactory.close();
//
//
//    }

    @Override
    public void initialize ( java.net.URL location, ResourceBundle resources ) {
        File file = new File( "c:\\Investitii" );
        File file1 = new File( "c:\\Investitii\\resurse" );
        File file2= new File( "c:\\Investitii\\rapoarte" );


        boolean fileExists = file.mkdir();
        boolean fileExists1 = file1.mkdir();
        boolean fileExists2 = file2.mkdir();

        File fz= new File("c:\\Investitii\\resurse\\fz" );
        File contract= new File("c:\\Investitii\\resurse\\contract" );
        File ctfz= new File("c:\\Investitii\\resurse\\ctFz" );
        File ctInv= new File("c:\\Investitii\\resurse\\ctInv" );
        File cui= new File("c:\\Investitii\\resurse\\cui" );
        File deviz= new File("c:\\Investitii\\resurse\\deviz" );
        File newproj= new File("c:\\Investitii\\resurse\\newproj" );
        File org= new File("c:\\Investitii\\resurse\\org" );
        File respproj= new File("c:\\Investitii\\resurse\\respproj" );
        File export = new File("c:\\Investitii\\rapoarte\\export.txt" );


        if (!fz.exists()){
            try {
                fz.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!contract.exists()){
            try {
                contract.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!ctfz.exists()){
            try {
                ctfz.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!ctInv.exists()){
            try {
                ctInv.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!cui.exists()){
            try {
                cui.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!deviz.exists()){
            try {
                deviz.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!newproj.exists()){
            try {
                newproj.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!org.exists()){
            try {
                org.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!respproj.exists()){
            try {
                respproj.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
