package com.trans.investitii.frontEnd.javaFX.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.text.Collator;
import java.util.*;
import java.util.List;

public class ControllerStageAdminOrg implements Initializable {
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
    public Button back0S;
    public Button exportButton;
    public TextArea ItemList;
    public Button addButton;
    public TextField addPOrg;
    public Text added;
    public String pathFileOrg = "C:\\Investitii\\resurse\\org";


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
    public void click(){
        this.adminOrg.setDisable( true );
    }

    @Override
    public void initialize ( URL location, ResourceBundle resources ) {
        try {
            Scanner s = new Scanner(new File("C:\\Investitii\\resurse\\org")).useDelimiter("\\s+");
            while (s.hasNext()) {
                if (s.hasNextInt()) { // check if next token is an int
                    ItemList.appendText(s.nextInt() + " "+"\n"); // display the found integer
                } else {
                    ItemList.appendText(s.next() + " "+"\n"); // else read the next token
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }


    }



    public void buttonBack ( ActionEvent event ) throws IOException {
        Parent stage1Intro = FXMLLoader.load( getClass().getResource( "/fxml/sample.fxml" ) );
        Scene tableViewScene = new Scene( stage1Intro );
        Stage windowStage1Intro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowStage1Intro.setScene( tableViewScene );
        windowStage1Intro.show();
    }
    public void exportOrg ( ActionEvent event ) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter("C:\\Investitii\\rapoarte\\export.txt") );
        bufferedWriter.append( ItemList.getText() );
        bufferedWriter.close();

        Desktop desktop= null;
        try {
            desktop.getDesktop().open( new File( "C:\\Investitii\\rapoarte\\export.txt" ) ); ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewOrg ( ActionEvent event ) throws FileNotFoundException {
        String fileLine;
        String addTextFieldString = addPOrg.getCharacters().toString();
        BufferedReader bReader = new BufferedReader(new FileReader( pathFileOrg ));

        if (addTextFieldString.isEmpty()) {
            Alert fail = new Alert( Alert.AlertType.INFORMATION );
            fail.setHeaderText( "Atentie!" );
            fail.setContentText( "Nu poti introduce campuri goale!" );
            fail.showAndWait();
            //  break;
        }
        else {
            try {
                while((fileLine=bReader.readLine()) != null){
                    if(fileLine.toLowerCase().equals(addTextFieldString.toLowerCase())) {
                        Alert fail = new Alert( Alert.AlertType.INFORMATION );
                        fail.setHeaderText( "Atentie!" );
                        fail.setContentText( "Elementul "+addTextFieldString+" exista in baza de date" );
                        fail.showAndWait();
                        addPOrg.clear();
                        break;
                    }
                }

                if(fileLine==null || !fileLine.toLowerCase().equals(addTextFieldString.toLowerCase())){
                    BufferedWriter writer = new BufferedWriter( new FileWriter( pathFileOrg, true ) );
                    writer.append( addTextFieldString+ "\n" );
                    writer.close();
                    ItemList.appendText( addTextFieldString + "\n" ); // ad data in TextArea from text field
                    addPOrg.clear();
                    this.added.setText( "Ati adaugat cu succes" );
                    sortFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void sortFile() throws IOException
    {
        FileReader fileReader = new FileReader("C:\\Investitii\\resurse\\org");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();

        Collections.sort(lines, Collator.getInstance());

        FileWriter writer = new FileWriter("C:\\Investitii\\resurse\\org");
        for(String str: lines) {
            writer.write(str + "\r\n");
        }
        writer.close();
    }
}
