package com.sorin.springjfx;

import com.sorin.springjfx.ChartApplication.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener <StageReadyEvent>{

//    @Value( "classpath:\\final\\src\\main\\resources\\fxml\\sample.fxml" )
    @Value( "classpath:\\chart.fxml" )
    private Resource chartResource;
    private ApplicationContext applicationContext;

    public StageInitializer ( ApplicationContext applicationContext ) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent ( StageReadyEvent event ) {
        Stage stage = event.getStage();

        try {
            FXMLLoader fxmlLoader= new FXMLLoader( chartResource.getURL() );
            fxmlLoader.setControllerFactory( aClass -> applicationContext.getBean(aClass ) );
            Parent parent=fxmlLoader.load();
            stage.setScene( new Scene( parent, 800, 600 ) );
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
    }
}


