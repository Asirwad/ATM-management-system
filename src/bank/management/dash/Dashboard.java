package bank.management.dash;

import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Dashboard extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
       nameRetrive();
       
       Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
       Scene scene = new Scene(root);
       primaryStage.setScene(scene);
       primaryStage.setResizable(false);
       primaryStage.initStyle(StageStyle.DECORATED);
       primaryStage.show();
       
       
    }
    public static void main(String[] args){
        launch(args);
    }
    public void nameRetrive(){
        try{
            Parameters params = getParameters();
            List<String> pList = params.getRaw();
            String name = pList.get(0);
            System.out.println(name);
       }catch(Exception ex){
           System.out.println(ex);
       }
    }
}
