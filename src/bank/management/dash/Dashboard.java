package bank.management.dash;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Dashboard extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
       
       Image icon = new Image("C:\\Users\\asirw\\OneDrive\\Documents\\NetBeansProjects\\bank management system\\src\\icons\\bankIcon.png");
       primaryStage.getIcons().add(icon);
       Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
       Scene scene = new Scene(root);
       primaryStage.setScene(scene);
       primaryStage.setResizable(false);
       primaryStage.initStyle(StageStyle.DECORATED);
       primaryStage.show();
       primaryStage.setOnCloseRequest(event -> System.exit(0));
       
    }
    public static void main(String[] args){
        launch(args);
    }
}
