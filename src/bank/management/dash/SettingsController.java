
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
import javafx.animation.RotateTransition;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SettingsController implements Initializable {
    boolean popupShowing=false;
    String adminName;
    @FXML
    private AnchorPane navBarAnchor;
    @FXML
    private FontAwesomeIconView menuIcon,homeIcon,listIcon,imageIcon,settingsIcon,plusBut;
    @FXML
    private Circle avatarCircle;
    @FXML
    private TextField oldPasswordTextField,newPasswordTextField,reNewPassTextField;
    @FXML
    private VBox changePassVBox,headerVBox,adminInfoVBox,removeUsersVBox;
    @FXML
    private Circle avatarCircleMain;
    @FXML
    private Label AdminNameLabel,adminIdLabel,adminParaLabel,paraLabel,dateLabel;
    @FXML
    private Button changePasswordBut;
    @FXML
    private StackPane rmUserStackPane;
    @FXML
    private VBox rmUserVBoxOne;
    @FXML
    private Button proceedBut;
    @FXML
    private VBox rmUserVBoxTwo;
    @FXML
    private TextField userIdTextField;
    @FXML
    private CheckBox rmUserCheckBox;
    @FXML
    private Button removeBut;
    @FXML
    private Button backBut;
    
    DropShadow shadowVBoxBlue,shadowVBox;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateSetter();
        avatarImageFetcher();
        nameFetcher();
        adminIDFetcher();
        VBoxEffectsInit();
        paraLabel.setWrapText(true);
        paraLabel.setText("Please create a strong password to protect your account. A strong password should be long (at least 8 characters), complex (include a mix of letters, numbers, and special characters), and unique (not used on any other accounts). Avoid using personal information or common words as part of your password."); 
        adminParaLabel.setWrapText(true);
        adminParaLabel.setText("Stay up-to-date with the latest activity and trends by checking the analytics section of the dashboard");

        rmUserVBoxOne.setVisible(true);
        rmUserVBoxTwo.setVisible(false);
        
        Color pinkColor = Color.web("#8382ff");
        DropShadow avatarShadow = new DropShadow();
        avatarShadow.setColor(pinkColor);
        avatarShadow.setRadius(10);
        avatarCircleMain.setEffect(avatarShadow);
        AdminNameLabel.setText("Name : "+adminName);
    }   
    
    @FXML
    private void HomeIconClicked(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            
            Scene homeScene = new Scene(root);
            
            Stage stage = (Stage) homeIcon.getScene().getWindow();
            stage.setScene(homeScene);
            
            
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
    private void dateSetter() {
        //setting date
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd-MMMM-yyyy");
        java.util.Date date = new java.util.Date();
        dateLabel.setText(formatter.format(date));
    }

    private void avatarImageFetcher() {
        try{
            Image avatarImage = new Image(getClass().getResourceAsStream("/icons/sampleAvatar.png"));
            avatarCircle.setFill(new ImagePattern(avatarImage));
            avatarCircleMain.setFill(new ImagePattern(avatarImage));
        }catch(Exception ex){
            System.out.println(ex);
        }
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
    private void changePassButClicked(MouseEvent event) {
        String oldPass = oldPasswordTextField.getText();
        String newPass = newPasswordTextField.getText();
        String reNewPass = reNewPassTextField.getText();
        String dbOldPass;
        if(!newPass.equals(reNewPass)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Not Same");
            alert.setHeaderText("Entered passwords dosent match");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                 return;
            }
        }
        if(newPass.length() < 8){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Password should contain atleast 8 characters");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                 return;
            }
        }
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select password from adminlogin where name='"+adminName+"'");
            rs.next();
            dbOldPass = rs.getString("password");
            System.out.println(dbOldPass);
            if(dbOldPass.equals(oldPass)){
                conn.s.executeUpdate("UPDATE adminlogin set password='"+newPass+"' where name='"+adminName+"'");
                oldPasswordTextField.setText("");
                newPasswordTextField.setText("");
                reNewPassTextField.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Info");
                alert.setHeaderText("Password changed successfully");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    return;
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Not Same");
                alert.setHeaderText("Old Password is incorrect");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    return;
                }
            }
        }catch(SQLException ex){
            System.out.println(ex);
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

    private void adminIDFetcher() {
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select userid from adminlogin where name='"+adminName+"'");
            rs.next();
            adminIdLabel.setText("ID : "+rs.getInt("userid"));
        }catch(SQLException ex){
            System.out.println(ex);
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

    private void VBoxEffectsInit() {
        shadowVBox = new DropShadow();
        shadowVBox.setColor(Color.GRAY);
        shadowVBox.setRadius(5);
        shadowVBox.setOffsetX(3);
        shadowVBox.setOffsetY(3);
        changePassVBox.setEffect(shadowVBox);
        adminInfoVBox.setEffect(shadowVBox);
        removeUsersVBox.setEffect(shadowVBox);

        RotateTransition rotateIn = new RotateTransition(Duration.seconds(0.2), adminInfoVBox);
        rotateIn.setFromAngle(0);
        rotateIn.setToAngle(1.7);

        RotateTransition rotateOut = new RotateTransition(Duration.seconds(0.2), adminInfoVBox);
        rotateOut.setFromAngle(1.7);
        rotateOut.setToAngle(0);

        adminInfoVBox.setOnMousePressed(event -> {
          rotateIn.play();
        });

        adminInfoVBox.setOnMouseReleased(event -> {
          rotateOut.play();
        });
    }

    @FXML
    private void proceedButClicked(MouseEvent event) {
        Color blueColor = Color.web("#182966");
        shadowVBoxBlue = new DropShadow();
        shadowVBoxBlue.setColor(blueColor);
        shadowVBoxBlue.setRadius(5);
        shadowVBoxBlue.setOffsetX(3);
        shadowVBoxBlue.setOffsetY(3);
        removeUsersVBox.setEffect(shadowVBoxBlue);
        rmUserVBoxOne.setVisible(false);
        rmUserVBoxTwo.setVisible(true);
        rmUserCheckBox.setSelected(false);
        userIdTextField.setText("");
    }

    @FXML
    private void backButtonClicked(MouseEvent event) {
        rmUserVBoxOne.setVisible(true);
        rmUserVBoxTwo.setVisible(false);
        removeUsersVBox.setEffect(shadowVBox);
    }

    @FXML
    private void removeButClicked(MouseEvent event) {
        String id = userIdTextField.getText();
        String name="";
        if(!rmUserCheckBox.isSelected()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Please mark the check box after reading");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                return;
            }
        }
        if(id.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty!");
            alert.setHeaderText("Please enter a User ID first");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                 return;
            }
        }
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select name from adminlogin where userid = '"+id+"';");
            rs.next();
            name = rs.getString("name");
        }catch(SQLException ex){
            System.out.println(ex);
        }
        if(name.equals(adminName)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("You cannot remove your own account");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                 return;
            }
        }
        if(name.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("No users with entered ID");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                 return;
            }
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("Are you sure want to remove '"+name+"'");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try{
                Conn conn = new Conn();
                conn.s.executeUpdate("delete from adminlogin where userid='"+id+"';");
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Success!");
                alert2.setHeaderText("User successfuly removed");
                Optional<ButtonType> result2 = alert2.showAndWait();
                if (result2.isPresent() && result2.get() == ButtonType.OK) {
                    rmUserVBoxOne.setVisible(true);
                    rmUserVBoxTwo.setVisible(false);
                    removeUsersVBox.setEffect(shadowVBox);
                }
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }else return;
        
    }
}
