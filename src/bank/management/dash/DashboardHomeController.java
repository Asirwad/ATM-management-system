package bank.management.dash;

import bank.management.atm.AdminLogin;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class DashboardHomeController implements Initializable {
    boolean popupShowing = false; 
    @FXML
    private Circle avatarCircle;
    @FXML
    private BarChart<String, Double> chart;
    @FXML
    private FontAwesomeIconView homeIcon,listIcon,imageIcon,settingsIcon;
    @FXML
    private Button viewAllTransacBut,settingsBut;
    @FXML
    private FontAwesomeIconView menuIcon;
    @FXML
    private AnchorPane navBarAnchor;
    @FXML
    private Label dateLabel;
    @FXML
    private AnchorPane bottomAnchor;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //getting current date and time
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date date = new java.util.Date();
        dateLabel.setText(formatter.format(date));
        
        try{
            Image avatarImage = new Image(getClass().getResourceAsStream("/icons/xav.jpg"));
            avatarCircle.setFill(new ImagePattern(avatarImage));
        }catch(Exception ex){
            System.out.println(ex);
        }
        XYChart.Series<String, Double> series_01 = new XYChart.Series<String, Double>();
        series_01.setName("Year 2022");
        series_01.getData().add(new XYChart.Data("Jan", 500));
        series_01.getData().add(new XYChart.Data("Feb", 400));
        series_01.getData().add(new XYChart.Data("Mar", 300));
        series_01.getData().add(new XYChart.Data("Apr", 200));
        series_01.getData().add(new XYChart.Data("May", 700));
        chart.getData().add(series_01);
        
        
        
        
    }    

    @FXML
    private void viewAllTransacFun(MouseEvent event) {
        
    }

    @FXML
    private void listIconClicked(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listAccounts.fxml"));
            Parent root = loader.load();
            
            Scene listAccountsScene = new Scene(root);
            
            Stage stage = (Stage) listIcon.getScene().getWindow();
            stage.setScene(listAccountsScene);
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void avatarCircleClicked(MouseEvent event) {
        if(!popupShowing){
            
            String  style= getClass().getResource("dynamicComponents.css").toExternalForm();
            Scene sc = navBarAnchor.getScene();
            sc.getStylesheets().add(style);
            
            avatarCircle.setRadius(20);
            Popup popup = new Popup();
            Label label = new Label("User: John Doe");
            label.setStyle("-fx-font-size: 14pt; -fx-text-fill: 4C4C4E;");
            
            Button logoutButton = new Button("Logout");
            logoutButton.setId("logoutButton");
            logoutButton.getStyleClass().add("logoutButton");
            logoutButton.setOnMouseClicked((MouseEvent event2) -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirm Exit");
                alert.setHeaderText("Are you sure you want to exit?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Platform.exit();
                    new AdminLogin().setVisible(true);
                }
            });
            

            VBox avatarCirclelayout = new VBox(label, logoutButton);
            avatarCirclelayout.setPadding(new Insets(10));
            avatarCirclelayout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85); "
                + "-fx-border-color: white; "
                + "-fx-border-width: 1; "
                + "-fx-border-radius: 20px;");

            popup.getContent().add(avatarCirclelayout);
            popup.setAutoHide(true);
            popup.show(avatarCircle, event.getScreenX()+30, event.getScreenY()-70);
            popup.setOnHidden(e -> {
                avatarCircle.setRadius(15);
                popupShowing = false;
            });
            popupShowing = true;
        }
    }
    
}
