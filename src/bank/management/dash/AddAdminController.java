package bank.management.dash;

import bank.management.atm.AdminLogin;
import bank.management.atm.Conn;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AddAdminController implements Initializable {
    boolean popupShowing=false;
    String adminName;
    @FXML
    private AnchorPane navBarAnchor;
    @FXML
    private FontAwesomeIconView menuIcon,homeIcon,listIcon,imageIcon,settingsIcon;
    @FXML
    private Circle avatarCircle;
    @FXML
    private VBox headerVBox,addAdminVBox;
    @FXML
    private TextField nameTextField,userIDTextField,passTextField;
    @FXML
    private Button createUserBut;
    @FXML
    private Label paraLabel,dateLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        avatarImageFetcher();
        dateSetter();
        nameFetcher();
        DropShadow shadowVBox = new DropShadow();
        shadowVBox.setColor(Color.GRAY);
        shadowVBox.setRadius(5);
        shadowVBox.setOffsetX(-3);
        shadowVBox.setOffsetY(3);
        addAdminVBox.setEffect(shadowVBox);
        paraLabel.setWrapText(true);
        paraLabel.setText("""
                          Before adding a new admin to the dashboard, we want to ensure that the chosen individual is fully qualified and prepared for this role. As an admin, they will have access to sensitive information and will be responsible for managing the dashboard and its users.
                          
                          We recommend thoroughly reviewing the qualifications and experience of the potential admin before proceeding with their addition. Make sure to also consider their ability to work well with the team and adhere to our policies and procedures.""");
        int userid = calculateLargestUserID() + 1;
        userIDTextField.setText(userid+"");
    } 
    

    @FXML
    private void HomeIconClicked(MouseEvent event) {
         try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            
            Scene dashboardScene = new Scene(root);
            
            Stage stage = (Stage) homeIcon.getScene().getWindow();
            stage.setScene(dashboardScene);
            
            
        }catch(IOException e){
            System.out.println(e);
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
    private void avatarIconClicked(MouseEvent event) {
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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
    private void avatarImageFetcher() {
        try{
            Image avatarImage = new Image(getClass().getResourceAsStream("/icons/sampleAvatar.png"));
            avatarCircle.setFill(new ImagePattern(avatarImage));
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    private void dateSetter() {
        //setting date
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd-MMMM-yyyy");
        java.util.Date date = new java.util.Date();
        dateLabel.setText(formatter.format(date));
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
    private void createUserButClicked(MouseEvent event) {
        String name = nameTextField.getText();
        int userid = Integer.parseInt(userIDTextField.getText());
        String password = passTextField.getText();
        if(name.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Name field cannot be empty!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                return;
            }
        }
        if(password.length() < 8){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Password should contain atleast 8 characters!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                return;
            }
        }
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select userid from adminlogin where name='"+name+"'");
            if(rs.next()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("User already exist!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    return;
                }
            }else{
                conn.s.executeUpdate("insert into adminlogin values('"+userid+"','"+name+"','"+password+"');");
                nameTextField.setText("");
                userIDTextField.setText("");
                passTextField.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("User added successfully!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                        Parent root = loader.load();
                        Scene dashboardScene = new Scene(root);
                        Stage stage = (Stage) homeIcon.getScene().getWindow();
                        stage.setScene(dashboardScene);
                    }catch(IOException e){
                        System.out.println(e);
                    }
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
    }

    private int calculateLargestUserID() {
        int userid=2022000;
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select max(userid) from adminlogin;");
            rs.next();
            userid = rs.getInt(1);
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return userid;
    }
    
}
