package bank.management.dash;

import bank.management.dash.DataModels.AccountsModel;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ListAccountsController implements Initializable {
    String adminName;
    boolean popupShowing;
    @FXML
    private AnchorPane navBarAnchor;
    @FXML
    private FontAwesomeIconView menuIcon,homeIcon,listIcon,imageIcon,settingsIcon;
    @FXML
    private Circle avatarCircle;
    @FXML
    private Label dateLabel;
    @FXML
    private TableColumn<AccountsModel, String> cardNumber;
    @FXML
    private TableColumn<AccountsModel, String> accountType;
    @FXML
    private TableColumn<AccountsModel, String> fName;
    @FXML
    private TableColumn<AccountsModel, String> lName;
    @FXML
    private TableColumn<AccountsModel, Double> balance;
    @FXML
    private TableView<AccountsModel> accountsTableView;
    @FXML
    private FontAwesomeIconView plusBut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //homeIcon.setGlyphStyle("-fx-fill: #C1C1C1;");
        //listIcon.setGlyphStyle("-fx-fill: #2B49B3;");
        accountsTableFetcher();
        dateSetter();
        avatarImageFetcher();
        nameFetcher();
    }    
    
    @FXML
    private void HomeIconClicked(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
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
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    private void accountsTableFetcher() {
        ObservableList<AccountsModel> data = FXCollections.observableArrayList();
        accountsTableView.setItems(data);
        accountsTableView.setStyle("-fx-border-color: transparent;");
       
        cardNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        Label cardNumLabel = new Label("Card Number");
        cardNumLabel.setStyle("-fx-background-color: linear-gradient(to right,#1A3384,#2B49B3); -fx-text-fill: white;");
        cardNumLabel.setMaxWidth(Double.MAX_VALUE);
        cardNumLabel.setMaxHeight(Double.MAX_VALUE);
        cardNumber.setGraphic(cardNumLabel);
        
        accountType.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        Label accountTypeLabel = new Label("Account type");
        accountTypeLabel.setStyle("-fx-background-color: linear-gradient(to right,#1A3384,#2B49B3); -fx-text-fill: white;");
        accountTypeLabel.setMaxWidth(Double.MAX_VALUE);
        accountTypeLabel.setMaxHeight(Double.MAX_VALUE);
        accountType.setGraphic(accountTypeLabel);
        
        fName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        Label fNameLabel = new Label("Name");
        fNameLabel.setStyle("-fx-background-color: linear-gradient(to right,#1A3384,#2B49B3); -fx-text-fill: white;");
        fNameLabel.setMaxWidth(Double.MAX_VALUE);
        fNameLabel.setMaxHeight(Double.MAX_VALUE);
        fName.setGraphic(fNameLabel);
        
        lName.setCellValueFactory(new PropertyValueFactory<>("lName"));
        Label lNameLabel = new Label("Father's Name");
        lNameLabel.setStyle("-fx-background-color: linear-gradient(to right,#1A3384,#2B49B3); -fx-text-fill: white;");
        lNameLabel.setMaxWidth(Double.MAX_VALUE);
        lNameLabel.setMaxHeight(Double.MAX_VALUE);
        lName.setGraphic(lNameLabel);
        
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        Label balanceLabel = new Label("Balance");
        balanceLabel.setStyle("-fx-background-color: linear-gradient(to right,#1A3384,#2B49B3); -fx-text-fill: white;");
        balanceLabel.setMaxWidth(Double.MAX_VALUE);
        balanceLabel.setMaxHeight(Double.MAX_VALUE);
        balance.setGraphic(balanceLabel);
        
        try{
            Conn conn = new Conn();
            String query = "select signupthree.cardNumber,signupthree.accountType,signup.name,signup.father_name,account.balance from signup join account join signupthree where signupthree.formno=signup.formno AND signupthree.cardNumber=account.cardno;";
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next()){
                data.add(new AccountsModel(rs.getString("cardNumber"),rs.getString("accountType"),rs.getString("name"),rs.getString("father_name"),rs.getInt("balance")));
            }
        }catch(SQLException ex){
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
}
