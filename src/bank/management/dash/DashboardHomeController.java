package bank.management.dash;

import bank.management.atm.AdminLogin;
import bank.management.atm.Conn;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
//import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
import javafx.util.Duration;


public class DashboardHomeController implements Initializable {
    boolean popupShowing = false; 
    private volatile boolean stopThread = false;
    String adminName;
    @FXML
    private Circle avatarCircle;
    @FXML
    private BarChart<String, Double> chart;
    @FXML
    private FontAwesomeIconView homeIcon,listIcon,imageIcon,settingsIcon,menuIcon,plusBut;
    @FXML
    private Button viewAllTransacBut,settingsBut;
    @FXML
    private AnchorPane navBarAnchor;
    @FXML
    private Label dateLabel,incomeLabel,expenseLabel,dateLabelFooter,collectedLabel,transferredLabel;
    @FXML
    private AnchorPane bottomAnchor;
    @FXML
    private Label welcomeLabel;
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //listIcon.setGlyphStyle("-fx-fill: #C1C1C1;");
        //homeIcon.setGlyphStyle("-fx-fill: #2B49B3;");
        nameFetcher();
        welcomeLabel.setText("Hello "+adminName+", welcome back!");
        incomeAndExpense();
        timeCalculator();
        avatarImageFetcher();
        barChartCalculator();
        addviewAllTransacAndsettingsButAnimations();
        SimpleDateFormat sdfDateOnly = new SimpleDateFormat("dd-MMMM-yyyy");
        final String dateNow = sdfDateOnly.format(new java.util.Date());
        dateLabelFooter.setText(dateNow);
    }   

    @FXML
    private void viewAllTransacFun(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewAllTransactions.fxml"));
            Parent root = loader.load();
            
            Scene viewAllTransacScene = new Scene(root);
            
            Stage stage = (Stage) listIcon.getScene().getWindow();
            stage.setScene(viewAllTransacScene);
           
        }catch(IOException e){
            e.printStackTrace();
        }
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
            Label adminNameLabel = new Label("    @"+adminName);
            adminNameLabel.setStyle("-fx-font-size: 14pt; -fx-text-fill: 4C4C4E; -fx-font-weight: bold;");
            
            
            
            Button logoutButton = new Button("Logout");
            logoutButton.setId("logoutButton");
            logoutButton.getStyleClass().add("logoutButton");
            
            TranslateTransition transition = new TranslateTransition(Duration.millis(50), logoutButton);
            transition.setByY(-3);
            
            logoutButton.setOnMouseClicked((MouseEvent event2) -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirm Exit");
                alert.setHeaderText("Are you sure you want to exit?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    System.exit(0);
                    new AdminLogin().setVisible(true);
                }
            });
            logoutButton.setOnMousePressed(e -> transition.playFromStart());
            logoutButton.setOnMouseReleased(e -> transition.stop());
            

            VBox avatarCirclelayout = new VBox(adminNameLabel, logoutButton);
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

    private void incomeAndExpense() {
        try{
            Conn conn = new Conn();
            ResultSet rsDeposit = conn.s.executeQuery("select amount from bank where type='Deposit';");
            rsDeposit.next();
            float totalAmount=0;
            while(rsDeposit.next()){
                totalAmount+=rsDeposit.getInt("amount");
            }
            collectedLabel.setText("₹"+totalAmount);
            incomeLabel.setText("₹"+(totalAmount/50));
            ResultSet rsWithdraw = conn.s.executeQuery("select amount from bank where type='Withdrawl';");
            rsWithdraw.next();
            totalAmount=0;
            while(rsWithdraw.next()){
                totalAmount+=rsWithdraw.getInt("amount");
            }
            transferredLabel.setText("₹"+totalAmount);
            expenseLabel.setText("₹"+(totalAmount-(totalAmount/50)));
        }catch(SQLException ex){
            //do nothing
        }
    }

    private void avatarImageFetcher() {
        try{
            Image avatarImage = new Image(getClass().getResourceAsStream("/icons/sampleAvatar.png"));
            avatarCircle.setFill(new ImagePattern(avatarImage));
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    private void barChartCalculator() {
        float totalSavingsAmount=0,totalFixedAmount=0,totalRDAmount=0,totalCurrentAmount=0;
        try{
            Conn conn = new Conn();
            ResultSet rsSavings = conn.s.executeQuery("select bank.amount from bank natural join signupthree where accountType='Savings Account';");
            //rsSavings.next();
            while(rsSavings.next()){
                totalSavingsAmount+=rsSavings.getInt("amount");
            }
            ResultSet rsFixed = conn.s.executeQuery("select bank.amount from bank natural join signupthree where accountType='Fixed Account';");
            //rsFixed.next();
            while(rsFixed.next()){
                totalFixedAmount+=rsFixed.getInt("amount");
            }
            ResultSet rsRD = conn.s.executeQuery("select bank.amount from bank natural join signupthree where accountType='Recurring Deposit Account';");
            //rsRD.next();
            while(rsRD.next()){
               totalRDAmount+=rsRD.getInt("amount");
            }
            ResultSet rsCurrent = conn.s.executeQuery("select bank.amount from bank natural join signupthree where accountType='Current Account';");
            //rsCurrent.next();
            while(rsCurrent.next()){
                totalCurrentAmount+=rsCurrent.getInt("amount");
            }

        }catch(SQLException ex){
            System.out.println(ex);
        }
        XYChart.Series<String, Double> series_01 = new XYChart.Series<String, Double>();
        series_01.setName("Account type wise transactions");
        series_01.getData().add(new XYChart.Data("Savings", totalSavingsAmount));
        series_01.getData().add(new XYChart.Data("Fixed", totalFixedAmount));
        series_01.getData().add(new XYChart.Data("RD", totalRDAmount));
        series_01.getData().add(new XYChart.Data("Current", totalCurrentAmount));
        chart.getData().add(series_01);
    }

    private void nameFetcher(){
       try{
            Conn conn = new Conn();
            String query = "select name from adminLoginRecord;";
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next())
                adminName =  rs.getString("name");
       }catch(SQLException ex){
           System.out.println(ex);
       }   
    }

    @FXML
    private void settingsClicked(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
            Parent root = loader.load();
            
            Scene settingsScene = new Scene(root);
            
            Stage stage = (Stage) settingsIcon.getScene().getWindow();
            stage.setScene(settingsScene);
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void plusButClicked(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAdmin.fxml"));
            Parent root = loader.load();
            
            Scene addUserScene = new Scene(root);
            
            Stage stage = (Stage) plusBut.getScene().getWindow();
            stage.setScene(addUserScene);
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void addviewAllTransacAndsettingsButAnimations() {
        TranslateTransition viewAllTransacTransition = new TranslateTransition(Duration.millis(50), viewAllTransacBut);
        viewAllTransacTransition.setByY(3);
        viewAllTransacBut.setOnMousePressed(e -> viewAllTransacTransition.playFromStart());
        viewAllTransacBut.setOnMouseReleased(e -> viewAllTransacTransition.stop());
        
        TranslateTransition settingsButTransition = new TranslateTransition(Duration.millis(50), settingsBut);
        settingsButTransition.setByY(3);
        settingsBut.setOnMousePressed(e -> settingsButTransition.playFromStart());
        settingsBut.setOnMouseReleased(e -> settingsButTransition.stop());
    }

    private void timeCalculator() {
        Thread thread = new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a dd-MMMM-yyyy");
            while(!stopThread){
                try{
                    Thread.sleep(1);
                }catch(Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new java.util.Date());
                Platform.runLater(() ->{
                    dateLabel.setText(timenow);
                });
            }
        });
        thread.start();
    } 
}
