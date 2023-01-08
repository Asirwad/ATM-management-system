
package bank.management.dash;

import java.sql.*;
import bank.management.atm.AdminLogin;
import bank.management.atm.Conn;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asirw
 */
public class ViewAllTransactionsController implements Initializable {
    boolean popupShowing=false;
    String adminName;
    @FXML
    private AnchorPane navBarAnchor;
    @FXML
    private FontAwesomeIconView menuIcon,homeIcon,listIcon,imageIcon,settingsIcon;
    @FXML
    private Circle avatarCircle;
    @FXML
    private Label dateLabel;
    @FXML
    private TableView<TransactionModel> transacTableView;
    @FXML
    private TableColumn<TransactionModel, String> cardNumber;
    @FXML
    private TableColumn<TransactionModel, String> date;
    @FXML
    private TableColumn<TransactionModel, String> type;
    @FXML
    private TableColumn<TransactionModel, Double> amount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transacTableFetcher();
        avatarImageFetcher();
        dateSetter();
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
            Label label = new Label("    @"+adminName);
            label.setStyle("-fx-font-size: 14pt; -fx-text-fill: 4C4C4E; -fx-font-weight: bold;");
            
            Button logoutButton = new Button("Logout");
            logoutButton.setId("logoutButton");
            logoutButton.getStyleClass().add("logoutButton");
            logoutButton.setOnMouseClicked((MouseEvent event2) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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

    private void transacTableFetcher() {
        ObservableList<TransactionModel> data = FXCollections.observableArrayList();
        transacTableView.setItems(data);
       
        cardNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        Label cardNumLabel = new Label("Card Number");
        cardNumLabel.setStyle("-fx-background-color: #2B49B3; -fx-text-fill: white;");
        cardNumLabel.setMaxWidth(Double.MAX_VALUE);
        cardNumLabel.setMaxHeight(Double.MAX_VALUE);
        cardNumber.setGraphic(cardNumLabel);
        
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Label dateCellLabel = new Label("Date");
        dateCellLabel.setStyle("-fx-background-color: #2B49B3; -fx-text-fill: white;");
        dateCellLabel.setMaxWidth(Double.MAX_VALUE);
        dateCellLabel.setMaxHeight(Double.MAX_VALUE);
        date.setGraphic(dateCellLabel);
        
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Label typeLabel = new Label("Type");
        typeLabel.setStyle("-fx-background-color: #2B49B3; -fx-text-fill: white;");
        typeLabel.setMaxWidth(Double.MAX_VALUE);
        typeLabel.setMaxHeight(Double.MAX_VALUE);
        type.setGraphic(typeLabel);
        
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        Label amountLabel = new Label("Amount");
        amountLabel.setStyle("-fx-background-color: #2B49B3; -fx-text-fill: white;");
        amountLabel.setMaxWidth(Double.MAX_VALUE);
        amountLabel.setMaxHeight(Double.MAX_VALUE);
        amount.setGraphic(amountLabel);
        
        try{
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from bank;");
            while(rs.next()){
                data.add(new TransactionModel(rs.getString("cardno"),rs.getString("date"),rs.getString("type"),rs.getInt("amount")));
            }
        }catch(SQLException ex){
            System.out.println(ex);
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
}
