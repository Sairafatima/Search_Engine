package searchengine;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class search_page_handler implements Initializable{
    public static String text;
    @FXML
    public TextField searchBar;
    @FXML
    public Button searchButton;
    
     @FXML
    private void searchClicked(ActionEvent e) throws IOException{
        System.out.println("Search button clicked");
        Parent loda=FXMLLoader.load(getClass().getClassLoader().getResource("resultPage.fxml"));
        Scene resultScene=new Scene(loda,640,373);
        SearchEngine.mainStage.setScene(resultScene);
        result_page_handler rph=new result_page_handler();
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
   
    
    
}
