package pex3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PEX3LauncherFXMLDocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button launchButton;
    
    @FXML
    private AnchorPane splashPane;

    @FXML
    private Label label;

    @FXML
    void startGame(ActionEvent event) {
        Parent root = null;
        FXMLLoader root1;
        root1 = new FXMLLoader(getClass().getResource("MainGameFXML.fxml"));
        try {
            root = (Parent)root1.load();
            
        } catch (IOException ex) {
        }
        MainGameFXMLController controller = root1.<MainGameFXMLController>getController();
        
        Scene sc = new Scene(root);
        Stage st = new Stage();
        st.setScene(sc);
        st.show();
        controller.startUp();
        splashPane.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {
        assert splashPane != null : "fx:id=\"splashPane\" was not injected: check your FXML file 'PEX3LauncherFXMLDocument.fxml'.";
        assert launchButton != null : "fx:id=\"launchButton\" was not injected: check your FXML file 'PEX3LauncherFXMLDocument.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'PEX3LauncherFXMLDocument.fxml'.";

    }
}
